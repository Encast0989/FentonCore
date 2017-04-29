package net.fenton.core.command.fentoncommand;

import net.fenton.core.Core;
import org.bukkit.command.CommandSender;

/**
 * Created by Encast (2017-01-13 4:20 PM)
 */
public class GlobalMultiplierCommand extends FentonCommand {

    public GlobalMultiplierCommand() {
        super("gm", "Change different options of the Global Multiplier.", "", true, "globalmultiplier");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1) {
            if(args[0].equalsIgnoreCase("m")) {
                if(args.length >= 2) {
                    int multiplier = 1;
                    try {
                        multiplier = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        sender.sendMessage("§c" + args[1] + " is not a number.");
                        return;
                    }
                    Core.getInstance().setGlobalMultiplier(multiplier);
                    sender.sendMessage("§aThe §bGlobal Multiplier §ahas been set to §6x" + multiplier + "§a.");
                    sender.sendMessage("§c§oNOTE: This is currently a one server thing at the moment. Will be changed soon.");
                } else {
                    sender.sendMessage("§cPlease specify a number.");
                }
            } else if(args[0].equalsIgnoreCase("mm")) {
                if(args.length >= 2) {
                    StringBuilder message = new StringBuilder();
                    for(int i = 1; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    Core.getInstance().setGlobalMultiplierMessage(message.toString());
                    sender.sendMessage("§aThe §bGlobal Multiplier Message §ahas been set to: §6" + message.toString());
                } else {
                    sender.sendMessage("§cPlease specify a message.");
                }
            } else {
                sender.sendMessage("§cInvalid argument.");
            }
        } else {
            sender.sendMessage("§cRequires: <m (multiplier), mm (multiplier message)> <value>");
        }
    }
}
