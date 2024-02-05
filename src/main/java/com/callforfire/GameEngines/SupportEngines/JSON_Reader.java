package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.Models.Location;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class JSON_Reader {

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

    public static void readItemsJson(String myItem) throws FileNotFoundException {
        Gson gson = new Gson();

        JsonObject json = gson.fromJson(new FileReader("src/locations.json"), JsonObject.class);
        JsonObject currentThing = json.getAsJsonObject(myItem);
    }
}
