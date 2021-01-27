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
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.ranin.TrueDestiny.job.Sql;

/*
@author "chibbi"
*/

abstract class Job {
    // #region FARMING
    protected final EnumSet<Material> farmingBlocks;
    protected final EnumSet<Material> woodBlocks;
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

    protected final EnumSet<Material> allMaterials = EnumSet.allOf(Material.class);
    protected final EnumSet<Material> redstone;
    protected final EnumSet<Material> stoneBlocks;
    protected final EnumSet<Material> flowers;
    protected final EnumSet<Material> common;
    protected final EnumSet<Material> allBeds;

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
        Material[] temporary = { Material.POTATO, Material.WHEAT, Material.CARROT, Material.BEETROOT, Material.PUMPKIN,
                Material.COCOA, Material.MELON, Material.SUGAR_CANE, Material.BAMBOO, Material.NETHER_WART,
                Material.FARMLAND, Material.WHEAT_SEEDS, Material.CARROTS, Material.BEETROOTS, Material.BAMBOO_SAPLING,
                Material.BEETROOT_SEEDS, Material.PUMPKIN_SEEDS, Material.PUMPKIN_PIE, Material.COCOA_BEANS,
                Material.MELON_SEEDS, Material.SUGAR_CANE, Material.NETHER_WART_BLOCK, Material.PUMPKIN_STEM,
                Material.MELON_STEM, Material.MELON_SLICE, Material.MUSHROOM_STEM, Material.BEETROOT_SOUP,
                Material.MUSHROOM_STEW, Material.RABBIT_STEW, Material.SUSPICIOUS_STEW };
        farmingBlocks = createEnum(temporary);
        temporary = new Material[] { Material.OAK_SAPLING, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING,
                Material.JUNGLE_SAPLING, Material.ACACIA_SAPLING, Material.DARK_OAK_SAPLING, Material.OAK_WOOD,
                Material.SPRUCE_WOOD, Material.BIRCH_WOOD, Material.JUNGLE_WOOD, Material.ACACIA_WOOD,
                Material.DARK_OAK_WOOD, Material.CRIMSON_STEM, Material.WARPED_STEM, Material.OAK_LOG,
                Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG,
                Material.DARK_OAK_LOG, Material.OAK_PLANKS, Material.SPRUCE_PLANKS, Material.BIRCH_PLANKS,
                Material.JUNGLE_PLANKS, Material.DARK_OAK_PLANKS, Material.ACACIA_PLANKS, Material.CRIMSON_PLANKS,
                Material.WARPED_PLANKS, Material.OAK_BOAT, Material.SPRUCE_BOAT, Material.BIRCH_BOAT,
                Material.JUNGLE_BOAT, Material.ACACIA_BOAT, Material.DARK_OAK_BOAT, };
        woodBlocks = createEnum(temporary);
        // TODO: COPPER_ORE => for 1.17
        temporary = new Material[] { Material.COAL_ORE, Material.IRON_ORE, Material.LAPIS_ORE, Material.GOLD_ORE,
                Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE, Material.REDSTONE_ORE, Material.DIAMOND_ORE,
                Material.EMERALD_ORE, Material.ANCIENT_DEBRIS };
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
        temporary = new Material[] { Material.OAK_SLAB, Material.SPRUCE_SLAB, Material.BIRCH_SLAB, Material.JUNGLE_SLAB,
                Material.ACACIA_SLAB, Material.DARK_OAK_SLAB, Material.CRIMSON_SLAB, Material.WARPED_SLAB,
                Material.OAK_STAIRS, Material.BIRCH_STAIRS, Material.JUNGLE_STAIRS, Material.ACACIA_STAIRS,
                Material.DARK_OAK_STAIRS, Material.CRIMSON_STAIRS, Material.WARPED_STAIRS, Material.OAK_FENCE,
                Material.SPRUCE_FENCE, Material.BIRCH_FENCE, Material.JUNGLE_FENCE, Material.ACACIA_FENCE,
                Material.DARK_OAK_FENCE, Material.CRIMSON_FENCE, Material.WARPED_FENCE, Material.OAK_FENCE_GATE,
                Material.SPRUCE_FENCE_GATE, Material.BIRCH_FENCE_GATE, Material.JUNGLE_FENCE_GATE,
                Material.ACACIA_FENCE_GATE, Material.DARK_OAK_FENCE_GATE, Material.CRIMSON_FENCE_GATE,
                Material.WARPED_FENCE_GATE, Material.OAK_BUTTON, Material.SPRUCE_BUTTON, Material.BIRCH_BUTTON,
                Material.JUNGLE_BUTTON, Material.ACACIA_BUTTON, Material.DARK_OAK_BUTTON, Material.CRIMSON_BUTTON,
                Material.WARPED_BUTTON, Material.OAK_SIGN, Material.SPRUCE_SIGN, Material.BIRCH_SIGN,
                Material.JUNGLE_SIGN, Material.ACACIA_SIGN, Material.DARK_OAK_SIGN, Material.CRIMSON_SIGN,
                Material.WARPED_SIGN, Material.OAK_PRESSURE_PLATE, Material.SPRUCE_PRESSURE_PLATE,
                Material.BIRCH_PRESSURE_PLATE, Material.JUNGLE_PRESSURE_PLATE, Material.ACACIA_PRESSURE_PLATE,
                Material.DARK_OAK_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE,
                Material.OAK_TRAPDOOR, Material.SPRUCE_TRAPDOOR, Material.BIRCH_TRAPDOOR, Material.JUNGLE_TRAPDOOR,
                Material.ACACIA_TRAPDOOR, Material.DARK_OAK_TRAPDOOR, Material.CRIMSON_TRAPDOOR,
                Material.WARPED_TRAPDOOR, Material.OAK_WALL_SIGN, Material.SPRUCE_WALL_SIGN, Material.BIRCH_WALL_SIGN,
                Material.JUNGLE_WALL_SIGN, Material.ACACIA_WALL_SIGN, Material.DARK_OAK_WALL_SIGN,
                Material.CRIMSON_WALL_SIGN, Material.WARPED_WALL_SIGN, Material.MELON_SLICE, Material.CRAFTING_TABLE,
                Material.STICK };
        common = createEnum(temporary);
        temporary = new Material[] { Material.PISTON, Material.STICKY_PISTON, Material.REPEATER, Material.COMPARATOR,
                Material.REDSTONE, Material.REDSTONE_LAMP, Material.REDSTONE_BLOCK, Material.REDSTONE_TORCH };
        redstone = createEnum(temporary);
        common.addAll(redstone);
        temporary = new Material[] { Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_SLAB,
                Material.POLISHED_ANDESITE_STAIRS, Material.POLISHED_DIORITE, Material.POLISHED_DIORITE_SLAB,
                Material.POLISHED_DIORITE_STAIRS, Material.POLISHED_GRANITE, Material.POLISHED_GRANITE_SLAB,
                Material.POLISHED_GRANITE_STAIRS, Material.POLISHED_BASALT, Material.POLISHED_BLACKSTONE, // ab hier
                Material.POLISHED_BLACKSTONE_WALL, Material.POLISHED_BLACKSTONE_STAIRS, // nether
                Material.POLISHED_BLACKSTONE_SLAB, Material.POLISHED_BLACKSTONE_BUTTON,
                Material.POLISHED_BLACKSTONE_PRESSURE_PLATE, Material.POLISHED_BLACKSTONE_BRICK_SLAB,
                Material.POLISHED_BLACKSTONE_BRICK_STAIRS, Material.POLISHED_BLACKSTONE_BRICKS,
                Material.POLISHED_BLACKSTONE_BRICK_WALL };
        stoneBlocks = createEnum(temporary);
        common.addAll(stoneBlocks);
        flowers = allMaterials.range(Material.DANDELION, Material.WITHER_ROSE);
        common.addAll(flowers);
        temporary = new Material[] { Material.WHITE_BED, Material.YELLOW_BED, Material.RED_BED, Material.PURPLE_BED,
                Material.PINK_BED, Material.ORANGE_BED, Material.MAGENTA_BED, Material.LIME_BED,
                Material.LIGHT_GRAY_BED, Material.LIGHT_BLUE_BED, Material.GREEN_BED, Material.GRAY_BED,
                Material.CYAN_BED, Material.BROWN_BED, Material.BLUE_BED, Material.BLACK_BED };
        allBeds = createEnum(temporary);
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
        Player player = (Player) event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (event.getMaterial()) {
                case TRIDENT:
                    if (new Sql("race").readfromTable(player.getName())[0] == "aquaman") {
                        return true;
                    }
                    return false;
                case WOODEN_HOE:
                    if (new Sql("hobby").readfromTable(player.getName())[0] == "farmer"
                            || new Sql("job").readfromTable(player.getName())[0] == "farmer") {
                        return true;
                    }
                    return false;
                case STONE_HOE:
                    if (new Sql("hobby").readfromTable(player.getName())[0] == "farmer"
                            || new Sql("job").readfromTable(player.getName())[0] == "farmer") {
                        return true;
                    }
                    return false;
                case IRON_HOE:
                    if (new Sql("hobby").readfromTable(player.getName())[0] == "farmer"
                            || new Sql("job").readfromTable(player.getName())[0] == "farmer") {
                        return true;
                    }
                    return false;
                case DIAMOND_HOE:
                    if (new Sql("hobby").readfromTable(player.getName())[0] == "farmer"
                            || new Sql("job").readfromTable(player.getName())[0] == "farmer") {
                        return true;
                    }
                    return false;
                case NETHERITE_HOE:
                    if (new Sql("hobby").readfromTable(player.getName())[0] == "farmer"
                            || new Sql("job").readfromTable(player.getName())[0] == "farmer") {
                        return true;
                    }
                    return false;

            }
            if (farmingBlocks.contains(event.getMaterial())) {
                if (new Sql("hobby").readfromTable(player.getName())[0] == "farmer"
                        || new Sql("job").readfromTable(player.getName())[0] == "farmer") {
                    return true;
                }
            }
            return true;
            // return onInteracting(event);
        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getMaterial() == Material.AIR) {
                onXpBreaking(event);
                return true;
            } else if (allowedTools.contains(event.getMaterial())) {
                onXpBreaking(event);
                return true;
            }
            System.out.println("IS SOME DISALLOWED TOOL, ...");
            return onBreaking(event);
        }

        return true;

    }

    public boolean onEntityShootBowEvent(EntityShootBowEvent event) {
        if (new Sql("hobby").readfromTable(event.getEntity().getName())[0] == "hunter"
                || new Sql("job").readfromTable(event.getEntity().getName())[0] == "hunter") {
            return true;
        }
        return false;
    }

    public final boolean onPlayerFishEvent(PlayerFishEvent event) {
        onXpFishing(event);
        if (new Sql("hobby").readfromTable(event.getPlayer().getName())[0] == "fisher") {
            return true;
        }
        return onFishing(event);
    }

    public final boolean onPlayerShearEntityEvent(PlayerShearEntityEvent event) {
        onXpShear(event);
        if (new Sql("hobby").readfromTable(event.getPlayer().getName())[0] == "shepard") {
            return true;
        }
        return onShear(event);
    }

    public final boolean onEnchantItemEvent(EnchantItemEvent event) {
        onXpEnchanting(event);
        if (new Sql("hobby").readfromTable(event.getEnchanter().getName())[0] == "mage") {
            return true;
        }
        return onEnchanting(event);
    }

    public final boolean onPrepareSmithingEvent(PrepareSmithingEvent event) {
        onXpSmithing(event);
        return onSmithing(event);
    }

    @Deprecated
    public final boolean onBlockDropItemEvent(BlockDropItemEvent event) {
        if (event.getBlockState().getType().name() == "POTATO" || event.getBlockState().getType().name() == "WHEAT"
                || event.getBlockState().getType().name() == "CARROTS"
                || event.getBlockState().getType().name() == "BEETROOT"
                || event.getBlockState().getType().name() == "PUMPKIN"
                || event.getBlockState().getType().name() == "COCOA"
                || event.getBlockState().getType().name() == "MELON"
                || event.getBlockState().getType().name() == "SUGAR_CANE"
                || event.getBlockState().getType().name() == "CACTUS"
                || event.getBlockState().getType().name() == "POTTED_CACTUS"
                || event.getBlockState().getType().name() == "NETHER_WART"
                || event.getBlockState().getType().name() == "FARMLAND") {
        }
        return true;
    }

    public final boolean onBlockBreakEvent(BlockBreakEvent event) {
        if (doubleDropBlocks.contains(event.getBlock().getType())) {
            for (ItemStack item : event.getBlock().getDrops()) {
                event.getPlayer().getInventory().addItem(item);
            }
        }
        if (event.getBlock().getType().name() == "POTATO" || event.getBlock().getType().name() == "WHEAT"
                || event.getBlock().getType().name() == "CARROTS" || event.getBlock().getType().name() == "BEETROOT"
                || event.getBlock().getType().name() == "PUMPKIN" || event.getBlock().getType().name() == "COCOA"
                || event.getBlock().getType().name() == "MELON" || event.getBlock().getType().name() == "SUGAR_CANE"
                || event.getBlock().getType().name() == "CACTUS" || event.getBlock().getType().name() == "POTTED_CACTUS"
                || event.getBlock().getType().name() == "NETHER_WART"
                || event.getBlock().getType().name() == "FARMLAND") {
            onXpHarvestBreak(event);
            if (new Sql("hobby").readfromTable(event.getPlayer().getName())[0] == "farmer") {
                return true;
            }
            return onHarvestBreak(event);
        }
        onXpBreakBlock(event);
        return true;
    }

    public final boolean onBlockPlaceEvent(BlockPlaceEvent event) {
        onXpPlaceBlock(event);
        // may do some logic for only builder when he gets implemented
        return true;
    }

    public final void onEntityDeathEvent(EntityDeathEvent event) {
        if (doubleDropMobs == null) {
            return;
        }
        for (String mob : doubleDropMobs) {
            if (event.getEntityType().name().equals(mob)) {
                for (ItemStack item : event.getDrops()) {
                    event.getEntity().getKiller().getInventory().addItem(item);
                }
            }
        }
        for (String mob : noDropMobs) {
            if (event.getEntityType().name().equals(mob)) {
                event.getDrops().clear();
            }
        }
        onXpMobKills(event);
    }

    public final boolean onEntityDamageEvent(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        switch (player.getItemInHand().getType()) {
            case STONE_SWORD:
                if (new Sql("hobby").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("job").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("hobby").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "hunter") {
                    return true;
                }
                return false;
            case IRON_SWORD:
                if (new Sql("hobby").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("job").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("hobby").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "hunter") {
                    return true;
                }
                return false;
            case DIAMOND_SWORD:
                if (new Sql("hobby").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("job").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("hobby").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "hunter") {
                    return true;
                }
                return false;
            case NETHERITE_SWORD:
                if (new Sql("hobby").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("job").readfromTable(player.getName())[0] == "assassin"
                        || new Sql("hobby").readfromTable(player.getName())[0] == "knight"
                        || new Sql("job").readfromTable(player.getName())[0] == "knight") {
                    return true;
                }
                return false;
            case BOW:
                if (new Sql("job").readfromTable(player.getName())[0] == "hunter"
                        || new Sql("job").readfromTable(player.getName())[0] == "hunter") {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    public final void onPlayerDeathEvent(PlayerDeathEvent event) {
        onXpPlayerKill(event);
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

    protected abstract boolean onHarvestBreak(BlockBreakEvent event);

    protected abstract boolean onVehicleEnter(VehicleEnterEvent event);

    protected abstract void onXpCraft(CraftItemEvent event);

    protected abstract void onXpBreaking(PlayerInteractEvent event);

    protected abstract void onXpInteracting(PlayerInteractEvent event);

    protected abstract void onXpShear(PlayerShearEntityEvent event);

    protected abstract void onXpFishing(PlayerFishEvent event);

    protected abstract void onXpEnchanting(EnchantItemEvent event);

    protected abstract void onXpSmithing(PrepareSmithingEvent event);

    protected abstract void onXpHarvestBreak(BlockBreakEvent event);

    protected abstract void onXpBreakBlock(BlockBreakEvent event);

    protected abstract void onXpPlaceBlock(BlockPlaceEvent event);

    protected abstract void onXpMobKills(EntityDeathEvent event);

    protected abstract void onXpPlayerKill(PlayerDeathEvent event);

    protected abstract void onXpVehicleEnter(VehicleEnterEvent event);

    protected abstract void onXpFurnaceExtract(FurnaceExtractEvent event);

    protected abstract void effects(String playerName, int xp);

}
