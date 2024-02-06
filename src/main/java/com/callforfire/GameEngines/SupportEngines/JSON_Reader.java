package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.Models.Location;
import com.callforfire.Models.NPC;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSON_Reader {
    private static final Gson gson = new Gson();

    public static NPC readNpcDialogue(String npcName) {
        try {
            // Read JSON file into a List of NPC objects
            Type npcListType = new TypeToken<List<NPC>>() {}.getType();
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

    public static Location returnLocationInformationForDirectionToMove(String currentLocation, String direction) {
        try {
            Type locationListType = new TypeToken<List<Location>>() {}.getType();
            List<Location> locationList = gson.fromJson(new FileReader("Data/Locations.json"), locationListType);

            for (Location location : locationList) {
                if (location.getName().trim().equalsIgnoreCase(currentLocation.trim().toLowerCase())) {
                    // Check the direction to move
                    switch (direction.toLowerCase().trim()) {
                        case "north":
                            return getLocationByName(location.getNorth());
                        case "south":
                            return getLocationByName(location.getSouth());
                        case "east":
                            return getLocationByName(location.getEast());
                        case "west":
                            return getLocationByName(location.getWest());
                        default:
                            return null;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to get location by name
    public static Location getLocationByName(String name) {
        try {
            Type locationListType = new TypeToken<List<Location>>() {}.getType();
            List<Location> locationList = gson.fromJson(new FileReader("Data/Locations.json"), locationListType);

            for (Location location : locationList) {
                if (location.getName().trim().equalsIgnoreCase(name.trim())) {
                    return location;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String readItemDescription(String myItem) {
        try {
            JsonObject gsonItems = gson.fromJson(new FileReader("Data/Items.json"), JsonObject.class);
            JsonObject jsonItem = gsonItems.getAsJsonObject(myItem);
            return jsonItem.get("description").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}