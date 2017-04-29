package net.fenton.core.inventory;

import net.fenton.core.achievement.FentonAchievementHandler;
import net.fenton.core.achievement.AchievementType;
import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by Encast (2016-12-16 6:11 PM)
 *
 */
public class AchievementMenu extends FentonInventory {

    public AchievementMenu() {
        super("Achievements", false, 45);
    }

    @Override
    public void openInventory(Player p) {
        Inventory i = getInventory();
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        for(AchievementType type : AchievementType.values()) {
            ItemStack stack = new ItemStack(Material.EMERALD, 1);
            ItemMeta m = stack.getItemMeta();
            m.setDisplayName("§2" + type.getItemName());
            List<String> lore = type.getAsLore();
            lore.add(" ");
            lore.add((FentonAchievementHandler.getInstance().hasAchievement(p, type) ? "§aAchieved" : "§cNot Achieved"));
            m.setLore(lore);
            stack.setItemMeta(m);
            i.addItem(stack);
        }
        ItemStack profile = FentonItemStack.createStack().createItemStack(Material.BOOK, 1, "§2Go back to Profile",
                Arrays.asList(" ", "§7Click to go back to your Profile."));
        i.setItem(40, profile);
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
            if(name.equals("Go back to Profile")) {
                FentonInventoryHandler.getInstance().getMenuByID("Profile").openInventory(p);
            }
        }
    }
}
