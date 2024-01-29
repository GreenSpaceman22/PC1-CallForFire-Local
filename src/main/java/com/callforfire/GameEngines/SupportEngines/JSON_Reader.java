package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.Models.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class JSON_Reader {

    public static void readLocationsJson() {
        try (Reader reader = new FileReader("Data/Locations.json")) {
            Gson gson = new Gson();
            // Convert JSON array to list of Location objects
            List<Location> locations = gson.fromJson(reader, new TypeToken<List<Location>>(){}.getType());
            for (Location location : locations) {
                System.out.println(location.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
