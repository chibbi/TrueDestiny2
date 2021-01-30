package org.ranin.TrueDestiny.job;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class JobTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            l.add("set");
            l.add("mine");
            l.add("help");
            if (sender.isOp()) {
                l.add("xp");
                l.add("put");
                l.add("config");
                l.add("listAll");
            }
        } else if (args.length == 2) {
            switch (args[0]) {
                case "set":
                    for (String job : new JobCommand().alljobs) {
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
                    case "xp":
                        for (Player sing : Bukkit.getOnlinePlayers()) {
                            l.add(sing.getName());
                        }
                        break;
                    case "config":
                        File customConfigFile = new File("plugins/TrueDestiny/config.yml");
                        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
                        for (String key : cusconf.getKeys(true)) {
                            l.add(key);
                        }
                }
            }
        } else if (args.length == 2) {
            if (sender.isOp()) {
                switch (args[0]) {
                    case "put":
                        // TODO: this doesn't work, why?
                        for (String job : new JobCommand().alljobs) {
                            l.add(job); // TODO: Make Configurable
                        }
                        break;
                    case "xp":
                        l.add("100");
                        break;
                    case "config":
                        File customConfigFile = new File("plugins/geobasedWeather/config.yml");
                        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
                        for (String key : cusconf.getKeys(true)) {
                            l.add(key);
                        }
                }
            }
        }
        return l;
    }

}
