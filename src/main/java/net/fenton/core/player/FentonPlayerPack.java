package net.fenton.core.player;

import net.fenton.core.item.pack.PlayerPack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Encast (2017-02-24 3:35 PM)
 */
public class FentonPlayerPack {

    private PlayerPack currentPack;
    private Map<String, Object> packs;

    public FentonPlayerPack(PlayerPack currentPack, Map<String, Object> packs) {
        this.currentPack = currentPack;
        this.packs = packs;
    }

    public PlayerPack getCurrentPack() {
        return currentPack;
    }

    public void setCurrentPack(PlayerPack currentPack) {
        this.currentPack = currentPack;
    }

    public Map<String, Object> getPacks() {
        updatePacks();
        return packs;
    }

    public void setPack(PlayerPack pack, boolean earned) {
        updatePacks();
        packs.remove(pack.getDbName());
        packs.put(pack.getDbName(), earned);
    }

    public void updatePacks() {
        for(PlayerPack pack : PlayerPack.values()) {
            if(!packs.containsKey(pack.getDbName())) {
                packs.put(pack.getDbName(), false);
            }
        }
        packs.remove(PlayerPack.DEFAULT.getDbName());
        packs.put(PlayerPack.DEFAULT.getDbName(), true);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> packData = new HashMap<String, Object>();
        packData.put("current_pack", currentPack.getDbName());
        packData.put("packs", getPacks());
        return packData;
    }
}
