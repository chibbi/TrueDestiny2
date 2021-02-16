package org.ranin.TrueDestiny.job;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.ranin.TrueDestiny.App;

public class HobbyCommand implements CommandExecutor {

    final String[] allhobbies = { "farmer", "miner", "mage", "builder", "hunter" };
    // TODO: Make Configurable

    public HobbyCommand() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String[] info = new Sql("hobby").readfromTable(player.getName());
            if (args.length == 1) {
                switch (args[0]) {
                    case "help":
                        player.sendMessage("§e ---------- §fHelp: hobby §e---------- \n"
                                + "§6set hobby: §7/hobby set JOB\n" + "§6stats of your hobby: §7/hobby mine\n"
                                + "§6list of all available commands: §7/hobby help");
                        if (player.isOp()) {
                            player.sendMessage("§cSERVER OPERATOR STUFF:\n"
                                    + "§6list of jobs of online players: §7/hobby listAll\n"
                                    + "§6set your hobby new (regardless if you have one or not): §7/hobby put PLAYER JOB\n"
                                    + "§6de/activate pvpmode: §7/hobby config pvpmode BOOL");
                        }
                        return true;
                    case "mine":
                        player.sendMessage("§6Your hobby§7(if hobby is null, you didn't choose one yet)§6:\n"
                                + "§6hobby §7" + info[0]);
                        return true;
                    case "listAll":
                        if (player.isOp()) {
                            List<Player> allplayers = player.getWorld().getPlayers();
                            for (Player singplayer : allplayers) {
                                info = new Sql("hobby").readfromTable(singplayer.getName());
                                player.sendMessage("§6" + singplayer.getName() + "'s hobby:\n" + "§6job §7" + info[0]
                                        + " §6xp §7" + info[1]);
                            }
                        }
                        return true;
                }
            } else if (args.length == 2) {
                switch (args[0]) {
                    case "set":
                        int timeLeft = getCooldown(player.getUniqueId());
                        if (timeLeft != 0) {
                            player.sendMessage(
                                    "§6You can't just change your hobby all the time! time left: §7" + timeLeft);
                            return true;
                        }
                        for (String hobby : allhobbies) { // TODO: Make Configurable
                            if (args[1].equals(hobby)) {
                                if (info[0] == null) {
                                    new Sql("hobby").addtoTable(player.getName(), args[1]);
                                    player.sendMessage("§6Set Hobby to §7" + args[1]);
                                } else {
                                    new Sql("hobby").UpdateJobinJobTable(player.getName(), args[1]);
                                    player.sendMessage("§6Set Hobby to §7" + args[1]);
                                }
                                return true;
                            }
                        }
                        player.sendMessage("§6Coulnd't set Hobby to §7" + args[1]);
                        return false;
                }
            } else if (args.length == 3) {
                switch (args[0]) {
                    case "put":
                        if (!player.isOp()) {
                            player.sendMessage("§6Not Allowed!, put");
                            return false;
                        }
                        for (String hobby : allhobbies) { // TODO: Make Configurable
                            if (args[2].equals(hobby)) {
                                if (info[0] == null) {
                                    new Sql("hobby").addtoTable(args[1], args[2]);
                                    player.sendMessage("§6Set Hobby of §7" + args[1] + "§6 to §7" + args[2]);
                                } else {
                                    new Sql("hobby").UpdateJobinJobTable(args[1], args[2]);
                                    player.sendMessage("§6Set Hobby of §7" + args[1] + "§6 to §7" + args[2]);
                                }
                                // Start the countdown task
                                setCooldown(player.getUniqueId(), DEFAULT_COOLDOWN);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        int timeLeft = getCooldown(player.getUniqueId());
                                        setCooldown(player.getUniqueId(), --timeLeft);
                                        if (timeLeft == 0) {
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(App.getPlugin(App.class), 20, 20);
                                return true;
                            }
                        }
                        player.sendMessage("§6Coulnd't set Hobby of §7" + args[1] + "§6 to §7" + args[2]);
                        return false;
                    case "config":
                        if (!player.isOp()) {
                            player.sendMessage("§6Not Allowed!, config");
                            return false;
                        }
                        File customConfigFile = new File("plugins/TrueDestiny/config.yml");
                        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
                        cusconf.set(args[1], args[2]);
                        try {
                            cusconf.save(customConfigFile);
                            player.sendMessage("Successfully set §7" + args[1] + "§6 to §7" + args[2]);
                        } catch (IOException e) {
                            System.out.println("Couldn't save config file!!");
                            player.sendMessage("Couldn't save config file");
                        }
                        return true;
                }
            }
            player.sendMessage("§e ---------- §fTip: hobby §e---------- \n"
                    + "§6You have to first specify, if you want to declare youre main hobby, or your second hobby"
                    + "\nLike that: §7/hobby set JOB" + "\n§6You can get a list of all jobs with: §7/hobby list"
                    + "\n§6You can get stats of  your hobby with: §7/hobby mine"
                    + "\n§6You can get a list of all available commands with: §7/hobby help");
        }

        return false;
    }

    private final Map<UUID, Integer> cooldowns = new HashMap<>();

    public static final int DEFAULT_COOLDOWN = 86400;

    public void setCooldown(UUID player, int time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public int getCooldown(UUID player) {
        return cooldowns.getOrDefault(player, 0);
    }

}