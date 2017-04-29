package net.fenton.core.event.fentonevent;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Encast (2017-01-08 4:14 PM)
 */
public class PostLoginEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private Player player;

    public PostLoginEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
