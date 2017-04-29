package net.fenton.core.item.menu;

import net.fenton.core.inventory.FentonInventory;
import net.fenton.core.item.FentonGadget;
import net.fenton.core.item.GadgetHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Encast (2017-02-25 6:27 PM)
 */
public class GadgetMenu extends FentonInventory {

    public GadgetMenu() {
        super("Gadgets", false, 27);
    }

    @Override
    public void openInventory(Player p) {
        Inventory i = getInventory();
        for(FentonGadget gadget : GadgetHandler.getGadgets().getGadgetsList()) {
            ItemStack item = new ItemStack(gadget.getIcon());
            ItemMeta m = item.getItemMeta();
            m.setDisplayName("§b" + gadget.getName());
            m.setLore(gadget.getDescription());
            item.setItemMeta(m);
            i.addItem(item);
        }
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        
    }
}
