package org.ranin.TrueDestiny.job.classes;

import org.bukkit.Material;
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

public class Farmer extends Job {

    public Farmer() {
        super();
        Material[] temporary = { Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.NETHERITE_HOE };
        allowedTools = super.createEnum(temporary);
        allowedTools.addAll(woodTools);
        allowedCraftingItems = super.woodTools;
        doubleDropBlocks = super.farmingBlocks;
        noDropMobs = super.concatenate(super.concatenate(super.hostileMobs, super.friendlyMobs), super.friendlyMobs);
    }

    @Override
    public boolean onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    public boolean onVehicleEnterEvent(VehicleEnterEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onCraft(CraftItemEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onBreaking(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onInteracting(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onShear(PlayerShearEntityEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onFishing(PlayerFishEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onEnchanting(EnchantItemEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onSmithing(PrepareSmithingEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onHarvest(BlockDropItemEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onBreakBlock(BlockBreakEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onPlaceBlock(BlockPlaceEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected void onMobKills(EntityDeathEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onPlayerKill(PlayerDeathEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpCraft(CraftItemEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpBreaking(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpInteracting(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpShear(PlayerShearEntityEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpFishing(PlayerFishEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpEnchanting(EnchantItemEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpSmithing(PrepareSmithingEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpHarvest(BlockDropItemEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpBreakBlock(BlockBreakEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpPlaceBlock(BlockPlaceEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpMobKills(EntityDeathEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpPlayerKill(PlayerDeathEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpFurnaceExtract(FurnaceExtractEvent event) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void effects(String playerName) {
        // TODO Auto-generated method stub
        // TODO: Add Hobby Abfrage

    }
}
