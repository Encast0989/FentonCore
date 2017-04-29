package net.fenton.core.permission;

import net.fenton.core.Core;
import net.fenton.core.permission.group.*;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Encast (2017-01-08 7:21 PM)
 */
public class PermissionManager implements Listener {

    private static PermissionManager instance;
    public static PermissionManager getInstance() {
        return instance;
    }

    private Map<String, FentonGroup> groups;
    private Map<UUID, PermissionAttachment> players;
    final private String VERSION = "0.2.0";

    public PermissionManager() {
        instance = this;
        groups = new HashMap<String, FentonGroup>();
        players = new HashMap<UUID, PermissionAttachment>();
        setupGroups();
    }

    public String getVersion() {
        return VERSION;
    }

    public PermissionAttachment getPlayer(UUID uuid) {
        PermissionAttachment pa = players.get(uuid);
        if(pa != null) {
            return pa;
        } else {
            return null;
        }
    }

    public void setupGroups() {
        // Group identifier, FentonGroup
        groups.put(FentonRank.OWNER.getName(), new OwnerGroup());
        groups.put(FentonRank.ADMIN.getName(), new AdminGroup());
        groups.put(FentonRank.MODERATOR.getName(), new ModeratorGroup());
        groups.put(FentonRank.HELPER.getName(), new HelperGroup());
        groups.put(FentonRank.BUILD_TEAM.getName(), new BuildTeamGroup());
        groups.put(FentonRank.MEDIA.getName(), new MediaGroup());
        groups.put(FentonRank.EVENT.getName(), new EventGroup());
        groups.put(FentonRank.FEN_PLUS.getName(), new FenPlusGroup());
        groups.put(FentonRank.FEN.getName(), new FenGroup());
        groups.put(FentonRank.VIP.getName(), new VIPGroup());
        groups.put(FentonRank.DEFAULT.getName(), new DefaultGroup());
        groups.put(FentonRank.TOAST.getName(), new DefaultGroup());

    }

    public void addPermissions(Player p, FentonRank rank) {
        groups.get(rank.getName()).setPermissions(p); //TODO Fix-- check if the group exists, if not, default to "default."
    }

    public void registerPermission(FentonRank rank, String permission) {
        groups.get(rank.getName()).addPermission(permission);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        players.put(p.getUniqueId(), p.addAttachment(Core.getInstance()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        players.remove(p.getUniqueId());
    }
}
