package net.fenton.core.command;

import net.fenton.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by Encast (2017-01-24 4:22 PM)
 */
public class BlastyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("blasty")) {
            if(sender.hasPermission("fenton.core.command.blasty")) {
                Vector blast = new Vector(0, 20, 0);
                for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                    online.setVelocity(blast);
                    online.playSound(online.getLocation(), Sound.EXPLODE, 5, 1);
                    Bukkit.getServer().getWorld(online.getWorld().getName()).strikeLightningEffect(online.getLocation());
                }
                Bukkit.getServer().broadcastMessage("§9§lBOOOOOOOOM!");
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
