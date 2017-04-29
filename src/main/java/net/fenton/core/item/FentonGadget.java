package net.fenton.core.item;

import net.fenton.core.player.rank.FentonRank;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Encast (2017-02-18 9:48 PM)
 */
public abstract class FentonGadget {

    private String name;
    private ItemStack icon;
    private int cost;
    private int cooldownSeconds;
    private FentonRank[] default_ranks;
    private List<String> description;

    public FentonGadget(String name, ItemStack icon, int cost, int cooldownSeconds, List<String> description, FentonRank... default_ranks) {
        this.name = name;
        this.icon = icon;
        this.cost = cost;
        this.cooldownSeconds = cooldownSeconds;
        this.description = description;
        if(default_ranks.length >= 1) {
            this.default_ranks = default_ranks;
        } else {
            this.default_ranks = new FentonRank[] {FentonRank.DEFAULT};
        }
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public int getCost() {
        return cost;
    }

    public int getCooldownSeconds() {
        return cooldownSeconds;
    }

    public List<String> getDescription() {
        return description;
    }

    public FentonRank[] getDefaultRanks() {
        return default_ranks;
    }

    public abstract void onUse(Player p);
}
