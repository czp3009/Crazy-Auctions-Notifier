package com.hiczp.spigot.crazyauctionsnotifier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class NotifierCommand implements TabExecutor {
    private JavaPlugin plugin;

    public NotifierCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("reload");
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) {
            //noinspection SpellCheckingInspection
            sender.sendMessage("/crazyauctionsnotifier reload - Reload config file");
            return true;
        }
        plugin.reloadConfig();
        sender.sendMessage(plugin.getName() + " Reloaded");
        return true;
    }
}
