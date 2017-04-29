package net.fenton.core.item.gadget;

import net.fenton.core.item.FentonGadget;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Encast (2017-02-19 10:07 PM)
 */
public class TeleportGadget extends FentonGadget {

    public TeleportGadget() {
        super("Teleport", new ItemStack(Material.ENDER_PEARL), 0, 15,
                Arrays.asList(" ", "§7Teleport to a random player... and", "§7annoy them?"), FentonRank.FEN_PLUS);
    }

    @Override
    public void onUse(Player p) {
        List<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
        if(players.size() > 1) {
            Random r = new Random();
            int max = players.size();
            Player target = players.get(r.nextInt(max));
            p.teleport(target);
            p.sendMessage("§aYou have randomly teleported to " + target.getName());
        } else {
            p.sendMessage("§cIt seems like you're the only player on the server. :(");
        }
    }
}
