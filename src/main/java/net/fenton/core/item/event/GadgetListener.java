package net.fenton.core.item.event;

import net.fenton.core.item.FentonGadget;
import net.fenton.core.item.GadgetHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Encast (2017-02-22 6:25 PM)
 */
public class GadgetListener implements Listener {

    @EventHandler
    public void onGadgetUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getItem() != null) {
            if(e.getItem().getItemMeta() != null) {
                if(e.getItem().getItemMeta().hasDisplayName()) {
                    for(FentonGadget gadget : GadgetHandler.getGadgets().getGadgetsList()) {
                        String name = ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName());
                        if(name.equalsIgnoreCase(gadget.getName())) {
                            e.setCancelled(true);
                            if(!GadgetHandler.getGadgets().getGadgetPlayers().containsKey(p.getName())) {
                                gadget.onUse(p);
                                GadgetHandler.getGadgets().addPlayer(p, gadget.getCooldownSeconds());
                            } else {
                                p.sendMessage("§cYou must wait " +
                                        GadgetHandler.getGadgets().getGadgetPlayers().get(p.getName()).getSeconds()
                                        + "s before using that!");
                            }
                        }
                    }
                }
            }
        }
    }
}
