package net.fenton.core.command;

import net.fenton.core.Core;
import net.fenton.core.inventory.FentonInventoryHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * Created by Encast (2016-12-16 5:45 PM)
 *
 */
public class InventoryCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("inventory")) {
            if(sender.hasPermission("fenton.core.command.inventory")) {
                if(sender instanceof Player) {
                    Player p = (Player) sender;
                    if(args.length > 0) {
                        FentonInventoryHandler.getInstance().getMenuByID(args[0]).openInventory(p);
                    } else {
                        p.sendMessage("§cPlease enter a valid Menu ID: \n§7"
                                + FentonInventoryHandler.getInstance().getStringIDValues());
                    }
                } else {
                    sender.sendMessage("Only players can use this command.");
                }
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
