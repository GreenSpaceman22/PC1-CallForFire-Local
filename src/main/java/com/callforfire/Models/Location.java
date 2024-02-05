package com.callforfire.Models;

public class Location {
    private static String name;
    private static String description;
    private static String item;
    private static String character;
    private static String north;
    private static String east;
    private static String south;
    private static String west;



    private static String currentLocation = "Mortar pit";

    public Location(){}

    public Location(String name, String locationDescription, String item, String character, String north, String east, String south, String west) {
        Location.name = name;
        description = locationDescription;
        Location.item = item;
        Location.character = character;
        Location.north = north;
        Location.east = east;
        Location.south = south;
        Location.west = west;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Location.name = name;
    }

    public String getLocationDescription() {
        return description;
    }

    public static void setLocationDescription(String locationDescription) {
        description = locationDescription;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        Location.item = item;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        Location.character = character;
    }

    public String getNorth() {
        return north;
    }

    public static void setNorth(String north) {
        Location.north = north;
    }

    public String getEast() {
        return east;
    }

    public static void setEast(String east) {
        Location.east = east;
    }

    public String getSouth() {
        return south;
    }

    public static void setSouth(String south) {
        Location.south = south;
    }

    public String getWest() {
        return west;
    }

    public static void setWest(String west) {
        Location.west = west;
    }
    public static String getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(String currentLocation) {
        Location.currentLocation = currentLocation;
    }
}