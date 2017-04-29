package net.fenton.core.command;

import net.fenton.core.Core;
import net.fenton.core.player.rank.FentonRank;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 * Created by Encast (2017-01-31 2:11 PM)
 */
public class SetRankCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("setrank")) {
            if(sender.hasPermission("fenton.core.command.setrank")) {
                if(args.length >= 3) {
                    if(args[2].equalsIgnoreCase("main") || args[2].equalsIgnoreCase("secondary")) {
                        FentonRank rank = null;
                        for(FentonRank ranks : FentonRank.values()) {
                            if(ranks.getDbName().equalsIgnoreCase(args[1].toUpperCase())) {
                                rank = ranks;
                            }
                        }
                        if(rank != null) {
                            Document data = Core.getInstance().getMainMongoInstance().getPlayerCollection().find(regex("name", Pattern.compile(args[0], Pattern.CASE_INSENSITIVE))).first();
                            if(data != null) {
                                String type = "rank";
                                if(args[2].equalsIgnoreCase("secondary")) {
                                    type = "secondary_rank";
                                }
                                Core.getInstance().getMainMongoInstance().getPlayerCollection()
                                        .updateOne(regex("name", Pattern.compile(args[0], Pattern.CASE_INSENSITIVE)),
                                                set(type, rank.getDbName()));
                                sender.sendMessage("§aSet " + data.get("name") + "'s rank to " + rank.getColour() + rank.getName());
                                sender.sendMessage("§aIf the player is online, they will need to rejoin the server to fully receive their rank.");
                                return true;
                            } else {
                                sender.sendMessage("§cThat player does not exist!");
                            }
                        } else {
                            sender.sendMessage("§c" + args[1] + " is not a valid rank.");
                        }
                    } else {
                        sender.sendMessage("§cPlease specify either §bmain §cor §bsecondary§c.");
                    }
                } else {
                    sender.sendMessage("§cPlease specify a player and rank.");
                    sender.sendMessage("§c/setrank <player> <rank> <main, secondary>");
                }
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
