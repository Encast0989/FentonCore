package net.fenton.core.runnable;

import net.fenton.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * Created by Encast (2016-12-10 5:16 PM)
 *
 */
public class FentonMessage extends BukkitRunnable {

    private String message;
    private int interval;
    private boolean paused;

    public FentonMessage(String message, int delayInSeconds, int intervalInSeconds) {
        this.message = message;
        this.interval = intervalInSeconds;
        this.paused = false;

        this.runTaskTimer(Core.getInstance(), 20 * delayInSeconds, 20 * intervalInSeconds);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIntervalInSeconds() {
        return interval;
    }

    public int getIntervalInTicks() {
        return (interval * 20);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void run() {
        if(!paused) {
            for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.sendMessage(" ");
                online.sendMessage(message);
                online.sendMessage(" ");
                //TODO Send action bar and maybe title message.
            }
        }
    }
}
