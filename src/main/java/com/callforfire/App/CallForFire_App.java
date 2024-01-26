package com.callforfire.App;

import com.callforfire.GameEngines.TextParser;

import java.util.List;

//This is where all our of our game engines and logic will run from
public class CallForFire_App {
    private TextParser textParser;
    private String currentLocation;

    // TODO: Build and add the TextParser Here

    // TODO: Build and add the OptionReader Here


    // Methods
    public void run() {
        // Game logic to run the game goes in here
       List<String> parsedInput = textParser.getActionNoun();
       System.out.println(parsedInput);
    }

    public void intialize() {
        // TODO: Build the splash screen and credits in here, follow dev ops for more clear instruction
    }
}