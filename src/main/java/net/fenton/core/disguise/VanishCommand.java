package net.fenton.core.disguise;

import net.fenton.core.player.FentonDisguise;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * Created by Encast (2016-12-29 3:10 PM)
 *
 */
public class VanishCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cm, String label, String[] args) {
        if(label.equalsIgnoreCase("vanish")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("fenton.core.command.vanish")) {
                    FentonDisguise fd = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getSettings().getDisguise();
                    if(!fd.isVanished()) {
                        fd.setVanished(true);
                        for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                            online.hidePlayer(p);
                        }
                        DisguiseHandler.getInstance().addVanishedPlayer(p.getUniqueId());
                        p.sendMessage("§aYou are now vanished!");
                    } else {
                        fd.setVanished(false);
                        for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                            online.showPlayer(p);
                        }
                        DisguiseHandler.getInstance().removeVanishedPlayer(p.getUniqueId());
                        p.sendMessage("§cYou are no longer vanished.");
                    }
                } else {
                    p.sendMessage("§cYou do not have permission to execute this command.");
                    return true;
                }
            } else {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
        }
        return true;
    }
}
