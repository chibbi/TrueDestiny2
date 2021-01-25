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

public class Miner extends Job {

    public Miner() {
        super();
        Material[] temporary = { Material.STONE_SHOVEL, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
                Material.NETHERITE_PICKAXE };
        allowedTools = super.createEnum(temporary);
        allowedTools.addAll(woodTools);
        allowedCraftingItems = super.woodTools;
        doubleDropBlocks = super.farmingBlocks;
        noDropMobs = super.concatenate(super.concatenate(super.hostileMobs, super.friendlyMobs), super.friendlyMobs);
    }

    @Override
    public boolean onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        // TODO: Add Hobby Abfrage
        return true;
    }

    @Override
    public boolean onVehicleEnterEvent(VehicleEnterEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onCraft(CraftItemEvent event) {

        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onBreaking(PlayerInteractEvent event) {

        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onInteracting(PlayerInteractEvent event) {

        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onShear(PlayerShearEntityEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onFishing(PlayerFishEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onEnchanting(EnchantItemEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onSmithing(PrepareSmithingEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onHarvest(BlockDropItemEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onBreakBlock(BlockBreakEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected boolean onPlaceBlock(BlockPlaceEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage
        return false;
    }

    @Override
    protected void onMobKills(EntityDeathEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onPlayerKill(PlayerDeathEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpCraft(CraftItemEvent event) {

        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpBreaking(PlayerInteractEvent event) {

        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpInteracting(PlayerInteractEvent event) {

        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpShear(PlayerShearEntityEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpFishing(PlayerFishEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpEnchanting(EnchantItemEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpSmithing(PrepareSmithingEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpHarvest(BlockDropItemEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpBreakBlock(BlockBreakEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpPlaceBlock(BlockPlaceEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpMobKills(EntityDeathEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpPlayerKill(PlayerDeathEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void onXpFurnaceExtract(FurnaceExtractEvent event) {
        // IMPORTANT
        // TODO: Add Hobby Abfrage

    }

    @Override
    protected void effects(String playerName) {

        // TODO: Add Hobby Abfrage

    }

}
