package org.ranin.TrueDestiny.job.classes;

import java.lang.reflect.Array;
import java.util.EnumSet;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
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
@author "chibbi"
*/

abstract class Job implements Class {
    // #region FARMING
    protected final EnumSet<Material> farmingBlocks;
    // #endregion

    // #region ORES
    protected final EnumSet<Material> oreBlocks;
    // #endregion

    // #region TOOLS
    protected final EnumSet<Material> woodTools;
    protected final EnumSet<Material> stoneTools;
    protected final EnumSet<Material> ironTools;
    protected final EnumSet<Material> diamondTools;
    protected final EnumSet<Material> netheriteTools;
    protected final EnumSet<Material> allTools;
    // #endregion

    // #region ARMOR
    protected final EnumSet<Material> leatherArmor;
    protected final EnumSet<Material> chainArmor;
    protected final EnumSet<Material> ironArmor;
    protected final EnumSet<Material> diamondArmor;
    protected final EnumSet<Material> netheriteArmor;
    protected final EnumSet<Material> allArmor;
    // #endregion

    // #region MOBS
    protected final String[] fishMobs = { "COD", "DOLPHIN", "PUFFERFISH", "SALMON", "SQUID", "TROPICAL_FISH",
            "TURTLE" };
    protected final String[] friendlyMobs = { "BAT", "BEE", "CAT", "CHICKEN", "COW", "DONKEY", "FOX", "HORSE",
            "IRON_GOLEM", "LLAMA", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PIG", "POLAR_BEAR", "RABBIT",
            "SHEEP", "SNOWMAN", "STRIDER", "TRADER_LLAMA", "VILLAGER", "WOLF" };
    protected final String[] hostileMobs = { "BLAZE", "CAVE_SPIDER", "CREEPER", "DROWNED", "ELDER_GUARDIAN",
            "ENDER_DRAGON", "ENDERMAN", "ENDERMITE", "EVOKER", "EVOKER_FANGS", "GHAST", "GIANT", "GUARDIAN", "HOGLIN",
            "HUSK", "ILLUSIONER", "MAGMA_CUBE", "PHANTOM", "PIGLIN", "PIGLIN_BRUTE", "PILLAGER", "RAVAGER", "SHULKER",
            "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SPIDER", "STRAY", "VEX", "VINDICATOR", "WITCH",
            "WITHER", "WITHER_SKELETON", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN" };
    // #endregion

    protected EnumSet<Material> allowedTools;
    protected EnumSet<Material> allowedCraftingItems;
    protected EnumSet<Material> doubleDropBlocks; // buff for your class
    protected EnumSet<Material> noDropBlocks; // disallowed for your class => only rare drops
    protected String[] doubleDropMobs;
    protected String[] normalDropMobs;
    protected String[] noDropMobs;

    public Job() {
        Material[] temporary = { Material.POTATO, Material.WHEAT, Material.CARROTS, Material.BEETROOT, Material.PUMPKIN,
                Material.COCOA, Material.MELON, Material.SUGAR_CANE, Material.NETHER_WART, Material.FARMLAND };
        farmingBlocks = createEnum(temporary);
        // TODO: COPPER_ORE => for 1.17
        temporary = new Material[] { Material.COAL_ORE, Material.IRON_ORE, Material.LAPIS_ORE, Material.GOLD_ORE,
                Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE, Material.REDSTONE_ORE, Material.DIAMOND_ORE,
                Material.EMERALD_ORE };
        oreBlocks = createEnum(temporary);
        temporary = new Material[] { Material.WOODEN_SHOVEL, Material.WOODEN_AXE, Material.WOODEN_PICKAXE,
                Material.WOODEN_SWORD, Material.WOODEN_HOE };
        woodTools = createEnum(temporary);
        temporary = new Material[] { Material.STONE_SHOVEL, Material.STONE_AXE, Material.STONE_PICKAXE,
                Material.STONE_SWORD, Material.STONE_HOE };
        stoneTools = createEnum(temporary);
        temporary = new Material[] { Material.IRON_SHOVEL, Material.IRON_AXE, Material.IRON_PICKAXE,
                Material.IRON_SWORD, Material.IRON_HOE };
        ironTools = createEnum(temporary);
        temporary = new Material[] { Material.DIAMOND_SHOVEL, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE,
                Material.DIAMOND_SWORD, Material.DIAMOND_HOE };
        diamondTools = createEnum(temporary);
        temporary = new Material[] { Material.NETHERITE_SHOVEL, Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE,
                Material.NETHERITE_SWORD, Material.NETHERITE_HOE };
        netheriteTools = createEnum(temporary);
        allTools = EnumSet.copyOf(woodTools);
        allTools.addAll(stoneTools);
        allTools.addAll(ironTools);
        allTools.addAll(diamondTools);
        allTools.addAll(netheriteTools);
        temporary = new Material[] { Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS };
        leatherArmor = createEnum(temporary);
        temporary = new Material[] { Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE,
                Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS };
        chainArmor = createEnum(temporary);
        temporary = new Material[] { Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS,
                Material.IRON_BOOTS };
        ironArmor = createEnum(temporary);
        temporary = new Material[] { Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS,
                Material.DIAMOND_BOOTS };
        diamondArmor = createEnum(temporary);
        temporary = new Material[] { Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE,
                Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS };
        netheriteArmor = createEnum(temporary);
        allArmor = EnumSet.copyOf(leatherArmor);
        allArmor.addAll(chainArmor);
        allArmor.addAll(ironArmor);
        allArmor.addAll(diamondArmor);
        allArmor.addAll(netheriteArmor);
    }

    protected final <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    protected final EnumSet<Material> createEnum(Material[] materials) {
        EnumSet<Material> set = EnumSet.noneOf(Material.class);
        for (Material material : materials) {
            set.add(material);
        }
        return set;
    }

    public final boolean onCraftItemEvent(CraftItemEvent event) {
        if (allowedCraftingItems.contains(event.getRecipe().getResult().getType())) {
            onXpCraft(event);
            return true;
        }
        return onCraft(event);
    }

    public final boolean onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getPlayer() instanceof Player) {
            System.out.println(allowedTools.toString());
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                // TODO: either just completly move this into the single Classes, or do a
                // blacklist for this here (like previously)
                onXpBreaking(event);
                return true;
                // return onInteracting(event);
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (event.getMaterial() == Material.AIR) {
                    onXpBreaking(event);
                    System.out.println("IS AIR, TRUE");
                    return true;
                } else if (allowedTools.contains(event.getMaterial())) {
                    onXpBreaking(event);
                    System.out.println("IS SOME ALLOWED TOOL, TRUE");
                    return true;
                }
                System.out.println("IS SOME DISALLOWED TOOL, ...");
                return onBreaking(event);
            }

        }
        return true;

    }

    public final boolean onPlayerFishEvent(PlayerFishEvent event) {
        onXpFishing(event);
        return onFishing(event);
    }

    public final boolean onPlayerShearEntityEvent(PlayerShearEntityEvent event) {
        onXpShear(event);
        return onShear(event);
    }

    public final boolean onEnchantItemEvent(EnchantItemEvent event) {
        onXpEnchanting(event);
        return onEnchanting(event);
    }

    public final boolean onPrepareSmithingEvent(PrepareSmithingEvent event) {
        onXpSmithing(event);
        return onSmithing(event);
    }

    public final boolean onBlockDropItemEvent(BlockDropItemEvent event) {
        if (event.getBlockState().getBlock().getType().name() == "POTATO"
                || event.getBlockState().getBlock().getType().name() == "WHEAT"
                || event.getBlockState().getBlock().getType().name() == "CARROTS"
                || event.getBlockState().getBlock().getType().name() == "BEETROOT"
                || event.getBlockState().getBlock().getType().name() == "PUMPKIN"
                || event.getBlockState().getBlock().getType().name() == "COCOA"
                || event.getBlockState().getBlock().getType().name() == "MELON"
                || event.getBlockState().getBlock().getType().name() == "SUGAR_CANE"
                || event.getBlockState().getBlock().getType().name() == "NETHER_WART"
                || event.getBlockState().getBlock().getType().name() == "FARMLAND") {
            onXpHarvest(event);
            return onHarvest(event);
        }
        return true;
    }

    public final boolean onBlockBreakEvent(BlockBreakEvent event) {
        onXpBreakBlock(event);
        return onBreakBlock(event);
    }

    public final boolean onBlockPlaceEvent(BlockPlaceEvent event) {
        onXpPlaceBlock(event);
        return onPlaceBlock(event);
    }

    public final void onEntityDeathEvent(EntityDeathEvent event) {
        onXpMobKills(event);
        onMobKills(event);
    }

    public final void onPlayerDeathEvent(PlayerDeathEvent event) {
        onXpPlayerKill(event);
        onPlayerKill(event);
    }

    public final void onFurnaceExtractEvent(FurnaceExtractEvent event) {
        onXpFurnaceExtract(event);
    }

    public final boolean onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        // TODO: test if breaking whitelsit applies on here to, if so this event is not
        // needed
        // if not, put limit on damage of non fighters (by calling their functions which
        // aren't implemented yet)
        return true;
    }

    public final boolean onVehicleEnterEvent(VehicleEnterEvent event) {
        onXpVehicleEnter(event);
        return onVehicleEnter(event);
    }

    protected abstract boolean onCraft(CraftItemEvent event);

    protected abstract boolean onBreaking(PlayerInteractEvent event);

    protected abstract boolean onInteracting(PlayerInteractEvent event);

    protected abstract boolean onShear(PlayerShearEntityEvent event);

    protected abstract boolean onFishing(PlayerFishEvent event);

    protected abstract boolean onEnchanting(EnchantItemEvent event);

    protected abstract boolean onSmithing(PrepareSmithingEvent event);

    protected abstract boolean onHarvest(BlockDropItemEvent event);

    protected abstract boolean onBreakBlock(BlockBreakEvent event);

    protected abstract boolean onPlaceBlock(BlockPlaceEvent event);

    protected abstract boolean onVehicleEnter(VehicleEnterEvent event);

    protected abstract void onMobKills(EntityDeathEvent event);

    protected abstract void onPlayerKill(PlayerDeathEvent event);

    protected abstract void onXpCraft(CraftItemEvent event);

    protected abstract void onXpBreaking(PlayerInteractEvent event);

    protected abstract void onXpInteracting(PlayerInteractEvent event);

    protected abstract void onXpShear(PlayerShearEntityEvent event);

    protected abstract void onXpFishing(PlayerFishEvent event);

    protected abstract void onXpEnchanting(EnchantItemEvent event);

    protected abstract void onXpSmithing(PrepareSmithingEvent event);

    protected abstract void onXpHarvest(BlockDropItemEvent event);

    protected abstract void onXpBreakBlock(BlockBreakEvent event);

    protected abstract void onXpPlaceBlock(BlockPlaceEvent event);

    protected abstract void onXpMobKills(EntityDeathEvent event);

    protected abstract void onXpPlayerKill(PlayerDeathEvent event);

    protected abstract void onXpVehicleEnter(VehicleEnterEvent event);

    protected abstract void onXpFurnaceExtract(FurnaceExtractEvent event);

    protected abstract void effects(String playerName, int xp);

}
