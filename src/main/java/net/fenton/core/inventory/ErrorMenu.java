package net.fenton.core.inventory;

import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 *
 * Created by Encast (2016-12-16 7:02 PM)
 *
 */
public class ErrorMenu extends FentonInventory {

    public ErrorMenu() {
        super("§cError", false, 27);
    }

    private String error = "ERROR";

    @Override
    public void openInventory(Player p) {
        Inventory i = getInventory();
        ItemStack errorItem = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13, "§c" + error,
                Arrays.asList(" ", "§7§oAn error occurred while trying", "§7§oto load this menu."));
        i.setItem(13, errorItem);
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(ChatColor.stripColor(i.getItemMeta().getDisplayName()).equals(error)) {
            p.closeInventory();
            p.sendMessage("§c§oAn error occurred while trying to load that menu. Please try again in a little bit.");
        }
    }
}
