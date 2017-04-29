package net.fenton.lobby.event;

import net.fenton.core.event.fentonevent.PostLoginEvent;
import net.fenton.core.server.ServerHandler;
import net.fenton.core.server.ServerType;
import net.fenton.lobby.LobbyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Encast (2017-02-18 8:36 PM)
 */
public class PlayerConnectListener implements Listener {

    @EventHandler
    public void onLogin(PlayerJoinEvent e) {
        if(ServerHandler.getServer().getServerType() == ServerType.LOBBY) {
            Player p = e.getPlayer();
            p.teleport(LobbyCore.getLobby().getSpawnLocation());
            LobbyCore.getLobby().getLobbyInventory().setMainLobbyLayout(p);
        }
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        if(ServerHandler.getServer().getServerType() == ServerType.LOBBY) {
            Player p = e.getPlayer();
            LobbyCore.getLobby().getLobbyScoreboard().setLobbyScoreboard(p);
        }
    }
}
