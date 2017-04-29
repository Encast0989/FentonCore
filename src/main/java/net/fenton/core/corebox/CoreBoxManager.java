package net.fenton.core.corebox;

import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.entity.Player;

/**
 *
 * Created by Encast (2016-12-10 4:35 PM)
 *
 */
public class CoreBoxManager {

    private static CoreBoxManager instance;
    public static CoreBoxManager getInstance() {
        return instance;
    }

    public CoreBoxManager() {
        instance = this;
    }

    public CoreBox convertToCoreBox(CoreBoxPreValue box, long date, CoreBoxTier tier) {
        return new CoreBox(date,
                tier,
                box.getRankChance(),
                box.getCoreItemChance(),
                box.getLevelChance(),
                box.getDoubleRewardsChance());
    }

    public void giveCoreBox(Player p, CoreBox box) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        fp.getCoreBoxes().getCoreBoxes().add(box);
        p.sendMessage("§bYou have gained §f1 Core Box§b! Check your profile to view it.");
    }
}
