package com.callforfire.GameEngines;

import com.google.gson.JsonObject;

import java.io.FileReader;
import java.util.List;

public class PlayerEngine {

    private static String currentLocation = "Mortar Pit";
    private static List<String> playerInventory;
    private int maxInventoryWeight = 120;
    private int currentInventoryWeight;
    private String name = "Player1"; // TODO: undo the hardcoding here
    private int health = 100;
    private String location = getCurrentLocation();



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

    public static String getCurrentLocation() {
        return currentLocation;
    }


    public static void setCurrentLocation(String currentLocation) {
        PlayerEngine.currentLocation = currentLocation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}