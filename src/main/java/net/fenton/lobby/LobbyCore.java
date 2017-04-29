package net.fenton.lobby;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.fenton.core.Core;
import net.fenton.core.server.ServerHandler;
import net.fenton.core.server.ServerType;
import net.fenton.lobby.event.MovementListener;
import net.fenton.lobby.event.PlayerConnectListener;
import net.fenton.lobby.inventory.LobbyInventory;
import net.fenton.lobby.scoreboard.LobbyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Encast (2017-02-18 4:12 PM)
 */
public class LobbyCore implements PluginMessageListener {

    private static LobbyCore instance = new LobbyCore();

    public static LobbyCore getLobby() {
        return instance;
    }

    private int player_count = 0;
    private LobbyScoreboard ls;
    private LobbyInventory li;

    private LobbyCore() {
        ls = new LobbyScoreboard();
        li = new LobbyInventory();

        updatePlayerCount();
    }

    public void update() {
        boolean lobby = Core.getInstance().getConfig().getBoolean("lobby.lobby_server");
        if(lobby) {
            ServerHandler.getServer().setServerType(ServerType.LOBBY);
            Bukkit.getServer().getPluginManager().registerEvents(new PlayerConnectListener(), Core.getInstance());
            Bukkit.getServer().getPluginManager().registerEvents(new MovementListener(), Core.getInstance());
            Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Core.getInstance(), "BungeeCord", this);
            Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(Core.getInstance(), "BungeeCord");
        }
    }

    private void updatePlayerCount() {
        new BukkitRunnable() {
            public void run() {
                requestPlayerCount();
            }
        }.runTaskTimer(Core.getInstance(), 0, 100);
    }

    public int getPlayerCount() {
        return player_count;
    }

    public void onPluginMessageReceived(String c, Player p, byte[] bytes) {
        if(c.equalsIgnoreCase("BungeeCord")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subChannel = in.readUTF();
            if(subChannel.equalsIgnoreCase("PlayerCount")) {
                String server = in.readUTF();
                player_count = in.readInt();
            }
        }
    }

    private void requestPlayerCount() {
        ByteArrayDataOutput o = ByteStreams.newDataOutput();
        o.writeUTF("PlayerCount");
        o.writeUTF("ALL");

        Player p = Iterables.getFirst(Bukkit.getServer().getOnlinePlayers(), null);
        if(p != null) {
            p.sendPluginMessage(Core.getInstance(), "BungeeCord", o.toByteArray());
        }
    }

    public LobbyScoreboard getLobbyScoreboard() {
        return ls;
    }

    public LobbyInventory getLobbyInventory() {
        return li;
    }

    public Location getSpawnLocation() {
        return new Location(Bukkit.getServer().getWorld("FenL"), 1150.5d, 101d, 691.5d, -179.6f, -1.9f);
    }
}
