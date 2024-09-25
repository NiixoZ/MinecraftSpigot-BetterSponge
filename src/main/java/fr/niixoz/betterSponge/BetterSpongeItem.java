package fr.niixoz.betterSponge;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BetterSpongeItem {

    public static ItemStack getItem(boolean isWet) {
        ItemStack betterSponge = new ItemStack(isWet ? Material.WET_SPONGE : Material.SPONGE, 1);
        ItemMeta meta = betterSponge.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + (isWet ? "Wet Better Sponge" : "Better Sponge"));
        meta.setLore(List.of(
                ChatColor.GRAY + "Une Superbe éponge qui permet d'absorber beaucoup",
                ChatColor.GRAY + "plus d'eau qu'une simple éponge.",
                ChatColor.GRAY + "",
                ChatColor.GRAY + "Fait gaffe à toi Bob l'Éponge !"
        ));
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.setCustomModelData(BetterSponge.BETTER_SPONGE_MODEL_DATA);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        betterSponge.setItemMeta(meta);
        return betterSponge;
    }

}
