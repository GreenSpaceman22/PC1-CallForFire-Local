package com.callforfire.Models;

public class Location {
    private String name;
    private String description;
    private String item;
    private String character;
    private String north;
    private String east;
    private String south;
    private String west;

    public Location(){}

    public Location(String name, String locationDescription, String item, String character, String north, String east, String south, String west) {
        this.name = name;
        this.description = locationDescription;
        this.item = item;
        this.character = character;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationDescription() {
        return description;
    }

    public void setLocationDescription(String locationDescription) {
        this.description = locationDescription;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
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

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }
}