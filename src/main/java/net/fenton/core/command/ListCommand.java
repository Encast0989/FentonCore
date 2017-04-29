package net.fenton.core.command;

import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Encast (2017-01-24 5:02 PM)
 */
public class ListCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("list")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                String list = "§2§lPLAYERS: §7(" + Bukkit.getServer().getOnlinePlayers().size() + ") ";
                int count = 0;
                int max = FentonPlayerHandler.getInstance().getPlayers().values().size();
                for(FentonPlayer fp : FentonPlayerHandler.getInstance().getPlayers().values()) {
                    if(!fp.getSettings().getDisguise().isVanished()) {
                        list += fp.getRank().getColour() + fp.getName();
                        if(count < (max - 1)) {
                            list += "§7, ";
                        }
                    }
                    count++;
                }
                p.sendMessage(list);
            } else {
                sender.sendMessage("Only players can use this command.");
            }
        }
        return true;
    }
}
