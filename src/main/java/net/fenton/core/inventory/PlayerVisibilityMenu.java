package net.fenton.core.inventory;

import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.fenton.core.player.FentonDisguise;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import net.fenton.core.player.PlayerVisibilityLevel;
import org.bukkit.Bukkit;
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
 * Created by Encast (2016-12-10 1:55 PM)
 *
 */
public class PlayerVisibilityMenu extends FentonInventory {

    public PlayerVisibilityMenu() {
        super("Player Visibility", false, 27);
    }

    @Override
    public void openInventory(Player p) {
        ItemStack all = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5, "§aALL",
                Arrays.asList(" ", "§7Click to show ALL players in your", "§7lobby."));
        ItemStack similarRank = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1, "§6SIMILAR RANK ONLY",
                Arrays.asList(" ", "§7Click to show all players with the", "§7same rank as you.",
                        "§7Rank: " + FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getOverridingRank().getPrefix()));
        ItemStack none = FentonItemStack.createStack().createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14, "§cNONE",
                Arrays.asList(" ", "§7Click to HIDE all players. This", "§7excludes §9Staff Members§7."));
        ItemMeta meta;
        switch(FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getPlayerVisibility().getVisibilityLevel()) {
            case ALL:
                meta = all.getItemMeta();
                List<String> loreAll = meta.getLore();
                loreAll.add(" ");
                loreAll.add("§aCurrently Selected!");
                meta.setLore(loreAll);
            case SIMILAR_RANK_ONLY:
                meta = similarRank.getItemMeta();
                List<String> loreSimilarRank = meta.getLore();
                loreSimilarRank.add(" ");
                loreSimilarRank.add("§aCurrently Selected!");
                meta.setLore(loreSimilarRank);
            case NONE:
                meta = none.getItemMeta();
                List<String> loreNone = meta.getLore();
                loreNone.add(" ");
                loreNone.add("§aCurrently Selected!");
                meta.setLore(loreNone);
        }
        Inventory playerVisibilityMenu = getInventory();
        playerVisibilityMenu.setItem(11, all);
        playerVisibilityMenu.setItem(13, similarRank);
        playerVisibilityMenu.setItem(15, none);

        p.openInventory(playerVisibilityMenu);
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        if(i.hasItemMeta()) {
            String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
            FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
            if(name.equals("ALL")) {
                if(fp.getPlayerVisibility().getVisibilityLevel().equals(PlayerVisibilityLevel.ALL)) {
                    p.sendMessage("§cYour player visibility is already at §aALL§c.");
                } else {
                    for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                        FentonDisguise fd = FentonPlayerHandler.getInstance().getPlayer(online.getUniqueId()).getSettings().getDisguise();
                        if(!fd.isVanished()) {
                            p.showPlayer(online);
                        }
                    }
                    fp.getPlayerVisibility().setPlayerVibility(PlayerVisibilityLevel.ALL);
                    p.sendMessage("§aYour player visibility has been changed to ALL.");
                }
            } else if(name.equals("SIMILAR RANK ONLY")) {
                if(fp.getPlayerVisibility().getVisibilityLevel().equals(PlayerVisibilityLevel.SIMILAR_RANK_ONLY)) {
                    p.sendMessage("§cYour player visibility is already at §6SIMILAR RANK ONLYc.");
                } else {
                    for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                        FentonPlayer fp2 = FentonPlayerHandler.getInstance().getPlayer(online.getUniqueId());
                        if(!fp2.getSettings().getDisguise().isVanished()) {
                            if(!fp2.getOverridingRank().isStaff() && (fp2.getOverridingRank() != fp.getOverridingRank())) {
                                p.hidePlayer(online);
                            }
                        }
                    }
                    fp.getPlayerVisibility().setPlayerVibility(PlayerVisibilityLevel.SIMILAR_RANK_ONLY);
                    p.sendMessage("§aYour player visibility has been changed to §6SIMILAR RANK ONLY§a.");
                }
            } else if(name.equals("NONE")) {
                if(fp.getPlayerVisibility().getVisibilityLevel().equals(PlayerVisibilityLevel.NONE)) {
                    p.sendMessage("§cYour player visibility is already at NONE.");
                } else {
                    for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                        FentonPlayer fp2 = FentonPlayerHandler.getInstance().getPlayer(online.getUniqueId());
                        if(!fp2.getOverridingRank().isStaff() && !fp2.getSettings().getDisguise().isVanished()) {
                            p.hidePlayer(online);
                        }
                    }
                    fp.getPlayerVisibility().setPlayerVibility(PlayerVisibilityLevel.NONE);
                    p.sendMessage("§aYour player visibility has been changed to §cNONE§a.");
                }
            }
        }
    }
}
