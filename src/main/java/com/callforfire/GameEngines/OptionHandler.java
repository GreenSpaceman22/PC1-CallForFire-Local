package com.callforfire.GameEngines;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.GameEngines.SupportEngines.MessageReader;
import com.callforfire.Models.Location;
import com.callforfire.Models.NPC;
import com.callforfire.Utils.HelpFunction;

import java.util.List;

public class OptionHandler {
    private boolean get;
    private boolean move;
    private boolean fire;
    private boolean talk;
    private boolean look;
    private boolean help;
    private boolean inventory;

    // Methods
    public void run(List<String> actionNoun) {
        handlePlayerAction(this.isGet(), this.isMove(), this.isFire(), this.isTalk(), this.isLook(), this.isInventory(), this.isHelp(), actionNoun);
    }

    // TODO: need to add options for drop(items), help
    public void handlePlayerAction(boolean get, boolean move, boolean fire, boolean talk, boolean look, boolean inventory, boolean help, List<String> actionNoun) {
        // Determine which case to execute based on boolean variables
        int caseNumber = determineCase(get, move, fire, talk, look, inventory, help);

        switch (caseNumber) {
            case 1:
                handleMove(actionNoun);
                break;
            case 2:
                // Handle fire
                break;
            case 3:
                handleTalkWithNpc(actionNoun.get(1));
                break;
            case 4:
                handleLook(actionNoun);
                break;
            // Add more cases as needed
            case 5:
                checkItemInPlayerInventory();
            case 6:
                HelpFunction.helpFunction();
            default:
                // Default case
//                InvalidCommand.showInvalidCommand(TextParser.getParsedWords());
                break;
        }
    }


    private static int determineCase(boolean get, boolean move, boolean fire, boolean talk, boolean look, boolean inventory, boolean help) {
        if (move) {
            return 1;
        } else if (get) {
            return 2;
        } else if (talk) {
            return 3;
        } else if (look) {
            return 4;
        } else if (inventory) {
            return 5;
        } else if (help) {
            return 6;
        } else {
            return 0; // Default case
        }
    }

    public void handleMove(List<String> actionNoun) {
        System.out.println("ActionNoun in handle move: " + actionNoun.get(1));
        Location location = JSON_Reader.returnLocationInformationForDirectionToMove(PlayerEngine.getCurrentLocation(), actionNoun.get(1));
        if (location != null) {
            PlayerEngine.setCurrentLocation(location.getName());
            MessageReader.printLocationMessage(location.getDescription(), location.getNorth(), location.getSouth(), location.getEast(), location.getWest());
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

    public boolean checkItemInPlayerInventory() {
        // TODO: Update this to use the JSON_Reader to look at the players inventory
        List<String> playerInventory = PlayerEngine.getPlayerInventory();
        System.out.println("You have the following items in your inventory:");
        for (String x : playerInventory
        ) {
            String y = JSON_Reader.readItemDescription(x);
            System.out.println(x + ": " + y);
        }
        return false;
    }


    public void handleLook(List<String> actionNoun) {
        if (actionNoun.size() == 1 && actionNoun.get(0).equalsIgnoreCase("look")) {
            Location location = JSON_Reader.getLocationByName(PlayerEngine.getCurrentLocation());
            if (location != null) {
                MessageReader.printLocationMessage(location.getDescription(), location.getNorth(), location.getSouth(), location.getEast(), location.getWest());
            } else {
                MessageReader.printError();
            }
        } else {
            String description = JSON_Reader.readItemDescription(actionNoun.get(1));
            if(description != null) {
                MessageReader.printItemDescription(description);
            } else {
                MessageReader.printError();
            }
        }
    }

    public void resetOptionHandler() {
        setGet(false);
        setMove(false);
        setInventory(false);
        setLook(false);
        setFire(false);
        setTalk(false);
        setHelp(false);
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
}
