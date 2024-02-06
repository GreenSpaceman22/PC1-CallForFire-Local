package com.callforfire.GameEngines;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.GameEngines.SupportEngines.MessageReader;
import com.callforfire.Models.Item;
import com.callforfire.Models.Location;
import com.callforfire.Models.NPC;
import com.callforfire.Utils.OptionChecker;
import com.callforfire.Utils.UtilFunctions;

import java.util.List;

public class OptionHandler {
    PlayerEngine playerEngine = new PlayerEngine();
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
    public void run(List<String> actionNoun) {
        handlePlayerAction(this.isMove(), this.isGet(), this.isFire(), this.isTalk(), this.isLook(), this.isInventory(), this.isDrop(), this.isHelp(), this.isQuit(), actionNoun);
    }

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
                UtilFunctions.helpFunction();
            case 7:
                // Drop Logic here
            case 8:
                // Help Logic here
            case 9:
                // Quit Logic here
            default:
                // Default case
//                InvalidCommand.showInvalidCommand(TextParser.getParsedWords());
                break;
        }
    }

    // move, get, fire, talk, look, inventory, drop, help, quit
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

    private void handleMove(List<String> actionNoun) {
        Location location = JSON_Reader.returnLocationInformationForDirectionToMove(playerEngine.getCurrentLocation(), actionNoun.get(1));
        if (location != null) {
            playerEngine.setCurrentLocation(location.getName());
            MessageReader.printLocationMessage(location.getDescription(), location.getNorth(), location.getSouth(), location.getEast(), location.getWest(), playerEngine.getCurrentLocation());
        } else {
            MessageReader.printMoveError();
        }
    }

    public void handleTalkWithNpc(String npcName) {
        NPC npcDialogue = JSON_Reader.readNpcDialogue(npcName);
        if (npcDialogue != null) {
            MessageReader.printNPCDialogue(npcDialogue);
        } else {
            MessageReader.printError();
        }
    }

    public void handleGetItem(String itemName) {
        boolean itemIsPresent = OptionChecker.checkItemIsPresentInLocation(playerEngine.getCurrentLocation(), itemName);
        boolean playerAlreadyHasItem = OptionChecker.checkItemNotInPlayerInventory(itemName);

        if (!playerAlreadyHasItem) {
            if (itemIsPresent) {
                playerEngine.addItemToInventory(itemName);
                MessageReader.printItemAddedMessage(itemName);
            } else {
                MessageReader.printGetItemError();
            }
        } else {
            MessageReader.printItemAlreadyPresentError(itemName);
        }
    }

    public void handleLook(List<String> actionNoun) {
        if (actionNoun.size() == 1 && actionNoun.get(0).equalsIgnoreCase("look")) {
            Location location = JSON_Reader.getLocationByName(playerEngine.getCurrentLocation());
            if (location != null) {
                MessageReader.printLocationMessage(location.getDescription(), location.getNorth(), location.getSouth(), location.getEast(), location.getWest(), playerEngine.getCurrentLocation());
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
