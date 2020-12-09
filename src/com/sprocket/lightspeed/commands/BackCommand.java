package com.sprocket.lightspeed.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BackCommand implements CommandExecutor {
    static HashMap<Player, Location> previousPositions = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){ return true;}
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("back")){
            Location locationToTeleport = previousPositions.get(player);
            player.teleport(locationToTeleport);
            previousPositions.remove(player);
        }
        return true;
    }

    public static void writePreviousPositions(Player player, Location playerLocation){
        previousPositions.put(player, playerLocation);
    }
}
