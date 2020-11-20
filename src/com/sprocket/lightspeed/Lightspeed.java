package com.sprocket.lightspeed;

import com.sprocket.lightspeed.commands.BombCommand;
import com.sprocket.lightspeed.commands.HomeCommand;
import com.sprocket.lightspeed.commands.SetHomeCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Lightspeed extends JavaPlugin {
    @Override
    public void onEnable() {
        //TODO: Find this getCommand and getExecutor info in docs
        getCommand("bomb").setExecutor(new BombCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        //TODO: Find a different way to deal w/ console printing or figure out why I can't find this in docs
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[Lightspeed]: Plugin enabled!");
    }

    @Override
    public void onDisable() {
        //TODO: Find a different way to deal w/ console printing or figure out why I can't find this in docs
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Lightspeed]: Plugin disabled!");
    }
}
