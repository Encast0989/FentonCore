package net.fenton.core.disguise;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * Created by Encast (2016-12-10 1:31 PM)
 *
 */
public class NicknameCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("nick")) {
            if(sender.hasPermission("fenton.core.command.nickname")) {
                if(sender instanceof Player) {
                    Player p = (Player) sender;
                    if(args.length > 0) {
                        if(args[0].equalsIgnoreCase("&reset") || args[0].equalsIgnoreCase("&r")) {
                            DisguiseHandler.getInstance().resetNickname(p);
                            return true;
                        } else {
                            DisguiseHandler.getInstance().setNickname(p, args[0]);
                           return true;
                        }
                    } else {
                        p.sendMessage("§cPlease specify an argument.");
                        p.sendMessage("§c/nick <&reset, &r, {name}>");
                        return true;
                    }
                } else {
                    sender.sendMessage("Only players can use this command.");
                    return true;
                }
            } else {
                sender.sendMessage("§cYou do not have permission to execute this command.");
                return true;
            }
        }
        return true;
    }
}
