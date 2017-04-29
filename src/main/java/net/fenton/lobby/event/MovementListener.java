package net.fenton.lobby.event;

import net.fenton.core.server.ServerHandler;
import net.fenton.core.server.ServerType;
import net.fenton.lobby.LobbyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Encast (2017-02-20 4:24 PM)
 */
public class MovementListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(ServerHandler.getServer().getServerType() == ServerType.LOBBY) {
            Player p = e.getPlayer();
            if(e.getTo().getY() <= -10) {
                p.teleport(LobbyCore.getLobby().getSpawnLocation());
            }
        }
    }
}
