package com.callforfire.GameEngines.SupportEngines;

import com.apps.util.Console;
import com.callforfire.GameEngines.OptionHandler;
import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.Models.NPC;

import java.util.List;

public class MessageReader {
    private String itemDialogue;

    public static void printMessage(List<String> actionNoun) {
        if(actionNoun.size() < 2) {
            System.out.println("You " + actionNoun.get(0) + " somewhere");
        } else {
            System.out.println("You " + actionNoun.get(0) + " " + actionNoun.get(1) + " to the " + OptionHandler.getLocationName());
        }
    }

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

    public static void printError() {
        System.out.println("Sorry that command is not recognized");
    }
}