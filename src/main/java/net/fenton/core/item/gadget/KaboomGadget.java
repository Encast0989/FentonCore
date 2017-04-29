package net.fenton.core.item.gadget;

import net.fenton.core.item.FentonGadget;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Encast (2017-02-19 10:06 PM)
 */
public class KaboomGadget extends FentonGadget {

    public KaboomGadget() {
        super("Kaboom", new ItemStack(Material.BLAZE_ROD), 0, 20,
                Arrays.asList(" ", "§7Great way to annoy some friends."), FentonRank.FEN);
    }

    @Override
    public void onUse(Player p) {
        int count = 0;
        for(Player online : Bukkit.getServer().getOnlinePlayers()) {
            if(online != p) {
                if(online.getWorld() == p.getWorld()) {
                    if(count <= 5) {
                        online.getWorld().strikeLightningEffect(online.getLocation());
                        count++;
                        online.sendMessage("§cYou have been struck by lightning!");
                    }
                }
            }
        }
        p.sendMessage("§aYou have struck lightning on " + count + " players!");
    }
}
