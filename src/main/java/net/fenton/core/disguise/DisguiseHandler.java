package net.fenton.core.disguise;

import net.fenton.core.Core;
import net.fenton.core.nms.PacketResource;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * Created by Encast (2017-01-01 1:15 PM)
 *
 */
public class DisguiseHandler extends BukkitRunnable {

    private static DisguiseHandler instance;
    public static DisguiseHandler getInstance() {
        return instance;
    }

    private List<UUID> vanishedPlayers;

    public DisguiseHandler() {
        instance = this;
        vanishedPlayers = new ArrayList<UUID>();
        this.runTaskTimer(Core.getInstance(), 0, 40);
    }

    // Vanish

    public void addVanishedPlayer(UUID uuid) {
        vanishedPlayers.add(uuid);
    }

    public void removeVanishedPlayer(UUID uuid) {
        vanishedPlayers.remove(uuid);
    }

    public void run() {
        if(vanishedPlayers.size() >= 1) {
            for(UUID uuid : vanishedPlayers) {
                PacketResource.getPackets().sendActionBarPacket(Bukkit.getServer().getPlayer(uuid), "§fYou are currently §3VANISHED§f.");
            }
        }
    }

    // Nickname

    void setNickname(Player p, String name) {
        if(name.length() <= 16 && name.length() >= 3) {
            char[] chars = name.toCharArray();
            int invalidCharacters = 0;
            for(char c : chars) {
                String cs = String.valueOf(c);
                if(cs.matches("[a-zA-Z]") || cs.matches("[0-9]") || c == '_') continue;

                invalidCharacters++;
            }
            if(invalidCharacters == 0) {
                if(!Core.getInstance().getMainMongoInstance().isUserExistByName(name)) {
                    FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
                    fp.getSettings().getDisguise().setNickname(name);
                    fp.getSettings().getDisguise().setNicked(true);
                    p.setPlayerListName(fp.getPrefixedName());

                    PacketResource.getPackets().sendNameAndSkinChangePacket(p, name);
                    p.sendMessage("§aSet your nickname to " + name);
                } else {
                    p.sendMessage("§cYou can't use that name as it already exists.");
                }
            } else {
                p.sendMessage("§c" + invalidCharacters + " invalid " + (invalidCharacters == 1 ? "character" : "characters")
                        + " are in the name.");
                p.sendMessage("§cNames can only be alphanumeric and can contain underscores.");
            }
        } else {
            p.sendMessage("§cNames can only be between 3 and 16 letters.");
        }
    }

    void resetNickname(Player p) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        if(fp.getSettings().getDisguise().isNicked()) {
            fp.getSettings().getDisguise().setNicked(false);
            p.setPlayerListName(fp.getPrefixedName());

            PacketResource.getPackets().sendNameAndSkinChangePacket(p, fp.getSettings().getDisguise().getOriginalName());
            p.sendMessage("§aReset your nickname.");
        } else {
            p.sendMessage("§cYou currently aren't nicked!");
        }
    }
}
