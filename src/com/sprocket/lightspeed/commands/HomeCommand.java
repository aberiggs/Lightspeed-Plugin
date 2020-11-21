package com.sprocket.lightspeed.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)){return true;}
        else if (command.getName().equalsIgnoreCase("home")){
            try {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.RED + "Teleporting to your home!");
                String filename = "plugins/Lightspeed/homes.json";
                String homeName = args[0];


                //TODO: Confine this to its own function
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(filename);
                JSONObject homeList = (JSONObject) parser.parse(reader);
                //TODO: Check if the homeName doesn't exist within the file.
                JSONObject homeInfo = (JSONObject) homeList.get(homeName);

                double xVal = (double) homeInfo.get("x");
                double yVal = (double) homeInfo.get("y");
                double zVal = (double) homeInfo.get("z");
                double pitchValD = (double) homeInfo.get("pitch");
                double yawValD = (double) homeInfo.get("yaw");
                String worldName = (String) homeInfo.get("world");
                World homeWorld = Bukkit.getWorld(worldName);

                //TODO: Make something so I don't have to create a new variable for these floats
                float yawVal = (float) yawValD;
                float pitchVal = (float) pitchValD;

                //TODO: Consider moving some of this stuff out of the try-catch if possible?
                Location homeLocation = new Location(homeWorld, xVal, yVal, zVal, yawVal , pitchVal);
                player.teleport(homeLocation);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
