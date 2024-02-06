package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.Models.Location;
import com.callforfire.Models.NPC;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSON_Reader {
   private static final Gson gson = new Gson();

    public static NPC readNpcDialogue(String npcName) {
        try {
            // Read JSON file into a List of NPC objects
            Type npcListType = new TypeToken<List<NPC>>(){}.getType();
            List<NPC> npcList = gson.fromJson(new FileReader("Data/NPC.json"), npcListType);

            for (NPC npc : npcList) {
                if (npc.getName().trim().equalsIgnoreCase(npcName.toLowerCase().trim())) {
                    return npc; // Exit the method after finding the NPC
                }
            }

            // If NPC with the given name is not found
            System.out.println("NPC with name '" + npcName + "' not found.");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void readLocationsJson(String direction) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(new FileReader("Data/Locations.json"), JsonObject.class);

        JsonObject currentPlace = json.getAsJsonObject(Location.getCurrentLocation());

        Location.setCurrentLocation(currentPlace.get(direction).getAsString());

        JsonObject newLocation = json.getAsJsonObject(Location.getCurrentLocation());

        Location.setLocationDescription(newLocation.get("description").getAsString());
        Location.setNorth(newLocation.get("north").getAsString());
        Location.setEast(newLocation.get("east").getAsString());
        Location.setSouth(newLocation.get("south").getAsString());
        Location.setWest(newLocation.get("west").getAsString());
    }

    public static String readItemDescription(String myItem) throws FileNotFoundException {
        Gson gson = new Gson();

        JsonObject gsonItems = gson.fromJson(new FileReader("Data/Items.json"), JsonObject.class);
        JsonObject jsonItem = gsonItems.getAsJsonObject(myItem);
        String itemDescription = jsonItem.get("description").getAsString();
        return itemDescription;
    }
}
