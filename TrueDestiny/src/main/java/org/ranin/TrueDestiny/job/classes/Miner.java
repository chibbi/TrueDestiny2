package org.ranin.TrueDestiny.job.classes;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

/*
author: "chibbi"
*/

public class Miner extends Job {

    public Miner() {
        super();
        Material[] temporary = {};
        allowedTools = super.createEnum(temporary);
        temporary = new Material[] {};
        allowedCraftingItems = super.createEnum(temporary);
        temporary = new Material[] {};
        doubleDropBlocks = super.createEnum(temporary);
        temporary = new Material[] {};
        normalDropBlocks = super.createEnum(temporary);
        temporary = new Material[] {};
        noDropBlocks = super.farmingBlocks;
        noDropMobs = super.concatenate(super.concatenate(super.hostileMobs, super.friendlyMobs), super.friendlyMobs);
    }

    // LEFT CLICK
    @Override
    protected boolean onBreaking(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        return true;

    }

    // RIGHT CLICK
    @Override
    protected boolean onInteracting(PlayerInteractEvent event) {
        // TODO Auto-generated method stub
        return true;

    }

    @Override
    protected boolean onShear(PlayerShearEntityEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected boolean onFishing(PlayerFishEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onEnchanting(EnchantItemEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onSmithing(PrepareSmithingEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected boolean onHarvest(BlockDropItemEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected boolean onHarvestBreak(BlockBreakEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected boolean onBreakBlock(BlockBreakEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected boolean onPlaceBlock(BlockPlaceEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected void onMobKills(EntityDeathEvent event) {
        event.getDrops().clear(); // no drops
        event.setDroppedExp(0); // no xp output
    }

    @Override
    protected void onPlayerKill(PlayerDeathEvent event) {
        // does nothing because it can kill, and won't get xp
        // may add negative xp for kills????
    }

    @Override
    protected void effects(String name) {
        // TODO: Implement Effects which the Miner will get according to xp sheet

    }

}
