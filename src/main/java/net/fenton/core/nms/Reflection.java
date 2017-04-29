package net.fenton.core.nms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * Created by Encast (2016-12-10 2:49 PM)
 *
 */
public class Reflection {

    private static Reflection instance;
    public static Reflection getInstance() {
        return instance;
    }

    public Reflection() {
        instance = this;
    }

    public Class<?> getNMSClass(String className) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<?> getCraftBukkitClass(String className) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("org.bukkit.craftbukkit." + version + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPacket(Player p, Object packet) {
        try {
            Object handle = p.getClass().getMethod("getHandle").invoke(p);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendClientCommandPacket(Player p, Object packet) {
        try {
            Object handle = p.getClass().getMethod("getHandle").invoke(p);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("a", getNMSClass("PacketPlayInClientCommand")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
