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
    private boolean playerHasRequiredItems = false;
    private boolean isGameOver = false;
    private boolean playerWonGame;

    // Methods
    public void run() {
        intialize();

        charStatus.displayCharacterInfo(playerEngine.getName(), playerEngine.getHealth(), playerEngine.getPlayerLocation(), playerEngine.getPlayerInventory());

        MessageReader.printLocationMessage("You are in a sandy mortar pit, you have a radio.", "Firing Point", "Hesco Barriers", "Ammo Depot", "range", "Mortar Pit");

        while(!isGameOver()) {

            optionHandler.resetOptionHandler(); // Ensure all our actions are set to false

            List<String> actionNoun = textParser.getUserString(optionHandler, playerEngine); // Get the users desired action

            optionHandler.run(actionNoun, combatEngine); // Handle what to do with that action

            checkPlayerHasRequiredItems(); // Check and set if the player has the required item's

            if(isPlayerHasRequiredItems()) {
                handleEnemyAttack();
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

    private void handleEnemyAttack() {
        MessageReader.displayIncomingMessage(playerEngine.getPlayerLocation(), combatEngine.getEnemy().getEnemyName());
        Console.pause(1000);
        boolean enemyAttackWasSuccesful = combatEngine.enemyAttacksPlayer();
        if (enemyAttackWasSuccesful) {
            int healthRemaining = combatEngine.calculateBattleDamage(false, playerEngine);
            System.out.println("Health Remaining: " + healthRemaining);
            MessageReader.displayDamageDoneToPlayer(healthRemaining);
            Console.pause(1000);
        } else {
            MessageReader.displayEnemyMissed();
            Console.pause(1000);
        }
        Console.pause(1000);
        Console.clear();
        charStatus.displayCharacterInfo(playerEngine.getName(), playerEngine.getHealth(), playerEngine.getPlayerLocation(), PlayerEngine.getPlayerInventoryItems());
    }

    public void intialize() {
        Console.clear(); // Clear the console
        playerEngine.clearPlayerInventory(); // Clear the players inventory
        playerEngine.setCurrentLocation("Mortar Pit");
        playerEngine.setHealth(100);
        JsonWriter.resetLocationsJSON(); // Ensure the Locations.json is reset to the starting game configs
        WelcomeTitleDisplay.render("banner"); // Display the welcome message
    }

    public void instructions() {

    }

    public void checkPlayerHasRequiredItems() {
        List<String> playerInventory = PlayerEngine.getPlayerInventoryItems();
        if(!playerInventory.isEmpty()) {
            boolean playerHasRequiredItems = OptionChecker.checkUserHasRequiredItemsToAttack(PlayerEngine.getPlayerInventoryItems());
            if(playerHasRequiredItems) {
                setPlayerHasRequiredItems(true);
            }
        }
    }

    // Getters and Setters
    public boolean isPlayerHasRequiredItems() {
        return playerHasRequiredItems;
    }

    public void setPlayerHasRequiredItems(boolean playerHasRequiredItems) {
        this.playerHasRequiredItems = playerHasRequiredItems;
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