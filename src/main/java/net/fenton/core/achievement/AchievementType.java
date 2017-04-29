package net.fenton.core.achievement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Encast (2016-12-13 5:37 PM)
 *
 */
public enum AchievementType {

    FIND_DARKOK_ISLAND(0, "find_darkok_island", "Find Darkok Island", "A mysterious island far off... " +
                                                "%%split%%they say that it rewards players" +
                                                "%%split%%who find the island..."),

    USE_GAME_MENU(1, "use_game_menu", "Game Menu? What's this magic?", "Use the Game Menu for the first time!"),

    MEET_ADMIN(2, "meet_admin", "ADMIN!?!?!?", "Meet an Admin (or higher) in the lobby!"),

    BLASTYMINEMAN(3, "blastymineman", "BlastyMineman", "Say \"BlastyMineman\" in chat... but wait," +
                                      "%%split%%there's a catch: BlastyMineman must be in" +
                                      "%%split%%your lobby (or game). ;)"),

    ENCAST_RANK(4, "encast_rank", "WTF IS THIS RANK?", "Exactly as the title says. Can you" +
                                        "%%split%%get this rank?"),

    XD(5, "xd", "Xd", "I wonder how you get this one. Xd");

    private int id;
    private String dbName;
    private String name;
    private String description;

    AchievementType(int id, String dbName, String name, String description) {
        this.id = id;
        this.dbName = dbName;
        this.name = name;
        this.description = description;
    }

    public int getID() {
        return id;
    }

    public String getDBName() {
        return dbName;
    }

    public String getName() {
        return name;
    }

    public String getItemName() {
        return "§2" + name;
    }

    public String getDescription() {
        return description.replaceAll("%%split%%", " ");
    }

    public List<String> getAsLore() {
        List<String> lore = new ArrayList<String>();
        String[] split = description.split("%%split%%");
        for(String s : split) {
            lore.add("§7" + s);
        }
        return lore;
    }
}
