package net.fenton.core.database;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.fenton.core.Core;
import net.fenton.core.FentonDBIdentifier;
import net.fenton.core.achievement.FentonAchievementHandler;
import net.fenton.core.corebox.CoreBox;
import net.fenton.core.corebox.CoreBoxTier;
import net.fenton.core.item.pack.PlayerPack;
import net.fenton.core.server.ServerType;
import net.fenton.core.player.*;
import net.fenton.core.player.rank.FentonRank;
import net.fenton.core.server.SettingType;
import org.bson.Document;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.InetAddress;
import java.util.*;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 * Created by Encast (2017-01-14 4:45 PM)
 */
public class MongoDBCore {

    private String host;
    private int port;
    private MongoClient client;
    private MongoDatabase fentonDB;
    private MongoCollection<Document> players;

    // Network Settings
    private MongoCollection<Document> settings;

    // YouTubers
    private MongoCollection<Document> media;

    public MongoDBCore(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        new Runnable() {
            public void run() {
                MongoClientURI uri = new MongoClientURI("removed");
                client = new MongoClient(uri);
            }
        }.run();
        System.out.println("[DATABASE] Connected to Mongo Database.");

        fentonDB = client.getDatabase("fenton");
        players = fentonDB.getCollection("players");

        new BukkitRunnable() {
            public void run() {
                setupSettings();
                setupMedia();
            }
        }.runTaskLater(Core.getInstance(), 20 * 7);
    }

    public void disconnect() {
        if(client != null) {
            client.close();
        }
    }

    public MongoDatabase getFentonDB() {
        return fentonDB;
    }

    public MongoCollection<Document> getPlayerCollection() {
        return players;
    }

    public MongoClient getClient() {
        return client;
    }


    /*
    Data:
      uuid
      name
      rank
      secondary_rank
      multiplier
      sacred_crystals
      settings
      visibility
      achievements
      core_box
      items
      first_login
      last_login
      bans
      mutes
     */

    private void newPlayer(Player p) {
        Map<String, Object> disguise = new HashMap<String, Object>();
        disguise.put("vanished", false);
        disguise.put("nicked", false);
        disguise.put("nickname", "NONE");

        Map<String, Object> playerPack = new HashMap<String, Object>();
        Map<String, Object> packs = new HashMap<String, Object>();
        for(PlayerPack pack : PlayerPack.values()) {
            if(!packs.containsKey(pack.getDbName())) {
                packs.put(pack.getDbName(), false);
            }
        }
        packs.remove(PlayerPack.DEFAULT.getDbName());
        packs.put(PlayerPack.DEFAULT.getDbName(), true);
        playerPack.put("current_pack", "DEFAULT");
        playerPack.put("packs", packs);

        Document doc = new Document("uuid", p.getUniqueId().toString());
        doc.put("uuid", p.getUniqueId().toString());
        doc.put("name", p.getName());
        doc.put("rank", FentonRank.DEFAULT.getDbName());
        doc.put("secondary_rank", FentonRank.NONE.getDbName());
        doc.put("multiplier", 1);
        doc.put("sacred_crystals", 500);
        doc.put("settings", DefaultPlayer.getInstance().getDefaultSettings(p));
        doc.put("disguise", disguise);
        doc.put("visibility", PlayerVisibilityLevel.ALL.getDbName());
        doc.put("achievements", FentonAchievementHandler.getInstance().getDefaultAchievementsAsMap());
        doc.put("core_box", Collections.emptyMap());
        doc.put("items", "null");
        doc.put("player_pack", playerPack);
        doc.put("first_login", System.currentTimeMillis());
        doc.put("last_login", System.currentTimeMillis());
        doc.put("bans", Collections.emptyMap());
        doc.put("mutes", Collections.emptyMap());


        players.insertOne(doc);
        FentonPlayerHandler.getInstance().addPlayer(p.getUniqueId(), new FentonPlayer(p.getUniqueId(), FentonRank.DEFAULT, FentonRank.NONE,
                new FentonSettings(new FentonDisguise(false, false, p.getName(), "NONE"), false, false),
                new FentonAchievement(FentonAchievementHandler.getInstance().getDefaultAchievementsAsMap()),
                new FentonCoreBox(Collections.<CoreBox>emptyList()), new FentonItems(),
                new FentonPlayerPack(PlayerPack.DEFAULT, Collections.<String, Object>emptyMap()),
                new PlayerVisibility(PlayerVisibilityLevel.ALL), 1, 500));
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 5, 1);
        p.sendMessage("§2+500 Sacred Crystals! (Joining for the first time)");
    }

    public void retrievePlayer(Player p) {
        Document data = players.find(eq("uuid", p.getUniqueId().toString())).first();
        if(data != null) {
            // General
            UUID uuid = UUID.fromString(data.getString("uuid"));
            FentonRank rank = FentonRank.valueOf(data.getString("rank"));
            FentonRank secondary_rank = FentonRank.valueOf(data.getString("secondary_rank"));
            int multiplier = data.getInteger("multiplier");
            int sacred_crystals = data.getInteger("sacred_crystals");

            // Settings/Disguise
            Document d = data.get("disguise", Document.class);
            FentonDisguise disguise = new FentonDisguise(d.getBoolean("vanished"), d.getBoolean("nicked"), p.getName(), d.getString("nickname"));

            Document s = data.get("settings", Document.class);
            FentonSettings settings = new FentonSettings(disguise, s.getBoolean(FentonDBIdentifier.getInstance().getSettingsDetailedInventory()),
                    s.getBoolean(FentonDBIdentifier.getInstance().getSettingsFlying()));

            // Visibility
            PlayerVisibility visibility = new PlayerVisibility(PlayerVisibilityLevel.valueOf(data.getString("visibility")));

            // Core Box
            Document core_boxes = data.get("core_box", Document.class);
            List<CoreBox> coreBoxList = new ArrayList<CoreBox>();
            for(String cb : core_boxes.keySet()) {
                Document contents = core_boxes.get(core_boxes.get(cb), Document.class);
                CoreBox c = new CoreBox(contents.getLong("received"), CoreBoxTier.valueOf(contents.getString("tier")),
                        contents.getDouble("rank_chance"), contents.getDouble("core_item_chance"),
                        contents.getDouble("level_chance"), contents.getDouble("double_rewards_chance"));
                coreBoxList.add(c);
            }

            // Packs
            Document packs = data.get("player_pack", Document.class);
            Document packData = packs.get("packs", Document.class);
            Map<String, Object> mapPackData = new HashMap<String, Object>();
            for(String packName : packData.keySet()) {
                mapPackData.put(packName, packData.get(packName));
            }
            FentonPlayerPack playerPack = new FentonPlayerPack(PlayerPack.valueOf(packs.getString("current_pack")), mapPackData);

            // Achievements
            Document achievementsData = data.get("achievements", Document.class);
            Map<String, Object> achievements = new HashMap<String, Object>();
            for(String key : achievementsData.keySet()) {
                achievements.put(key, achievementsData.get(key));
            }


            FentonPlayer fp = new FentonPlayer(uuid, rank, secondary_rank, settings, new FentonAchievement(achievements),
                    new FentonCoreBox(coreBoxList), new FentonItems(),
                    playerPack, visibility, multiplier, sacred_crystals);

            FentonPlayerHandler.getInstance().addPlayer(p.getUniqueId(), fp);

            players.updateOne(eq("uuid", p.getUniqueId().toString()), set("last_login", System.currentTimeMillis()));
            players.updateOne(eq("uuid", p.getUniqueId().toString()), set("name", p.getName()));
        } else {
            newPlayer(p);
        }
    }

    public void savePlayer(Player p) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        players.updateOne(eq("uuid", p.getUniqueId().toString()),
                combine(
                        set("name", fp.getSettings().getDisguise().getOriginalName()),
                        set("multiplier", fp.getMultiplier()),
                        set("sacred_crystals", fp.getSacredCrystals()),
                        set("settings", new Document(fp.getSettings().toMap())),
                        set("disguise", new Document(fp.getSettings().getDisguise().toMap())),
                        set("visibility", PlayerVisibilityLevel.ALL.getDbName()),
                        set("achievements", new Document(fp.getAchievements().toMap())),
                        set("core_box", new Document(fp.getCoreBoxes().toMap())),
                        set("player_pack", new Document(fp.getPacks().toMap()))
        ));
    }

    public boolean isUserExistByUUID(UUID uuid) {
        Document data = players.find(eq("uuid", uuid)).first();
        if(data != null) {
            return true;
        }
        return false;
    }

    public boolean isUserExistByName(String name) {
        Document data = players.find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE))).first();
        if (data != null) {
            return true;
        }
        return false;
    }

    // Network Settings

    public MongoCollection<Document> getSettings() {
        return settings;
    }

    private void setupSettings() {
        settings = fentonDB.getCollection("settings");
        Document s = settings.find(eq("server", ServerType.NETWORK.getDbName())).first();
        if(s == null) {
            Document newSettings = new Document("server", ServerType.NETWORK.getDbName());
            newSettings.put(SettingType.GLOBAL_MULTIPLIER.getDbName(), 1);
            newSettings.put(SettingType.GLOBAL_MULTIPLIER_MESSAGE.getDbName(), "None");
            newSettings.put(SettingType.MOTD.getDbName(), "none");

            settings.insertOne(newSettings);
        }
    }

    // YouTubers

    public MongoCollection<Document> getMedia() {
        return media;
    }

    private void setupMedia() {
        media = fentonDB.getCollection("media");
    }

    public boolean hasMedia(String name) {
        Document data = media.find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE))).first();
        if(data != null) {
            return true;
        }
        return false;
    }

    public Document getMediaInfo(String name) {
        Document data = media.find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE))).first();
        if(data != null) {
            return data;
        }
        return null;
    }

    public void addMedia(Player p, String url) {
        Document data = media.find(eq("uuid", p.getUniqueId().toString())).first();
        if(data == null) {
            Document yt = new Document("uuid", p.getUniqueId().toString());
            yt.put("name", p.getName());
            yt.put("url", url);
            media.insertOne(yt);
        }
    }

    public void removeMedia(String name) {
        Document data = media.find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE))).first();
        if(data != null) {
            media.deleteOne(eq("uuid", data.getString("uuid")));
        }
    }
}
