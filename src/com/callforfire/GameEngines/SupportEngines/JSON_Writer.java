package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.Models.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
}