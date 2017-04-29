package net.fenton.core.permission;

import net.fenton.core.player.rank.FentonRank;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Encast (2017-01-08 7:12 PM)
 */
public abstract class FentonGroup {

    private String name;
    private FentonRank[] inheritance;
    private List<String> permissions;

    public FentonGroup(String name, FentonRank... inheritance) {
        this.name = name;
        this.inheritance = inheritance;
        permissions = new ArrayList<String>();
    }

    public final void addPermission(String permission) {
        permissions.add(permission);
    }

    public final void setPermissions(Player p) {
        PermissionAttachment pa = PermissionManager.getInstance().getPlayer(p.getUniqueId());
        if(permissions.size() >= 1) {
            for(String s : permissions) {
                pa.setPermission(s, true);
            }
        }
        if(inheritance != null) {
            for(FentonRank inherit : inheritance) {
                PermissionManager.getInstance().addPermissions(p, inherit);
            }
        }
    }
}
