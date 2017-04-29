package net.fenton.core.player;

import net.fenton.core.item.pack.PlayerPack;
import net.fenton.core.player.rank.FentonRank;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 *
 * Created by Encast (2016-12-10 1:58 PM)
 *
 */
public class FentonPlayer {

    private UUID uuid;
    private FentonRank rank;
    private FentonRank secondary_rank;
    private FentonSettings settings;
    private FentonAchievement achievements;
    private FentonCoreBox coreboxes;
    private FentonItems gadgets;
    private FentonPlayerPack packs;
    private PlayerVisibility visibility;
    private int multiplier;
    private int sacredCrystals;

    public FentonPlayer(UUID uuid, FentonRank rank, FentonRank secondary_rank, FentonSettings settings, FentonAchievement achievements, FentonCoreBox coreboxes, FentonItems gadgets, FentonPlayerPack packs, PlayerVisibility visibility, int multiplier, int sacredCrystals) {
        this.uuid = uuid;
        this.rank = rank;
        this.secondary_rank = secondary_rank;
        this.settings = settings;
        this.achievements = achievements;
        this.gadgets = gadgets;
        this.coreboxes = coreboxes;
        this.packs = packs;
        this.visibility = visibility;
        this.multiplier = multiplier;
        this.sacredCrystals = sacredCrystals;
    }

    public UUID getUUID() {
        return uuid;
    }

    public FentonRank getMainRank() {
        return rank;
    }

    public void setMainRank(FentonRank rank) {
        this.rank = rank;
    }

    public FentonRank getSecondaryRank() {
        return secondary_rank;
    }

    public void setSecondaryRank(FentonRank secondary_rank) {
        this.secondary_rank = secondary_rank;
    }

    public FentonRank getOverridingRank() {
        if(secondary_rank == FentonRank.NONE) {
            return rank;
        }
        return secondary_rank;
    }

    public FentonRank getRank() {
        if(getSettings().getDisguise().isNicked()) {
            return FentonRank.VIP;
        } else {
            if(secondary_rank == FentonRank.NONE) {
                return rank;
            } else {
                return secondary_rank;
            }
        }
    }

    public FentonSettings getSettings() {
        return settings;
    }

    public FentonAchievement getAchievements() {
        return achievements;
    }

    public FentonItems getGadgets() {
        return gadgets;
    }

    public FentonCoreBox getCoreBoxes() {
        return coreboxes;
    }

    public FentonPlayerPack getPacks() {
        return packs;
    }

    public PlayerVisibility getPlayerVisibility() {
        return visibility;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getSacredCrystals() {
        return sacredCrystals;
    }

    public void addSacredCrystals(int sacredCrystals) {
        this.sacredCrystals += sacredCrystals;
    }

    public void removeSacredCrystals(int sacredCrystals) {
        this.sacredCrystals -= sacredCrystals;
    }

    public String getName() {
        if(getSettings().getDisguise().isNicked()) {
            return getSettings().getDisguise().getNickname();
        } else {
            return Bukkit.getServer().getPlayer(uuid).getName();
        }
    }

    public String getActualPrefixedName() {
        return (getOverridingRank().getPrefix() + getSettings().getDisguise().getOriginalName()
                .replaceAll("\\+", getPacks().getCurrentPack().getColour() + "+"));
    }

    public String getPrefixedName() {
        if(getSettings().getDisguise().isNicked()) {
            return (FentonRank.VIP.getPrefix() + getSettings().getDisguise().getNickname());
        } else {
            return (getOverridingRank().getPrefix() + getSettings().getDisguise().getOriginalName()
                    .replaceAll("\\+", getPacks().getCurrentPack().getColour() + "+"));
        }
    }

    public String getChatFormattedName() {
        String name = getPrefixedName()
                .replaceAll("\\+", getPacks().getCurrentPack().getColour() + "+");
        name += getPacks().getCurrentPack().getColour() + ":§f";
        return name;
    }

    public String getPlayerListName() {
        String name = getPrefixedName()
                .replaceAll("\\+", getPacks().getCurrentPack().getColour() + "+");
        if(getPacks().getCurrentPack() != PlayerPack.DEFAULT) {
            name += " " + getPacks().getCurrentPack().getColour() + "§l•";
        }
        return name;
    }
}
