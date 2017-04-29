package net.fenton.core.player;

import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Created by Encast (2017-01-31 12:06 PM)
 */
public class DefaultPlayer {

    private static DefaultPlayer instance;
    public static DefaultPlayer getInstance() {
        return instance;
    }

    public DefaultPlayer() {
        instance = this;
    }

    public Map<String, Object> getDefaultSettings(Player p) {
        FentonSettings settings = new FentonSettings(new FentonDisguise(false, false, p.getName(), "NONE"),
                false, false);
        return settings.toMap();
    }
}
