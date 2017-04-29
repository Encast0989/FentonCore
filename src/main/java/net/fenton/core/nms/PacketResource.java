package net.fenton.core.nms;

import com.mojang.authlib.GameProfile;
import net.fenton.core.Core;
import net.fenton.core.player.FentonPlayer;
import net.fenton.core.player.FentonPlayerHandler;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 *
 * Created by Encast (2016-12-17 9:49 PM)
 *
 */
public class PacketResource {

    private static PacketResource instance;
    public static PacketResource getPackets() {
        return instance;
    }

    public PacketResource() {
        instance = this;
    }

    public void sendTitle(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        try {
            // TITLE
            Class<?> titleClass = Reflection.getInstance().getNMSClass("PacketPlayOutTitle");
            Constructor<?> titleConstructor = titleClass.getConstructor(
                    Reflection.getInstance().getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                    Reflection.getInstance().getNMSClass("IChatBaseComponent"),
                    int.class,
                    int.class,
                    int.class);
            Object enumTitle = Reflection.getInstance().getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getEnumConstants()[0];
            Object titleMethod = Reflection.getInstance().getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
                    .getMethod("a", String.class).invoke(null, "{text:\"" + title + "\"}");

            Object titlePacket = titleConstructor.newInstance(enumTitle, titleMethod, fadeIn, stay, fadeOut);

            // SUBTITLE
            Class<?> subTitleClass = Reflection.getInstance().getNMSClass("PacketPlayOutTitle");
            Constructor<?> subTitleConstructor = subTitleClass.getConstructor(
                    Reflection.getInstance().getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                    Reflection.getInstance().getNMSClass("IChatBaseComponent"),
                    int.class,
                    int.class,
                    int.class);
            Object enumSubTitle = Reflection.getInstance().getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getEnumConstants()[1];
            Object subTitleMethod = Reflection.getInstance().getNMSClass("IChatBaseComponent")
                    .getMethod("a", String.class).invoke(null, "{text:\"" + subTitle + "\")");
            Object subTitlePacket = subTitleConstructor.newInstance(enumSubTitle, subTitleMethod, fadeIn, stay, fadeOut);

            // Sending packets
            Reflection.getInstance().sendPacket(p, titlePacket);
            Reflection.getInstance().sendPacket(p, subTitlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTabHeaderFooter(final Player p, final String header, final String footer) {
            new BukkitRunnable() {
                public void run() {
                    try {
                        Class<?> playerListClass = Reflection.getInstance().getNMSClass("PacketPlayOutPlayerListHeaderFooter");
                        Constructor<?> playerListConstructor = playerListClass.getConstructor(
                                Reflection.getInstance().getNMSClass("IChatBaseComponent"));

                        Object headerMethod = Reflection.getInstance().getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
                                .getMethod("a", String.class).invoke(null, "{text:\"" + header + "\"}");
                        Object footerMethod = Reflection.getInstance().getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
                                .getMethod("a", String.class).invoke(null, "{text:\"" + footer + "\"}");

                        Object packet = playerListConstructor.newInstance(headerMethod);
                        Field b = playerListClass.getDeclaredField("b");
                        b.setAccessible(true);
                        b.set(packet, footerMethod);
                        Reflection.getInstance().sendPacket(p, packet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskLater(Core.getInstance(), 20);
    }

    public void sendNameAndSkinChangePacket(Player p, String name) {
        FentonPlayer fp = FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId());
        boolean changedVanish = false, reload = false;
        if(fp.getSettings().getDisguise().isVanished()) {
            fp.getSettings().getDisguise().setVanished(false);
            changedVanish = true;
            p.sendMessage("§3You are no longer vanished.");
        }
        //TODO Reflection
        try {
            EntityHuman eh = ((CraftPlayer) p).getHandle();

            PacketPlayOutEntityDestroy p29 = new PacketPlayOutEntityDestroy(p.getEntityId());
            PacketPlayOutNamedEntitySpawn p20 = new PacketPlayOutNamedEntitySpawn(eh);

            Field profileField = eh.getClass().getSuperclass().getDeclaredField("bH");

            profileField.setAccessible(true);

            profileField.set(eh, new GameProfile(p.getUniqueId(), name));

            reload = true;
            for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                if(online != p) {
                    online.hidePlayer(p);
                    online.showPlayer(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            p.sendMessage("§cSomething happened and we couldn't send you the new name or skin.");
            if(changedVanish && !reload) {
                fp.getSettings().getDisguise().setVanished(true);
                p.sendMessage("§aVanish has been enabled again due to this.");
            }
        }
    }

    public void sendActionBarPacket(Player p, String text) {
        try {
            Class<?> chatClass = Reflection.getInstance().getNMSClass("PacketPlayOutChat");
            Object a = Reflection.getInstance().getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
                    .getMethod("a", String.class).invoke(null, "{text:\"" + text + "\"}");
            Constructor<?> chatConstructor = chatClass.getConstructor(Reflection.getInstance().getNMSClass("IChatBaseComponent"),
                    byte.class);
            Object packet = chatConstructor.newInstance(a, (byte) 2);
            Reflection.getInstance().sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRespawnPacket(final Player p) {
        new BukkitRunnable() {
            public void run() {
                try {
                    Class<?> clientCommandRespawn = Reflection.getInstance().getNMSClass("PacketPlayInClientCommand");
                    Constructor<?> respawnConstructor = clientCommandRespawn.getConstructor(
                            clientCommandRespawn.getDeclaredClasses()[0]);
                    Object packet = respawnConstructor.newInstance(clientCommandRespawn.getDeclaredClasses()[0].getEnumConstants()[0]);
                    Reflection.getInstance().sendClientCommandPacket(p, packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(Core.getInstance(), 10);
    }

    public void addTemp(final Player p) {
        new BukkitRunnable() {
            public void run() {
                try {
                    PacketPlayOutScoreboardTeam p1 = new PacketPlayOutScoreboardTeam();
                    Field c = p1.getClass().getDeclaredField("c");
                    c.setAccessible(true);
                    c.set(p1, "§a");
                    Field d = p1.getClass().getDeclaredField("d");
                    d.setAccessible(true);
                    d.set(p1, "s ");
                    Field g = p1.getClass().getDeclaredField("g");
                    g.setAccessible(true);
                    ((Collection) g.get(p1)).add(p.getName());
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(p1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(Core.getInstance(), 40);
    }
}
