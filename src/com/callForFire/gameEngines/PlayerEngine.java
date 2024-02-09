package com.callForFire.gameEngines;

import com.callForFire.gameEngines.supportEngines.JsonReader;
import com.callForFire.gameEngines.supportEngines.JsonWriter;

import java.util.ArrayList;
import java.util.List;

public class PlayerEngine {

    private String currentLocation = "Mortar Pit";
    private List<String> playerInventory = new ArrayList<>();
    private int maxInventoryWeight = 120;
    private int currentInventoryWeight;
    private String name = "Player1"; // TODO: undo the hardcoding here
    private int health = 100;
    private String location = getCurrentLocation();


    // Business Methods
    public void addItemToInventory(String itemToAdd) {
        // Add Item to inventory
        playerInventory.add(itemToAdd);
        // Update player's JSON file
        JsonWriter.writePlayerToFile(this);
    }

    public void dropItemFromInventory(String itemToDrop) {
        // Remove Item from inventory
        playerInventory.remove(itemToDrop);
        // Update player's JSON file
        JsonWriter.writePlayerToFile(this);
    }

    public static List<String> getPlayerInventoryItems() {
        PlayerEngine player = JsonReader.readPlayerFromFile();
        if (player != null) {
            return player.getPlayerInventory();
        }
        return null;
    }

    public void clearPlayerInventory() {
        playerInventory.clear(); // Clear the inventory list
        // Update player's JSON file
        JsonWriter.writePlayerToFile(this);
    }

    public String getPlayerLocation() {
        PlayerEngine player = JsonReader.readPlayerFromFile();
        if(player !=  null) {
            return player.getCurrentLocation();
        }
        return null;
    }


    // GETTERS AND SETTERS
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
        JsonWriter.writePlayerToFile(this);
    }

    public List<String> getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(List<String> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public int getMaxInventoryWeight() {
        return maxInventoryWeight;
    }

    public void setMaxInventoryWeight(int maxInventoryWeight) {
        this.maxInventoryWeight = maxInventoryWeight;

    }

    public int getCurrentInventoryWeight() {
        return currentInventoryWeight;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        JsonWriter.writePlayerToFile(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public void setCurrentInventoryWeight(int currentInventoryWeight) {
        this.currentInventoryWeight = currentInventoryWeight;
    }
}