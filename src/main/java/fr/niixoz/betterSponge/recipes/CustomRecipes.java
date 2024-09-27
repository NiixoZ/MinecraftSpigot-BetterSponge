package fr.niixoz.betterSponge.recipes;

import fr.niixoz.betterSponge.BetterSponge;
import fr.niixoz.betterSponge.BetterSpongeItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

public class CustomRecipes {

    public static void registerCustomRecipes() {

        registerWorkbenchRecipes();
        registerFurnaceRecipes();

    }

    public static void registerWorkbenchRecipes() {
        NamespacedKey namespacedKey = new NamespacedKey(BetterSponge.getInstance(), "better_sponge_workbench");
        ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, BetterSpongeItem.getItem(false));
        recipe.addIngredient(Material.SPONGE);
        recipe.addIngredient(Material.NAUTILUS_SHELL);
        Bukkit.addRecipe(recipe);

        NamespacedKey namespacedKeyWS = new NamespacedKey(BetterSponge.getInstance(), "wet_better_sponge_workbench");
        ShapelessRecipe recipeWS = new ShapelessRecipe(namespacedKeyWS, BetterSpongeItem.getItem(true));
        recipeWS.addIngredient(Material.WET_SPONGE);
        recipeWS.addIngredient(Material.NAUTILUS_SHELL);
        Bukkit.addRecipe(recipeWS);
    }

    public static void registerFurnaceRecipes() {
        // Remove and recreate sponge furnace recipes
        Bukkit.removeRecipe(NamespacedKey.fromString("minecraft:sponge"));
        FurnaceRecipe recipeSonge = new FurnaceRecipe(
                NamespacedKey.fromString("minecraft:sponge"),
                new ItemStack(Material.SPONGE), // result
                new RecipeChoice.ExactChoice(new ItemStack(Material.WET_SPONGE)), // input
                0.15f,
                200
        );
        Bukkit.addRecipe(recipeSonge);

        FurnaceRecipe recipeBS = new FurnaceRecipe(
                new NamespacedKey(BetterSponge.getInstance(), "wet_better_sponge_furnace"),
                BetterSpongeItem.getItem(false), // result
                new RecipeChoice.ExactChoice(BetterSpongeItem.getItem(true)), // input
                1.5f,
                200
        );
        Bukkit.addRecipe(recipeBS);
    }

}
