package net.fenton.api;

import net.fenton.core.Core;
import net.fenton.core.achievement.FentonAchievementHandler;
import net.fenton.core.database.MongoDBCore;
import net.fenton.core.disguise.DisguiseHandler;
import net.fenton.core.nms.PacketResource;
import net.fenton.core.nms.Reflection;
import net.fenton.core.player.FentonPlayerHandler;
import net.fenton.core.player.rank.FentonRankHandler;

/**
 * Created by Encast (2017-01-09 3:41 PM)
 */
public class Fenton {

    private static Fenton instance;

    /**
     *
     * @return Fenton instance.
     */
    public static Fenton getAPI() {
        return instance;
    }

    public Fenton() {
        instance = this;
    }

    /**
     * Get data for players.
     *
     * @return Retuns the main player data.
     */
    public FentonPlayerHandler getPlayerHandler() {
        return Core.getInstance().getPlayerHandler();
    }

    /**
     * Ability to set/remove player tags.
     *
     * @return Returns the main rank handler.
     */
    public FentonRankHandler getRankHandler() {
        return Core.getInstance().getRankHandler();
    }

    /**
     * Methods to give/remove achievements.
     *
     * @return Returns the main achievement handler.
     */
    public FentonAchievementHandler getAchievementHandler() {
        return Core.getInstance().getAchievementHandler();
    }

    /**
     * Ability to set/reset a players nickname.
     *
     *
     * @return Returns the main nickname manager.
     */
    public DisguiseHandler getDisguiseHandler() {
        return Core.getInstance().getDisguiseHandler();
    }

    /**
     * Contains a few packets to easily send to players.
     *
     * @return Returns an instance of the PacketResource class.
     */
    public PacketResource getPacketResource() {
        return new PacketResource();
    }

    /**
     * Simple reflection contains the ability to get an NMS class/send a packet.
     *
     * @return Returns an instance of the Reflection class.
     */
    public Reflection getSimpleReflection() {
        return new Reflection();
    }

    /**
     * Returns the network IP, this could change in the future however.
     *
     * @return Returns the network IP.
     */
    public String getNetworkIP() {
        return Core.getInstance().getNetworkIP();
    }

    /**
     *
     * Returns the main instance for Mongo, for easy access to the
     * multiple network collections. Includes the client as well, for
     * quick and easy connections.
     *
     * @return Returns the main MongoDB instance connection.
     */
    @Deprecated
    public MongoDBCore getMainMongoInstance() {
        return Core.getInstance().getMainMongoInstance();
    }
}
