package com.hiczp.spigot.crazyauctionsnotifier;

import me.badbones69.crazyauctions.api.events.AuctionBuyEvent;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang.text.StrSubstitutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class AuctionBuyListener implements Listener {
    private JavaPlugin plugin;

    public AuctionBuyListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("DuplicatedCode")
    @EventHandler
    public void onAuctionBuy(AuctionBuyEvent event) {
        Map<String, String> values = new HashMap<>();
        values.put("buyer", event.getPlayer().getName());
        values.put("itemName", event.getItem().getType().name());
        values.put("itemAmount", Integer.toString(event.getItem().getAmount()));
        values.put("price", Long.toString(event.getPrice()));
        //noinspection ConstantConditions
        values.put("currencyName",
                plugin.getServer().getServicesManager().getRegistration(Economy.class).getProvider().currencyNamePlural()
        );
        String message = StrSubstitutor.replace(plugin.getConfig().getString("onBuy.format"), values);
        plugin.getServer().broadcastMessage(message);
    }
}
