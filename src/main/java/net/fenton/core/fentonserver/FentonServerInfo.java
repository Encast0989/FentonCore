package net.fenton.core.fentonserver;

/**
 *
 * Created by Encast (2016-12-27 4:44 PM)
 *
 */
public class FentonServerInfo {

    private FentonServer server;
    private int gameMenuSlot;

    public FentonServerInfo(FentonServer server, int gameMenuSlot) {
        this.server = server;
        this.gameMenuSlot = gameMenuSlot;
    }

    public FentonServer getServer() {
        return server;
    }

    public int getGameMenuSlot() {
        return gameMenuSlot;
    }
}
