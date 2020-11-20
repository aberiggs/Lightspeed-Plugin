package com.sprocket.lightspeed.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class BombCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //TODO: Go through each command and put a warning message for when a command to be used by players is in console
        if(!(sender instanceof Player)){ return true;}

        Player player = (Player) sender;

        //TODO: Why can't I use "==" over equalsIgnoreCase
        if (command.getName().equalsIgnoreCase("bomb")){
            if (args.length >= 1){
                //TODO: Have a try catch for if the player listed doesn't exist on the server.
                Player target = Bukkit.getPlayer(args[0]);
                //TODO: why .class?
                for (int i =0; i <= 1000; i++ ) {
                    Entity bomb = target.getWorld().spawn(target.getLocation(), TNTPrimed.class);
                    ((TNTPrimed) bomb).setFuseTicks(5);
                }
                target.sendMessage(ChatColor.RED + "YOU'VE BEEN BLOWN UP");
            }
        }
        return true;
    }

}
