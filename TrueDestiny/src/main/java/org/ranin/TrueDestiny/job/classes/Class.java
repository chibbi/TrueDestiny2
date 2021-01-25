package org.ranin.TrueDestiny.job.classes;

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
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

/*
author: "chibbi"
*/

public interface Class {

    boolean onEntityDamageByEntityEvent(EntityDamageByEntityEvent event);

    boolean onCraftItemEvent(CraftItemEvent event);

    boolean onPlayerInteractEvent(PlayerInteractEvent event);

    boolean onPlayerFishEvent(PlayerFishEvent event);

    boolean onPlayerShearEntityEvent(PlayerShearEntityEvent event);

    boolean onEnchantItemEvent(EnchantItemEvent event);

    boolean onPrepareSmithingEvent(PrepareSmithingEvent event);

    boolean onBlockDropItemEvent(BlockDropItemEvent event);

    boolean onBlockBreakEvent(BlockBreakEvent event);

    boolean onBlockPlaceEvent(BlockPlaceEvent event);

    boolean onVehicleEnterEvent(VehicleEnterEvent event);

    void onEntityDeathEvent(EntityDeathEvent event);

    void onPlayerDeathEvent(PlayerDeathEvent event);

    void onFurnaceExtractEvent(FurnaceExtractEvent event);

}
