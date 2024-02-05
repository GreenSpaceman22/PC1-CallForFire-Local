package com.callforfire.GameEngines;

import com.callforfire.GameEngines.SupportEngines.JSON_Reader;
import com.callforfire.GameEngines.SupportEngines.MessageReader;
import com.callforfire.Models.NPC;
import com.callforfire.Utils.InvalidCommand;

import java.util.ArrayList;
import java.util.List;

public class OptionHandler {
    private boolean get;
    private boolean move;
    private boolean fire;
    private boolean talk;
    private boolean look;
    private String actionResponse; // This should be set to whatever verb the user passed based on the booleans above after reading the JSON
    private List<String> actionNoun = new ArrayList<>();
    //    private List<String> nouns = new getJSONObject("nouns"); // intent is to get a list of all nouns from the JSON file
    private List<String> nouns = new ArrayList<>();
    private static String locationChoice = "north"; // This should be set if move is 'True', then read the JSON with this String
    private static String locationName = "Firing Point";
    private static String character = "Joe Snuffy";
    private static String item = "broken mortar tube";


    // These temporary for testing, these should be set by the JSON_Reader later,  we can create a seperate class for handling this later maybe?
    private String locationDescription = "You hoof your way to the firing point, the equipment is no longer there except for a broken mortar tube, and private Snuffy.";
    private String north;
    private String east;
    private String west;
    private String south;

    // Methods
    public void run(List<String> actionNoun) {
        handlePlayerAction(this.isGet(), this.isMove(), this.isFire(), this.isTalk(), this.isLook(), actionNoun);
    }

    public void handlePlayerAction(boolean get, boolean move, boolean fire, boolean talk, boolean look, List<String> actionNoun) {
        // Determine which case to execute based on boolean variables
        int caseNumber = determineCase(get, move, fire, talk, look);

        switch (caseNumber) {
            case 1:
                handleMove(locationChoice, actionNoun);
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
            default:
                // Default case
                break;
        }
    }


    private static int determineCase(boolean get, boolean move, boolean fire, boolean talk, boolean look) {
        if (move) {
            return 1;
        } else if (get) {
            return 2;
        } else if (talk) {
            return 3;
        } else if (look) {
            return 4;
        } else {
            return 0; // Default case
        }
    }

    private void handleNounChoice(List<String> actionNoun) {
        // TODO: Update this to use the JSON_Reader to check if noun is a location or item;
        if (actionNoun.get(1).equals("north".toLowerCase())) {
            setLocationChoice("north");
            setLocationDescription("You hoof your way to the firing point, the equipment is no longer there except for a broken mortar tube, and private Snuffy.");
            setNorth("Hesco Barriers");
            setSouth("Mortar pit");
            setWest("TOC");
            setEast("Vehicle Yard");
            PlayerEngine.setCurrentLocation("Firing Point");
        } else if (actionNoun.get(1).equalsIgnoreCase("south")) {
            setLocationDescription("You can not go to the barriers, they are there for your protection");
        } else if (actionNoun.get(1).equalsIgnoreCase("east")) {
            setLocationChoice("east");
            setLocationDescription("You walk into the range, \n" +
                    "There is your mortars base plate");
            setNorth("TOC");
            setWest("Mortar Pit");
            setEast("Quarters");
            setSouth("Hesco Barriers");
            PlayerEngine.setCurrentLocation("Range");
        } else if (actionNoun.get(1).equalsIgnoreCase("west")) {
            setLocationChoice("west");
            setLocationDescription("You enter into the Ammo Depot \n" +
                    "There are rounds, and unused cheese charges ");
            setNorth("Vehicle Yard");
            setSouth("Hesco Barriers");
            setWest("Hesco Barriers");
            setEast("Mortar Pit");
            PlayerEngine.setCurrentLocation("Ammo Depot");
        }
    }

    // Handle if the movement command was given
    public void handleMove(String locationChoice, List<String> actionNoun) {
        handleNounChoice(actionNoun); // Set the next location variables

        MessageReader.printLocationMessage(getLocationDescription(), getNorth(), getSouth(), getEast(), getWest());
    }

    public void handleTalkWithNpc(String npcName) {
        NPC npcDialogue = JSON_Reader.readNpcDialogue(npcName);
        if (npcDialogue != null) {
            MessageReader.printNPCDialogue(npcDialogue);
        } else {
            MessageReader.printError();
        }
    }

    public static void returnOptionFromJsonLocation(String location) {
        // TODO: Make this read from the JSON_Reader passing the location the of whichever direction the user picked I.E (north, south, yatta)

        // The location argument needs to be whatever was read from the json in the direction the user picked
    }

    public boolean checkItemInPlayerInventory() {
        // TODO: Update this to use the JSON_Reader to look at the players inventory
        return false;
    }

    // List<String> actionNoun passed from TextParser
    // actionNoun[0] is the verb and actionNoun[1] is the noun
    public void handleLook(List<String> actionNoun) {
        String noun = actionNoun.get(1);
        if (actionNoun.size() < 2) {
            MessageReader.printLocationMessage(getLocationDescription(), getNorth(), getEast(), getSouth(), getWest());
        }
        // check and see if noun is in the JSON list of nouns; if not give invalid command
        else if (!nouns.contains(noun)) {
            InvalidCommand.showInvalidCommand(actionNoun);
        } else if (nouns.contains(noun)) {
            System.out.println("\nYou take a closer look and see that " + noun + " is here");
        }
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

    public static String getLocationChoice() {
        return locationChoice;
    }

    public static void setLocationChoice(String locationChoice) {
        OptionHandler.locationChoice = locationChoice;
    }

    public static String getLocationName() {
        return locationName;
    }

    public static void setLocationName(String locationName) {
        OptionHandler.locationName = locationName;
    }

    public static String getCharacter() {
        return character;
    }

    public static void setCharacter(String character) {
        OptionHandler.character = character;
    }

    public static String getItem() {
        return item;
    }

    public static void setItem(String item) {
        OptionHandler.item = item;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }
}
