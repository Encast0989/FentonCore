package net.fenton.core.inventory;


import net.fenton.core.Core;
import net.fenton.core.fentonserver.FentonServer;
import net.fenton.core.fentonserver.FentonServerData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Created by Encast (2016-12-10 1:53 PM)
 *
 */
public class GameMenu extends FentonInventory {

    public GameMenu() {
        super("Game Menu", true, 45);
        updateGameMenu();

        new BukkitRunnable() {
            public void run() {
                updateGameMenu();
            }
        }.runTaskTimer(Core.getInstance(), 100, 100);
    }

    public void updateGameMenu() {
        getInventory().clear();
        for(String name : FentonServerData.getInstance().getServers().keySet()) {
            FentonServer server = FentonServerData.getInstance().getServerByID(name);
            ItemStack s = server.getServerItem().clone();
            ItemMeta m = s.getItemMeta();
            List<String> lore = new ArrayList<String>(m.getLore());
            lore.add(" ");
            lore.add("§3Current Players§7: null"); //TODO Get player count.
            m.setLore(lore);
            s.setItemMeta(m);
            getInventory().setItem(FentonServerData.getInstance().getServers().get(name).getGameMenuSlot(), s);
        }
    }

    @Override
    public void openInventory(Player p) {
        p.openInventory(getInventory());
    }

    @Override
    public void onClick(Player p, ItemStack i) {
        String name = ChatColor.stripColor(i.getItemMeta().getDisplayName());
        if(name.equals("Server Lobby")) {
            Core.getInstance().sendPlayerToServer(p, "FenL");
        } else if(name.equals("Battlefield")) {
            Random r = new Random();
            FentonServer server = FentonServerData.getInstance().getServerByID("Battlefield");
            String subServerName = server.getGameServers().get(r.nextInt(server.getGameServers().size()));
            Core.getInstance().sendPlayerToServer(p, subServerName);
        } else if(name.equals("Practice PvP")) {
            p.closeInventory();
            p.sendMessage("§cThis is coming soon!");
        }
    }
}
