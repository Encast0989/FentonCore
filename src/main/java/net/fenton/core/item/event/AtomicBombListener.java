package net.fenton.core.item.event;

import net.fenton.core.Core;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Encast (2017-02-22 6:32 PM)
 */
public class AtomicBombListener implements Listener {

    private static AtomicBombListener instance;
    public static AtomicBombListener getInstance() {
        return instance;
    }

    private ArrayList<TNTPrimed> bombs;

    public AtomicBombListener() {
        instance = this;
        this.bombs = new ArrayList<TNTPrimed>();
    }

    public ArrayList<TNTPrimed> getBombs() {
        return bombs;
    }

    @EventHandler
    public void onTNTExplode(EntityExplodeEvent e) {
        if(e.getEntityType() == EntityType.PRIMED_TNT) {
            final TNTPrimed bomb = (TNTPrimed) e.getEntity();
            if(this.bombs.contains(bomb)) {
                final List<Block> blocks = new ArrayList<Block>(e.blockList());
                for(Block b : blocks) {
                    final BlockState state = b.getState();
                    b.setType(Material.AIR);
                    new BukkitRunnable() {
                        public void run() {
                            state.update(true, false);
                        }
                    }.runTaskLater(Core.getInstance(), 6 * 20);
                }
            }
        }
    }
}
