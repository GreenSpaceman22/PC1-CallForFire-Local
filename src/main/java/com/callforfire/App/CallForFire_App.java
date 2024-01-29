package com.callforfire.App;

import com.apps.util.Console;
import com.apps.util.Prompter;
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


    // Methods
    public void run() {
        Console.clear();
        intialize();
        // Game logic to run the game goes in here
        System.out.println("Your Location: " + playerEngine.getCurrentLocation());

        List<String> parsedInput = textParser.getUserString(optionHandler);
        MessageReader.printMessage(parsedInput);
    }

    public void intialize() {
        WelcomeTitleDisplay.render("banner");
    }

    public void instructions() {

    }
}