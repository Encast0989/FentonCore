package net.fenton.core.item.gadget;

import net.fenton.core.item.FentonGadget;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

/**
 * Created by Encast (2017-02-19 10:06 PM)
 */
public class SpeedRunGadget extends FentonGadget {

    public SpeedRunGadget() {
        super("Speed Run", new ItemStack(Material.FEATHER), 0, 15,
                Arrays.asList(" ", "§7Be the speedy person you are."), FentonRank.VIP);
    }

    @Override
    public void onUse(Player p) {
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 10 * 20, 5, true, false);
        p.addPotionEffect(speed);
        p.sendMessage("§aI BELIEVE I CAN BE SPEEDY.");
    }
}
