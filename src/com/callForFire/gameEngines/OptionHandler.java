/*
 * This is one of the main game engines, this engine is fed by multiple other supporting engines.
 *
 * The attributes here are set by our text parser to determine what type of action our engine should take
 *
 * We must first determine which attribute was set in the switch case, we then make methods to take the appropriate action
 * while using our "sub" engines to handle the job otherwise this would become bloated. The methods here should not need to be changed
 */

package com.callForFire.gameEngines;


import com.apps.util.Console;
import com.callForFire.gameEngines.supportEngines.CombatEngine;
import com.callForFire.gameEngines.supportEngines.JsonReader;
import com.callForFire.gameEngines.supportEngines.JsonWriter;
import com.callForFire.gameEngines.supportEngines.MessageReader;
import com.callForFire.models.Item;
import com.callForFire.models.Location;
import com.callForFire.models.NPC;
import com.callForFire.utils.CharacterStatusDisplay;
import com.callForFire.utils.OptionChecker;
import com.callForFire.utils.UtilFunctions;

import java.util.List;

public class OptionHandler {
    PlayerEngine playerEngine = new PlayerEngine();
    CharacterStatusDisplay charStatus = new CharacterStatusDisplay();
    private boolean get;
    private boolean move;
    private boolean fire;
    private boolean talk;
    private boolean look;
    private boolean inventory;
    private boolean drop;
    private boolean help;
    private boolean quit;

    // Methods
    /*
     * This is the main method called from the callForFireApp
     */
    public void run(List<String> actionNoun, CombatEngine combatEngine) {
        handlePlayerAction(this.isMove(), this.isGet(), this.isFire(), this.isTalk(), this.isLook(), this.isInventory(), this.isDrop(), this.isHelp(), this.isQuit(), actionNoun, combatEngine);
    }

    /*
     *  This method is the driving force of this engine, it handles each case based on what the user has input into the TextParsing Engine
     */
    public void handlePlayerAction(boolean move, boolean get, boolean fire, boolean talk, boolean look, boolean inventory, boolean drop, boolean help, boolean quit, List<String> actionNoun, CombatEngine combatEngine) {
        // Determine which case to execute based on boolean variables
        int caseNumber = determineCase(move, get, fire, talk, look, inventory, drop, help, quit);

        switch (caseNumber) {
            case 1:
                handleMove(actionNoun);
                break;
            case 2:
                handleGetItem(actionNoun.get(1));
                break;
            case 3:
                handleAttackEnemy(combatEngine, actionNoun);
                break;
            case 4:
                handleTalkWithNpc(actionNoun.get(1));
                break;
            case 5:
                handleLook(actionNoun);
                break;
            case 6:
                handleCheckIventory();
                break;
            case 7:
                handleDropItem(actionNoun.get(1));
                break;
            case 8:
                UtilFunctions.helpFunction();
                break;
            case 9:
                UtilFunctions.confirmAndQuitGame();
                break;
            default:
                // Default case
//                InvalidCommand.showInvalidCommand(TextParser.getParsedWords());
                break;
        }
    }

    // This is the method to determine which attribute was set from the TextParsing engine and return the number case
    private static int determineCase(boolean move, boolean get, boolean fire, boolean talk, boolean look, boolean inventory, boolean drop, boolean help, boolean quit) {
        if (move) {
            return 1;
        } else if (get) {
            return 2;
        } else if (fire) {
            return 3;
        } else if (talk) {
            return 4;
        } else if (look) {
            return 5;
        } else if (inventory) {
            return 6;
        } else if (drop) {
            return 7;
        } else if (help) {
            return 8;
        } else if (quit) {
            return 9;
        } else {
            return 0; // Default case
        }
    }

    // This is the method to handle the movement around the game
    private void handleMove(List<String> actionNoun) {
        // Here we will use the JsonReader to get the information of the location we are trying to travel to, the actionNoun at index 1 should be the "direction" of where we want to go
        Location location = JsonReader.returnLocationInformationForDirectionToMove(playerEngine.getPlayerLocation(), actionNoun.get(1));

        if (location != null) {
            playerEngine.setCurrentLocation(location.getName()); // Set the current location to the name of the location we are going
            UtilFunctions.pauseFunction(1000);
            updateLocation(location); // Use our extracted method to display the new information of where the player is now located
        } else {
            MessageReader.printMoveError(); // If the desired location doesn't exist, print the error
        }
    }

    // This is the method to handle talking with an NPC
    public void handleTalkWithNpc(String npcName) {
        NPC npc = JsonReader.readNpcDialogue(npcName); // Use the JsonReader to retrieve the NPC object from the json file
        if (npc != null) {
            MessageReader.printNPCDialogue(npc); // Pass the NPC object to the message reader to find out what the NPC has to say
        } else {
            MessageReader.printDialogueError(); // If no NPC was found, print the error
        }
    }

    // This is the method to handle getting an Item from a location
    public void handleGetItem(String itemName) {
        // Here we must check that the Item is actually present at the users location and that the player does not already have that Item
        boolean itemIsPresent = OptionChecker.checkItemIsPresentInLocation(playerEngine.getPlayerLocation(), itemName);
        boolean playerAlreadyHasItem = OptionChecker.checkItemNotInPlayerInventory(itemName);

        if (!playerAlreadyHasItem) { // If the conditions are met
            if (itemIsPresent) {
                playerEngine.addItemToInventory(itemName); // Add the Item to the players invetory using our PlayerEngine
                JsonWriter.modifyLocation(playerEngine.getCurrentLocation(), itemName, false); // Remove the Item from the location
                MessageReader.printItemAddedMessage(itemName); // Alert user the got the Item
                UtilFunctions.pauseFunction(1000);
                updateLocation(JsonReader.getLocationByName(playerEngine.getPlayerLocation())); // Use extracted method to show the location changes
            } else {
                MessageReader.printGetItemError(); // Alert that the items is not here
            }
        } else {
            MessageReader.printItemAlreadyPresentError(itemName); // Alert the player already has the Item
        }
    }

    public void handleAttackEnemy(CombatEngine combatEngine, List<String> actionNoun) {

        boolean attackResult = combatEngine.attackEnemy(playerEngine.getPlayerInventory().contains("CopenHagen-Wintergreen"));
    }

    public void handleDropItem(String itemName) {
        boolean playerAlreadyHasItem = OptionChecker.checkItemNotInPlayerInventory(itemName);
        if(playerAlreadyHasItem) {
            playerEngine.dropItemFromInventory(itemName);
            JsonWriter.modifyLocation(playerEngine.getCurrentLocation(), itemName, true);
            MessageReader.printItemDroppedMessage(itemName, playerEngine.getCurrentLocation());
            UtilFunctions.pauseFunction(1000);
            updateLocation(JsonReader.getLocationByName(playerEngine.getPlayerLocation()));
        } else {
            MessageReader.printDropItemError(itemName);
        }
    }

    public void handleLook(List<String> actionNoun) {
        if (actionNoun.size() == 1 && actionNoun.get(0).equalsIgnoreCase("look")) {
            Location location = JsonReader.getLocationByName(playerEngine.getPlayerLocation());
            if (location != null) {
                updateLocation(location);
            } else {
                MessageReader.printError();
            }
        } else {
            Item item = JsonReader.readItemDescription(actionNoun.get(1));
            if (item != null) {
                MessageReader.printItemDescription(item.getDescription());
            } else {
                MessageReader.printError();
            }
        }
    }

    private void updateLocation(Location location) {
        Console.clear();
        charStatus.displayCharacterInfo(playerEngine.getName(), playerEngine.getHealth(), playerEngine.getPlayerLocation(), playerEngine.getPlayerInventory());
        MessageReader.printLocationMessage(location);
    }

    public void handleCheckIventory() {
        List<String> inventory = playerEngine.getPlayerInventory();
        MessageReader.displayPlayerInventory(inventory);
    }

    // move, get, fire, talk, look, inventory, drop, help, quit
    public void resetOptionHandler() {
        setMove(false);
        setGet(false);
        setFire(false);
        setTalk(false);
        setLook(false);
        setInventory(false);
        setDrop(false);
        setHelp(false);
        setQuit(false);
    }

    // Getters/Setters
    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public boolean isTalk() {
        return talk;
    }

    public void setTalk(boolean talk) {
        this.talk = talk;
    }

    public boolean isLook() {
        return look;
    }

    public void setLook(boolean look) {
        this.look = look;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    public boolean isDrop() {
        return drop;
    }

    public void setDrop(boolean drop) {
        this.drop = drop;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}