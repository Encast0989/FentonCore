package net.fenton.core.event;

import net.fenton.core.nms.PacketResource;
import net.fenton.core.player.FentonPlayerHandler;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * Created by Encast (2016-12-11 3:02 PM)
 *
 */
public class MiscListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onEntityDamageTake(EntityDamageEvent e) {
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onVanishDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(FentonPlayerHandler.getInstance().getPlayer(p.getUniqueId()).getSettings().getDisguise().isVanished()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onWeatherChange(WeatherChangeEvent e) {
        if(e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onItemDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getPlayer() != null) {
            Player p = e.getPlayer();
            if(p.getGameMode() == GameMode.CREATIVE && p.getInventory().contains(Material.EYE_OF_ENDER)) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.CREATIVE && p.getInventory().contains(Material.EYE_OF_ENDER)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent e) {
        PacketResource.getPackets().sendRespawnPacket(e.getEntity());
    }
}
