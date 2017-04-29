package net.fenton.core.command.fentoncommand;

import net.fenton.core.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Encast (2017-01-13 4:09 PM)
 */
public class FentonCommandHandler implements CommandExecutor {

    private static FentonCommandHandler instance;
    public static FentonCommandHandler getInstance() {
        return instance;
    }

    private List<FentonCommand> commands;

    public FentonCommandHandler() {
        instance = this;
        commands = new ArrayList<FentonCommand>();
        setupCommands();
    }

    private void setupCommands() {
        // Add commands.
        commands.add(new GlobalMultiplierCommand());
        commands.add(new SeeVanishCommand());
        commands.add(new NicknameHandlerCommand());
        commands.add(new VersionCommand());
    }

    public List<FentonCommand> getCommands() {
        return commands;
    }

    public boolean registerCommand(FentonCommand command) {
        if(!commands.contains(command)) {
            commands.add(command);
            return true;
        }
        return false;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("fenton")) {
            if(sender.hasPermission("fenton.core.command.fenton")) {
                if(args.length >= 1) {
                    for(FentonCommand command : commands) {
                        if(command.getName().equalsIgnoreCase(args[0]) || command.getAliases().contains(args[0].toLowerCase())) {
                            if(sender instanceof ConsoleCommandSender) {
                                if(!command.isConsoleAllowed()) {
                                    sender.sendMessage("§2You cannot use this command. (ConsoleAllowed=false)");
                                    return true;
                                }
                            }
                            if(sender.hasPermission(command.getPermission())) {
                                String newArgsAsString = "";
                                for(int i = 1; i < args.length; i++) {
                                    newArgsAsString += args[i];
                                    newArgsAsString += " ";
                                }
                                String[] newArgs = newArgsAsString.split(" ");
                                command.execute(sender, newArgs);
                                return true;
                            } else {
                                sender.sendMessage("§2You do not have permission to use this sub-command.");
                                return true;
                            }
                        }
                    }
                    sender.sendMessage("§cThat command doesn't exist!");
                } else {
                    sender.sendMessage("§cPlease specify a sub-command:");
                    for(FentonCommand c : commands) {
                        sender.sendMessage("§7" + c.getName().toUpperCase() + " - " + c.getDescription());
                    }
                }
            } else {
                sender.sendMessage(Core.getInstance().getNoPermissionColouredMessage());
            }
        }
        return true;
    }
}
