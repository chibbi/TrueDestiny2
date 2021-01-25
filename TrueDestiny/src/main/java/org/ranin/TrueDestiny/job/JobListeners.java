package org.ranin.TrueDestiny.job;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.ranin.TrueDestiny.job.classes.Farmer;
import org.ranin.TrueDestiny.job.classes.Miner;

/*
author: "chibbi"
*/

public class JobListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            if (info[0] == null) {
                event.getPlayer().sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer() instanceof Player) {
            File customConfigFile = new File("plugins/TrueDestiny/config.yml");
            FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
            if (!cusconf.getBoolean("pvpmode")) {
                new Sql("job").deletefromJobTable(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getEntity().getKiller().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onPlayerDeathEvent(event);
                    break;
                case "farmer":
                    new Farmer().onPlayerDeathEvent(event);
                    break;
            }
        } else {
            // do smth else if player died out of nowhere?
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getEntity().getKiller().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onEntityDeathEvent(event);
                    break;
                case "farmer":
                    new Farmer().onEntityDeathEvent(event);
                    break;
            }
        } else {
            event.setDroppedExp(0);
            event.getDrops().clear();
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getDamager().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onEntityDamageByEntityEvent(event);
                    break;
                case "farmer":
                    new Farmer().onEntityDamageByEntityEvent(event);
                    break;
            }
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getWhoClicked().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onCraftItemEvent(event);
                    break;
                case "farmer":
                    new Farmer().onCraftItemEvent(event);
                    break;
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
        switch (info[0]) {
            case "miner":
                new Miner().onPlayerInteractEvent(event);
                break;
            case "farmer":
                new Farmer().onPlayerInteractEvent(event);
                break;
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
        switch (info[0]) {
            case "miner":
                new Miner().onPlayerFishEvent(event);
                break;
            case "farmer":
                new Farmer().onPlayerFishEvent(event);
                break;
        }
    }

    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
        switch (info[0]) {
            case "miner":
                new Miner().onPlayerShearEntityEvent(event);
                break;
            case "farmer":
                new Farmer().onPlayerShearEntityEvent(event);
                break;
        }
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getViewers().get(0).getName());
        switch (info[0]) {
            case "miner":
                new Miner().onEnchantItemEvent(event);
                break;
            case "farmer":
                new Farmer().onEnchantItemEvent(event);
                break;
        }
    }

    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getViewers().get(0).getName());
        switch (info[0]) {
            case "miner":
                new Miner().onPrepareSmithingEvent(event);
                break;
            case "farmer":
                new Farmer().onPrepareSmithingEvent(event);
                break;
        }
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onBlockDropItemEvent(event);
                    break;
                case "farmer":
                    new Farmer().onBlockDropItemEvent(event);
                    break;
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onBlockBreakEvent(event);
                    break;
                case "farmer":
                    new Farmer().onBlockBreakEvent(event);
                    break;
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            switch (info[0]) {
                case "miner":
                    new Miner().onBlockPlaceEvent(event);
                    break;
                case "farmer":
                    new Farmer().onBlockPlaceEvent(event);
                    break;
            }
        } else {
            // who should place blocks thats not a player????
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            // TODO: probably stay here? (should be destroyed and put into the players inv,
            // chance to completle destroy)
            // if player not fisher or messenger or smth like that
        }
    }
}
