package net.fenton.core.player;

import net.fenton.core.FentonDBIdentifier;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Encast (2016-12-10 2:03 PM)
 *
 */
public class FentonSettings {

    private FentonDisguise disguise;
    private boolean detailedInventory;
    private boolean flying;

    public FentonSettings(FentonDisguise disguise, boolean detailedInventory, boolean flying) {
        this.disguise = disguise;
        this.detailedInventory = detailedInventory;
        this.flying = flying;
    }

    public FentonDisguise getDisguise() {
        return disguise;
    }

    public boolean isDetailedInventory() {
        return detailedInventory;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public String toJSON() {
        JSONObject o = new JSONObject();
        Map<String, Object> d = new HashMap<String, Object>();
        d.put(FentonDBIdentifier.getInstance().getSettingsDisguiseVanished(), getDisguise().isVanished());
        d.put(FentonDBIdentifier.getInstance().getSettingsDisguiseNicked(), getDisguise().isNicked());
        d.put(FentonDBIdentifier.getInstance().getSettingsDisguiseNickname(), getDisguise().getNickname());
        o.put(FentonDBIdentifier.getInstance().getSettingsDisguise(), d);
        o.put(FentonDBIdentifier.getInstance().getSettingsDetailedInventory(), isDetailedInventory());
        o.put(FentonDBIdentifier.getInstance().getSettingsFlying(), flying);
        return o.toJSONString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> settings = new HashMap<String, Object>();
        settings.put(FentonDBIdentifier.getInstance().getSettingsDetailedInventory(), detailedInventory);
        settings.put(FentonDBIdentifier.getInstance().getSettingsFlying(), flying);
        return settings;
    }
}
