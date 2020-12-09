package com.sprocket.lightspeed.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

    public class TeleportCommand implements CommandExecutor {

        HashMap<Player, Player> tpRequests = new HashMap<>();

        public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) { return true;}
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("tpa")){
                //TODO: Check to make sure that an argument is provided by the player, and that if its not a player that they are warned.
                Player target = Bukkit.getPlayer(args[0]);
                tpRequests.put(target, player);
                target.sendMessage(ChatColor.GOLD + player.getName() + " wants to teleport to you! /tpaccept to accept the tp request!");
            }
            else if(command.getName().equalsIgnoreCase("tpaccept")){
                Player targetToTeleport = tpRequests.get(player);
                targetToTeleport.sendMessage(ChatColor.GREEN + "Teleporting you to " + player.getName());

                //Before teleporting the player, set their position in BackCommand.
                BackCommand.writePreviousPositions(targetToTeleport, targetToTeleport.getLocation());

                targetToTeleport.teleport(player);
            }
            return true;

        }

}
