package com.callforfire.GameEngines;

import com.callforfire.Utils.InvalidCommand;

import com.apps.util.Console;
import com.callforfire.GameEngines.SupportEngines.MessageReader;

import java.util.List;

public class OptionHandler {
    private boolean get;
    private boolean move;
    private boolean fire;
    private boolean talkOrLook;
    private String actionResponse; // This should be set to whatever verb the user passed based on the booleans above after reading the JSON
    private List<String> actionNoun = new TextParser().getActionNoun();
    private List<String> nouns = new getJSONObject("nouns"); // intent is to get a list of all nouns from the JSON file
    private static String locationChoice = "north"; // This should be set if move is 'True', then read the JSON with this String
    private static String locationName = "Firing Point";
    private static String character = "Joe Snuffy";
    private static String item = "broken mortar tube";


    // These temporary for testing, these should be set by the JSON_Reader later,  we can create a seperate class for handling this later maybe?
    private String locationDescription = "You hoof your way to the firing point, the equipment is no longer there except for a broken mortar tube, and private Snuffy.";
    private String north = "Barbed Wire";
    private String east = "TOC";
    private String west = "Vehicle Yard";
    private String south = "Mortar Pit";

    // Methods
    public void run(List<String> actionNoun) {
        handlePlayerAction(this.isGet(), this.isMove(), this.isFire(), this.isTalkOrLook(), actionNoun);
    }

    public void handlePlayerAction(boolean get, boolean move, boolean fire, boolean talkOrLook, List<String> actionNoun) {
        // Determine which case to execute based on boolean variables
        int caseNumber = determineCase(get, move, fire, talkOrLook);

        switch (caseNumber) {
            case 1:
                handleMove(locationChoice);
                break;
            case 2:
                // HandleLocation
                break;
            // Add more cases as needed
            default:
                // Default case
                break;
        }
    }


    private static int determineCase(boolean get, boolean move, boolean fire, boolean talkOrLook) {

        System.out.println(move);
        if (move) {
            return 1;
        } else if (get) {
            return 2;
        } else if (talkOrLook) {
            return 3;
        } else {
            return 0; // Default case
        }
    }

    // Handle if the movement command was given
    public void handleMove(String locationChoice) {
        // TODO: use the JSON_Reader to determine information of the "location" argument they went to
        // For now we will hard code the response

        MessageReader.printLocationMessage(getLocationDescription(), getNorth(), getEast(), getSouth(), getWest());
        Console.pause(2);
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
    public void doLook(List<String> actionNoun) {
        String noun  = actionNoun.get(1);
        // check and see if noun is in the JSON list of nouns; if not give invalid command
        if (!nouns.contains(noun)) {
            InvalidCommand.showInvalidCommand(actionNoun);
        }
        else {
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

    public boolean isTalkOrLook() {
        return talkOrLook;
    }

    public void setTalkOrLook(boolean talkOrLook) {
        this.talkOrLook = talkOrLook;
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
