package fr.niixoz.betterSponge;

import fr.niixoz.betterSponge.listener.SpongeListener;
import fr.niixoz.betterSponge.recipes.CustomRecipes;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterSponge extends JavaPlugin {

    public static final int BETTER_SPONGE_MODEL_DATA = 9696000;
    private static BetterSponge instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new SpongeListener(), this);
        CustomRecipes.registerCustomRecipes();

    }

    @Override
    public void onDisable() {

    }

    public static BetterSponge getInstance() {
        return instance;
    }
}
