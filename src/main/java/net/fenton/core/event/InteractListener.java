package net.fenton.core.event;

import net.fenton.core.inventory.FentonInventory;
import net.fenton.core.inventory.FentonInventoryHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

/**
 *
 * Created by Encast (2016-12-10 1:49 PM)
 *
 */
public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK
                || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                Map<String, FentonInventory> menus = FentonInventoryHandler.getInstance().getAllMenus();
                for(String s : menus.keySet()) {
                    if(ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName())
                            .startsWith(menus.get(s).getName())) {
                        menus.get(s).openInventory(p);
                    }
                }
            }
        }
    }
}
