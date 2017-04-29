package net.fenton.core.command.fentoncommand;

import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.command.CommandSender;

/**
 * Created by Encast (2017-01-23 4:06 PM)
 */
public class SeeVanishCommand extends FentonCommand {

    public SeeVanishCommand() {
        super("seevanish", "See all players in vanish.", "", true, "sv");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String players = "§7";
        int count = 0;
        for(FentonPlayer fp : FentonPlayerHandler.getInstance().getPlayers().values()) {
            if(fp.getSettings().getDisguise().isVanished()) {
                players += fp.getActualPrefixedName();
                if(count <= (FentonPlayerHandler.getInstance().getPlayers().size())) {
                    players += "§7, ";
                }
            }
            count++;
        }
        sender.sendMessage("§bCurrently vanished players: \n" + players);
        if(players.equals("§7")) {
            sender.sendMessage("§cNo players currently in vanish!");
        }
    }
}
