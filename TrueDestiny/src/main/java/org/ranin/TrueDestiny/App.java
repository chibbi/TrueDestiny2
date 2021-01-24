package org.ranin.TrueDestiny;

/*
author: "chibbi"
*/

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.ranin.TrueDestiny.finance.FinanceTabCompletion;

public class App extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        File dir = new File("plugins/rolePlay/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        config = getConfig();
        config.addDefault("imperatormode", true);
        config.addDefault("pvpmode", false);
        config.options().copyDefaults(true);
        saveConfig();
        // Initiating other Classes
        // TODO: job Command, job TabCompletion
        this.getCommand("money").setTabCompleter(new FinanceTabCompletion());
        this.getCommand("warn").setExecutor(new WarnCommand(getLogger()));
        this.getCommand("warn").setTabCompleter(new WarnTabCompletion());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        startScheduler();
        getLogger().info("Hello, SpigotMC!");
        getLogger().info(Bukkit.getWorlds().toString());

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
                // TODO: check for xp amount (give effect)
            }
        }, 12000L, 10L); // 24000L => 20minutes (one mc day)
    }

    @Override
    public void onDisable() {
        config = null;
        getLogger().info("See you again, SpigotMC!");
    }
}
