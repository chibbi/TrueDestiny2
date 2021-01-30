package org.ranin.TrueDestiny.repetual;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RaceTasks {

    public void giveRaceEffects(Player player, String race) {
        for (PotionEffect eff : player.getActivePotionEffects()) {
            player.removePotionEffect(eff.getType());
        }
        switch (race) {
            case "nereid":
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, 1));
                break;
            case "elve":
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                break;
            case "dwarf":
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 1));
                break;
            case "NAME":
                // TODO: add another Effect (so its balanced)
                // player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,
                // Integer.MAX_VALUE, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
                break;
        }
    }

    // One time gifts
    public void choseRace(Player player, String race) {
        switch (race) {
            case "nereid":
                Dolphin d = (Dolphin) player.getWorld().spawnEntity(player.getLocation(), EntityType.DOLPHIN);
                d.setCustomName(player.getName());
                break;
            case "elve":
                Horse h = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                h.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)
                        .setBaseValue(h.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 2D);
                h.setTamed(true);
                h.setOwner(player);
                h.setAdult();
                h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                h.setCustomName(player.getName());
                break;
            case "dwarf":
                Cat c = (Cat) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAT);
                c.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
                        .setBaseValue(c.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2D);
                c.setAdult();
                c.setTamed(true);
                c.setOwner(player);
                c.setCustomName(player.getName() + " 1");
                c = (Cat) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAT);
                c.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
                        .setBaseValue(c.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2D);
                c.setAdult();
                c.setTamed(true);
                c.setOwner(player);
                c.setCustomName(player.getName() + " 2");
                break;
            case "NAME":
                Wolf w = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
                w.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
                        .setBaseValue(w.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2D);
                w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)
                        .setBaseValue(w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() * 2D);
                w.setAdult();
                w.setTamed(true);
                w.setOwner(player);
                w.setCustomName(player.getName() + " 1");
                w = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
                w.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
                        .setBaseValue(w.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2D);
                w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)
                        .setBaseValue(w.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() * 2D);
                w.setAdult();
                w.setTamed(true);
                w.setOwner(player);
                w.setCustomName(player.getName() + " 2");
                break;
        }
    }

}
