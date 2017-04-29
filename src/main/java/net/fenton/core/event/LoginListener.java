package net.fenton.core.event;

import net.fenton.core.Core;
import net.fenton.core.achievement.FentonAchievementHandler;
import net.fenton.core.corebox.CoreBox;
import net.fenton.core.event.fentonevent.PostLoginEvent;
import net.fenton.core.item.pack.PlayerPack;
import net.fenton.core.player.*;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by Encast (2016-12-10 1:51 PM)
 *
 */
public class LoginListener implements Listener {

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        final PostLoginEvent postLogin = new PostLoginEvent(p);
        new BukkitRunnable() {
            public void run() {
//                if(Core.getInstance().isPlayerExistByUUID(p.getUniqueId())) {
//                    FentonPlayerHandler.getInstance().retrievePlayer(p.getUniqueId());
//                } else {
//                    FentonPlayerHandler.getInstance().newPlayer(p.getUniqueId());
//                }
                Core.getInstance().getMainMongoInstance().retrievePlayer(p);
                Bukkit.getServer().getPluginManager().callEvent(postLogin);
            }
        }.runTaskLater(Core.getInstance(), 10);
    }
}
