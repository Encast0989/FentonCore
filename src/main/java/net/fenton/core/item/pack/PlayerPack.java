package net.fenton.core.item.pack;

/**
 * Created by Encast (2017-02-24 3:33 PM)
 */
public enum PlayerPack {

    DEFAULT("DEFAULT", "Default", "§f"),
    ELITE("ELITE", "Elite", "§8"),
    WATER("WATER", "Water", "§9"),
    FIRE("FIRE", "Fire", "§c"),
    EARTH("EARTH", "Earth", "§2");

    private String dbName;
    private String friendlyName;
    private String colour;

    PlayerPack(String dbName, String friendlyName, String colour) {
        this.dbName = dbName;
        this.friendlyName = friendlyName;
        this.colour = colour;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getColour() {
        return colour;
    }
}
