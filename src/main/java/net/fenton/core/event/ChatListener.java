package net.fenton.core.event;

import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import net.fenton.core.server.ServerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * Created by Encast (2016-12-10 1:48 PM)
 *
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        e.setCancelled(true);
        if(FentonPlayerHandler.getInstance().getPlayers().containsKey(p.getUniqueId())) {
            FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
            if(ServerHandler.getServer().isChatEnabled() || fp.getMainRank().isStaff() || fp.getSecondaryRank().isStaff()) {
                for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                    online.sendMessage(fp.getChatFormattedName() + "§f " + message);
                }
            } else {
                p.sendMessage("§cChat is currently disabled!");
            }
        } else {
            p.sendMessage("§cCould not send your message! Try rejoining the server if the problem persists.");
        }
    }
}
