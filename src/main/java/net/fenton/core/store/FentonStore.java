package net.fenton.core.store;

import net.fenton.core.Core;
import net.fenton.core.player.rank.FentonRank;
import org.bson.Document;

import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Updates.*;

/**
 * Created by Encast (2017-02-06 6:01 PM)
 */
public class FentonStore {

    private static FentonStore instance;
    public static FentonStore getInstance() {
        return instance;
    }

    public FentonStore() {
        instance = this;
    }

    public void giveItem(String playerName, StoreItem item) {
        switch(item) {
            case RANK_VIP:
            case RANK_FEN:
            case RANK_FEN_PLUS:
                giveRank(playerName, item);
                break;
            case CORE_BOX:
                break;
            default:
                break;
        }
    }

    public void giveRank(String playerName, StoreItem item) {
        if(item.getType().equalsIgnoreCase("RANK")) {
            Document player = Core.getInstance().getMainMongoInstance().getPlayerCollection()
                    .find(regex("name", Pattern.compile(playerName, Pattern.CASE_INSENSITIVE))).first();
            if(player != null) {
                if(item == StoreItem.RANK_VIP) {
                    Core.getInstance().getMainMongoInstance().getPlayerCollection()
                            .updateOne(eq("name", Pattern.compile(playerName, Pattern.CASE_INSENSITIVE)),
                                    set("rank", FentonRank.VIP.getDbName()));
                } else if(item == StoreItem.RANK_FEN) {
                    Core.getInstance().getMainMongoInstance().getPlayerCollection()
                            .updateOne(eq("name", Pattern.compile(playerName, Pattern.CASE_INSENSITIVE)),
                                    set("rank", FentonRank.FEN.getDbName()));
                } else if(item == StoreItem.RANK_FEN_PLUS) {
                    Core.getInstance().getMainMongoInstance().getPlayerCollection()
                            .updateOne(eq("name", Pattern.compile(playerName, Pattern.CASE_INSENSITIVE)),
                                    set("rank", FentonRank.FEN_PLUS.getDbName()));
                } else {
                    //TODO error
                }
            }
        } else if(item.getType().equalsIgnoreCase("CB")) {

        } else {
            //TODO error
        }
    }
}
