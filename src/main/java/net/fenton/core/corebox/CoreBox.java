package net.fenton.core.corebox;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by Encast (2016-12-10 4:18 PM)
 *
 */
public class CoreBox {

    private long received;
    private CoreBoxTier tier;
    private double rankChance;
    private double coreItemChance;
    private double levelChance;
    private double doubleRewardsChance;

    public CoreBox(long received, CoreBoxTier tier, double rankChance, double coreItemChance, double levelChance, double doubleRewardsChance) {
        this.received = received;
        this.tier = tier;
        this.rankChance = rankChance;
        this.coreItemChance = coreItemChance;
        this.levelChance = levelChance;
        this.doubleRewardsChance = doubleRewardsChance;
    }

    public long getReceived() {
        return received;
    }

    public String convertToDateString() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss z");
        Date date = new Date(getReceived());
        return format.format(date);
    }

    public CoreBoxTier getTier() {
        return tier;
    }

    public double getRankChance() {
        return rankChance;
    }

    public void setRankChance(double rankChance) {
        this.rankChance = rankChance;
    }

    public double getCoreItemChance() {
        return coreItemChance;
    }

    public void setCoreItemChance(double coreItemChance) {
        this.coreItemChance = coreItemChance;
    }

    public double getLevelChance() {
        return levelChance;
    }

    public void setLevelChance(double levelChance) {
        this.levelChance = levelChance;
    }

    public double getDoubleRewardsChance() {
        return doubleRewardsChance;
    }

    public void setDoubleRewardsChance(double doubleRewardsChance) {
        this.doubleRewardsChance = doubleRewardsChance;
    }

    public String toString() {
        return "CoreBox{" +
                "dateReceived=" + received +
                ", tier=\"" + tier.getName() + "\"" +
                ", rankChance=" + rankChance +
                ", coreItemChance=" + coreItemChance +
                ", levelChance=" + levelChance +
                ", doubleRewardsChance=" + doubleRewardsChance +
                '}';
    }
}
