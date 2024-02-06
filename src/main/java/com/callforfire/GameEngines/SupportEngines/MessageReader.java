package com.callforfire.GameEngines.SupportEngines;

import com.apps.util.Console;
import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.Models.NPC;

public class MessageReader {
    private String itemDialogue;


    public static void printLocationMessage(String locationDescription, String north, String south, String east, String west) {
        Console.clear();
        System.out.println("Your Current Location: " + PlayerEngine.getCurrentLocation());
        // TODO: Use the arguments from the json to update this later.
        System.out.println(locationDescription);
        System.out.println("To your north is " + north + ", to your east is " + east);
        System.out.println("To your south is " + south + ", to your west is " + west);
    }

    public static void printNPCDialogue(NPC npc) {
        System.out.println(npc.getName());
        System.out.println(npc.getDialogue());
    }

    public static void printItemDescription(String description) {
        System.out.println(description);
    }

    public static void printError() {
        System.out.println("Sorry that command is not recognized");
    }

    public static void printMoveError() {
        System.out.println("Sorry you can't move there");
    }
}