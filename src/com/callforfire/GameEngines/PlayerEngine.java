package com.callforfire.GameEngines;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.GameEngines.SupportEngines.JSON_Writer;

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
        // Add item to inventory
        playerInventory.add(itemToAdd);
        // Update player's JSON file
        JSON_Writer.writePlayerToFile(this);
    }

    public void dropItemFromInventory(String itemToDrop) {
        // Remove item from inventory
        playerInventory.remove(itemToDrop);
        // Update player's JSON file
        JSON_Writer.writePlayerToFile(this);
    }



    public static List<String> getPlayerInventoryItems() {
        PlayerEngine player = JSON_Reader.readPlayerFromFile();
        if (player != null) {
            return player.getPlayerInventory();
        }
        return null;
    }

    public void clearPlayerInventory() {
        playerInventory.clear(); // Clear the inventory list
        // Update player's JSON file
        JSON_Writer.writePlayerToFile(this);
    }

    // GETTERS AND SETTERS
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
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