package net.fenton.core.command;

import net.fenton.core.Core;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Encast (2017-01-24 4:23 PM)
 */
public class FlyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("fly")) {
            if(sender instanceof Player) {
                if(sender.hasPermission("fenton.core.command.fly")) {
                    Player p = (Player) sender;
                    FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
                    if(fp.getSettings().isFlying()) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        fp.getSettings().setFlying(false);
                        p.sendMessage("§cYou are no longer flying.");
                    } else {
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        fp.getSettings().setFlying(true);
                        p.sendMessage("§aYou are now flying!");
                    }
                } else {
                    sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
                }
            } else {
                sender.sendMessage("Only players can use this command.");
            }
        }
        return true;
    }
}
