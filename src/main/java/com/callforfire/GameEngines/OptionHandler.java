package com.callforfire.GameEngines;

public class OptionHandler {
    private boolean get;
    private boolean move;
    private boolean fire;
    private boolean talkOrLook;
    private String actionResponse; // This should be set to whatever verb the user passed based on the booleans above after reading the JSON
    private static String locationChoice = "north"; // This should be set if move is 'True', then read the JSON with this String
    private static String locationName = "Firing Point";
    private static String character = "Joe Snuffy";
    private static String item = "broken mortar tube";

    // Methods
    public void run() {

    }


    public static void returnOptionFromJsonLocation(String location) {
        // TODO: Make this read from the JSON_Reader passing the location the of whichever direction the user picked I.E (north, south, yatta)

        // The location argument needs to be whatever was read from the json in the direction the user picked
    }

    public boolean checkItemInPlayerInventory() {
        // TODO: Update this to use the JSON_Reader to look at the players inventory
        return false;
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
}
