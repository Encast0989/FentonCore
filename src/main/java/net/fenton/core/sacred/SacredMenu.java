package net.fenton.core.sacred;

import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.fenton.core.inventory.FentonInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Encast (2017-01-27 5:00 PM)
 */
public class SacredMenu extends FentonInventory {

    public SacredMenu() {
        super("Sacred Menu", false, 54);
    }

    @Override
    public void openInventory(Player p) {
        Inventory i = getInventory();
        // Start
        ItemStack start = FentonItemStack.createStack().createItemStack(Material.EMERALD_BLOCK, 1, "§aSTART",
                Arrays.asList(" ", "§aThe Sacred Menu is used for", "§aPlayer Progression. You can choose",
                        "§aany path you wish. Every path has", "§asomething unique for you to upgrade!",
                        "§aUpon fully upgrading everything, you", "§awill have access to the Master Menu.",
                        " ", "§aA lot of the requirements can", "§abe earned in-game!"));
        // Coming Soon
        ItemStack coming_soon = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1, "§cComing Soon!", null);
        // Path Requirement
        ItemStack path_requirement = FentonItemStack.createStack().createItemStack(Material.COAL, 1,
                (short) 1, "§fPath Requirement", Arrays.asList(" ", "§7Coming Soon!"));

        // Multiplier
        ItemStack multiplierI = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fMultiplier x1.5", Arrays.asList("", "§7Requirements:", "§7- 10,000 Sacred Crystals"));
        ItemStack multiplierII = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fMultiplier x2", Arrays.asList("", "§7Requirements:", "§7- 50,000 Sacred Crystals"));
        ItemStack multiplierIII = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fMultiplier x3", Arrays.asList("", "§7Requirements:", "§7- 300,000 Sacred Crystals"));
        ItemStack multiplierIV = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fMultiplier x5", Arrays.asList("", "§7Requirements:", "§7- 1,000,000 Sacred Crystals"));

        // Sacred Crystals
        ItemStack sacredCrystalsI = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fDouble Sacred Crystals (10%)", Arrays.asList(" ", "§7Requirements:", "§7- 10,000 Sacred Crystals"));
        ItemStack sacredCrystalsII = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fDouble Sacred Crystals (20%)", Arrays.asList(" ", "§7Requirements:", "§7- 50,000 Sacred Crystals"));
        ItemStack sacredCrystalsIII = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fDouble Sacred Crystals (30%)", Arrays.asList(" ", "§7Requirements:", "§7- 300,000 Sacred Crystals"));
        ItemStack sacredCrystalsIV = FentonItemStack.createStack().createItemStack(Material.COAL_BLOCK, 1,
                "§fDouble Sacred Crystals (50%)", Arrays.asList(" ", "§7Requirements:", "§7- 1,000,000 Sacred Crystals"));

        ItemStack percent0 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5,
                "§aProgression (0%)", null);
        ItemStack percent1 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (12.5%)", null);
        ItemStack percent2 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (25.0%)", null);
        ItemStack percent3 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (37.5%)", null);
        ItemStack percent4 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (50.0%)", null);
        ItemStack percent5 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (62.5%)", null);
        ItemStack percent6 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (75.0%)", null);
        ItemStack percent7 = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0,
                "§aProgression (87.5%)", null);
        ItemStack master = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS, 1, (short) 3,
                "§bMaster Menu (100%)", Arrays.asList(" ", "§7Menu dedicated to the most determined", "§7players."));

        // Setting Items
        i.setItem(22, start);
        i.setItem(21, path_requirement);
        i.setItem(20, path_requirement);
        i.setItem(23, path_requirement);
        i.setItem(24, path_requirement);

        // Thing 1
        i.setItem(0, coming_soon);
        i.setItem(1, coming_soon);
        i.setItem(2, coming_soon);
        i.setItem(11, coming_soon);

        // Thing 2
        i.setItem(29, coming_soon);
        i.setItem(36, coming_soon);
        i.setItem(37, coming_soon);
        i.setItem(38, coming_soon);

        // Multiplier
        i.setItem(15, multiplierI);
        i.setItem(6, multiplierII);
        i.setItem(7, multiplierIII);
        i.setItem(8, multiplierIV);

        // Sacred Crystals
        i.setItem(33, sacredCrystalsI);
        i.setItem(42, sacredCrystalsII);
        i.setItem(43, sacredCrystalsIII);
        i.setItem(44, sacredCrystalsIV);

        // Percent
        i.setItem(45, percent0);
        i.setItem(46, percent1);
        i.setItem(47, percent2);
        i.setItem(48, percent3);
        i.setItem(49, percent4);
        i.setItem(50, percent5);
        i.setItem(51, percent6);
        i.setItem(52, percent7);
        i.setItem(53, master);

        // Opening Inventory
        p.openInventory(i);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            String name = i.getItemMeta().getDisplayName();
            p.closeInventory();
            p.sendMessage("§cThe Sacred Menu is coming soon!");
        }
    }
}
