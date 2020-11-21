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
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Array;


public class SetHomeCommand implements CommandExecutor {
    public static float[] getInfoFromLocation(Location playerLocation){
        //TODO: Can I turn it into a vector w/ toVector() ?? ? ?
        String locationString = playerLocation.toString();
        String[] splitLocation = locationString.split(",");

        //x=[1], y=[2], z=[3], pitch=[4], yaw=[5]
        //TODO: Make this more elegant if possible.
        float xVal = Float.parseFloat(splitLocation[1].replaceAll("x=",""));
        float yVal = Float.parseFloat(splitLocation[2].replaceAll("y=",""));
        float zVal = Float.parseFloat(splitLocation[3].replaceAll("z=",""));
        float pitchVal = Float.parseFloat(splitLocation[4].replaceAll("pitch=",""));
        splitLocation[5] = splitLocation[5].replaceAll("yaw=","");
        float yawVal = Float.parseFloat(splitLocation[5].replaceAll("}",""));
        //TODO: Reconsider whether to use float[] or Float[]
        float[] locationData = {xVal, yVal, zVal, pitchVal, yawVal};
        return locationData;
    }

    public static void writeToHomeList(String filename, String homeName, String worldName, float[] locationData) throws Exception {
        //TODO: Create check for if home already exists.
        //TODO: If no home exists for the player already, don't try and parse the nonexistent json.
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(filename);
        JSONObject homeList = (JSONObject) parser.parse(reader);
        JSONObject homeInfo = new JSONObject();

        homeInfo.put("world", worldName);
        homeInfo.put("x", locationData[0]);
        homeInfo.put("y", locationData[1]);
        homeInfo.put("z", locationData[2]);
        homeInfo.put("pitch", locationData[3]);
        homeInfo.put("yaw", locationData[4]);
        homeList.put(homeName, homeInfo);

        FileWriter file = new FileWriter(filename, false);
        file.write(homeList.toJSONString());
        file.flush();
        file.close();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) { return true;}
        else if(command.getName().equalsIgnoreCase("sethome")){
            try {
                Location playerLocation = ((Player)sender).getLocation();
                String worldName = ((Player) sender).getWorld().getName();
                float[] locationData = getInfoFromLocation(playerLocation);
                //TODO: make home name an argument, and grab the position and orientation of the player.
                writeToHomeList("plugins/Lightspeed/homes.json", args[0], worldName, locationData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
