package com.sprocket.lightspeed.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.json.simple.JSONObject;

import java.io.FileWriter;


public class SetHomeCommand implements CommandExecutor {
    public static void writeToHomeList(String filename, String homeName) throws Exception {
        //TODO: Create check for if home already exists.
        /*JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject previousHomes = (JSONObject) obj;*/
        JSONObject homeList = new JSONObject();

        JSONObject homeInfo = new JSONObject();
        homeInfo.put("Coords", "The coordinates");
        homeInfo.put("World", "The world I was in bruh");

        homeList.put(homeName, homeInfo);

        FileWriter file = new FileWriter(filename, true);
        file.write(homeList.toJSONString());
        file.flush();
        file.close();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) { return true;}
        else if(command.getName().equalsIgnoreCase("sethome")){
            try {
                //TODO: make home name an argument, and grab the position and orientation of the player.
                writeToHomeList("plugins/Lightspeed/homes.json", "OTHA HOME");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
