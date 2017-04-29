package net.fenton.core.item;

import net.fenton.core.item.gadget.AtomicBombGadget;
import net.fenton.core.item.gadget.KaboomGadget;
import net.fenton.core.item.gadget.SpeedRunGadget;
import net.fenton.core.item.gadget.TeleportGadget;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Encast (2017-02-18 10:17 PM)
 */
public class GadgetHandler {

    private static GadgetHandler instance = new GadgetHandler();

    public static GadgetHandler getGadgets() {
        return instance;
    }

    private List<FentonGadget> gadgets;
    private Map<String, GadgetTimer> gadgetPlayers;

    private GadgetHandler() {
        setupGadgets();
        this.gadgetPlayers = new HashMap<String, GadgetTimer>();
    }

    private void setupGadgets() {
        gadgets = new ArrayList<FentonGadget>();
        gadgets.add(new AtomicBombGadget());
        gadgets.add(new KaboomGadget());
        gadgets.add(new SpeedRunGadget());
        gadgets.add(new TeleportGadget());
    }

    public List<FentonGadget> getGadgetsList() {
        return gadgets;
    }

    public Map<String, GadgetTimer> getGadgetPlayers() {
        return gadgetPlayers;
    }

    public void addPlayer(Player p, int seconds) {
        this.gadgetPlayers.put(p.getName(), new GadgetTimer(p.getName(), seconds));
    }

    public void removePlayer(String name) {
        this.gadgetPlayers.remove(name);
    }
}
