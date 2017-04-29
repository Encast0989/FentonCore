package net.fenton.core.store;

/**
 * Created by Encast (2017-02-06 6:04 PM)
 */
public enum StoreItem {

    RANK_VIP("RANK", "Rank: VIP"),
    RANK_FEN("RANK", "Rank: Fen"),
    RANK_FEN_PLUS("RANK", "Rank: Fen+"),
    CORE_BOX("CB", "Core Box");

    private String type;
    private String name;

    StoreItem(String type, String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
