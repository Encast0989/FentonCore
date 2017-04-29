package net.fenton.core.event;

import net.fenton.core.Core;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * Created by Encast (2016-12-10 1:52 PM)
 *
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("");
        if(FentonPlayerHandler.getInstance().getPlayers().containsKey(p.getUniqueId())) {
            Core.getInstance().getMainMongoInstance().savePlayer(p);
            FentonPlayerHandler.getInstance().removePlayer(p.getUniqueId());
        }
    }
}
