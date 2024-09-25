package fr.niixoz.betterSponge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomRecipes {

    public static void registerCustomRecipes() {

        ItemStack betterSponge = BetterSpongeItem.getItem(false);
        NamespacedKey namespacedKey = new NamespacedKey(BetterSponge.getInstance(), "better_sponge");
        ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, betterSponge);
        recipe.addIngredient(Material.SPONGE);
        recipe.addIngredient(Material.NAUTILUS_SHELL);
        Bukkit.addRecipe(recipe);
    }

}
