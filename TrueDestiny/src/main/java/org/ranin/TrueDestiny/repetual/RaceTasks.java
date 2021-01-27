package org.ranin.TrueDestiny.repetual;

import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RaceTasks {

    public void giveRaceEffects(Player player, String race) {
        switch (race) {
            case "aquaman":
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, 2));
                break;
            case "elfe":
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                break;
            case "dwarf":
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                break;
        }
    }

    public void choseRace(Player player, String race) {
        switch (race) {
            case "aquaman":
                Dolphin d = (Dolphin) player.getWorld().spawnEntity(player.getLocation(), EntityType.DOLPHIN);
                d.setCustomName(player.getName());
                break;
            case "elfe":
                // player.getInventory().addItem(new ItemStack(Material.SADDLE));
                Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                h.setArrowsInBody(100);
                h.setAdult();
                h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                h.setCustomName(player.getName());
                break;
            case "dwarf":
                Cat c = (Cat) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAT);
                c.setArrowsInBody(100);
                c.setAdult();
                c.setTamed(true);
                c.setOwner(player);
                c.setCustomName(player.getName());
                break;
        }
    }

}
