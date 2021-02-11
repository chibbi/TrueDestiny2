package org.ranin.TrueDestiny.finance;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class McXpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                // TODO: check if player is online/ exists
                if (Integer.valueOf(args[1]) >= 0 && (player.getLevel() - Integer.valueOf(args[1])) >= 0) {
                    System.out.println("Current XP lvl: " + player.getLevel());
                    System.out.println("Add XP lvl: " + args[1]);
                    System.out.println("new XP lvl: " + (player.getLevel() - Integer.valueOf(args[1])));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
                            "xp add " + player.getName() + " -" + args[1] + " levels");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
                            "xp add " + args[0] + " " + args[1] + " levels");
                    player.sendMessage("§6gave §7" + args[0] + "  §7" + args[1] + " §6xp");
                    Bukkit.getPlayer(args[0]).sendMessage("Got §7" + args[1] + " §6xp from §7" + player.getName());
                    return true;
                } else {
                    player.sendMessage("§6you don't have enough xp");
                    return true;
                }
            }
            player.sendMessage("§e ---------- §fTip: givexp §e---------- \n" + "§7/givexp PLAYER AMOUNT");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            for (Player sing : Bukkit.getOnlinePlayers()) {
                l.add(sing.getName());
            }
        } else if (args.length == 2) {
            l.add("100");
        }
        return l;
    }
}
