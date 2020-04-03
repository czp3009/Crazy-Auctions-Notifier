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
    private String format;
    private Economy economy;

    public AuctionBuyListener(JavaPlugin plugin, String format, Economy economy) {
        this.plugin = plugin;
        this.format = format;
        this.economy = economy;
    }

    @SuppressWarnings("DuplicatedCode")
    @EventHandler
    public void onAuctionBuy(AuctionBuyEvent event) {
        Map<String, String> values = new HashMap<>();
        values.put("buyer", event.getPlayer().getName());
        values.put("itemName", event.getItem().getType().name());
        values.put("itemAmount", Integer.toString(event.getItem().getAmount()));
        values.put("price", Long.toString(event.getPrice()));
        values.put("currencyName", economy.currencyNamePlural());
        String message = StrSubstitutor.replace(format, values);
        plugin.getServer().broadcastMessage(message);
    }
}
