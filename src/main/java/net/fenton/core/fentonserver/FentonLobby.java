package net.fenton.core.fentonserver;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Created by Encast (2016-12-10 1:46 PM)
 *
 */
public class FentonLobby extends FentonServer {

    public FentonLobby() {
        super("Server Lobby", FentonServerTag.NONE, "Lobby", Collections.singletonList("lobby"));

        setServerItem(Material.ITEM_FRAME, 1, (short) 0, "Server Lobby", Arrays.asList("",
                "§7Join the Main Network Lobby to AFK, talk",
                "§7with friends, take part in events, and more!"));
    }
}
