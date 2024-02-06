package com.callforfire.GameEngines.SupportEngines;

import com.apps.util.Console;
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

    // MOVING MESSAGES
    public static void printMoveError() {
        System.out.println("Sorry you can't move there");
    }

    public static void printLocationMessage(String locationDescription, String north, String south, String east, String west, String currentLocation) {
        Console.clear();
        System.out.println("Your Current Location: " + currentLocation);
        System.out.println(locationDescription);
        System.out.println("To your north is " + north + ", to your east is " + east);
        System.out.println("To your south is " + south + ", to your west is " + west);
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