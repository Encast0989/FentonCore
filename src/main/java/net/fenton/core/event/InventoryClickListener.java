package net.fenton.core.event;

import net.fenton.core.inventory.FentonInventory;
import net.fenton.core.inventory.FentonInventoryHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 *
 * Created by Encast (2016-12-16 4:07 PM)
 *
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory i = e.getInventory();
        ItemStack c = e.getCurrentItem();
        Map<String, FentonInventory> menus = FentonInventoryHandler.getInstance().getAllMenus();
        String inventoryName = ChatColor.stripColor(i.getName());

        for(String s : menus.keySet()) {
            if(inventoryName.equalsIgnoreCase(ChatColor.stripColor(menus.get(s).getName()))) {
                e.setCancelled(true);
                menus.get(s).onClick(p, c);
            }
        }
    }
}
