package net.fenton.core.permission.group;

import net.fenton.core.permission.FentonGroup;

/**
 * Created by Encast (2017-01-08 7:33 PM)
 */
public class ModeratorGroup extends FentonGroup {

    public ModeratorGroup() {
        super("Moderator");

        addPermission("fenton.core.command.nickname");
    }
}
