package net.fenton.core.command;

import com.mongodb.client.MongoCollection;
import net.fenton.core.Core;
import net.fenton.core.player.rank.FentonRank;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.regex;

/**
 * Created by Encast (2017-02-01 3:42 PM)
 */
public class InfoCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("info")) {
            if(sender.hasPermission("fenton.core.command.info")) {
                if(sender instanceof Player) {
                    if(args.length >= 1) {
                        Player p = (Player) sender;
                        if(Core.getInstance().getMainMongoInstance().isUserExistByName(args[0])) {
                            MongoCollection<Document> players = Core.getInstance().getMainMongoInstance().getPlayerCollection();
                            Document data = players.find(regex("name", Pattern.compile(args[0], Pattern.CASE_INSENSITIVE))).first();
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss z");
                            p.sendMessage(" ");
                            p.sendMessage("§aInfo for " + data.getString("name"));
                            p.sendMessage("§aUUID: " + data.getString("uuid"));
                            p.sendMessage("§aFirst Login: " + format.format(data.getLong("first_login")));
                            p.sendMessage("§aLast Login: "  + format.format(data.getLong("last_login")));
                            p.sendMessage("§aMain Rank: " + FentonRank.valueOf(data.getString("rank")).getPrefix());
                            p.sendMessage("§aSecondary Rank: " + FentonRank.valueOf(data.getString("secondary_rank")).getPrefix());
                            p.sendMessage(" ");
                            p.sendMessage("§aBans: null");
                            p.sendMessage("§aMutes null");
                            p.sendMessage(" ");
                        } else {
                            p.sendMessage("§cThat player doesn't exist!");
                        }
                    } else {
                        sender.sendMessage("§cPlease specify a player.");
                        sender.sendMessage("§c/info <player>");
                    }
                } else {
                    sender.sendMessage("Only players can use this command at this moment!");
                }
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
