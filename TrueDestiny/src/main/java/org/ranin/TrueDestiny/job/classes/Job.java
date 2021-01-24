package org.ranin.TrueDestiny.job.classes;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

/*
author: "chibbi"
*/

public interface Job {

    public boolean checkEvent(Event event);

    public boolean xp(Event event);

    public boolean tools(Event event);

    public boolean items(Event event);

    public boolean mobDrops(EntityDeathEvent event);

    public void effects();
}
