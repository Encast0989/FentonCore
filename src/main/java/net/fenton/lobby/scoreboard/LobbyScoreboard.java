package net.fenton.lobby.scoreboard;

import net.fenton.core.Core;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import net.fenton.lobby.LobbyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Encast (2017-02-18 4:13 PM)
 */
public class LobbyScoreboard {

    public void setLobbyScoreboard(final Player p) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        final Scoreboard s = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        final Objective o = s.registerNewObjective("lobby", "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        final Team online = s.registerNewTeam("online_count");
        online.setPrefix("§aONLINE: ");
        online.addEntry(ChatColor.WHITE.toString());
        o.setDisplayName("§2§lFENTON");
        o.getScore(" ").setScore(9);
        o.getScore("§aRank: §f" + fp.getOverridingRank().getColour() + fp.getOverridingRank().getName()).setScore(8);
        o.getScore("§aMultiplier: §fx" + fp.getMultiplier()).setScore(7);
        o.getScore(ChatColor.BLACK.toString()).setScore(6);
        o.getScore("§aCore Boxes: §f" + fp.getCoreBoxes().getCoreBoxes().size()).setScore(5);
        o.getScore("§aS-Crystals: §f" + fp.getSacredCrystals()).setScore(4);
        o.getScore("  ").setScore(3);
        o.getScore(ChatColor.WHITE.toString()).setScore(2);
        o.getScore(ChatColor.GRAY.toString()).setScore(1);
        o.getScore("§2" + Core.getInstance().getNetworkIP()).setScore(0);

        p.setScoreboard(s);

        new BukkitRunnable() {
            int title = 0;
            public void run() {
                online.setSuffix(String.valueOf(LobbyCore.getLobby().getPlayerCount()));
                switch(title) {
                    case 0:
                        o.setDisplayName("§2§lFENTON");
                        title++;
                        break;
                    case 1:
                        o.setDisplayName("§a§lF§2§lENTO§a§lN");
                        title++;
                        break;
                    case 2:
                        o.setDisplayName("§f§lF§a§lE§2§lNT§a§lO§f§lN");
                        title++;
                        break;
                    case 3:
                        o.setDisplayName("§f§lFE§a§lNT§f§lON");
                        title++;
                        break;
                    case 4:
                        o.setDisplayName("§f§lFENTON");
                        title++;
                        break;
                    case 5:
                        o.setDisplayName("§f§lFENTON");
                        title++;
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        o.setDisplayName("§a§lFENTON");
                        title++;
                        break;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                        o.setDisplayName("§2§lFENTON");
                        title = 0;
                        break;
                    default:
                        title = 0;
                        break;
                }
            }
        }.runTaskTimer(Core.getInstance(), 0, 5);
    }
}
