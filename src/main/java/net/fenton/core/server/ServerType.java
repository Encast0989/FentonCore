package net.fenton.core.server;

/**
 * Created by Encast (2017-02-06 5:22 PM)
 */
public enum ServerType {

    NETWORK("NETWORK", "Network", "§aYou are currently playing on the §cNetwork§a.", 0),
    LOBBY("LOBBY", "Lobby", "§aYou are currently in a §9Lobby§a.", 1),
    GAME("GAME", "Game", "§aYou are currently in a §6Game§a.", 2),
    GAME_LOBBY("GAME_LOBBY", "Game Lobby", "§aYou are currently in a §6Game Lobby§a.", 3),
    IN_DEVELOPMENT("IN_DEVELOPMENT", "In-Development", "§aYou are currently in an §cIn-Development §aserver.", 4),
    DEVELOPER("DEVELOPER", "Developer", "§aYou are currently in a §cDeveloper §aserver.", 5),
    UNKNOWN("UNKNOWN", "Sky", "§aHow did you get here?", 6);

    private String dbName;
    private String friendlyName;
    private String whereMessage;
    private int priority;

    ServerType(String dbName, String friendlyName, String whereMessage, int priority) {
        this.dbName = dbName;
        this.friendlyName = friendlyName;
        this.whereMessage = whereMessage;
        this.priority = priority;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getWhereMessage() {
        return whereMessage;
    }

    public int getPriority() {
        return priority;
    }
}
