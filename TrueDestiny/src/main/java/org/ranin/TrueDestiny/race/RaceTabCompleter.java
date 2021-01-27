package org.ranin.TrueDestiny.race;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class RaceTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            l.add("set");
            l.add("all");
            l.add("mine");
            l.add("help");
            if (sender.isOp()) {
                l.add("put");
                l.add("listAll");
            }
        } else if (args.length == 2) {
            switch (args[0]) {
                case "set":
                    for (String job : new RaceCommand().allraces) {
                        l.add(job); // TODO: Make Configurable
                    }
                    break;
            }
            if (sender.isOp()) {
                switch (args[0]) {
                    case "put":
                        for (Player sing : Bukkit.getOnlinePlayers()) {
                            l.add(sing.getName());
                        }
                        break;
                }
            }
        } else if (args.length == 2) {
            if (sender.isOp()) {
                switch (args[0]) {
                    case "put":
                        for (String job : new RaceCommand().allraces) {
                            l.add(job); // TODO: Make Configurable
                        }
                        break;
                }
            }
        }
        return l;
    }

}
