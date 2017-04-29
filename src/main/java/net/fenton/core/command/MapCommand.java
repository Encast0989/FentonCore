package net.fenton.core.command;

import net.fenton.core.server.ServerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Encast (2017-02-20 2:33 PM)
 */
public class MapCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("map")) {
            if(sender instanceof Player) {
                sender.sendMessage("§aMap you're currently playing on: §6" + ServerHandler.getServer().getMapName());
            } else {
                sender.sendMessage("Only players can use this command.");
            }
        }
        return true;
    }
}
