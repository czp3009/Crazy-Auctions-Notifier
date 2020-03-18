package com.hiczp.spigot.crazyauctionsnotifier;

import me.badbones69.crazyauctions.api.events.AuctionBuyEvent;
import me.badbones69.crazyauctions.api.events.AuctionListEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    protected FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onAuctionList(AuctionListEvent event) {
        getServer().broadcastMessage(String.format(
                "%s sells %s x %d for $%d to auction house",
                event.getPlayer().getName(),
                event.getItem().getType().name(),
                event.getItem().getAmount(),
                event.getPrice()
        ));
    }

    @EventHandler
    public void onAuctionBuy(AuctionBuyEvent event) {

    }
}
