package net.fenton.core.player.rank;

/**
 *
 * Created by Encast (2016-12-10 2:01 PM)
 *
 */
public enum FentonRank {

    NETWORK("NETWORK", "Network", "§c[NETWORK] §c", "§c", true, 0, -1),
    OWNER("OWNER", "Owner", "§c[OWNER] §c", "§c", true, 1, -1),
    ADMIN("ADMIN", "Admin", "§c[ADMIN] §c", "§c", true, 2, -1),
    MODERATOR("MODERATOR", "Moderator", "§3[MOD] §3", "§3", true, 3, -2),
    HELPER("HELPER", "Helper", "§b[HELPER] §b", "§b", true, 4, -2),
    BUILD_TEAM("BUILD_TEAM", "Build Team", "§9[BUILD] §9", "§9", false, 5, -2),
    MEDIA("MEDIA", "Media", "§6[MEDIA] §6", "§6", false, 6, 25),
    EVENT("EVENT", "Event", "§6[EVENT] §6", "§6", false, 7, -2),
    FEN_PLUS("FEN_PLUS", "Fen+", "§2[FEN§f+§2] §2", "§2", false, 8, 25),
    FEN("FEN", "Fen", "§a[FEN] §a", "§a", false, 9, 20),
    VIP("VIP", "VIP", "§e[VIP] §e", "§e", false, 10, 15),
    DEFAULT("DEFAULT", "Default", "§7", "§7", false, 11, 10),
    NONE("NONE", "None", "§fNONE", "§f", false, 12, 0),

    // Other ranks (not the main ranks)
    TOAST("TOAST", "Toast", "§a[TOAST] §a", "§a", false, 9, 25);

    private String dbName;
    private String name;
    private String prefix;
    private String colour;
    private boolean isStaff;
    private int priority;
    private int maxCoreBoxes;

    FentonRank(String dbName, String name, String prefix, String colour, boolean isStaff, int priority, int maxCoreBoxes) {
        this.dbName = dbName;
        this.name = name;
        this.prefix = prefix;
        this.colour = colour;
        this.isStaff = isStaff;
        this.priority = priority;
        this.maxCoreBoxes = maxCoreBoxes;
    }

    public String getDbName() {
        return dbName;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColour() {
        return colour;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public int getPriority() {
        return priority;
    }

    public int getMaxCoreBoxes() {
        return maxCoreBoxes;
    }
}
