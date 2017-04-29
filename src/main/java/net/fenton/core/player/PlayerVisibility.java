package net.fenton.core.player;

/**
 *
 * Created by Encast (2016-12-10 2:04 PM)
 *
 */
public class PlayerVisibility {

    private PlayerVisibilityLevel level;

    public PlayerVisibility(PlayerVisibilityLevel level) {
        this.level = level;
    }

    public PlayerVisibilityLevel getVisibilityLevel() {
        return level;
    }

    public void setPlayerVibility(PlayerVisibilityLevel level) {
        this.level = level;
    }
}
