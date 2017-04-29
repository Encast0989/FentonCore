package net.fenton.core;

/**
 * Created by Encast (2017-01-02), All Rights Reserved.
 * <p>
 * Claiming this code as one's own is strictly forbidden.
 * <p>
 * Class created at: 9:22 PM
 */
public class FentonDBIdentifier {

    private static FentonDBIdentifier instance;
    public static FentonDBIdentifier getInstance() {
        return instance;
    }

    public FentonDBIdentifier() {
        instance = this;
    }

    public String getRank() {
        return "rank";
    }

    public String getSettingsDisguise() {
        return "disguise";
    }

    public String getSettingsDisguiseVanished() {
        return "vanished";
    }

    public String getSettingsDisguiseNicked() {
        return "nicked";
    }

    public String getSettingsDisguiseNickname() {
        return "nickname";
    }

    public String getSettingsDetailedInventory() {
        return "default_inventory";
    }

    public String getSettingsFlying() {
        return "fly";
    }

    public String getCoreBoxReceived() {
        return "received";
    }

    public String getCoreBoxTier() {
        return "tier";
    }

    public String getCoreBoxRankChance() {
        return "rank_chance";
    }

    public String getCoreBoxCoreItemChance() {
        return "core_item_chance";
    }

    public String getCoreBoxLevelChance() {
        return "level_chance";
    }

    public String getCoreBoxDoubleRewardsChance() {
        return "double_rewards_chance";
    }

    public String getPlayerVisibility() {
        return "visibility";
    }

    public String getMultiplier() {
        return "multiplier";
    }

    public String getGlobalCoins() {
        return "global_coins";
    }
}
