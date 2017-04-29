package net.fenton.core.achievement;

import net.fenton.core.event.fentonevent.PostLoginEvent;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/**
 *
 * Created by Encast
 *
 */
public class AchievementDistributor implements Listener {

    // FIND DARKOK ISLAND
    // SOON TM

    // GAME MENU
    @EventHandler
    public void onGameMenuUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getItem() != null) {
            if(e.getItem().hasItemMeta()) {
                if(e.getItem().getItemMeta().hasDisplayName()) {
                    if(ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).startsWith("Game Menu")) {
                        FentonAchievementHandler.getInstance().awardAchievement(p, AchievementType.USE_GAME_MENU);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMeetAdmin(PostLoginEvent e) {
        Player p = e.getPlayer();
        for(FentonPlayer players : FentonPlayerHandler.getInstance().getPlayers().values()) {
            if(players.getOverridingRank().getPriority() <= 2) {
                FentonAchievementHandler.getInstance().awardAchievement(p, AchievementType.MEET_ADMIN);
            }
        }
    }

    @EventHandler
    public void onBlastyMineman(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        //                                                                         BlastyMineman's UUID
        if(FentonPlayerHandler.getInstance().getPlayers().containsKey(UUID.fromString("2879e827-ed84-42bd-bd08-8d97aecbbfa4"))) {
            if(message.equalsIgnoreCase("BlastyMineman")) {
                FentonAchievementHandler.getInstance().awardAchievement(p, AchievementType.BLASTYMINEMAN);
            }
        }
    }

    // ENCAST RANK
    // SOON TM

    @EventHandler
    public void onXD(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        if(message.equals("Xd")) {
            FentonAchievementHandler.getInstance().awardAchievement(p, AchievementType.XD);
        }
    }
}
