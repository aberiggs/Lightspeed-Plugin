package com.sprocket.lightspeed.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class HomeCommand implements CommandExecutor {
    public static void readHomeList(String filename, String homeName) throws Exception{
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(filename);
        JSONObject homeList = (JSONObject) parser.parse(reader);

        //TODO: Check if the homeName doesn't exist within the file.
        JSONObject homeInfo = (JSONObject) homeList.get(homeName);

        String coords = (String) homeInfo.get("Coords");

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Hey, here's the coords of your home: " + coords);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)){return true;}
        else if (command.getName().equalsIgnoreCase("home")){
            try {
                readHomeList("plugins/Lightspeed/homes.json", "OTHA HOME");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
