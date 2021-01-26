package org.ranin.TrueDestiny.job;

import java.io.File;

import org.bukkit.Material;
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
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.ranin.TrueDestiny.job.classes.Farmer;
import org.ranin.TrueDestiny.job.classes.Miner;
import org.ranin.TrueDestiny.repetual.RaceTasks;

/*
author: "chibbi"
*/

public class JobListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            String[] info = new Sql("job").readfromJobTable(player.getName());
            if (info[0] == null) {
                player.sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            } else {
                switch (info[0]) {
                    case "miner":
                        new Miner().effects(player.getName());
                        break;
                    case "farmer":
                        new Farmer().effects(player.getName());
                        break;
                }
            }
            info = new Sql("race").readfromJobTable(player.getName());
            if (info[0] == null) {
                player.sendMessage("§6EY JOOOOO \nPlease choose a RACE (§7/race help§6)");
            } else {
                new RaceTasks().giveRaceEffects(player, info[0]);
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
                    if (!new Miner().onEntityDamageByEntityEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onEntityDamageByEntityEvent(event)) {
                        event.setCancelled(true);
                    }
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
                    if (!new Miner().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
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
                if (!new Miner().onPlayerInteractEvent(event)) {
                    System.out.println("I AM CANCELING");
                    event.setCancelled(true);
                }
                break;
            case "farmer":
                if (!new Farmer().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
        switch (info[0]) {
            case "miner":
                if (!new Miner().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "farmer":
                if (!new Farmer().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
        switch (info[0]) {
            case "miner":
                if (!new Miner().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "farmer":
                if (!new Farmer().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getViewers().get(0).getName());
        switch (info[0]) {
            case "miner":
                if (!new Miner().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "farmer":
                if (!new Farmer().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        String[] info = new Sql("job").readfromJobTable(event.getViewers().get(0).getName());
        switch (info[0]) {
            case "miner":
                if (!new Miner().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "farmer":
                if (!new Farmer().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
        }
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            switch (info[0]) {
                case "miner":
                    if (!new Miner().onBlockDropItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onBlockDropItemEvent(event)) {
                        event.setCancelled(true);
                    }
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
                    if (!new Miner().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
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
                    if (!new Miner().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
            }
        } else {
            // who should place blocks thats not a player????
            // maybe lava and water call this?
            // and commands?
        }
    }

    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            // no cancel part here, because of Hoppers
            switch (info[0]) {
                case "miner":
                    new Miner().onFurnaceExtractEvent(event);
                    break;
                case "farmer":
                    new Farmer().onFurnaceExtractEvent(event);
                    break;
            }
        }

    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getEntered().getName());
            // no cancel part here, because of Hoppers
            switch (info[0]) {
                case "miner":
                    if (!new Miner().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
            }
        }
    }
}
