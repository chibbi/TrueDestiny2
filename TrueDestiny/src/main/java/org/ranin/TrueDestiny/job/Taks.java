package org.ranin.TrueDestiny.job;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
author: "chibbi"
*/

public class Taks {
    public void giveNearbyReg(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            String[] info = new Sql("job").readfromJobTable(singplayer.getName());
            if (info[0] != null && info[0].equals("knight")) {
                double maxDist = 10L; // TODO make configurable
                for (Player other : allplayers) {
                    if (other != singplayer && other.getLocation().distance(singplayer.getLocation()) <= maxDist) {
                        String[] inf = new Sql("job").readfromJobTable(other.getName());
                        if (inf[0] != null && inf[0] != "assassin") {
                            other.addPotionEffect(new PotionEffect(PotionEffectType.getByName("REGENERATION"), 65,
                                    Integer.parseInt(info[1])));
                        }
                    }
                }
            }
        }
    }
}
