package net.fenton.core;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.fenton.core.achievement.AchievementDistributor;
import net.fenton.core.achievement.FentonAchievementHandler;
import net.fenton.api.Fenton;
import net.fenton.core.command.*;
import net.fenton.core.command.fentoncommand.FentonCommandHandler;
import net.fenton.core.database.MongoDBCore;
import net.fenton.core.disguise.NicknameCommand;
import net.fenton.core.disguise.DisguiseHandler;
import net.fenton.core.disguise.VanishCommand;
import net.fenton.core.enhancedbukkit.FentonItemStack;
import net.fenton.core.event.*;
import net.fenton.core.fentonserver.FentonServerData;
import net.fenton.core.inventory.FentonInventoryHandler;
import net.fenton.core.item.event.AtomicBombListener;
import net.fenton.core.item.event.GadgetListener;
import net.fenton.core.media.MediaHandler;
import net.fenton.core.nms.PacketResource;
import net.fenton.core.nms.Reflection;
import net.fenton.core.permission.PermissionManager;
import net.fenton.core.player.DefaultPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import net.fenton.core.player.rank.FentonRankHandler;
import net.fenton.core.sacred.SacredCrystal;
import net.fenton.core.server.ServerType;
import net.fenton.core.server.SettingType;
import net.fenton.core.store.FentonStore;
import net.fenton.lobby.LobbyCore;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 *
 * Created by Encast for use on Fenton(MC) Network. Distribution of this program
 * is strictly forbidden.
 *
 * *NOTE: As of April 28, 2017, this program (plugin) can be used by anyone.
 * This will probably not be of much use to many, but it may be useful to learn from.
 *
 */
public class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    private MongoDBCore mongoDB;

    private FentonPlayerHandler playerHandler;
    private FentonServerData serverData;
    private FentonItemStack itemStack;
    private FentonRankHandler rankHandler;
    private FentonAchievementHandler achievementHandler;
    private FentonInventoryHandler inventoryHandler;
    private Reflection reflection;
    private PacketResource packetResource;
    private DisguiseHandler disguiseHandler;

    public void onEnable() {
        // CLASS INITIALIZING

        instance = this;
        registerClasses();
        registerListeners();
        registerCommands();
        mongoDB = new MongoDBCore("null", 27017);
        mongoDB.connect();

        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getConfig().options().copyDefaults(true);
        saveConfig();

        LobbyCore.getLobby().update();
    }

    public void onDisable() {
        mongoDB.disconnect();
    }

    private void registerClasses() {
        playerHandler = new FentonPlayerHandler();
        serverData = new FentonServerData();
        itemStack = new FentonItemStack();
        rankHandler = new FentonRankHandler();
        achievementHandler = new FentonAchievementHandler();
        inventoryHandler = new FentonInventoryHandler();

        reflection = new Reflection();
        packetResource = new PacketResource();

        disguiseHandler = new DisguiseHandler();

        // Database
        new FentonDBIdentifier();
        // API
        new Fenton();

        new DefaultPlayer();
        new FentonStore();
        new SacredCrystal();
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new LoginListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new InteractListener(), this);
        pm.registerEvents(new MiscListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new AchievementDistributor(), this);
        pm.registerEvents(new CommandPreprocessListener(), this);
        pm.registerEvents(new PostLoginListener(), this);
        pm.registerEvents(new PermissionManager(), this);
        pm.registerEvents(new GadgetListener(), this);
        pm.registerEvents(new AtomicBombListener(), this);
    }

    private void registerCommands() {
        getCommand("fenton").setExecutor(new FentonCommandHandler());
        getCommand("inventory").setExecutor(new InventoryCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("nick").setExecutor(new NicknameCommand());
        getCommand("optimization").setExecutor(new OptimizationCommand());
        getCommand("chat").setExecutor(new ChatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("blasty").setExecutor(new BlastyCommand());
        getCommand("list").setExecutor(new ListCommand());
        getCommand("setrank").setExecutor(new SetRankCommand());
        getCommand("info").setExecutor(new InfoCommand());
        getCommand("media").setExecutor(new MediaHandler());
        getCommand("where").setExecutor(new WhereCommand());
        getCommand("map").setExecutor(new MapCommand());
    }

    public String getNetworkIP() {
        return "fentonmc.com";
    }

    public int getGlobalMultiplier() {
        Document s = getMainMongoInstance().getSettings().find(eq("server", ServerType.NETWORK.getDbName())).first();
        if(s != null) {
            return s.getInteger(SettingType.GLOBAL_MULTIPLIER.getDbName());
        }
        return 1;
    }

    public void setGlobalMultiplier(int multiplier) {
        getMainMongoInstance().getSettings().updateOne(eq("server", ServerType.NETWORK),
                set("global_multiplier", multiplier));
    }

    public String getGlobalMultiplierMessage() {
        Document s = getMainMongoInstance().getSettings().find(eq("server", ServerType.NETWORK.getDbName())).first();
        if(s != null) {
            return s.getString("global_multiplier_message");
        }
        return "";
    }

    public void setGlobalMultiplierMessage(String message) {
        getMainMongoInstance().getSettings().updateOne(eq("server", ServerType.NETWORK),
                set(SettingType.GLOBAL_MULTIPLIER_MESSAGE.getDbName(), message));
    }

    public MongoDBCore getMainMongoInstance() {
        return mongoDB;
    }

    public void sendPlayerToServer(Player p, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);

        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }

    public String getNoPermissionMessage() {
        return "You do not have permission to execute this command.";
    }

    public String getNoPermissionColouredMessage() {
        return "§cYou do not have permission to execute this command.";
    }

    public FentonPlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public FentonServerData getServerData() {
        return serverData;
    }

    public FentonItemStack getItemStack() {
        return itemStack;
    }

    public FentonRankHandler getRankHandler() {
        return rankHandler;
    }

    public FentonAchievementHandler getAchievementHandler() {
        return achievementHandler;
    }

    public FentonInventoryHandler getInventoryHandler() {
        return inventoryHandler;
    }

    public Reflection getReflection() {
        return reflection;
    }

    public PacketResource getPacketResource() {
        return packetResource;
    }

    public DisguiseHandler getDisguiseHandler() {
        return disguiseHandler;
    }
}
