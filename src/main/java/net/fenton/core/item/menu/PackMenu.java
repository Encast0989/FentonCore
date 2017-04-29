package net.fenton.core.item.menu;

import net.fenton.core.inventory.FentonInventory;
import net.fenton.core.item.pack.PlayerPack;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Encast (2017-02-25 6:27 PM)
 */
public class PackMenu extends FentonInventory {

    public PackMenu() {
        super("Player Packs", false, 27);
    }

    @Override
    public void openInventory(Player p) {
        Inventory i = getInventory();
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        for(PlayerPack packs : PlayerPack.values()) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta m = item.getItemMeta();
            m.setDisplayName(packs.getColour() + packs.getFriendlyName());
            m.setLore(Arrays.asList(" ", "§7Player Packs change different aspects",
                    "§7of your rank.",
                    "§7Changes:",
                    "§7- Plus (+) colour (for applicable ranks)",
                    "§7- Colon (:) in chat",
                    "§7- Player List (tab)",
                    "§7More Coming Soon!",
                    " ",
                    (Boolean.valueOf(String.valueOf(fp.getPacks().getPacks().get(packs.getDbName()))) ? "§aEarned!" : "§cNot yet earned!"),
                    "§6You're an admin, you can choose this!"));
            item.setItemMeta(m);
            i.addItem(item);
        }
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
            for(PlayerPack packs : PlayerPack.values()) {
                if(packs.getFriendlyName().equalsIgnoreCase(name)) {
                    FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
                    if(fp.getOverridingRank().getPriority() <= 2) {
                        fp.getPacks().setCurrentPack(packs);
                        p.setPlayerListName(fp.getPlayerListName());
                        p.sendMessage("§aPlayer Pack set to " + packs.getColour() + packs.getFriendlyName());
                    }
                }
            }
        }
    }
}
