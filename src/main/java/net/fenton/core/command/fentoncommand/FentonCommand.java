package net.fenton.core.command.fentoncommand;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Encast (2017-01-13 4:03 PM)
 */
public abstract class FentonCommand {

    private String name;
    private String description;
    private String permission;
    private boolean consoleAllowed;
    private List<String> aliases;

    public FentonCommand(String name, String description, String permission, boolean consoleAllowed, String... aliases) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.consoleAllowed = consoleAllowed;
        this.aliases = new ArrayList<String>();
        for(String a : aliases) {
            this.aliases.add(a.toLowerCase());
        }
    }

    public final String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public final String getPermission() {
        return permission;
    }

    public final List<String> getAliases() {
        return aliases;
    }

    public boolean isConsoleAllowed() {
        return consoleAllowed;
    }

    public final String getNoPermissionMessage() {
        return "§cYou do not have permission to execute this command.";
    }

    public abstract void execute(CommandSender sender, String[] args);
}
