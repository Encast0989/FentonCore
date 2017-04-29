package net.fenton.core.event;

import net.fenton.core.Core;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * Created by Encast (2016-12-29 3:12 PM)
 *
 */
public class CommandPreprocessListener implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String command = e.getMessage().toLowerCase();
        //TODO Redo.
        if(FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getMainRank().getPriority() > 2) {
            if(command.startsWith("/pl") || command.startsWith("/plugins")) {
                e.setCancelled(true);
                p.sendMessage("§cYou do not have permission to view our plugin list. " +
                        "Most of our plugins are custom and cannot be downloaded.");
            } else if(command.startsWith("/help") || command.startsWith("/?")) {
                e.setCancelled(true);
                p.sendMessage(" ");
                p.sendMessage("§3Need to report someone or a bug?");
                p.sendMessage("§aMessage a staff member or report on the forums: [coming soon]");
                p.sendMessage("§3Want to buy something on our store?");
                p.sendMessage("§aStore: fenton.buycraft.net");
                p.sendMessage("§3Issues regarding the store?");
                p.sendMessage("§aEmail us: fentonnetwork@gmail.com");
                p.sendMessage(" ");
            } else if(command.startsWith("/version") || command.startsWith("/icanhasbukkit")) {
                e.setCancelled(true);
                p.sendMessage("§2This server is running Fenton (Implementing " + Bukkit.getBukkitVersion() + "). " +
                        "[Core " + Core.getInstance().getDescription().getVersion() + "]");
            }
        }
    }
}
