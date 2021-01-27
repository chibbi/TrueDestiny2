package org.ranin.TrueDestiny.job;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class JobCommand implements CommandExecutor {

    final String[] alljobs = { "miner", "farmer", "blacksmith", "fisher", "mage", "lumberjack" }; // TODO:
    // Make
    // Configurable
    final String[] allhobbies = { "mage", "farmer", "fisher", "shepard", "assassin", "knight" }; // TODO: Make
                                                                                                 // Configurable

    public JobCommand() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String[] info = new Sql("job").readfromTable(player.getName());
            if (args.length == 1) {
                switch (args[0]) {
                    case "help":
                        player.sendMessage("§e ---------- §fHelp: job §e---------- \n" + "§6set job: §7/job set JOB\n"
                                + "§6list of all jobs: §7/job all\n" + "§6stats of your job: §7/job mine\n"
                                + "§6list of all available commands: §7/job help");
                        if (player.isOp()) {
                            player.sendMessage("§cSERVER OPERATOR STUFF:\n"
                                    + "§6list of jobs of online players: §7/job listAll\n"
                                    + "§6cheat XP to job: §7/job xp PLAYER XP\n"
                                    + "§6set your job new (regardless if you have one or not): §7/job put PLAYER JOB\n"
                                    + "§6de/activate pvpmode: §7/job config pvpmode BOOL");
                        }
                        return true;
                    case "all":
                        // TODO: Add new Classes
                        player.sendMessage("§6All Jobs:\n§7" + alljobs.toString());
                        return true;
                    case "mine":
                        player.sendMessage("§6Your job§7(if job is null, you didn't choose one yet)§6:\n" + "§6job §7"
                                + info[0] + " §6xp §7" + info[1]);
                        return true;
                    case "listAll":
                        if (player.isOp()) {
                            List<Player> allplayers = player.getWorld().getPlayers();
                            for (Player singplayer : allplayers) {
                                info = new Sql("job").readfromTable(singplayer.getName());
                                player.sendMessage("§6" + singplayer.getName() + "'s job:\n" + "§6job §7" + info[0]
                                        + " §6xp §7" + info[1]);
                            }
                        }
                        return true;
                }
            } else if (args.length == 2) {
                switch (args[0]) {
                    case "set":
                        if (info[0] != null) {
                            player.sendMessage("§6You already have a job you moron!");
                            return true;
                        }
                        for (String job : alljobs) { // TODO: Make Configurable
                            if (args[1].equals(job)) {
                                new Sql("job").addtoTable(player.getName(), args[1]);
                                player.sendMessage("§6Set Job to §7" + args[1]);
                                return true;
                            }
                        }
                        player.sendMessage("§6Coulnd't set Job to §7" + args[1]);
                        return false;
                }
            } else if (args.length == 3) {
                switch (args[0]) {
                    case "put":
                        if (!player.isOp()) {
                            player.sendMessage("§6Not Allowed!, put");
                            return false;
                        }
                        for (String job : alljobs) { // TODO: Make Configurable
                            if (args[2].equals(job)) {
                                if (info[0] == null) {
                                    new Sql("job").addtoTable(args[1], args[2]);
                                    player.sendMessage("§6Set Job of §7" + args[1] + "§6 to §7" + args[2]);
                                } else {
                                    new Sql("job").UpdateJobinJobTable(args[1], args[2]);
                                    player.sendMessage("§6Set Job of §7" + args[1] + "§6 to §7" + args[2]);
                                }
                                return true;
                            }
                        }
                        player.sendMessage("§6Coulnd't set Job of §7" + args[1] + "§6 to §7" + args[2]);
                        return false;
                    case "xp":
                        if (!player.isOp()) {
                            player.sendMessage("§6Not Allowed!, xp");
                            return false;
                        }
                        new Sql("job").SetXp(args[1], Integer.parseInt(args[2]));
                        player.sendMessage("§6Set xp of §7" + args[1] + "§6 to §7" + args[2]);
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
            player.sendMessage("§e ---------- §fTip: job §e---------- \n"
                    + "§6You have to first specify, if you want to declare youre main job, or your second job"
                    + "\nLike that: §7/job set JOB" + "\n§6You can get a list of all jobs with: §7/job list"
                    + "\n§6You can get stats of  your job with: §7/job mine"
                    + "\n§6You can get a list of all available commands with: §7/job help");
        }

        return false;
    }

}
