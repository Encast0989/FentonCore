package net.fenton.core.inventory;

import net.fenton.core.corebox.CoreBox;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Encast (2016-12-10 1:53 PM)
 *
 */
public class CoreBoxMenu extends FentonInventory {

    public CoreBoxMenu() {
        super("Core Box", false, 45);
    }

    public void openInventory(Player p) {
        Inventory i = getInventory();
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        for(CoreBox box : fp.getCoreBoxes().getCoreBoxes()) {
            ItemStack item = new ItemStack(Material.ENCHANTMENT_TABLE);
            ItemMeta m = item.getItemMeta();
            m.setDisplayName("§bCore Box");
            List<String> lore = new ArrayList<String>();
            lore.add(" ");
            lore.add("§cReceived: §3" + box.convertToDateString());
            lore.add("§cTier: §3" + box.getTier().getColouredName());
            lore.add("§cRank Chance: §3" + box.getRankChance());
            lore.add("§cCore Item Chance: §3" + box.getCoreItemChance());
            lore.add("§cLevel Chance: §3" + box.getLevelChance());
            lore.add("§cDouble Rewards Chance: §3" + box.getDoubleRewardsChance());
            lore.add(" ");
            lore.add("§6Click to upgrade statistics");
            m.setLore(lore);
            item.setItemMeta(m);

            i.addItem(item);
        }
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
            p.sendMessage("§c§lCORE BOX §cComing Soon!");
        }
    }
}
