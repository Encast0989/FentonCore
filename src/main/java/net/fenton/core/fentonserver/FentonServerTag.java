package net.fenton.core.fentonserver;

/**
 *
 * Created by Encast (2016-12-10 1:44 PM)
 *
 */
public enum FentonServerTag {

    NONE(" "),
    ERROR("§c§lERROR"),
    SPECIAL("§6§lSPECIAL §6(Limited Time!)"),
    NEW("§6§lNEW"),
    UPDATED("§5§lUPDATED"),
    ALPHA("§5§lALPHA"),
    BETA("§3§lBETA"),
    COMING_SOON("§c§lCOMING SOON");

    private String name;

    // FentonServerTag is a suffix for fentonserver names.
    FentonServerTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
