package net.fenton.core.player;

/**
 *
 * Created by Encast (2016-12-10 2:04 PM)
 *
 */
public enum PlayerVisibilityLevel {

    ALL("ALL", "ALL", "§aALL"),
    SIMILAR_RANK_ONLY("SIMILAR_RANK_ONLY", "SIMILAR RANK ONLY", "§6SIMILAR RANK ONLY"),
    NONE("NONE", "NONE", "§cNONE §7§o(Excludes Staff)");

    private String dbName;
    private String name;
    private String itemName;

    PlayerVisibilityLevel(String dbName, String name, String itemName) {
        this.dbName = dbName;
        this.name = name;
        this.itemName = itemName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getName() {
        return name;
    }

    public String getItemName() {
        return itemName;
    }
}
