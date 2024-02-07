package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.Models.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSON_Writer {
    private static final String UNEDITED_JSON_FILE = "Data/OriginalLocations.json";
    private static final String EDITED_JSON_FILE = "Data/Locations.json";
    private static final String PLAYER_FILE_PATH = "Data/Player.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writePlayerToFile(PlayerEngine player) {
        try (FileWriter writer = new FileWriter(PLAYER_FILE_PATH)) {
            gson.toJson(player, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetLocationsJSON() {
        try {
            // Read the unedited JSON file
            Gson gson = new Gson();
            Location[] uneditedLocations;
            try (FileReader reader = new FileReader(UNEDITED_JSON_FILE)) {
                uneditedLocations = gson.fromJson(reader, Location[].class);
            }

            // Write the unedited locations back to the edited JSON file
            try (FileWriter writer = new FileWriter(EDITED_JSON_FILE)) {
                gson.toJson(uneditedLocations, writer);
            }

            System.out.println("Edited JSON file has been reset to match the unedited one.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyLocation(String locationName, String item, boolean add) {
        try {
            // Read the JSON file and deserialize it into a Location array
            Gson gson = new Gson();
            Location[] locations;
            try (FileReader reader = new FileReader(EDITED_JSON_FILE)) {
                locations = gson.fromJson(reader, Location[].class);
            }

            // Find the location to modify
            for (Location location : locations) {
                if (location.getName().equalsIgnoreCase(locationName)) {
                    // Modify the items list
                    String formattedItem = item.trim(); // Trim whitespace
                    if (add) {
                        // Ignore case when checking for duplicates
                        if (!containsIgnoreCase(location.getItems(), formattedItem)) {
                            location.getItems().add(formattedItem);
                            System.out.println("Item modified successfully.");
                        }
                    } else {
                        location.getItems().removeIf(existingItem -> existingItem.trim().equalsIgnoreCase(formattedItem));
                        System.out.println("Item modified successfully.");
                    }
                    break; // No need to continue searching
                }
            }

            // Write the modified locations back to the JSON file
            try (FileWriter writer = new FileWriter(EDITED_JSON_FILE)) {
                gson.toJson(locations, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean containsIgnoreCase(List<String> list, String item) {
        for (String listItem : list) {
            if (listItem.trim().equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }
}