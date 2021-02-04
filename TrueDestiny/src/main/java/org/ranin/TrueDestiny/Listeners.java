package org.ranin.TrueDestiny;
/*
author: "chibbi"
*/

import java.io.File;
import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {

    public Listeners() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        File customConfigFile = new File("plugins/TrueDestiny/config.yml");
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        if (cusconf.getBoolean("imperatormode")) {
            event.getPlayer().sendTitle("all HAIL imperator CHIBBI!", "brought to you by Illuminati", 20, 45, 20);
        } else {
            event.getPlayer().sendTitle("You are awesome!", "", 20, 45, 20);
        }
        switch (new Random().nextInt(5 - 1 + 1) + 1) {
            case 1:
                event.setJoinMessage("§5" + event.getPlayer().getName() + " slided in dich rein");
                break;
            case 2:
                event.setJoinMessage("§2" + event.getPlayer().getName() + " has descended upon us");
                break;
            case 3:
                event.setJoinMessage("§6" + event.getPlayer().getName() + " wird dich jetzt auseinandernehmen");
                break;
            case 4:
                event.setJoinMessage("§bCronus has summoned " + event.getPlayer().getName() + "!");
                break;
            case 5:
                event.setJoinMessage("§bHello i am here and i am " + event.getPlayer().getName() + "");
                break;
        }
    }
}