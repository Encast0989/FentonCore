package net.fenton.core.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * Created by Encast (2016-12-10 1:50 PM)
 *
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
       e.setJoinMessage("");
    }
}
