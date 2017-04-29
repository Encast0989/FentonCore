package net.fenton.core.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * Created by Encast (2016-12-16 4:10 PM)
 *
 */
public abstract class FentonInventory {

    private Inventory i;
    private String name;
    private boolean playerIndependantInventory;
    private int inventorySize;

    public FentonInventory(String name, boolean playerIndependantInventory, int inventorySize) {
        this.i = Bukkit.getServer().createInventory(null, inventorySize, "§2" + name);
        this.name = name;
        this.playerIndependantInventory = playerIndependantInventory;
        this.inventorySize = inventorySize;
    }

    public Inventory getInventory() {
        if(playerIndependantInventory) {
            return i;
        } else {
            return Bukkit.getServer().createInventory(null, inventorySize, "§2" + name);
        }
    }

    public String getName() {
        return name;
    }

    public abstract void openInventory(Player p);

    public abstract void onClick(Player p, ItemStack i);
}
