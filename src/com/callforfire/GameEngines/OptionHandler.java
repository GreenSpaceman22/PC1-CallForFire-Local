/*
 * This is one of the main game engines, this engine is fed by multiple other supporting engines.
 *
 * The attributes here are set by our text parser to determine what type of action our engine should take
 *
 * We must first determine which attribute was set in the switch case, we then make methods to take the appropriate action
 * while using our "sub" engines to handle the job otherwise this would become bloated. The methods here should not need to be changed
 */

package com.callforfire.GameEngines;


import com.apps.util.Console;
import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.GameEngines.SupportEngines.JSON_Writer;
import com.callforfire.GameEngines.SupportEngines.MessageReader;
import com.callforfire.Models.Item;
import com.callforfire.Models.Location;
import com.callforfire.Models.NPC;
import com.callforfire.Utils.CharacterStatusDisplay;
import com.callforfire.Utils.OptionChecker;
import com.callforfire.Utils.UtilFunctions;

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
     * This is the main method called from the CallForFire_App
     */
    public void run(List<String> actionNoun) {
        handlePlayerAction(this.isMove(), this.isGet(), this.isFire(), this.isTalk(), this.isLook(), this.isInventory(), this.isDrop(), this.isHelp(), this.isQuit(), actionNoun);
    }

    /*
     *  This method is the driving force of this engine, it handles each case based on what the user has input into the TextParsing Engine
     */
    public void handlePlayerAction(boolean move, boolean get, boolean fire, boolean talk, boolean look, boolean inventory, boolean drop, boolean help, boolean quit, List<String> actionNoun) {
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
                // Fire Logic here
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
        // Here we will use the JSON_Reader to get the information of the location we are trying to travel to, the actionNoun at index 1 should be the "direction" of where we want to go
        Location location = JSON_Reader.returnLocationInformationForDirectionToMove(playerEngine.getPlayerLocation(), actionNoun.get(1));

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
        NPC npc = JSON_Reader.readNpcDialogue(npcName); // Use the JSON_Reader to retrieve the NPC object from the json file
        if (npc != null) {
            MessageReader.printNPCDialogue(npc); // Pass the NPC object to the message reader to find out what the NPC has to say
        } else {
            MessageReader.printDialogueError(); // If no NPC was found, print the error
        }
    }

    // This is the method to handle getting an item from a location
    public void handleGetItem(String itemName) {
        // Here we must check that the item is actually present at the users location and that the player does not already have that item
        boolean itemIsPresent = OptionChecker.checkItemIsPresentInLocation(playerEngine.getPlayerLocation(), itemName);
        boolean playerAlreadyHasItem = OptionChecker.checkItemNotInPlayerInventory(itemName);

        if (!playerAlreadyHasItem) { // If the conditions are met
            if (itemIsPresent) {
                playerEngine.addItemToInventory(itemName); // Add the item to the players invetory using our PlayerEngine
                JSON_Writer.modifyLocation(playerEngine.getCurrentLocation(), itemName, false); // Remove the item from the location
                MessageReader.printItemAddedMessage(itemName); // Alert user the got the item
                UtilFunctions.pauseFunction(1000);
                updateLocation(JSON_Reader.getLocationByName(playerEngine.getPlayerLocation())); // Use extracted method to show the location changes
            } else {
                MessageReader.printGetItemError(); // Alert that the items is not here
            }
        } else {
            MessageReader.printItemAlreadyPresentError(itemName); // Alert the player already has the item
        }
    }

    public void handleDropItem(String itemName) {
        boolean playerAlreadyHasItem = OptionChecker.checkItemNotInPlayerInventory(itemName);
        if(playerAlreadyHasItem) {
            playerEngine.dropItemFromInventory(itemName);
            JSON_Writer.modifyLocation(playerEngine.getCurrentLocation(), itemName, true);
            MessageReader.printItemDroppedMessage(itemName, playerEngine.getCurrentLocation());
            UtilFunctions.pauseFunction(1000);
            updateLocation(JSON_Reader.getLocationByName(playerEngine.getPlayerLocation()));
        } else {
            MessageReader.printDropItemError(itemName);
        }
    }

    public void handleLook(List<String> actionNoun) {
        if (actionNoun.size() == 1 && actionNoun.get(0).equalsIgnoreCase("look")) {
            Location location = JSON_Reader.getLocationByName(playerEngine.getPlayerLocation());
            if (location != null) {
                updateLocation(location);
            } else {
                MessageReader.printError();
            }
        } else {
            Item item = JSON_Reader.readItemDescription(actionNoun.get(1));
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