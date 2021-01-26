package org.ranin.TrueDestiny;

/*
author: "chibbi"
*/

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.ranin.TrueDestiny.job.JobCommand;
import org.ranin.TrueDestiny.job.JobListeners;
import org.ranin.TrueDestiny.job.JobTabCompleter;
import org.ranin.TrueDestiny.job.Sql;

public class App extends JavaPlugin {

    @Override
    public void onEnable() {
        String comPath = "";
        try {
            // complete path to jar
            String[] completePath = App.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
                    .split("/");
            for (int i = 0; i < completePath.length - 1; i++) {
                comPath += completePath[i] + "/";
            }
        } catch (URISyntaxException e) {
            System.out.println("Getting Complete Path of Jar didn't work");
        }
        System.out.println("YOUR PATH: \033[31m" + comPath + "\033[39m");
        File dir = new File(comPath + "TrueDestiny/");
        if (!dir.exists()) {
            System.out.println("Created Config Folder");
            dir.mkdir();
        }
        dir = new File(comPath + "TrueDestiny/db/");
        if (!dir.exists()) {
            System.out.println("Created Database");
            dir.mkdir();
        }
        // this.getConf();
        new Sql("job").createJobTable();
        new Sql("race").createJobTable();
        // Initiating other Classes
        // TODO: job Command, job TabCompletion
        this.getCommand("job").setExecutor(new JobCommand());
        this.getCommand("job").setTabCompleter(new JobTabCompleter());
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
                // TODO: check for xp amount (give effect) (look into folder/ package
                // "repetual")
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
        getLogger().info("See you again, SpigotMC!");
    }
}
