package net.fenton.core.fentonserver.gameserver;

import net.fenton.core.fentonserver.FentonServer;
import net.fenton.core.fentonserver.FentonServerTag;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Created by Encast (2016-12-10 1:46 PM)
 *
 */
public class FactionsGame extends FentonServer {

    public FactionsGame() {
        super("Factions", FentonServerTag.ALPHA, "PvP", Collections.singletonList("FenG-Factions"));

        setServerItem(Material.POTION, 1, "§2Factions",
                Arrays.asList(" ",
                        "§7Join a Faction with your friends, or",
                        "§7create your own, and fight to the top.",
                        "§7Raid, kill, brew, ally... you own the",
                        "§7play field."));
    }
}
