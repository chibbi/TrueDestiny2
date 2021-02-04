package org.ranin.TrueDestiny.repetual;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.ranin.TrueDestiny.job.Sql;
import org.ranin.TrueDestiny.job.classes.Assassin;
import org.ranin.TrueDestiny.job.classes.Blacksmith;
import org.ranin.TrueDestiny.job.classes.Farmer;
import org.ranin.TrueDestiny.job.classes.Fisher;
import org.ranin.TrueDestiny.job.classes.Hunter;
import org.ranin.TrueDestiny.job.classes.Knight;
import org.ranin.TrueDestiny.job.classes.Lumberjack;
import org.ranin.TrueDestiny.job.classes.Mage;
import org.ranin.TrueDestiny.job.classes.Miner;

public class JobTasks {

    public void giveJobEffects(Player player, String[] info) {
        if (info[0] == null) {
            return;
        }
        switch (info[0]) {
            case "miner":
                new Miner().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[miner]" + player.getName());
                break;
            case "farmer":
                new Farmer().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[farmer]" + player.getName());
                break;
            case "mage":
                new Mage().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[mage]" + player.getName());
                break;
            case "knight":
                new Knight().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[knight]" + player.getName());
                break;
            case "hunter":
                new Hunter().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[hunter]" + player.getName());
                break;
            case "lumberjack":
                new Lumberjack().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[lumberjack]" + player.getName());
                break;
            case "fisher":
                new Fisher().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[fisher]" + player.getName());
                break;
            case "blacksmith":
                new Blacksmith().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[blacksmith]" + player.getName());
                break;
            case "assassin":
                new Assassin().effects(player.getName(), Integer.valueOf(info[1]));
                player.setPlayerListName("[assassin]" + player.getName());
                break;
        }
    }

    public void giveEveryoneJobEffect(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            String[] info = new Sql("job").readfromTable(singplayer.getName());
            giveJobEffects(singplayer, info);
        }
    }

    // TODO: Check if rewrite, cause its copy pasted from old one
    public void giveNearbyReg(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            String[] info = new Sql("job").readfromTable(singplayer.getName());
            if (info[0] != null && info[0].equals("knight")) {
                if (singplayer.getInventory().getItemInOffHand().getType().equals(Material.SWEET_BERRIES)) {
                    System.out.println("KNIGHT GIVES REGGING");
                    double maxDist = 10L; // TODO make configurable
                    for (Player other : allplayers) {
                        if (other != singplayer && other.getLocation().distance(singplayer.getLocation()) <= maxDist) {
                            String[] inf = new Sql("job").readfromTable(other.getName());
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
}
