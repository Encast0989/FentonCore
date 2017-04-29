package net.fenton.core.player;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Encast (2016-12-10 1:56 PM)
 *
 */
public class FentonDisguise {

    private boolean vanished;
    private boolean nicked;
    private String originalName;
    private String nickname;

    public FentonDisguise(boolean vanished, boolean nicked, String originalName, String nickname) {
        this.vanished = vanished;
        this.nicked = nicked;
        this.nickname = nickname;
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished) {
        this.vanished = vanished;
    }

    public boolean isNicked() {
        return nicked;
    }

    public void setNicked(boolean nicked) {
        this.nicked = nicked;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> disguise = new HashMap<String, Object>();
        disguise.put("vanished", vanished);
        disguise.put("nicked", nicked);
        disguise.put("nickname", nickname);
        return disguise;
    }
}
