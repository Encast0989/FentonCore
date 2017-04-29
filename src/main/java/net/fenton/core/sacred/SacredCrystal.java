package net.fenton.core.sacred;

import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Encast (2017-02-11 12:17 PM)
 */
public class SacredCrystal {

    public static SacredCrystal instance;
    public static SacredCrystal getInstance() {
        return instance;
    }

    public SacredCrystal() {
        instance = this;
    }

    public void giveRandomCrystals(Player p, boolean sendMessage) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        Random r = new Random();
        int sc = r.nextInt(50) + 1;
        fp.addSacredCrystals(sc);
        if(sendMessage) {
            p.sendMessage("§b+" + sc + " Sacred Crystals!");
        }
    }
}
