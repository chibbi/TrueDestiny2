package org.ranin.TrueDestiny.job.classes;

import org.bukkit.event.Event;

/*
author: "chibbi"
*/

public interface Job {

    public boolean checkEvent(Event event);

    public boolean xp(Event event);

    public boolean tools(Event event);

    public boolean items(Event event);

    public boolean mobDrops(Event event);

    public void effects(Event event);
}
