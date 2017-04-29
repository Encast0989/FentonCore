package net.fenton.core.command.fentoncommand;

import net.fenton.core.Core;
import net.fenton.core.permission.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Created by Encast (2017-01-25 8:24 PM)
 */
public class VersionCommand extends FentonCommand {

    public VersionCommand() {
        super("version", "Check Fenton sub-core versions.", "", true, "v");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(" ");
        sender.sendMessage("§2Fenton Core: §cv" + Core.getInstance().getDescription().getVersion());
        sender.sendMessage("§2Fenton Permission: §cv" + PermissionManager.getInstance().getVersion());
        sender.sendMessage("§2Server: §cv" + Bukkit.getServer().getVersion());
        sender.sendMessage(" ");
    }
}
