package com.callforfire.Models;

public class Item {

    private String itemName;
    private String itemDescription;
    private String roundType;

    public Item(){}

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public Item(String itemName, String itemDescription, String roundType) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.roundType = roundType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }
}