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
                if(Core.getInstance().getMainMongoInstance().getClient() == null) {
                    FentonPlayer fp = new FentonPlayer(p.getUniqueId(), FentonRank.FEN_PLUS, FentonRank.FEN_PLUS,
                            new FentonSettings(new FentonDisguise(false, false, p.getName(), "null"),
                                    false, false),
                            new FentonAchievement(FentonAchievementHandler.getInstance().getDefaultAchievements()),
                            new FentonCoreBox(new ArrayList<CoreBox>()), new FentonItems(),
                            new FentonPlayerPack(PlayerPack.DEFAULT, new HashMap<String, Object>()),
                            new PlayerVisibility(PlayerVisibilityLevel.ALL), 1, 1000);
                }
                Core.getInstance().getMainMongoInstance().retrievePlayer(p);
                Bukkit.getServer().getPluginManager().callEvent(postLogin);
            }
        }.runTaskLater(Core.getInstance(), 10);
    }
}
