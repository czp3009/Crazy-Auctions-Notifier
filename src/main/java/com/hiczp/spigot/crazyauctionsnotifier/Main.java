package com.hiczp.spigot.crazyauctionsnotifier;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        //noinspection SpellCheckingInspection,ConstantConditions
        getCommand("crazyauctionsnotifier").setExecutor(new NotifierCommand(this));

        if (getConfig().getBoolean("onSell.enable")) {
            getServer().getPluginManager().registerEvents(new AuctionListListener(this), this);
        }
        if (getConfig().getBoolean("onBuy.enable")) {
            getServer().getPluginManager().registerEvents(new AuctionBuyListener(this), this);
        }
    }
}
