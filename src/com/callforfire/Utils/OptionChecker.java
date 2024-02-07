package com.callforfire.Utils;

import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.Models.Location;

import java.util.List;

public class OptionChecker {
    public static boolean checkItemIsPresentInLocation(String currentLocation, String itemName) {
        Location location = JSON_Reader.getLocationByName(currentLocation);
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

}
