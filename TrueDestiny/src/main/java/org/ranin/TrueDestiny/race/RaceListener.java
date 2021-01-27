package org.ranin.TrueDestiny.race;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.ranin.TrueDestiny.job.Sql;
import org.ranin.TrueDestiny.repetual.RaceTasks;

public class RaceListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            String[] info = new Sql("race").readfromTable(player.getName());
            if (info[0] == null) {
                player.sendMessage("§6EY JOOOOO \nPlease choose a RACE (§7/race help§6)");
            } else {
                new RaceTasks().giveRaceEffects(player, info[0]);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            String[] info = new Sql("race").readfromTable(player.getName());
            if (info[0] == "elve") {
                event.setConsumeItem(false);
            }
        }
    }

}
