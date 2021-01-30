package org.ranin.TrueDestiny.job.classes;

import java.lang.reflect.Array;
import java.util.EnumSet;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.Inventory;
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
    protected EnumSet<Material> doubleCraftingItems;// buff for your class
    protected EnumSet<Material> doubleDropBlocks; // buff for your class
    protected EnumSet<Material> noDropBlocks; // disallowed for your class => only rare drops
    protected String[] doubleDropMobs;
    protected String[] normalDropMobs;
    protected String[] noDropMobs = { "HI" };

    public Job() {
        Material[] temporary = { Material.POTATO, Material.WHEAT, Material.CARROT, Material.BEETROOT, Material.PUMPKIN,
                Material.COCOA, Material.MELON, Material.SUGAR_CANE, Material.BAMBOO, Material.NETHER_WART,
                Material.WHEAT_SEEDS, Material.CARROTS, Material.BEETROOTS, Material.BAMBOO_SAPLING,
                Material.BEETROOT_SEEDS, Material.PUMPKIN_SEEDS, Material.PUMPKIN_PIE, Material.COCOA_BEANS,
                Material.MELON_SEEDS, Material.SUGAR_CANE, Material.NETHER_WART_BLOCK, Material.PUMPKIN_STEM,
                Material.MELON_STEM, Material.MELON_SLICE, Material.MUSHROOM_STEM, Material.BEETROOT_SOUP,
                Material.MUSHROOM_STEW, Material.RABBIT_STEW, Material.SUSPICIOUS_STEW, Material.SWEET_BERRIES,
                Material.SWEET_BERRY_BUSH };
        farmingBlocks = createEnum(temporary);
        temporary = new Material[] { Material.OAK_SAPLING, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING,
                Material.JUNGLE_SAPLING, Material.ACACIA_SAPLING, Material.DARK_OAK_SAPLING, Material.OAK_WOOD,
                Material.SPRUCE_WOOD, Material.BIRCH_WOOD, Material.JUNGLE_WOOD, Material.ACACIA_WOOD,
                Material.DARK_OAK_WOOD, Material.CRIMSON_STEM, Material.WARPED_STEM, Material.OAK_LOG,
                Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG,
                Material.DARK_OAK_LOG, Material.OAK_PLANKS, Material.SPRUCE_PLANKS, Material.BIRCH_PLANKS,
                Material.JUNGLE_PLANKS, Material.DARK_OAK_PLANKS, Material.ACACIA_PLANKS, Material.CRIMSON_PLANKS,
                Material.WARPED_PLANKS, Material.ACACIA_DOOR, Material.OAK_DOOR, Material.SPRUCE_DOOR,
                Material.BIRCH_DOOR, Material.JUNGLE_DOOR, Material.DARK_OAK_DOOR, Material.CRIMSON_DOOR,
                Material.WARPED_DOOR, Material.OAK_TRAPDOOR, Material.SPRUCE_TRAPDOOR, Material.BIRCH_TRAPDOOR,
                Material.JUNGLE_TRAPDOOR, Material.ACACIA_TRAPDOOR, Material.DARK_OAK_TRAPDOOR,
                Material.CRIMSON_TRAPDOOR, Material.WARPED_TRAPDOOR, Material.ACACIA_BOAT, Material.OAK_BOAT,
                Material.SPRUCE_BOAT, Material.BIRCH_BOAT, Material.JUNGLE_BOAT, Material.DARK_OAK_BOAT,
                Material.CHEST };
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
        // TODO: automate some of that
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
                Material.OAK_WALL_SIGN, Material.MELON_SLICE, Material.CRAFTING_TABLE, Material.STICK,
                Material.COBBLESTONE_SLAB, Material.COBBLESTONE_STAIRS, Material.COBBLESTONE_WALL, Material.STONE_SLAB,
                Material.STONE_STAIRS, Material.STONE_BUTTON, Material.STONE_BRICKS, Material.STONE_BRICK_SLAB,
                Material.STONE_BRICK_STAIRS, Material.STONE_BRICK_WALL, Material.SANDSTONE, Material.SANDSTONE_SLAB,
                Material.SANDSTONE_STAIRS, Material.SANDSTONE_WALL, Material.RED_SANDSTONE, Material.RED_SANDSTONE_SLAB,
                Material.RED_SANDSTONE_STAIRS, Material.RED_SANDSTONE_WALL, Material.SMOOTH_RED_SANDSTONE,
                Material.SMOOTH_RED_SANDSTONE_SLAB, Material.SMOOTH_RED_SANDSTONE_STAIRS, Material.SMOOTH_SANDSTONE,
                Material.SMOOTH_SANDSTONE_SLAB, Material.SMOOTH_SANDSTONE_STAIRS, Material.SMOOTH_STONE,
                Material.SMOOTH_STONE_SLAB, Material.STONE_PRESSURE_PLATE, Material.CHISELED_STONE_BRICKS,
                Material.CRACKED_STONE_BRICKS, Material.DRIED_KELP_BLOCK, Material.COAL_BLOCK, Material.COAL,
                Material.GOLD_BLOCK, Material.GOLD_INGOT, Material.DIAMOND_BLOCK, Material.DIAMOND,
                Material.REDSTONE_BLOCK, Material.REDSTONE, Material.LAPIS_BLOCK, Material.LAPIS_LAZULI,
                Material.EMERALD_BLOCK, Material.EMERALD, Material.IRON_BLOCK, Material.IRON_INGOT,
                Material.IRON_NUGGET, Material.IRON_BARS, Material.GOLDEN_APPLE, Material.TERRACOTTA, Material.TORCH,
                Material.LANTERN };
        // TODO: GOLD_APPLE only for Mage
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
        flowers = allMaterials.range(Material.DANDELION, Material.WITHER_ROSE); // THAT WORKS
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
        ItemStack craftedItem = event.getInventory().getResult(); // Get result of recipe
        Inventory Inventory = event.getInventory(); // Get crafting inventory
        ClickType clickType = event.getClick();
        int realAmount = craftedItem.getAmount();
        if (clickType.isShiftClick()) {
            int lowerAmount = craftedItem.getMaxStackSize() + 1000; // Set lower at recipe result max stack size + 1000
                                                                    // (or just highter max stacksize of reciped item)
            for (ItemStack actualItem : Inventory.getContents()) // For each item in crafting inventory
            {
                if (!actualItem.getType().isAir() && lowerAmount > actualItem.getAmount()
                        && !actualItem.getType().equals(craftedItem.getType()))
                    // if slot is not air && lowerAmount is higher than this slot amount && it's not
                    // the recipe amount
                    lowerAmount = actualItem.getAmount(); // Set new lower amount
            }
            // Calculate the final amount : lowerAmount * craftedItem.getAmount
            realAmount = lowerAmount * craftedItem.getAmount();
        }
        if (doubleCraftingItems != null) {
            if (doubleCraftingItems.contains(event.getRecipe().getResult().getType())) {
                onXpCraft(event);
                ItemStack doublsstuff = new ItemStack(event.getRecipe().getResult().getType());
                doublsstuff.setAmount(realAmount);
                event.getWhoClicked().getInventory().addItem(doublsstuff);
                return true;
            }
        }
        if (allowedCraftingItems.contains(event.getRecipe().getResult().getType())) {
            onXpCraft(event);
            return true;
        }
        return onCraft(event);
    }

    public final boolean onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();
        String[] jobInfo = new Sql("job").readfromTable(player.getName());
        String[] hobbyInfo = new Sql("hobby").readfromTable(player.getName());
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (event.getMaterial().name()) {
                case "TRIDENT":
                    if (new Sql("race").readfromTable(player.getName())[0].equals("aquaman")) {
                        return true;
                    }
                    return false;
                case "WOODEN_HOE":
                    if (hobbyInfo[0].equals("farmer") || jobInfo[0].equals("farmer")) {
                        return true;
                    }
                    return false;
                case "STONE_HOE":
                    if (hobbyInfo[0].equals("farmer") || jobInfo[0].equals("farmer")) {
                        return true;
                    }
                    return false;
                case "IRON_HOE":
                    if (hobbyInfo[0].equals("farmer") || jobInfo[0].equals("farmer")) {
                        return true;
                    }
                    return false;
                case "DIAMOND_HOE":
                    if (hobbyInfo[0].equals("farmer") || jobInfo[0].equals("farmer")) {
                        return true;
                    }
                    return false;
                case "NETHERITE_HOE":
                    if (hobbyInfo[0].equals("farmer") || jobInfo[0].equals("farmer")) {
                        return true;
                    }
                    return false;

            }
            EnumSet<Material> temp = farmingBlocks;
            temp.remove(Material.PUMPKIN);
            temp.remove(Material.MELON);
            if (farmingBlocks.contains(event.getMaterial())) {
                if (jobInfo[0].equals("farmer")) {
                    return true;
                } else if (hobbyInfo[0] != null && hobbyInfo[0].equals("farmer")) {
                    return true;
                } else {
                    event.getPlayer().sendMessage("Can't do that");
                    return false;
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
            event.getPlayer().sendMessage("DISALLOWED TOOL, " + event.getMaterial());
            return onBreaking(event);
        }

        return true;

    }

    public boolean onEntityShootBowEvent(EntityShootBowEvent event) {
        String[] jobInfo = new Sql("job").readfromTable(event.getEntity().getName());
        String[] hobbyInfo = new Sql("hobby").readfromTable(event.getEntity().getName());
        if (hobbyInfo[0] == null) { // TODO: do null pointer test
            // everywhere xD || fall in love with a terminal full of Exception, and Error
            if (jobInfo[0].equals("hunter")) {
                return true;
            } else {
                return false;
            }
        }
        if (jobInfo[0].equals("hunter") || jobInfo[0].equals("hunter")) {
            return true;
        }
        return false;
    }

    public final boolean onPlayerFishEvent(PlayerFishEvent event) {
        onXpFishing(event);
        String[] hobbyInfo = new Sql("hobby").readfromTable(event.getPlayer().getName());
        if (hobbyInfo[0].equals("fisher")) {
            return true;
        }
        return onFishing(event);
    }

    public final boolean onPlayerShearEntityEvent(PlayerShearEntityEvent event) {
        onXpShear(event);
        String[] hobbyInfo = new Sql("hobby").readfromTable(event.getEntity().getName());
        if (hobbyInfo[0].equals("shepard")) {
            return true;
        }
        return onShear(event);
    }

    public final boolean onEnchantItemEvent(EnchantItemEvent event) {
        onXpEnchanting(event);
        String[] hobbyInfo = new Sql("hobby").readfromTable(event.getEnchanter().getName());
        if (hobbyInfo[0].equals("mage")) {
            return true;
        }
        return onEnchanting(event);
    }

    public final boolean onPrepareSmithingEvent(PrepareSmithingEvent event) {
        onXpSmithing(event);
        return onSmithing(event);
    }

    public final boolean onPlayerHarvestBlockEvent(PlayerHarvestBlockEvent event) {
        String[] hobbyInfo = new Sql("hobby").readfromTable(event.getPlayer().getName());
        Material brokenBlock = event.getHarvestedBlock().getType();
        if (doubleDropBlocks != null && doubleDropBlocks.contains(brokenBlock)) {
            for (ItemStack item : event.getHarvestedBlock().getDrops()) {
                event.getPlayer().getInventory().addItem(item);
                event.getPlayer().sendMessage("Doubling: " + item);
            }
        }
        if (noDropBlocks != null && noDropBlocks.contains(brokenBlock)) {
            if (farmingBlocks.contains(brokenBlock) && hobbyInfo[0].equals("farmer")) {
            } else {
                System.out.println("CONTAINS");
                return false;
            }
        }
        return true;
    }

    public final boolean onBlockBreakEvent(BlockBreakEvent event) {
        String[] hobbyInfo = new Sql("hobby").readfromTable(event.getPlayer().getName());
        Material brokenBlock = event.getBlock().getType();
        if (doubleDropBlocks != null && doubleDropBlocks.contains(brokenBlock)) {
            for (ItemStack item : event.getBlock().getDrops()) {
                event.getPlayer().getInventory().addItem(item);
                event.getPlayer().sendMessage("Doubling: " + item);
            }
        }
        if (noDropBlocks != null && noDropBlocks.contains(brokenBlock)) {
            if (!farmingBlocks.contains(brokenBlock) && !hobbyInfo[0].equals("farmer")) {
                event.getPlayer().sendMessage("Can't do that");
                event.setDropItems(false);
                return true;
            }
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
        System.out.println(event.getEntityType().name());
        System.out.println("doubles: " + doubleDropMobs);
        if (doubleDropMobs != null) {
            for (String mob : doubleDropMobs) {
                System.out.println(event.getEntityType().name() + " == " + mob);
                if (event.getEntityType().name().equals(mob)) {
                    for (ItemStack item : event.getDrops()) {
                        event.getEntity().getKiller().getInventory().addItem(item);
                    }
                }
            }
        }
        System.out.println("noos: " + noDropMobs);
        for (String mob : noDropMobs) {
            if (event.getEntityType().name().equals(mob)) {
                event.setDroppedExp(0);
                event.getDrops().clear();
            }
        }
        onXpMobKills(event);
    }

    /*
     * If you want to use it again, just uncomment (also in JobListeners.java)
     * public final boolean onEntityDamageEvent(EntityDamageByEntityEvent event) {
     * Player player = (Player) event.getDamager(); String[] jobInfo = new
     * Sql("job").readfromTable(player.getName()); String[] hobbyInfo = new
     * Sql("hobby").readfromTable(player.getName()); // TODO: live with a lot of
     * NullPointerExeptions switch
     * (player.getInventory().getItemInMainHand().getType()) { case STONE_SWORD: if
     * (hobbyInfo[0].equals("assassin") || jobInfo[0].equals("assassin") ||
     * hobbyInfo[0].equals("knight") || jobInfo[0].equals("knight") ||
     * jobInfo[0].equals("hunter")) { return true; } return false; case IRON_SWORD:
     * if (hobbyInfo[0].equals("assassin") || jobInfo[0].equals("assassin") ||
     * hobbyInfo[0].equals("knight") || jobInfo[0].equals("knight") ||
     * jobInfo[0].equals("hunter")) { return true; } return false; case
     * DIAMOND_SWORD: if (hobbyInfo[0].equals("assassin") ||
     * jobInfo[0].equals("assassin") || hobbyInfo[0].equals("knight") ||
     * jobInfo[0].equals("knight") || jobInfo[0].equals("hunter")) { return true; }
     * return false; case NETHERITE_SWORD: if (hobbyInfo[0].equals("assassin") ||
     * jobInfo[0].equals("assassin") || hobbyInfo[0].equals("knight") ||
     * jobInfo[0].equals("knight")) { return true; } return false; case BOW: if
     * (jobInfo[0].equals("hunter") || jobInfo[0].equals("hunter")) { return true; }
     * return false; default: return true; } }
     */

    public final void onPlayerDeathEvent(PlayerDeathEvent event) {
        onXpPlayerKill(event);
    }

    public final void onFurnaceExtractEvent(FurnaceExtractEvent event) {
        onXpFurnaceExtract(event);
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

    protected abstract boolean onVehicleEnter(VehicleEnterEvent event);

    protected abstract void onXpCraft(CraftItemEvent event);

    protected abstract void onXpBreaking(PlayerInteractEvent event);

    protected abstract void onXpInteracting(PlayerInteractEvent event);

    protected abstract void onXpShear(PlayerShearEntityEvent event);

    protected abstract void onXpFishing(PlayerFishEvent event);

    protected abstract void onXpEnchanting(EnchantItemEvent event);

    protected abstract void onXpSmithing(PrepareSmithingEvent event);

    protected abstract void onXpBreakBlock(BlockBreakEvent event);

    protected abstract void onXpPlaceBlock(BlockPlaceEvent event);

    protected abstract void onXpMobKills(EntityDeathEvent event);

    protected abstract void onXpPlayerKill(PlayerDeathEvent event);

    protected abstract void onXpVehicleEnter(VehicleEnterEvent event);

    protected abstract void onXpFurnaceExtract(FurnaceExtractEvent event);

    protected abstract void effects(String playerName, int xp);

}
