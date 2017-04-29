package net.fenton.lobby.inventory;

import net.fenton.core.enhancedbukkit.FentonItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Encast (2017-02-18 5:32 PM)
 */
public class LobbyInventory {

    public LobbyInventory() {

    }

    public void setMainLobbyLayout(Player p) {
        ItemStack sacred_crystal_shop = FentonItemStack.createStack().createItemStack(Material.PRISMARINE_SHARD, 1,
                "§bSacred Crystal Shop §7(Click)", Arrays.asList(" ", "§7Click to view the §bSacred Crystal Shop§7.",
                        "§7You can buy cosmetics, upgrade the", "§6Sacred Menu§7, and more!"));
        ItemStack items = FentonItemStack.createStack().createItemStack(Material.PISTON_BASE, 1, "§2Items §7(Click)",
                Arrays.asList(" ", "§7Click to view everything you have", "§7collected, from §bGadgets §7to §bSuits§7!"));
        ItemStack player_visibility = FentonItemStack.createStack().createItemStack(Material.LEVER, 1,
                "§2Player Visibility §7(Click)", Arrays.asList(" ", "§7Click to change which players you", "§7see in lobbies."));
        ItemStack lobby_selector = FentonItemStack.createStack().createItemStack(Material.STORAGE_MINECART, 1,
                "§bLobby Selector §7(Click)", Arrays.asList(" ", "§7Click to select a §3Lobby §7to connect", "§7to!"));

        Inventory i = p.getInventory();
        i.clear();
        i.setItem(0, getGameMenuItem());
        i.setItem(1, getProfileItem());
        i.setItem(3, sacred_crystal_shop);
        i.setItem(4, items);
        i.setItem(7, player_visibility);
        i.setItem(8, lobby_selector);
    }

    public ItemStack getGameMenuItem() {
        return FentonItemStack.createStack().createItemStack(Material.POWERED_MINECART, 1, "§bGame Menu §7(Click)",
                Arrays.asList(" ", "§7Click to view available games and",
                        "§7ongoing/upcoming §6Special Events§7."));
    }

    public ItemStack getProfileItem() {
        return FentonItemStack.createStack().createItemStack(Material.BOOK, 1, "§6Profile §7(Click)",
                Arrays.asList(" ", "§7Click to view your §2Profile§7."));
    }
}
