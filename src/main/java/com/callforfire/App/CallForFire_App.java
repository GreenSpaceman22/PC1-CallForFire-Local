package com.callforfire.App;

import com.apps.util.Console;
import com.callforfire.GameEngines.OptionHandler;
import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.GameEngines.SupportEngines.MessageReader;
import com.callforfire.GameEngines.TextParser;
import com.callforfire.Utils.WelcomeTitleDisplay;

import java.util.List;

//This is where all our of our game engines and logic will run from
public class CallForFire_App {
    private final TextParser textParser = new TextParser();
    private final PlayerEngine playerEngine = new PlayerEngine();
    private final OptionHandler optionHandler = new OptionHandler();

    private boolean isGameOver = false;


    // Methods
    public void run() {
        intialize();
        // Game logic to run the game goes in here
        System.out.println("Your Location: " + playerEngine.getCurrentLocation());
        // This should be outside the game loop and only showed during the begining phase
        MessageReader.printLocationMessage("You are in a sandy mortar pit, you have a radio.", "Firing Point", "Hesco Barriers", "range", "Ammo Depot");

//        while(!isGameOver()) {
            List<String> actionNoun = textParser.getUserString(optionHandler);
            optionHandler.run(actionNoun);
            System.out.println("\n Thanks for trying the demo");
            Console.pause(5000);
//        }
    }

    public void intialize() {
        WelcomeTitleDisplay.render("banner");
        // TODO: Build the splash screen and credits in here, follow dev ops for more clear instruction
    }

    public void instructions() {

    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}