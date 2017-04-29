package net.fenton.core.event;

import net.fenton.core.event.fentonevent.PostLoginEvent;
import net.fenton.core.nms.PacketResource;
import net.fenton.core.permission.PermissionManager;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import net.fenton.core.player.rank.FentonRank;
import net.fenton.core.server.ServerHandler;
import net.fenton.core.server.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Encast (2017-01-08 4:24 PM)
 */
public class PostLoginListener implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        Player p = e.getPlayer();
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        fp.getSettings().getDisguise().setOriginalName(p.getName());
        p.setPlayerListName(fp.getPlayerListName());

        PermissionManager.getInstance().addPermissions(p, fp.getMainRank());
        if(fp.getSecondaryRank() != FentonRank.NONE) {
            PermissionManager.getInstance().addPermissions(p, fp.getSecondaryRank());
        }

        if(fp.getSettings().isFlying() && ServerHandler.getServer().getServerType() == ServerType.LOBBY) {
            p.setAllowFlight(true);
        }
        if(fp.getSettings().getDisguise().isNicked()) {
            PacketResource.getPackets().sendNameAndSkinChangePacket(p, fp.getSettings().getDisguise().getNickname());
        }
        if(fp.getSettings().getDisguise().isVanished()) {
            for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.hidePlayer(p);
            }
        }

        PacketResource.getPackets().sendTabHeaderFooter(p, "§2§lFenton Network",
                "§6Store: store.fentonmc.com  §aWebsite: fentonmc.com");

        PacketResource.getPackets().addTemp(p);
    }
}
