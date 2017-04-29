package net.fenton.core.inventory;

import net.fenton.core.item.menu.ItemsMenu;
import net.fenton.core.item.menu.PackMenu;
import net.fenton.core.sacred.SacredMenu;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Encast (2016-12-16 4:35 PM)
 *
 */
public class FentonInventoryHandler {

    private static FentonInventoryHandler instance;
    public static FentonInventoryHandler getInstance() {
        return instance;
    }

    private Map<String, FentonInventory> menus;

    public FentonInventoryHandler() {
        instance = this;
        setMenus();
    }

    private void setMenus() {
        menus = new HashMap<String, FentonInventory>();
        menus.put("Error", new ErrorMenu());
        menus.put("Game", new GameMenu());
        menus.put("Profile", new ProfileMenu());
        menus.put("PlayerVisibility", new PlayerVisibilityMenu());
        menus.put("Achievements", new AchievementMenu());
        menus.put("CoreBox", new CoreBoxMenu());
        menus.put("SacredMenu", new SacredMenu());
        menus.put("Items", new ItemsMenu());
        menus.put("Pack", new PackMenu());
    }

    public void registerMenu(String referName, FentonInventory i) {
        menus.put(referName, i);
    }

    public Map<String, FentonInventory> getAllMenus() {
        return menus;
    }

    public FentonInventory getMenuByID(String id) {
        if(menus.containsKey(id)) {
            return menus.get(id);
        }
        return menus.get("Error");
    }

    public String getStringIDValues() {
        String values = "";
        int current = 0;
        for(String s : menus.keySet()) {
            if(current == (menus.size() - 1)) {
                values += s;
            } else {
                values += s + ", ";
            }
            current++;
        }
        return values;
    }
}
