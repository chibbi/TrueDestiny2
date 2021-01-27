package org.ranin.TrueDestiny.job.classes;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
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

public class Assassin extends Job {

    public Assassin() {
        super();
        Material[] temporary = { Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.NETHERITE_HOE };
        allowedTools = super.createEnum(temporary);
        allowedTools.addAll(woodTools);
        allowedCraftingItems = super.woodTools;
        noDropMobs = super.concatenate(super.concatenate(super.hostileMobs, super.friendlyMobs), super.fishMobs);
    }

    @Override
    protected boolean onCraft(CraftItemEvent event) {
        // TODO: add Hobby stuff
        return false;
    }

    @Override
    protected boolean onBreaking(PlayerInteractEvent event) {
        return false;
    }

    @Override
    protected boolean onInteracting(PlayerInteractEvent event) {
        return true;
    }

    @Override
    protected boolean onShear(PlayerShearEntityEvent event) {
        return false;
    }

    @Override
    protected boolean onFishing(PlayerFishEvent event) {
        // fishing by default false => Fisher
        return false;
    }

    @Override
    protected boolean onEnchanting(EnchantItemEvent event) {
        // enchanting by default false => Mage
        return false;
    }

    @Override
    protected boolean onSmithing(PrepareSmithingEvent event) {
        // smithing by default false => Blacksmith
        return false;
    }

    @Override
    protected boolean onHarvestBreak(BlockBreakEvent event) {
        event.setDropItems(false);
        if (event.getBlock().getType().equals(Material.FARMLAND)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onVehicleEnter(VehicleEnterEvent event) {
        // vehicles by default true => TODO: Configure vehicles for everyone
        // aka. everyone can use boats, minecarts
        return true;
    }

    /*
     * XP FUNCTIONS --------- --------- --------- --------- --------- ---------
     * ------------ --------- --------- --------- --------- --------- ---------
     * ------------ --------- --------- --------- --------- --------- ---------
     */

    @Override
    protected void onXpCraft(CraftItemEvent event) {
    }

    @Override
    protected void onXpBreaking(PlayerInteractEvent event) {
    }

    @Override
    protected void onXpInteracting(PlayerInteractEvent event) {
    }

    @Override
    protected void onXpShear(PlayerShearEntityEvent event) {
    }

    @Override
    protected void onXpFishing(PlayerFishEvent event) {
    }

    @Override
    protected void onXpEnchanting(EnchantItemEvent event) {
    }

    @Override
    protected void onXpSmithing(PrepareSmithingEvent event) {
    }

    @Override
    protected void onXpHarvestBreak(BlockBreakEvent event) {
    }

    @Override
    protected void onXpBreakBlock(BlockBreakEvent event) {
    }

    @Override
    protected void onXpPlaceBlock(BlockPlaceEvent event) {
    }

    @Override
    protected void onXpMobKills(EntityDeathEvent event) {
    }

    @Override
    protected void onXpPlayerKill(PlayerDeathEvent event) {
        // TODO: GIVE XPPP
    }

    @Override
    protected void onXpVehicleEnter(VehicleEnterEvent event) {
    }

    @Override
    protected void onXpFurnaceExtract(FurnaceExtractEvent event) {
    }

    @Override
    public void effects(String playerName, int xp) {
    }

}
