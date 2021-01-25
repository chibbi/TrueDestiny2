package org.ranin.TrueDestiny;

/*
author: "chibbi"
*/

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.ranin.TrueDestiny.job.JobListeners;

public class App extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        File dir = new File("plugins/geobasedWeather/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        this.getConf();
        // Initiating other Classes
        // TODO: job Command, job TabCompletion
        this.getCommand("warn").setExecutor(new WarnCommand(getLogger()));
        this.getCommand("warn").setTabCompleter(new WarnTabCompletion());
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
        this.getServer().getPluginManager().registerEvents(new JobListeners(), this);
        this.startScheduler();
        this.getLogger().info("Hello, SpigotMC!");

    }

    // should deactivate onDisable , but not nesseccary so hups? xD
    public void startScheduler() {
        Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                // TODO: check for xp amount (give effect)
                // TODO: check for knight nähe => give regenation
            }
        }, 10L, 10L);
        Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                // TODO: write messages like:
                // "Ey don't change your name, you will loose your job and xp"
            }
        }, 12000L, 10L); // 24000L => 20minutes (one mc day)
    }

    public FileConfiguration getConf() {
        File customConfigFile = new File("plugins/TrueDestiny/config.yml");
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        if (!customConfigFile.exists()) {
            cusconf.set("pvpmode", "false");
            cusconf.set("imperatormode", "true");
            try {
                cusconf.save(customConfigFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return cusconf;
    }

    @Override
    public void onDisable() {
        config = null;
        getLogger().info("See you again, SpigotMC!");
    }
}
