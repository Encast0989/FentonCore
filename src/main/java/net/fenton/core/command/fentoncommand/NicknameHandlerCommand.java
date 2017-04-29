package net.fenton.core.command.fentoncommand;

import org.bukkit.command.CommandSender;

/**
 * Created by Encast (2017-01-25 8:20 PM)
 */
public class NicknameHandlerCommand extends FentonCommand {

    public NicknameHandlerCommand() {
        super("nickname", "Handle player nicknames.", "", true, "nick");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("Coming Soon!");
    }
}
