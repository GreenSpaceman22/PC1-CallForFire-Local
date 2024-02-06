package com.callforfire.GameEngines;

import com.google.gson.JsonObject;

import java.io.FileReader;
import java.util.List;

public class PlayerEngine {

    private static String currentLocation = "Mortar Pit";
    private static List<String> playerInventory;
    private int maxInventoryWeight = 120;
    private int currentInventoryWeight;



    // Methods
    public static void addItemToInventory(String itemToAdd) {
        // TODO: Update this method to use the JSON_Writer to update the players inventory in the "player.gson"
    }

    public static void dropItemFromInventory(String itemToDrop) {

    }

    // Getters/Setters
    public static List<String> getPlayerInventory() {
        // TODO: Update this to use the JSON_Reader and return the playser JSON inventory
        return playerInventory;
    }

    public void setPlayerInventory(List<String> playerInventory) {
        this.playerInventory = playerInventory;
    }
    
    public int getMaxInventoryWeight() {
        return maxInventoryWeight;
    }

//TODO: implement this later
//    public int getCurrentInventoryWeight() {
//        for (Item item: playerInventory
//             ) {
//            Gson gson = new Gson();
//            JsonObject json = gson.fromJson(new FileReader("Data/Items.json"), JsonObject.class);
//        }
//    }

    public static String getCurrentLocation() {
        return currentLocation;
    }


    public static void setCurrentLocation(String currentLocation) {
        PlayerEngine.currentLocation = currentLocation;
    }


}