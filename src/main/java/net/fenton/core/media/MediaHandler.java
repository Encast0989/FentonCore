package net.fenton.core.media;

import com.mongodb.client.FindIterable;
import net.fenton.core.Core;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Encast (2017-02-06 6:49 PM)
 */
public class MediaHandler implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("media")) {
            if(sender.hasPermission("fenton.core.command.media")) {
                if(args.length >= 1) {
                    if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("list")) {
                            String fullList = "§7Name: §6";
                            FindIterable<Document> list = Core.getInstance().getMainMongoInstance().getMedia().find();
                            for(Document doc : list) {
                                fullList += doc.getString("name");
                                fullList += " §7URL:";
                                fullList += doc.getString("url");
                                fullList += " \n§7Name: §6";
                            }
                            sender.sendMessage("§6Media List:");
                            sender.sendMessage(fullList);
                        } else {
                            sender.sendMessage("§cInvalid arugments!");
                            sender.sendMessage("§c/media <list,info,add,remove> [name] [url]");
                        }
                    } else if(args.length >= 2) {
                        if(args[0].equalsIgnoreCase("info")) {
                            if(Core.getInstance().getMainMongoInstance().hasMedia(args[1])) {
                                Document data = Core.getInstance().getMainMongoInstance().getMediaInfo(args[1]);
                                if(data != null) {
                                    sender.sendMessage("§7Name: §6" + data.getString("name") + " §7URL: " + data.getString("url"));
                                } else {
                                    sender.sendMessage("§cCould not retrieve player information!");
                                    return true;
                                }
                            } else {
                                sender.sendMessage("§c" + args[1] + " doesn't exist as Media!");
                            }
                        } else if(args[0].equalsIgnoreCase("add")) {
                            if(args.length >= 3) {
                                Player target = Bukkit.getServer().getPlayer(args[1]);
                                if(target != null) {
                                    if(!Core.getInstance().getMainMongoInstance().hasMedia(target.getName())) {
                                        Core.getInstance().getMainMongoInstance().addMedia(target, args[2]);
                                    } else {
                                        sender.sendMessage("§c" + target.getName() + " already exists Media!");
                                    }
                                } else {
                                    sender.sendMessage("§cPlease make sure the target player is online.");
                                    sender.sendMessage("§c§oThis command will allow offline players in the future. As of now, it's in testing phase.");
                                }
                            } else {
                                sender.sendMessage("§cPlease specify the Media URL.");
                            }
                        } else if(args[0].equalsIgnoreCase("remove")) {
                            if(Core.getInstance().getMainMongoInstance().hasMedia(args[1])) {
                                Core.getInstance().getMainMongoInstance().removeMedia(args[1]);
                            } else {
                                sender.sendMessage("§c" + args[1] + " doesn't exist as Media!");
                            }
                        } else {
                            sender.sendMessage("§cInvalid arugments!");
                            sender.sendMessage("§c/media <list,info,add,remove> [name] [url]");
                        }
                    } else {
                        sender.sendMessage("§cInvalid arugments!");
                        sender.sendMessage("§c/media <list,info,add,remove> [name] [url]");
                    }
                } else {
                    sender.sendMessage("§cInvalid arugments!");
                    sender.sendMessage("§c/media <list,info,add,remove> [name] [url]");
                }
            } else {
                Core.getInstance().getNoPermissionColouredMessage();
            }
        }
        return true;
    }
}
