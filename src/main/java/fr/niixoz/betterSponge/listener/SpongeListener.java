package fr.niixoz.betterSponge.listener;

import com.jeff_media.customblockdata.CustomBlockData;
import fr.niixoz.betterSponge.BetterSponge;
import fr.niixoz.betterSponge.BetterSpongeItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SpongeAbsorbEvent;

import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpongeListener implements Listener {

    private NamespacedKey betterSpongeKey = new NamespacedKey(BetterSponge.getInstance(), "is_better_sponge");

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerPlaceSponge(SpongeAbsorbEvent event) {
        Block block = event.getBlock();
        final PersistentDataContainer customBlockData = new CustomBlockData(block, BetterSponge.getInstance());
        if(!customBlockData.has(betterSpongeKey, PersistentDataType.BOOLEAN)) {
            return;
        }
        event.setCancelled(true);
        Location loc = event.getBlock().getLocation();
        // Get block in radius of 5
        int radius = 8;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Location checkLoc = loc.clone().add(x, y, z);
                    if (checkLoc.getBlock().getType() == Material.WATER && checkLoc.distance(event.getBlock().getLocation()) < radius) {
                        checkLoc.getBlock().setType(Material.AIR);
                    }
                    else if (checkLoc.getBlock().getType() == Material.KELP_PLANT || checkLoc.getBlock().getType() == Material.KELP) {
                        checkLoc.getWorld().dropItemNaturally(checkLoc, new ItemStack(Material.KELP));
                        checkLoc.getBlock().setType(Material.AIR);
                    }
                    else if(checkLoc.getBlock().getType() == Material.SEAGRASS || checkLoc.getBlock().getType() == Material.TALL_SEAGRASS) {
                        checkLoc.getBlock().setType(Material.AIR);
                    }
                    else if(checkLoc.getBlock().getBlockData() instanceof Waterlogged) {
                        Waterlogged blockData = ((Waterlogged) checkLoc.getBlock().getBlockData());
                        blockData.setWaterlogged(false);
                        checkLoc.getBlock().setBlockData(blockData);
                    }
                }
            }
        }
        event.getBlock().setType(Material.WET_SPONGE);
        customBlockData.set(betterSpongeKey, PersistentDataType.BOOLEAN, true);
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void playerPlaceSpongeEvent(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if(item.getItemMeta() != null && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == BetterSponge.BETTER_SPONGE_MODEL_DATA) {
            Block block = event.getBlock();
            final PersistentDataContainer customBlockData = new CustomBlockData(block, BetterSponge.getInstance());
            customBlockData.set(betterSpongeKey, PersistentDataType.BOOLEAN, true);
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void playerBreakSpongeEvent(BlockBreakEvent event) {
        Block block = event.getBlock();
        final PersistentDataContainer customBlockData = new CustomBlockData(block, BetterSponge.getInstance());
        if(!customBlockData.has(betterSpongeKey)) {
            return;
        }
        if(event.getBlock().getType() != Material.WET_SPONGE && event.getBlock().getType() != Material.SPONGE) {
            return;
        }
        if(event.getPlayer().getGameMode().equals(org.bukkit.GameMode.CREATIVE)) {
            return;
        }
        event.setDropItems(false);
        ItemStack item = BetterSpongeItem.getItem(event.getBlock().getType() == Material.WET_SPONGE);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
        customBlockData.remove(betterSpongeKey);
    }
}
