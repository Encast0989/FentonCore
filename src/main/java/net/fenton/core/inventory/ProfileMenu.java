package net.fenton.core.inventory;

import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 *
 * Created by Encast (2016-12-16 3:29 PM)
 *
 */
public class ProfileMenu extends FentonInventory {

    public ProfileMenu() {
        super("Profile", false, 45);
    }

    private String achievements = "Achievements";
    private String settings = "Settings";
    private String coreBox = "Core Box";
    private String general = "General";

    @Override
    public void openInventory(Player p) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        ItemStack achievementsMenu = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1, "§2" + achievements,
                Arrays.asList(" ", "§7See all the §bGLOBAL §7achievements."));
        ItemStack settingsMenu = FentonItemStack.createStack().createItemStack(Material.REDSTONE_COMPARATOR, 1, "§2" + settings,
                Arrays.asList(" ", "§7Check or update your current settings."));
        ItemStack coreBoxMenu = FentonItemStack.createStack().createItemStack(Material.ENCHANTMENT_TABLE, 1, "§2" + coreBox,
                Arrays.asList(" ", "§7View or upgrade your Core Box."));
        ItemStack generalInfo = FentonItemStack.createStack().createItemStack(Material.ANVIL, 1, "§2" + general,
                Arrays.asList(" ",
                        "§3Rank: " + fp.getOverridingRank().getPrefix(),
                        "§3Multiplier: " + fp.getMultiplier(),
                        "§6Core Boxes: " + fp.getCoreBoxes().getCoreBoxes().size(),
                        "§bSacred Crystals: " + fp.getSacredCrystals(),
                        " ",
                        "§7Store Link: §2fenton.buycraft.net"));
        Inventory i = getInventory();
        i.setItem(10, achievementsMenu);
        i.setItem(13, settingsMenu);
        i.setItem(16, coreBoxMenu);
        i.setItem(31, generalInfo);
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            if(i.getItemMeta().hasDisplayName()) {
                String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
                if(name.equals(achievements)) {
                    FentonInventoryHandler.getInstance().getMenuByID("Achievements").openInventory(p);
                } else if(name.equals(settings)) {
                    //TODO settings menu
                    p.sendMessage("§cComing Soon!");
                } else if(name.equals(coreBox)) {
                    FentonInventoryHandler.getInstance().getMenuByID("CoreBox").openInventory(p);
                } else if(name.equals(general)) {
                    p.closeInventory();
                    p.sendMessage(" ");
                    p.sendMessage("§3Website: Soon™");
                    p.sendMessage("§3Store: fenton.buycraft.net");
                    p.sendMessage(" ");
                }
            }
        }
    }
}
