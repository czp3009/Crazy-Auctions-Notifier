package com.hiczp.spigot.crazyauctionsnotifier;

import me.badbones69.crazyauctions.api.events.AuctionBuyEvent;
import me.badbones69.crazyauctions.api.events.AuctionListEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Listener listListener;
    private Listener buyListener;

    @SuppressWarnings("ConstantConditions")
    private void start() {
        String currencyName = getServer().getServicesManager().getRegistration(Economy.class).getProvider().currencyNamePlural();

        ConfigurationSection onSell = getConfig().getConfigurationSection("onSell");
        if (onSell.getBoolean("enable")) {
            listListener = new AuctionListListener(this, onSell.getString("format"), currencyName);
            getServer().getPluginManager().registerEvents(listListener, this);
        }
        ConfigurationSection onBuy = getConfig().getConfigurationSection("onBuy");
        if (onBuy.getBoolean("enable")) {
            buyListener = new AuctionBuyListener(this, onBuy.getString("format"), currencyName);
            getServer().getPluginManager().registerEvents(buyListener, this);
        }
    }

    private void stop() {
        if (listListener != null) AuctionListEvent.getHandlerList().unregister(listListener);
        if (buyListener != null) AuctionBuyEvent.getHandlerList().unregister(buyListener);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        //noinspection SpellCheckingInspection,ConstantConditions
        getCommand("crazyauctionsnotifier").setExecutor(new NotifierCommand(this));
        start();
    }

    protected void reload() {
        reloadConfig();
        stop();
        start();
    }
}
