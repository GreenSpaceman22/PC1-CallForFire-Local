package com.callForFire.utils;

import com.callForFire.gameEngines.PlayerEngine;
import com.callForFire.gameEngines.supportEngines.JsonReader;
import com.callForFire.models.Location;

import java.util.List;

public class OptionChecker {
    public static boolean checkItemIsPresentInLocation(String currentLocation, String itemName) {
        Location location = JsonReader.getLocationByName(currentLocation);
        if (location != null) {
            for(String item : location.getItems()) {
                if(item.equalsIgnoreCase(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkItemNotInPlayerInventory(String itemName) {
        List<String> playerInventory = PlayerEngine.getPlayerInventoryItems();

        if(playerInventory != null) {
            for(String item :playerInventory) {
                if(item.equalsIgnoreCase(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkUserHasRequiredItemsToAttack(List<String> playerInventory) {
        int inventoryCount = 0; // There are 3 required items to attack, we must make sure the user has them before attacking

        if(playerInventory.contains("base-plate")) {
            inventoryCount++;
        }
        return false;
    }
}