package net.fenton.core.command;

import net.fenton.core.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Encast (2017-01-04), All Rights Reserved.
 * <p>
 * Claiming this code as one's own is strictly forbidden.
 * <p>
 * Class created at: 10:47 PM
 */
public class OptimizationCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("optimization")) {
            if(sender.hasPermission("fenton.core.command.optimization")) {
                if(args.length >= 1) {

                } else {
                    sender.sendMessage("§3§lOPTIMIZATION §3Tunes down heavy tasks to reduce lag.");
                    sender.sendMessage("§cOptimization mode is currently in alpha and may not work as intended yet.");
                    sender.sendMessage("§eOptions: §cNone - will be done soon");
                }
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
