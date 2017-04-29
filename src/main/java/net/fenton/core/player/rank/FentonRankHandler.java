package net.fenton.core.player.rank;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 *
 * Created by Encast (2016-12-11 4:37 PM)
 *
 */
public class FentonRankHandler {

    private static FentonRankHandler instance;
    public static FentonRankHandler getInstance() {
        return instance;
    }

    private Scoreboard s;
    private String tag = "F:";
    private boolean prefix = true;

    public FentonRankHandler() {
        instance = this;
        s = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    }

//    public void applyTag(Player p, FentonRank rank) {
//
//    }

    public void removeTag(Player p) {

    }

    private void updateTags() {

    }

    public String getTeamTag() {
        return tag;
    }

    public boolean isPrefix() {
        return prefix;
    }

    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }

    public Scoreboard getScoreboard() {
        return s;
    }

    public void overrideScoreboard(Scoreboard s) {
        this.s = s;
    }
}
