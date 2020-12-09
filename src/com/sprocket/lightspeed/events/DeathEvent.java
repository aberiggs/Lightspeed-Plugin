package com.sprocket.lightspeed.events;

import com.sprocket.lightspeed.commands.BackCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class DeathEvent implements Listener {

    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        BackCommand.writePreviousPositions(player, player.getLocation());
    }
}
