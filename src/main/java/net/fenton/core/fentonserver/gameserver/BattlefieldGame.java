package net.fenton.core.fentonserver.gameserver;

import net.fenton.core.fentonserver.FentonServer;
import net.fenton.core.fentonserver.FentonServerTag;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Created by Encast (2016-12-10 1:45 PM)
 *
 */
public class BattlefieldGame extends FentonServer {

    public BattlefieldGame() {
        super("Battlefield", FentonServerTag.NEW, "PvP", Collections.singletonList("FenG-BF"));

        setServerItem(Material.GOLDEN_APPLE, 1, "Battlefield",
                Arrays.asList(" ",
                "§7Play an original game based off the famous KitPvP",
                "§7game type. Includes many balanced classes to choose",
                "§7from, different twists to game play, and upgradable",
                "§7perks/classes."));
    }
}
