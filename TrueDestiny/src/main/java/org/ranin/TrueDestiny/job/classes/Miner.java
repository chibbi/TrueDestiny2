package org.ranin.TrueDestiny.job.classes;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.ranin.TrueDestiny.job.Sql;

public class Miner implements Job {

    private FileConfiguration xpConfig;

    public Miner() {
        // TODO: add xpConfig
    }

    @Override
    public boolean checkEvent(Event event) {
        if (!xp(event)) {
            return false;
        }
        if (!tools(event)) {
            return false;
        }
        if (!items(event)) {
            return false;
        }
        if (event.getEventName() == "EntityDeathEvent") {
            if (!mobDrops((EntityDeathEvent) event)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean xp(Event evnt) {
        switch (evnt.getEventName()) {
            case "PlayerHarvestBlockEvent":
                PlayerHarvestBlockEvent harvest = (PlayerHarvestBlockEvent) evnt;
                harvest.setCancelled(true);
                return false;
            case "PlayerFishEvent":
                PlayerFishEvent fish = (PlayerFishEvent) evnt;
                // TODO: add query to subclasses (hobbies), so if a hobby says its okay to fish
                // (probably just "if(subclass == "fisher")")
                // => allow fishing
                fish.setCancelled(true);
                return false;
            case "EnchantItemEvent":
                EnchantItemEvent enchant = (EnchantItemEvent) evnt;
                enchant.setCancelled(true);
                return false;
            case "PrepareSmithingEvent":
                PrepareSmithingEvent smith = (PrepareSmithingEvent) evnt;
                smith.getViewers().get(0).sendMessage("You destroyed your items, trying to process them");
                smith.setResult(new ItemStack(Material.AIR));
                return false;
            case "BlockBreakEvent":
                BlockBreakEvent brek = (BlockBreakEvent) evnt;
                String[] info1 = new Sql("job").readfromJobTable(brek.getPlayer().getName());
                new Sql("job").AddXp(brek.getPlayer().getName(),
                        Integer.parseInt(info1[1]) + xpConfig.getInt("miner.break." + brek.getBlock().getType()));
                return true;
            case "BlockPlaceEvent":
                BlockPlaceEvent plac = (BlockPlaceEvent) evnt;
                String[] info2 = new Sql("job").readfromJobTable(plac.getPlayer().getName());
                new Sql("job").AddXp(plac.getPlayer().getName(),
                        Integer.parseInt(info2[1]) + xpConfig.getInt("miner.break." + plac.getBlock().getType()));
                return true;
        }
        return true;
    }

    @Override
    public boolean tools(Event event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean items(Event event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean mobDrops(EntityDeathEvent event) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void effects() {
        // TODO: add effects, according to the amount of Xp someone has =>
        // ex. miner:
        // 3000xp => FAST_DIGGING 1 ,
        // 10000xp => FAST_DIGGING 1, LUCK 1
        // 23000xp => FAST_DIGGING 2, LUCK 1
        // 37000xp => FAST_DIGGING 2, LUCK 2
        // 50000xp => FAST_DIGGING 3, LUCK 2
        // 70000xp => FAST_DIGGING 4, LUCK 2
        // 90000xp => FAST_DIGGING 4, LUCK 3
        // 100000xp => FAST_DIGGING 4, LUCK 3 , and a cake
    }

}
