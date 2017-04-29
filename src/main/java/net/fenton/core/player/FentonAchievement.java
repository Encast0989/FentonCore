package net.fenton.core.player;

import org.json.simple.parser.ParseException;

import java.util.Map;

/**
 *
 * Created by Encast (2016-12-14 7:17 PM)
 *
 */
public class FentonAchievement {

    private Map<String, Object> achievements;

    public FentonAchievement(Map<String, Object> achievements) {
        this.achievements = achievements;
    }

    public Object getRawAchievements() throws ParseException {
        return achievements;
    }

    public String getAchievementsAsString() {
        return String.valueOf(achievements);
    }

    public void setAchievements(Map<String, Object> achievements) {
        this.achievements = achievements;
    }

    public Map<String, Object> toMap() {
        return achievements;
    }
}
