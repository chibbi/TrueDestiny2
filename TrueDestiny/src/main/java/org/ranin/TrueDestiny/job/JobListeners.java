package org.ranin.TrueDestiny.job;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JobListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Sql("job").readfromJobTable(event.getPlayer().getName());
            if (info[0] == null) {
                event.getPlayer().sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            }
        }
    }
}
