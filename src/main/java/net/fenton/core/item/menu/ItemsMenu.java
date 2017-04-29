package net.fenton.core.item.menu;

import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.fenton.core.inventory.FentonInventory;
import net.fenton.core.inventory.FentonInventoryHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Encast (2017-02-22 6:08 PM)
 */
public class ItemsMenu extends FentonInventory {

    public ItemsMenu() {
        super("Items", false, 27);
    }

    @Override
    public void openInventory(Player p) {
        Inventory i = getInventory();
        i.setItem(11, FentonItemStack.createStack().createItemStack(Material.PAPER, 1, "§fPlayer Packs",
                Arrays.asList(" ", "§7Click to view Player Packs!")));
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
            if(name.equalsIgnoreCase("Player Packs")) {
                FentonInventoryHandler.getInstance().getMenuByID("Pack").openInventory(p);
            }
        }
    }
}
