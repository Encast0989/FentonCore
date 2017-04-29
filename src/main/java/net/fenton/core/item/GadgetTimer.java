package net.fenton.core.item;

import net.fenton.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Encast (2017-02-22 5:30 PM)
 */
public class GadgetTimer extends BukkitRunnable {

    private String playerName;
    private int seconds;
    private boolean paused;

    public GadgetTimer(String playerName, int seconds) {
        this.playerName = playerName;
        this.seconds = seconds;
        paused = false;

        this.runTaskTimer(Core.getInstance(), 10, 20);
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void run() {
        if(!paused) {
            seconds--;
            if(seconds == 0) {
                this.cancel();
                Player p = Bukkit.getServer().getPlayer(playerName);
                if(p != null) {
                    GadgetHandler.getGadgets().removePlayer(p.getName());
                }
            }
        }
    }
}
