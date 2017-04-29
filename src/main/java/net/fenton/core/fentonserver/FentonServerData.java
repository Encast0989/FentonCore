package net.fenton.core.fentonserver;

import net.fenton.core.fentonserver.gameserver.BattlefieldGame;
import net.fenton.core.fentonserver.gameserver.FactionsGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Encast (2016-12-10), All Rights Reserved.
 * <p>
 * Claiming this code as one's own is strictly forbidden.
 * <p>
 * Class created at: 1:43 PM
 */
/**
 *
 * Created by Encast (2016-12-10 1:43 PM)
 *
 */
public class FentonServerData {

    private static FentonServerData instance;
    public static FentonServerData getInstance() {
        return instance;
    }

    private Map<String, FentonServerInfo> servers;

    public FentonServerData() {
        instance = this;
        servers = new HashMap<String, FentonServerInfo>();

        // Adding Games
        servers.put("Battlefield", new FentonServerInfo(new BattlefieldGame(), 12));
//        servers.put("Factions", new FentonServerInfo(new FactionsGame(), 13));
        servers.put("Lobby", new FentonServerInfo(new FentonLobby(), 18));
    }

    public Map<String, FentonServerInfo> getServers() {
        return servers;
    }

    public FentonServer getServerByID(String id) {
        return servers.get(id).getServer();
    }
}
