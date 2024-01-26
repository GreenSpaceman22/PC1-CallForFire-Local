package com.callforfire.GameEngines;

import java.util.List;

public class PlayerEngine {
    private String currentLocation = "Mortar Pit";
    private List<String> playerInventory;


    // Methods
    public static void addItemToInventory(String itemToAdd) {
        // TODO: Update this method to use the JSON_Writer to update the players inventory in the "player.gson"
    }

    // Getters/Setters
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<String> getPlayerInventory() {
        // TODO: Update this to use the JSON_Reader and return the playser JSON inventory
        return playerInventory;
    }
}