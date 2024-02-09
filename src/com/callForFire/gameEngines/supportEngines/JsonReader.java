package com.callForFire.gameEngines.supportEngines;

import com.callForFire.gameEngines.PlayerEngine;
import com.callForFire.models.Enemy;
import com.callForFire.models.Item;
import com.callForFire.models.Location;
import com.callForFire.models.NPC;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
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
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Enemy> returnListOfEnemies() {
        try {
            Type enemyList = new TypeToken<List<Enemy>>() {}.getType();
            List<Enemy> enemies = gson.fromJson(new FileReader("Data/Enemy.json"), enemyList);

            if(!enemies.isEmpty()) {
                return enemies;
            }

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


    public static Item readItemDescription(String myItem) {
        try {
            Type itemListType = new TypeToken<List<Item>>() {}.getType();
            List<Item> itemList = gson.fromJson(new FileReader("Data/Items.json"), itemListType);

            for (Item item : itemList) {
                if (item.getName().trim().equalsIgnoreCase(myItem.trim())) {
                    return item;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PlayerEngine readPlayerFromFile() {
        try (FileReader reader = new FileReader("Data/Player.json")) {
            Type playerType = PlayerEngine.class;
            return gson.fromJson(reader, playerType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readVerbJson(List<String> userInput) {
        try {
            String myVerb = "";
            String[] verbs = new String[]{"go", "talk", "get", "look", "fire", "inventory", "drop", "help", "quit"};
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(new FileReader("Data/Verbs.json"), JsonObject.class);

            int topIter = 0;
            int iter = 0;
            while (myVerb.equals("") || topIter < 200) {
                if (iter == verbs.length) {
                    iter = 0;
                }
                    String verb = json.get(verbs[iter]).getAsString();
                    String[] synonyms = verb.split(" ");
                    for (String synonym : synonyms) {
                        if (userInput.contains(synonym.toLowerCase())) {
                            myVerb = verbs[iter];
                            break;
                        }
                    }
            iter++;
            topIter++;
            }
            return myVerb;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // WILL RETURN NULL IF INPUT IS INVALID
    public static String readNounJson(List<String> userInput) {
        try {
            if (userInput.size() == 1) {
                return null;
            }
            String myNoun = "";
            String[] nouns = new String[]{"nouns"};

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(new FileReader("Data/Nouns.json"), JsonObject.class);
            int topIter = 0;
            int iter = 0;
            while (myNoun.equals("") || topIter < 200) {
                String noun = json.get("nouns").getAsString();
                String[] synonyms = noun.split(" ");
                for (String synonym : synonyms) {
                    if (userInput.contains(synonym.toLowerCase())) {
                        myNoun = synonym;
                        break;
                    }
                }
                iter++;
                topIter++;
            }
            return myNoun;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}