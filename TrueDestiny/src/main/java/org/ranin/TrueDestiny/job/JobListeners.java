package org.ranin.TrueDestiny.job;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.ranin.TrueDestiny.job.classes.Assassin;
import org.ranin.TrueDestiny.job.classes.Blacksmith;
import org.ranin.TrueDestiny.job.classes.Farmer;
import org.ranin.TrueDestiny.job.classes.Fisher;
import org.ranin.TrueDestiny.job.classes.Hunter;
import org.ranin.TrueDestiny.job.classes.Knight;
import org.ranin.TrueDestiny.job.classes.Lumberjack;
import org.ranin.TrueDestiny.job.classes.Mage;
import org.ranin.TrueDestiny.job.classes.Miner;
import org.ranin.TrueDestiny.repetual.JobTasks;

/*
author: "chibbi"
*/

public class JobListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            String[] info = new Sql("job").readfromTable(player.getName());
            if (info[0] == null) {
                player.sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            } else {
                new JobTasks().giveJobEffects(player, info);
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
            String[] info = new Sql("job").readfromTable(event.getEntity().getKiller().getName());
            if (info[0] == null) {
                event.getEntity().getKiller().sendMessage("Get a Job");
                return;
            }
            switch (info[0]) {
                case "miner":
                    new Miner().onPlayerDeathEvent(event);
                    break;
                case "farmer":
                    new Farmer().onPlayerDeathEvent(event);
                    break;
                case "mage":
                    new Mage().onPlayerDeathEvent(event);
                    break;
                case "knight":
                    new Knight().onPlayerDeathEvent(event);
                    break;
                case "hunter":
                    new Hunter().onPlayerDeathEvent(event);
                    break;
                case "lumberjack":
                    new Lumberjack().onPlayerDeathEvent(event);
                    break;
                case "fisher":
                    new Fisher().onPlayerDeathEvent(event);
                    break;
                case "blacksmith":
                    new Blacksmith().onPlayerDeathEvent(event);
                    break;
                case "assassin":
                    new Assassin().onPlayerDeathEvent(event);
                    break;
            }
        } else {
            // do smth else if player died out of nowhere?
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            String[] info = new Sql("job").readfromTable(event.getEntity().getKiller().getName());
            if (info[0] == null) {
                event.getEntity().getKiller().sendMessage("Get a Job");
                event.setDroppedExp(0);
                event.getDrops().clear();
                return;
            }
            switch (info[0]) {
                case "miner":
                    new Miner().onEntityDeathEvent(event);
                    break;
                case "farmer":
                    new Farmer().onEntityDeathEvent(event);
                    break;
                case "mage":
                    new Mage().onEntityDeathEvent(event);
                    break;
                case "knight":
                    new Knight().onEntityDeathEvent(event);
                    break;
                case "hunter":
                    new Hunter().onEntityDeathEvent(event);
                    break;
                case "lumberjack":
                    new Lumberjack().onEntityDeathEvent(event);
                    break;
                case "fisher":
                    new Fisher().onEntityDeathEvent(event);
                    break;
                case "blacksmith":
                    new Blacksmith().onEntityDeathEvent(event);
                    break;
                case "assassin":
                    new Assassin().onEntityDeathEvent(event);
                    break;
            }
        } else {
            if (event.getEntity() instanceof Player) {
                // so the stuff of an player isn't getting deleted
                event.setDroppedExp(0);
                return;
            }
            event.getDrops().clear();
        }
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            String[] info = new Sql("job").readfromTable(player.getName());
            if (info[0] == null) {
                player.sendMessage("Get a Job");
                event.setCancelled(true);
                return;
            }
            switch (info[0]) {
                case "miner":
                    if (!new Miner().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "farmer":
                    if (!new Farmer().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "mage":
                    if (!new Mage().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "knight":
                    if (!new Knight().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "hunter":
                    if (!new Hunter().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "lumberjack":
                    if (!new Lumberjack().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "fisher":
                    if (!new Fisher().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "blacksmith":
                    if (!new Blacksmith().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;
                case "assassin":
                    if (!new Assassin().onEntityShootBowEvent(event)) {
                        player.sendMessage("You're Class doesn't know how to use a Bow");
                        event.setCancelled(true);
                    }
                    break;

            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            String[] jobInfo = new Sql("job").readfromTable(player.getName());
            // if not used, not needed => String[] hobbyInfo = new
            // Sql("hobby").readfromTable(player.getName());
            // it jsut generates nullpointer exceptions
            if (jobInfo[0] == null) {
                event.getDamager().sendMessage("Get a Job");
                event.setCancelled(true);
                return;
            }
            switch (player.getInventory().getItemInMainHand().getType().name()) {
                case "WOODEN_SWORD":
                    if (jobInfo[0].equals("assassin") || jobInfo[0].equals("knight") || jobInfo[0].equals("hunter")) {
                        event.setDamage(event.getDamage() * 1.05D);
                        break;
                    }
                    player.sendMessage("You're Class doesn't know how to use such a Sword!");
                    event.setDamage(event.getDamage() * 0.8D); // TODO: make this configurable (for every sword)
                    // is already good configured i think but still make it configurable
                    break;
                case "GOLDEN_SWORD":
                    if (jobInfo[0].equals("assassin") || jobInfo[0].equals("knight") || jobInfo[0].equals("hunter")) {
                        event.setDamage(event.getDamage() * 1.1D);
                        break;
                    }
                    player.sendMessage("You're Class doesn't know how to use such a Sword!");
                    event.setDamage(event.getDamage() * 0.85D);
                    break;
                case "STONE_SWORD":
                    if (jobInfo[0].equals("assassin") || jobInfo[0].equals("knight") || jobInfo[0].equals("hunter")) {
                        event.setDamage(event.getDamage() * 1.15D);
                        break;
                    }
                    player.sendMessage("You're Class doesn't know how to use such a Sword!");
                    event.setDamage(event.getDamage() * 0.8D);
                    break;
                case "IRON_SWORD":
                    if (jobInfo[0].equals("assassin") || jobInfo[0].equals("knight") || jobInfo[0].equals("hunter")) {
                        event.setDamage(event.getDamage() * 1.2D);
                        break;
                    }
                    player.sendMessage("You're Class doesn't know how to use such a Sword!");
                    event.setDamage(event.getDamage() * 0.75D);
                    break;
                case "DIAMOND_SWORD":
                    if (jobInfo[0].equals("assassin") || jobInfo[0].equals("knight") || jobInfo[0].equals("hunter")) {
                        event.setDamage(event.getDamage() * 1.25D);
                        break;
                    }
                    player.sendMessage("You're Class doesn't know how to use such a Sword!");
                    event.setDamage(event.getDamage() * 0.7D);
                    break;
                case "NETHERITE_SWORD":
                    if (jobInfo[0].equals("assassin") || jobInfo[0].equals("knight")) {
                        event.setDamage(event.getDamage() * 1.3D);
                        break;
                    }
                    player.sendMessage("You're Class doesn't know how to use such a Sword!");
                    event.setDamage(event.getDamage() * 0.65D);
                    break;
                default:
                    event.setDamage(event.getDamage() / 2.5D);
                    break;
            }
            /*
             * If you want to use it again, just uncomment (also in Job.java) switch
             * (jobInfo[0]) { case "miner": if (!new
             * Miner().onEntityDamageByEntityEvent(event)) { event.setCancelled(true); }
             * break; case "farmer": if (!new Farmer().onEntityDamageByEntityEvent(event)) {
             * event.setCancelled(true); } break; case "mage": if (!new
             * Mage().onEntityDamageByEntityEvent(event)) { event.setCancelled(true); }
             * break; case "knight": if (!new Knight().onEntityDamageByEntityEvent(event)) {
             * event.setCancelled(true); } break; case "hunter": if (!new
             * Hunter().onEntityDamageByEntityEvent(event)) { event.setCancelled(true); }
             * break; case "lumberjack": if (!new
             * Lumberjack().onEntityDamageByEntityEvent(event)) { event.setCancelled(true);
             * } break; case "fisher": if (!new Fisher().onEntityDamageByEntityEvent(event))
             * { event.setCancelled(true); } break; case "blacksmith": if (!new
             * Blacksmith().onEntityDamageByEntityEvent(event)) { event.setCancelled(true);
             * } break; case "assassin": if (!new
             * Assassin().onEntityDamageByEntityEvent(event)) { event.setCancelled(true); }
             * break;
             * 
             * }
             */
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            String[] info = new Sql("job").readfromTable(event.getWhoClicked().getName());
            if (info[0] == null) {
                event.getWhoClicked().sendMessage("Get a Job");
                event.setCancelled(true);
                return;
            }
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
                case "mage":
                    if (!new Mage().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "knight":
                    if (!new Knight().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "hunter":
                    if (!new Hunter().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "lumberjack":
                    if (!new Lumberjack().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "fisher":
                    if (!new Fisher().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "blacksmith":
                    if (!new Blacksmith().onCraftItemEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "assassin":
                    if (!new Assassin().onCraftItemEvent(event)) {
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
        String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
        if (info[0] == null) {
            event.getPlayer().sendMessage("Get a Job");
            event.setCancelled(true);
            return;
        }
        switch (info[0]) {
            case "miner":
                if (!new Miner().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "farmer":
                if (!new Farmer().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "mage":
                if (!new Mage().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "knight":
                if (!new Knight().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "hunter":
                if (!new Hunter().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "lumberjack":
                if (!new Lumberjack().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "fisher":
                if (!new Fisher().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "blacksmith":
                if (!new Blacksmith().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "assassin":
                if (!new Assassin().onPlayerInteractEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onPlayerExtract(PlayerHarvestBlockEvent event) {
        String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
        if (info[0] == null) {
            event.getPlayer().sendMessage("Get a Job");
            event.setCancelled(true);
            return;
        }
        switch (info[0]) {
            case "miner":
                if (!new Miner().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "farmer":
                if (!new Farmer().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "mage":
                if (!new Mage().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "knight":
                if (!new Knight().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "hunter":
                if (!new Hunter().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "lumberjack":
                if (!new Lumberjack().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "fisher":
                if (!new Fisher().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "blacksmith":
                if (!new Blacksmith().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
            case "assassin":
                if (!new Assassin().onPlayerHarvestBlockEvent(event)) {
                    event.getItemsHarvested().clear();
                }
                break;
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
        if (info[0] == null) {
            event.getPlayer().sendMessage("Get a Job");
            event.setCancelled(true);
            return;
        }
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
            case "mage":
                if (!new Mage().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "knight":
                if (!new Knight().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "hunter":
                if (!new Hunter().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "lumberjack":
                if (!new Lumberjack().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "fisher":
                if (!new Fisher().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "blacksmith":
                if (!new Blacksmith().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "assassin":
                if (!new Assassin().onPlayerFishEvent(event)) {
                    event.setCancelled(true);
                }
                break;

        }
    }

    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent event) {
        String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
        if (info[0] == null) {
            event.getPlayer().sendMessage("Get a Job");
            event.setCancelled(true);
            return;
        }
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
            case "mage":
                if (!new Mage().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "knight":
                if (!new Knight().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "hunter":
                if (!new Hunter().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "lumberjack":
                if (!new Lumberjack().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "fisher":
                if (!new Fisher().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "blacksmith":
                if (!new Blacksmith().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "assassin":
                if (!new Assassin().onPlayerShearEntityEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        String[] info = new Sql("job").readfromTable(event.getViewers().get(0).getName());
        if (info[0] == null) {
            event.getViewers().get(0).sendMessage("Get a Job");
            event.setCancelled(true);
            return;
        }
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
            case "mage":
                if (!new Mage().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "knight":
                if (!new Knight().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "hunter":
                if (!new Hunter().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "lumberjack":
                if (!new Lumberjack().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "fisher":
                if (!new Fisher().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "blacksmith":
                if (!new Blacksmith().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
            case "assassin":
                if (!new Assassin().onEnchantItemEvent(event)) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        String[] info = new Sql("job").readfromTable(event.getViewers().get(0).getName());
        if (info[0] == null) {
            event.getViewers().get(0).sendMessage("Get a Job");
            return;
        }
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
            case "mage":
                if (!new Mage().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "knight":
                if (!new Knight().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "hunter":
                if (!new Hunter().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "lumberjack":
                if (!new Lumberjack().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "fisher":
                if (!new Fisher().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "blacksmith":
                if (!new Blacksmith().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
            case "assassin":
                if (!new Assassin().onPrepareSmithingEvent(event)) {
                    event.getViewers().get(0).sendMessage("AWW you lost your resources, ask a blacksmith next time");
                    event.setResult(new ItemStack(Material.AIR));
                }
                break;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
            if (info[0] == null) {
                event.getPlayer().sendMessage("§eGET YOURSELF A JOB");
                event.setCancelled(true);
                return;
            }
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
                case "mage":
                    if (!new Mage().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "knight":
                    if (!new Knight().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "hunter":
                    if (!new Hunter().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "lumberjack":
                    if (!new Lumberjack().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "fisher":
                    if (!new Fisher().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "blacksmith":
                    if (!new Blacksmith().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "assassin":
                    if (!new Assassin().onBlockBreakEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
            }
        } else {
            if (event.getBlock().getType().name() == "POTATO" || event.getBlock().getType().name() == "WHEAT"
                    || event.getBlock().getType().name() == "CARROTS" || event.getBlock().getType().name() == "BEETROOT"
                    || event.getBlock().getType().name() == "PUMPKIN" || event.getBlock().getType().name() == "COCOA"
                    || event.getBlock().getType().name() == "MELON" || event.getBlock().getType().name() == "SUGAR_CANE"
                    || event.getBlock().getType().name() == "CACTUS"
                    || event.getBlock().getType().name() == "POTTED_CACTUS"
                    || event.getBlock().getType().name() == "NETHER_WART") {
                event.setDropItems(false);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
            if (info[0] == null) {
                event.getPlayer().sendMessage("§eGET YOURSELF A JOB");
                event.setCancelled(true);
                return;
            }
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
                case "mage":
                    if (!new Mage().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "knight":
                    if (!new Knight().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "hunter":
                    if (!new Hunter().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "lumberjack":
                    if (!new Lumberjack().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "fisher":
                    if (!new Fisher().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "blacksmith":
                    if (!new Blacksmith().onBlockPlaceEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "assassin":
                    if (!new Assassin().onBlockPlaceEvent(event)) {
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
            String[] info = new Sql("job").readfromTable(event.getPlayer().getName());
            // no cancel part here, because of Hoppers
            switch (info[0]) {
                case "miner":
                    new Miner().onFurnaceExtractEvent(event);
                    break;
                case "farmer":
                    new Farmer().onFurnaceExtractEvent(event);
                    break;
                case "mage":
                    new Mage().onFurnaceExtractEvent(event);
                    break;
                case "knight":
                    new Knight().onFurnaceExtractEvent(event);
                    break;
                case "hunter":
                    new Hunter().onFurnaceExtractEvent(event);
                    break;
                case "lumberjack":
                    new Lumberjack().onFurnaceExtractEvent(event);
                    break;
                case "fisher":
                    new Fisher().onFurnaceExtractEvent(event);
                    break;
                case "blacksmith":
                    new Blacksmith().onFurnaceExtractEvent(event);
                    break;
                case "assassin":
                    new Assassin().onFurnaceExtractEvent(event);
                    break;
            }
        }

    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            String[] info = new Sql("job").readfromTable(event.getEntered().getName());
            // no cancel part here, because of Hoppers
            if (info[0] == null) {
                event.getEntered().sendMessage("Get a Job");
                event.setCancelled(true);
            }
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
                case "mage":
                    if (!new Mage().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "knight":
                    if (!new Knight().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "hunter":
                    if (!new Hunter().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "lumberjack":
                    if (!new Lumberjack().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "fisher":
                    if (!new Fisher().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "blacksmith":
                    if (!new Blacksmith().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
                case "assassin":
                    if (!new Assassin().onVehicleEnterEvent(event)) {
                        event.setCancelled(true);
                    }
                    break;
            }
        }
    }
}
