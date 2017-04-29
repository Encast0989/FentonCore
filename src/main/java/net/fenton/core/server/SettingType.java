package net.fenton.core.server;

/**
 * Created by Encast (2017-02-06 5:32 PM)
 */
public enum SettingType {

    GLOBAL_MULTIPLIER("global_multiplier", "Global Multiplier"),
    GLOBAL_MULTIPLIER_MESSAGE("global_multiplier_message", "Global Multiplier Message"),
    MOTD("motd", "MOTD");

    private String dbName;
    private String friendlyName;

    SettingType(String dbName, String friendlyName) {
        this.dbName = dbName;
        this.friendlyName = friendlyName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
