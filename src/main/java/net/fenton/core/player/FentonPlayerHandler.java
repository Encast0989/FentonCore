package net.fenton.core.player;

import org.bukkit.entity.Player;

import java.util.*;

/**
 *
 * Created by Encast (2016-12-10 2:00 PM)
 *
 */
public class FentonPlayerHandler {

    private static FentonPlayerHandler instance;
    public static FentonPlayerHandler getInstance() {
        return instance;
    }

    private Map<UUID, FentonPlayer> players;

    public FentonPlayerHandler() {
        instance = this;
        players = new HashMap<UUID, FentonPlayer>();
    }

    public void addPlayer(UUID uuid, FentonPlayer data) {
        players.put(uuid, data);
    }

    public void removePlayer(UUID uuid) {
        if(players.containsKey(uuid)) {
            players.remove(uuid);
        }
    }

    public Map<UUID, FentonPlayer> getPlayers() {
        return new HashMap<UUID, FentonPlayer>(players);
    }

    public FentonPlayer getPlayer(UUID uuid) {
        if(players.containsKey(uuid)) {
            return players.get(uuid);
        }
        return null;
    }

    public boolean hasPlayer(Player p) {
        return getPlayers().containsKey(p.getUniqueId());
    }
}
