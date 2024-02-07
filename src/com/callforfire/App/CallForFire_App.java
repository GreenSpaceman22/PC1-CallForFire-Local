package com.callforfire.App;

import com.apps.util.Console;
import com.callforfire.GameEngines.OptionHandler;
import com.callforfire.GameEngines.PlayerEngine;
import com.callforfire.GameEngines.SupportEngines.JSON_Writer;
import com.callforfire.GameEngines.SupportEngines.MessageReader;
import com.callforfire.GameEngines.TextParser;
import com.callforfire.Utils.CharacterStatusDisplay;
import com.callforfire.Utils.WelcomeTitleDisplay;

import java.util.List;

//This is where all our of our game engines and logic will run from
public class CallForFire_App {
    private final TextParser textParser = new TextParser();
    private final PlayerEngine playerEngine = new PlayerEngine();
    private final OptionHandler optionHandler = new OptionHandler();
    private final CharacterStatusDisplay charStatus = new CharacterStatusDisplay();

    private boolean isGameOver = false;

    // Methods
    public void run() {
        intialize();

        charStatus.displayCharacterInfo(playerEngine.getName(), playerEngine.getHealth(), playerEngine.getPlayerLocation(), playerEngine.getPlayerInventory());

        MessageReader.printLocationMessage("You are in a sandy mortar pit, you have a radio.", "Firing Point", "Hesco Barriers", "range", "Ammo Depot", "Mortar Pit");

        while(!isGameOver()) {
            optionHandler.resetOptionHandler(); // Ensure all our actions are set to false
            // persistently display character information
            List<String> actionNoun = textParser.getUserString(optionHandler);
            optionHandler.run(actionNoun);
        }
    }

    public void intialize() {
        Console.clear();
        playerEngine.clearPlayerInventory();
        JSON_Writer.resetLocationsJSON();
        WelcomeTitleDisplay.render("banner");
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