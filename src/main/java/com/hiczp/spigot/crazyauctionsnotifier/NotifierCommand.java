package com.hiczp.spigot.crazyauctionsnotifier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.List;

public class NotifierCommand implements TabExecutor {
    private Main plugin;

    public NotifierCommand(Main plugin) {
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
            sender.sendMessage(String.format("/%s reload - Reload config file", label));
            return true;
        }
        plugin.reload();
        sender.sendMessage(plugin.getName() + " Reloaded");
        return true;
    }
}
