package net.fenton.core.enhancedbukkit;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Encast (2016-12-10 1:38 PM)
 *
 */
public class FentonItemStack {

    private static FentonItemStack instance;
    public static FentonItemStack createStack() {
        return instance;
    }

    public FentonItemStack() {
        instance = this;
    }

    public ItemStack createItemStack(Material material, int amount, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta i = item.getItemMeta();
        i.setDisplayName(displayName);
        if(lore != null) {
            i.setLore(lore);
        }
        item.setItemMeta(i);
        return item;
    }

    public ItemStack createItemStack(Material material, int amount, short damage, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, damage);
        ItemMeta i = item.getItemMeta();
        i.setDisplayName(displayName);
        if(lore != null) {
            i.setLore(lore);
        }
        item.setItemMeta(i);
        return item;
    }

    public ItemStack createItemStack(Material material, int amount, String displayName, Map<Enchantment, Integer> enchants, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta i = item.getItemMeta();
        i.setDisplayName(displayName);
        for(Enchantment e : enchants.keySet()) {
            i.addEnchant(e, enchants.get(e), true);
        }
        if(lore != null) {
            i.setLore(lore);
        }
        item.setItemMeta(i);
        return item;
    }

    public ItemStack createPotion(int amount, short data, String displayName, List<PotionEffect> effects, List<String> lore) {
        ItemStack potion = new ItemStack(Material.POTION, amount, data);
        PotionMeta p = (PotionMeta) potion.getItemMeta();
        p.setDisplayName(displayName);
        for(PotionEffect effect : effects) {
            p.addCustomEffect(effect, true);
        }
        if(lore != null) {
            p.setLore(lore);
        }
        potion.setItemMeta(p);
        return potion;
    }

    public ItemStack createLeatherArmour(Material material, int amount, Color color, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setColor(color);
        if(lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createLeatherArmour(Material material, int amount, Color color, String displayName, List<String> lore,
                                         Map<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material, amount);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        for(Enchantment e : enchants.keySet()) {
            meta.addEnchant(e, enchants.get(e), true);
        }
        meta.setDisplayName(displayName);
        meta.setColor(color);
        if(lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createLeatherArmour(Material material, int amount, int red, int green, int blue, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setColor(Color.fromRGB(red, green, blue));
        if(lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createLeatherArmour(Material material, int amount, int red, int green, int blue, String displayName, List<String> lore,
                                         Map<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material, amount);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        for(Enchantment e : enchants.keySet()) {
            meta.addEnchant(e, enchants.get(e), true);
        }
        meta.setDisplayName(displayName);
        meta.setColor(Color.fromRGB(red, green, blue));
        if(lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    @Deprecated
    public void createSkull() {

    }

    @Deprecated
    public void createFirework() {
        Firework fw = (Firework) new ItemStack(Material.FIREWORK);
        FireworkMeta meta = fw.getFireworkMeta();
    }
}
