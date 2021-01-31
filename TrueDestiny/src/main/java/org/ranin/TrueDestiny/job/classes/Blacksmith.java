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

public class Blacksmith extends Job {

    public Blacksmith() {
        super();
        Material[] temporary = { Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.NETHERITE_HOE };
        allowedTools = super.createEnum(temporary);
        allowedTools.addAll(woodTools);
        allowedCraftingItems = super.woodTools;
        allowedCraftingItems.addAll(super.allMaterials);
        allowedCraftingItems.addAll(stoneTools);
        allowedCraftingItems.addAll(ironTools);
        allowedCraftingItems.addAll(diamondTools);
        allowedCraftingItems.addAll(netheriteTools);
        allowedCraftingItems.addAll(allArmor);
        allowedCraftingItems.addAll(super.common);
        allowedCraftingItems.add(Material.ARROW);
        allowedCraftingItems.add(Material.BOW);
        allowedCraftingItems.add(Material.CROSSBOW);
        allowedCraftingItems.add(Material.BUCKET);
        allowedCraftingItems.add(Material.ANVIL);
        // all of this should be cached in a always on class
        allowedCraftingItems.removeIf(m -> m.name().contains("LEGACY"));
        noDropBlocks = super.farmingBlocks;
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
