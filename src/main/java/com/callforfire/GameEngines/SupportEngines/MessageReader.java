package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.GameEngines.OptionHandler;

import java.util.List;

public class MessageReader {
//    private String itemDialogue;
//    private String

    public static void printMessage(List<String> actionNoun) {
        if(actionNoun.size() < 2) {
            System.out.println("You " + actionNoun.get(0) + " somewhere");
        } else {
            System.out.println("You " + actionNoun.get(0) + " " + actionNoun.get(1) + " to the " + OptionHandler.getLocationName());
        }
    }
}