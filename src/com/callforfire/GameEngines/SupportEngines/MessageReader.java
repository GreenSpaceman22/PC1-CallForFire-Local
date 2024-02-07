package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.Models.Location;
import com.callforfire.Models.NPC;

import java.util.List;

public class MessageReader {
    private String itemDialogue;

    public static void printItemDescription(String description) {
        System.out.println(description);
    }

    public static void printError() {
        System.out.println("Sorry that command is not recognized");
    }

    // NPC MESSAGES
    public static void printNPCDialogue(NPC npc) {
        System.out.println(npc.getName() + ": " + npc.getDialogue());
    }

    public static void printDialogueError() {
        System.out.println("Sorry we cannot find a character with that name, make sure you spell it the same way it is presented");
    }

    // MOVING MESSAGES
    public static void printMoveError() {
        System.out.println("Sorry you can't move there");
    }

    public static void printLocationMessage(String locationDescription, String north, String south, String east, String west, String currentLocation) {
//        System.out.println("Your Current Location: " + currentLocation);
        System.out.println(locationDescription);
        System.out.println("To your north is " + north + ", to your east is " + east);
        System.out.println("To your south is " + south + ", to your west is " + west);
    }

    public static void printLocationMessage(Location location) {
        System.out.println(location.getDescription());
        System.out.println("To your north is " + location.getNorth() + ", to your east is " + location.getEast());
        System.out.println("To your south is " + location.getSouth() + ", to your west is " + location.getWest());
        if(location.getItems().size() >= 1) {
            System.out.print("There is a ");
            for(int i = 0; i < location.getItems().size(); i++) {
                System.out.print(location.getItems().get(i));
                if(i < location.getItems().size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" here");
        } else {
            System.out.println("There is nothing useful here");
        }
    }

    // ITEM MESSAGES
    public static void printGetItemError() {
        System.out.println("That item is not here, ensure you are spelling exactly how the items is spelt in the description");
    }

    public static void printItemAlreadyPresentError(String itemName) {
        System.out.println("You already have the " + itemName);
    }

    public static void printItemAddedMessage(String itemName) {
        System.out.println("You picked up the " + itemName + " and put it in your inventory");
    }

    // Display Player Inventory
    public static void displayPlayerInventory(List<String> inventory) {
        if(inventory != null) {
            System.out.println("Your Inventory: ");
            for(String item : inventory) {
                System.out.println(item);
            }
        } else {
            System.out.println("Your inventory is empty");
        }
    }
}