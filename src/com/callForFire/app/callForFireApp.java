package com.callForFire.app;

import com.apps.util.Console;
import com.callForFire.gameEngines.OptionHandler;
import com.callForFire.gameEngines.PlayerEngine;
import com.callForFire.gameEngines.TextParser;
import com.callForFire.gameEngines.supportEngines.CombatEngine;
import com.callForFire.gameEngines.supportEngines.JsonWriter;
import com.callForFire.gameEngines.supportEngines.MessageReader;
import com.callForFire.utils.CharacterStatusDisplay;
import com.callForFire.utils.OptionChecker;
import com.callForFire.utils.WelcomeTitleDisplay;

import java.util.List;

//This is where all our of our game engines and logic will run from
public class callForFireApp {
    private final CombatEngine combatEngine = new CombatEngine();
    private final TextParser textParser = new TextParser();
    private final PlayerEngine playerEngine = new PlayerEngine();
    private final OptionHandler optionHandler = new OptionHandler();
    private final CharacterStatusDisplay charStatus = new CharacterStatusDisplay();
    private boolean isGameOver = false;
    private boolean playerWonGame;

    // Methods
    public void run() {
        intialize();

        charStatus.displayCharacterInfo(playerEngine.getName(), playerEngine.getHealth(), playerEngine.getPlayerLocation(), playerEngine.getPlayerInventory());

        MessageReader.printLocationMessage("You are in a sandy mortar pit, you have a radio.", "Firing Point", "Hesco Barriers", "range", "Ammo Depot", "Mortar Pit");

        while(!isGameOver()) {

            optionHandler.resetOptionHandler(); // Ensure all our actions are set to false

            List<String> actionNoun = textParser.getUserString(optionHandler); // Get the users desired action

            optionHandler.run(actionNoun, combatEngine); // Handle what to do with that action


            boolean playerHasRequiredItems = OptionChecker.checkUserHasRequiredItemsToAttack(playerEngine.getPlayerInventory()); // Once the user has the required items, the enemy starts attacking
            if(playerHasRequiredItems) {
                // TODO: Add enemy attack logic
            }

            if(playerEngine.getHealth() <= 0) {
                setGameOver(true);
                setPlayerWonGame(false);
            }
            if(combatEngine.getEnemy().getEnemyHealth() <= 0) {
                setGameOver(true);
                setPlayerWonGame(true);
            }
        }
    }

    public void intialize() {
        Console.clear(); // Clear the console
        playerEngine.clearPlayerInventory(); // Clear the players inventory
        JsonWriter.resetLocationsJSON(); // Ensure the Locations.json is reset to the starting game configs
        WelcomeTitleDisplay.render("banner"); // Display the welcome message
    }

    public void instructions() {

    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isPlayerWonGame() {
        return playerWonGame;
    }

    public void setPlayerWonGame(boolean playerWonGame) {
        this.playerWonGame = playerWonGame;
    }
}