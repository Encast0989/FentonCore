package net.fenton.core.fentonserver;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by Encast (2016-12-10 1:40 PM)
 *
 */
public abstract class FentonServer {

    private String name;
    private FentonServerTag tag;
    private String serverType;
    private ItemStack gameItem;
    private List<String> gameServers;

    public FentonServer(String name, FentonServerTag tag, String serverType, List<String> gameServers) {
        this.name = name;
        this.tag = tag;
        this.serverType = serverType;
        this.gameServers = gameServers;
    }

    public final String getName() {
        return name;
    }

    public final FentonServerTag getTag() {
        return tag;
    }

    public void setTag(FentonServerTag tag) {
        this.tag = tag;
    }

    public final String getServerType() {
        return serverType;
    }

    public List<String> getGameServers() {
        return gameServers;
    }

    public final ItemStack getServerItem() {
        if(gameItem == null) {
            ItemStack error = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta e = error.getItemMeta();
            e.setDisplayName("§c" + getName());
            e.setLore(Arrays.asList(FentonServerTag.ERROR.getName(), " ", "§7§oAn error occurred while loading", "§7§othis game. Please try again later."));
            error.setItemMeta(e);
            return error;
        } else {
            return gameItem;
        }
    }

    public void setServerItem(Material item, int amount, short damage, String displayName, List<String> lore) {
        ItemStack gameItem = new ItemStack(item, amount, damage);
        ItemMeta gi = gameItem.getItemMeta();
        gi.setDisplayName("§2" + displayName);
        List<String> newLore = new ArrayList<String>(lore);
        newLore.add(0, getTag().getName());
        gi.setLore(newLore);
        gameItem.setItemMeta(gi);
        this.gameItem = gameItem;
    }

    public void setServerItem(Material item, int amount, String displayName, List<String> lore) {
        ItemStack gameItem = new ItemStack(item, amount);
        ItemMeta gi = gameItem.getItemMeta();
        gi.setDisplayName("§2" + displayName);
        List<String> newLore = new ArrayList<String>(lore);
        newLore.add(0, getTag().getName());
        gi.setLore(newLore);
        gameItem.setItemMeta(gi);
        this.gameItem = gameItem;
    }

    public void setDetailedServerItem() {
        //TODO complete this method (include game/server creator for this)
    }
}
