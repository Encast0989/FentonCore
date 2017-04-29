package net.fenton.core.achievement;

import net.fenton.core.player.FentonAchievement;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Encast (2016-12-13 6:49 PM)
 *
 */
public class FentonAchievementHandler implements Listener {

    // Handles achievement awarding/removing.

    private static FentonAchievementHandler instance;
    public static FentonAchievementHandler getInstance() {
        return instance;
    }

    public FentonAchievementHandler() {
        instance = this;
    }

    public void updateAchievements(Player p) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        Map<String, Object> achievements = fp.getAchievements().toMap();
        for (AchievementType type : AchievementType.values()) {
            if (!achievements.containsKey(type.getDBName())) {
                achievements.put(type.getDBName(), false);
            }
        }
        fp.getAchievements().setAchievements(achievements);
    }

    public boolean hasAchievement(Player p, AchievementType type) {
        FentonAchievement fa = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getAchievements();
        Map<String, Object> achievements = fa.toMap();
        return achievements.get(type.getDBName()).equals(true);
    }

    public void awardAchievement(Player p, AchievementType type) {
        FentonAchievement fa = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getAchievements();
        updateAchievements(p);
        Map<String, Object> achievements = fa.toMap();
        if(achievements.get(type.getDBName()).equals(false)) {
            achievements.remove(type.getDBName());
            achievements.put(type.getDBName(), true);
            fa.setAchievements(achievements);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 5, 1);
            p.sendMessage("You have achieved the §3" + type.getName() + " §fachievement!");
        }
    }

    public Map<String, Object> getDefaultAchievements() {
        Map<String, Object> achievements = new HashMap<String, Object>();
        for(AchievementType type : AchievementType.values()) {
            achievements.put(type.getDBName(), false);
        }
        return achievements;
    }

    public Map<String, Object> getDefaultAchievementsAsMap() {
        Map<String, Object> achievements = new HashMap<String, Object>();
        for(AchievementType type : AchievementType.values()) {
            achievements.put(type.getDBName(), false);
        }
        return achievements;
    }
}
