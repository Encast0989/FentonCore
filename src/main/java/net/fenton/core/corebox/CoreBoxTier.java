package net.fenton.core.corebox;

/**
 *
 * Created by Encast (2016-12-10 4:24 PM)
 *
 */
public enum CoreBoxTier {

    CORE_BOX_TIER_I("CORE_BOX_TIER_I", "Core Box [I]", "§aCore Box [I]", 25, new CoreBoxPreValue(0, 0, 0, 0)),
    CORE_BOX_TIER_II("CORE_BOX_TIER_II", "Core Box [II]", "§bCore Box [II]", 15, new CoreBoxPreValue(0, 0, 0, 0)), //TODO chances
    CORE_BOX_TIER_III("CORE_BOX_TIER_III", "Core Box [III]", "§6Core Box [III]", 5, new CoreBoxPreValue(0, 0, 0, 0)), //TODO chances
    CORE_BOX_TIER_IV("CORE_BOX_TIER_IV", "Core Box [IV]", "§2Core Box [IV]", 0.5, new CoreBoxPreValue(0, 0, 0, 0)); //TODO chances

    private String name;
    private String colouredName;
    private double chance;
    private CoreBoxPreValue defaultBox;

    CoreBoxTier(String dbName, String name, String colouredName, double chance, CoreBoxPreValue defaultBox) {
        this.name = name;
        this.colouredName = colouredName;
        this.chance = chance;
        this.defaultBox = defaultBox;
    }

    public String getName() {
        return name;
    }

    public String getColouredName() {
        return colouredName;
    }

    public double getChance() {
        return chance;
    }

    public CoreBoxPreValue getDefaultBox() {
        return defaultBox;
    }
}
