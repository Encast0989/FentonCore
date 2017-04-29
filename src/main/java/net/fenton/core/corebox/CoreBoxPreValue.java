package net.fenton.core.corebox;

/**
 *
 * Created by Encast (2016-12-10 4:46 PM)
 *
 */
public class CoreBoxPreValue {

    private double rankChance;
    private double coreItemChance;
    private double levelChance;
    private double doubleRewardsChance;

    public CoreBoxPreValue(double rankChance, double coreItemChance, double levelChance, double doubleRewardsChance) {
        this.rankChance = rankChance;
        this.coreItemChance = coreItemChance;
        this.levelChance = levelChance;
        this.doubleRewardsChance = doubleRewardsChance;
    }

    public double getRankChance() {
        return rankChance;
    }

    public double getCoreItemChance() {
        return coreItemChance;
    }

    public double getLevelChance() {
        return levelChance;
    }

    public double getDoubleRewardsChance() {
        return doubleRewardsChance;
    }
}
