package net.fenton.core.permission.group;

import net.fenton.core.permission.FentonGroup;

/**
 * Created by Encast (2017-01-08 7:33 PM)
 */
public class MediaGroup extends FentonGroup {

    public MediaGroup() {
        super("Media");

        addPermission("fenton.core.command.nickname");
    }
}
