package org.ranin.TrueDestiny.race;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ranin.TrueDestiny.job.Sql;
import org.ranin.TrueDestiny.repetual.RaceTasks;

public class RaceCommand implements CommandExecutor {

    final String[] allraces = { "aquaman", "elve", "dwarf", "troll" }; // TODO: Make Configurable
    // TODO implement a hobbit (player only 1,5 blocks high, with sneaking only 1
    // block high)

    public RaceCommand() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String[] info = new Sql("race").readfromTable(player.getName());
            if (args.length == 1) {
                switch (args[0]) {
                    case "help":
                        player.sendMessage(
                                "§e ---------- §fHelp: race §e---------- \n" + "§6set race: §7/race set RACE\n"
                                        + "§6list of all races: §7/race all\n" + "§6stats of your race: §7/race mine\n"
                                        + "§6list of all available commands: §7/race help");
                        if (player.isOp()) {
                            player.sendMessage("§cSERVER OPERATOR STUFF:\n"
                                    + "§6list of races of online players: §7/race listAll\n"
                                    + "§6cheat XP to race: §7/race xp PLAYER XP\n"
                                    + "§6set your race new (regardless if you have one or not): §7/race put PLAYER RACE\n");
                        }
                        return true;
                    case "all":
                        // TODO: Add new Classes
                        player.sendMessage("§6All Races:\n§7" + allraces);
                        return true;
                    case "mine":
                        player.sendMessage("§6Your race§7(if race is null, you didn't choose one yet)§6:\n"
                                + "§6race §7" + info[0]);
                        return true;
                    case "listAll":
                        if (player.isOp()) {
                            List<Player> allplayers = player.getWorld().getPlayers();
                            // order it so you have lists of races
                            for (Player singplayer : allplayers) {
                                info = new Sql("race").readfromTable(singplayer.getName());
                                player.sendMessage("§6" + singplayer.getName() + "'s race:\n" + "§6race §7" + info[0]);
                            }
                        }
                        return true;
                }
            } else if (args.length == 2) {
                switch (args[0]) {
                    case "set":
                        if (info[0] != null) {
                            player.sendMessage("§6You already have a race you moron!");
                            return true;
                        }
                        for (String race : allraces) { // TODO: Make Configurable
                            if (args[1].equals(race)) {
                                new Sql("race").addtoTable(player.getName(), args[1]);
                                player.sendMessage("§6Set Race to §7" + args[1]);
                                new RaceTasks().giveRaceEffects(player, args[1]);
                                new RaceTasks().choseRace(player, args[1]);
                                return true;
                            }
                        }
                        player.sendMessage("§6Coulnd't set Race to §7" + args[1]);
                        return false;
                }
            } else if (args.length == 3) {
                switch (args[0]) {
                    case "put":
                        if (!player.isOp()) {
                            player.sendMessage("§6Not Allowed!, put");
                            return false;
                        }
                        for (String race : allraces) { // TODO: Make Configurable
                            if (args[2].equals(race)) {
                                if (info[0] == null) {
                                    new Sql("race").addtoTable(args[1], args[2]);
                                    player.sendMessage("§6Set Race of §7" + args[1] + "§6 to §7" + args[2]);
                                } else {
                                    new Sql("race").UpdateJobinJobTable(args[1], args[2]);
                                    player.sendMessage("§6Set Race of §7" + args[1] + "§6 to §7" + args[2]);
                                }
                                new RaceTasks().giveRaceEffects(player, args[2]);
                                return true;
                            }
                        }
                        player.sendMessage("§6Coulnd't set Race of §7" + args[1] + "§6 to §7" + args[2]);
                        return false;
                }
            }
            player.sendMessage("§e ---------- §fTip: race §e---------- \n"
                    + "§6You have to first specify, if you want to declare youre main race, or your second race"
                    + "\nLike that: §7/race set JOB" + "\n§6You can get a list of all races with: §7/race list"
                    + "\n§6You can get stats of  your race with: §7/race mine"
                    + "\n§6You can get a list of all available commands with: §7/race help");
        }

        return false;
    }

}