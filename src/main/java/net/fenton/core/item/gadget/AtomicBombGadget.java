package net.fenton.core.item.gadget;

import net.fenton.core.Core;
import net.fenton.core.item.FentonGadget;
import net.fenton.core.item.event.AtomicBombListener;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

/**
 * Created by Encast (2017-02-19 10:01 PM)
 */
public class AtomicBombGadget extends FentonGadget {

    public AtomicBombGadget() {
        super("Atomic Bomb", new ItemStack(Material.TNT), 0, 45,
                Arrays.asList(" ", "§7I wonder what damage this can do."), FentonRank.FEN_PLUS);
    }

    @Override
    public void onUse(Player p) {
        Location bombLoc = p.getLocation();
        bombLoc.add(0, 40, 0);
        TNTPrimed bomb = (TNTPrimed) p.getWorld().spawnEntity(bombLoc, EntityType.PRIMED_TNT);
        AtomicBombListener.getInstance().getBombs().add(bomb);
        bomb.setPassenger(p);
        bomb.setFuseTicks(10 * 20);
        bomb.setFireTicks(5);
        Bukkit.getServer().broadcastMessage("§c§l * ATOMIC BOMB ALERT! *");
        new BukkitRunnable() {
            int count = 0;
            public void run() {
                for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                    online.playSound(online.getLocation(), Sound.NOTE_PIANO, 5, 1);
                }
                count++;
                if(count >= 15) {
                    this.cancel();
                }
            }
        }.runTaskTimer(Core.getInstance(), 20, 40);
    }
}
