package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.GameEngines.OptionHandler;

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
        // TODO: Use the arguments from the json to update this later.
        System.out.println(locationDescription);
        System.out.println("To your north is " + north + ", to your east is " + east);
        System.out.println("To your south is " + south + ", to your west is " + west);
    }
}