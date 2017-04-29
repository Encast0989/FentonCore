package net.fenton.core.command;

import net.fenton.core.Core;
import net.fenton.core.server.ServerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Encast (2017-01-24 4:23 PM)
 */
public class ChatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("chat")) {
            if(sender.hasPermission("fenton.core.command.chat")) {
                if(args.length >= 1) {
                    if(args[0].equalsIgnoreCase("clear")) {
                        for(int i = 0; i <= 150; i++) {
                            Bukkit.getServer().broadcastMessage(" ");
                        }
                        Bukkit.getServer().broadcastMessage("§2Chat has been cleared by a Staff Member.");
                    } else if(args[0].equalsIgnoreCase("mute")) {
                        if(args.length >= 2) {
                            if(args[1].equalsIgnoreCase("true")) {
                                if(ServerHandler.getServer().isChatEnabled()) {
                                    ServerHandler.getServer().setChatEnabled(false);
                                    Bukkit.getServer().broadcastMessage("§c§lChat has been muted by a Staff Member.");
                                    sender.sendMessage("§aChat has been muted.");
                                } else {
                                    sender.sendMessage("§cChat is already enabled!");
                                }
                            } else if(args[1].equalsIgnoreCase("false")) {
                                if(!ServerHandler.getServer().isChatEnabled()) {
                                    ServerHandler.getServer().setChatEnabled(true);
                                    Bukkit.getServer().broadcastMessage("§a§lChat has been unmuted.");
                                    sender.sendMessage("§aChat has been unmuted.");
                                } else {
                                    sender.sendMessage("§cChat is already disabled!");
                                }
                            } else {
                                sender.sendMessage("§cInvalid option!");
                                sender.sendMessage("§cPlease specify either §btrue §cor §bfalse§c.");
                            }
                        } else {
                            sender.sendMessage("§cPlease specify either §btrue §cor §bfalse§c.");
                        }
                    } else {
                        sender.sendMessage("§cInvalid argument!");
                        sender.sendMessage("§c/chat <clear, mute>");
                    }
                } else {
                    sender.sendMessage("§cPlease specify an argument.");
                    sender.sendMessage("§c/chat <clear, mute>");
                }
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
