package net.fenton.core.server;

/**
 * Created by Encast (2017-01-25 8:16 PM)
 */
public class ServerHandler {

    private static ServerHandler instance = new ServerHandler();

    public static ServerHandler getServer() {
        return instance;
    }

    // General
    private ServerType type;
    private String description = "Fenton Server";
    private String map = "None";

    // Settings
    private boolean chat_enabled = true;

    private ServerHandler() {
    }

    public void setServerType(ServerType type) {
        this.type = type;
    }

    public ServerType getServerType() {
        if(type == null) {
            return ServerType.UNKNOWN;
        }
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChatEnabled() {
        return chat_enabled;
    }

    public void setChatEnabled(boolean enabled) {
        this.chat_enabled = enabled;
    }

    public String getMapName() {
        return map;
    }

    public void setMapName(String map) {
        this.map = map;
    }
}
