package net.fenton.core.player;

import net.fenton.core.corebox.CoreBox;
import net.fenton.core.FentonDBIdentifier;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Encast (2016-12-10 4:56 PM)
 *
 */
public class FentonCoreBox {

    private List<CoreBox> boxes;

    public FentonCoreBox(List<CoreBox> boxes) {
        this.boxes = boxes;
    }

    public List<CoreBox> getCoreBoxes() {
        return boxes;
    }

    public String toJSON() {
        if(getCoreBoxes().size() > 0) {
            JSONObject o = new JSONObject();
            int index = 1;
            for(CoreBox cb : boxes) {
                Map<String, Object> stats = new HashMap<String, Object>();
                stats.put(FentonDBIdentifier.getInstance().getCoreBoxReceived(), cb.getReceived());
                stats.put(FentonDBIdentifier.getInstance().getCoreBoxTier(), cb.getTier().getName());
                stats.put(FentonDBIdentifier.getInstance().getCoreBoxRankChance(), cb.getRankChance());
                stats.put(FentonDBIdentifier.getInstance().getCoreBoxCoreItemChance(), cb.getCoreItemChance());
                stats.put(FentonDBIdentifier.getInstance().getCoreBoxLevelChance(), cb.getLevelChance());
                stats.put(FentonDBIdentifier.getInstance().getCoreBoxDoubleRewardsChance(), cb.getDoubleRewardsChance());
                o.put(index, stats);
                index++;
            }
            return o.toJSONString();
        } else {
            return "none";
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> list = new HashMap<String, Object>();
        for(CoreBox cb : boxes) {
            Map<String, Object> stats = new HashMap<String, Object>();
            stats.put(FentonDBIdentifier.getInstance().getCoreBoxReceived(), cb.getReceived());
            stats.put(FentonDBIdentifier.getInstance().getCoreBoxTier(), cb.getTier().getName());
            stats.put(FentonDBIdentifier.getInstance().getCoreBoxRankChance(), cb.getRankChance());
            stats.put(FentonDBIdentifier.getInstance().getCoreBoxCoreItemChance(), cb.getCoreItemChance());
            stats.put(FentonDBIdentifier.getInstance().getCoreBoxLevelChance(), cb.getLevelChance());
            stats.put(FentonDBIdentifier.getInstance().getCoreBoxDoubleRewardsChance(), cb.getDoubleRewardsChance());
            list.put(String.valueOf(cb.getReceived()), stats);
        }
        return list;
    }
}
