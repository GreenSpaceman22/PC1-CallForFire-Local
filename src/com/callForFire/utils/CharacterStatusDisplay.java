package com.callForFire.utils;

import java.util.List;

public class CharacterStatusDisplay {

    private String doubleLineBreak = "===================================="; // 26 x =
    private String blackFont = "\u001b[30m";
    private String redFont = "\u001b[31m";
    private String greenFont = "\u001b[32m";
    private String yellowFont = "\u001b[33m";
    private String blueFont = "\u001b[34m";
    private String magentaFont = "\u001b[35m";
    private String cyanFont = "\u001b[36m";
    private String whiteFont = "\u001b[37m";
    private String resetFontColor = "\u001b[0m";

    private String blackBackground = "\u001b[40m";
    private String greenBackground = "\u001b[42m";
    private String whiteBackground = "\u001b[47m";
    private String yellowBackground = "\u001b[43m";
    private String redBackground = "\u001b[41m";
    private String resetStyles = "\u001b[0m";
    private String healthBackground;

    // changes the background color based on the player's health
    public String setBackgroundColor(int health) {
        if (health > 50) {
            healthBackground = whiteBackground;
        } else if (health > 20) {
            healthBackground = yellowBackground;
        } else if (health < 20) {
            healthBackground = redBackground;
        }
        return healthBackground;
    }

    public void displayCharacterInfo(String name, int health, String location, List<String> inventory) {
        System.out.println("\t" +greenBackground + blackFont + doubleLineBreak + resetStyles);
        System.out.println("\t" +greenBackground + blackFont + name + "'s CURRENT STATUS \t\t\t" + resetStyles);
        System.out.println("\t" +greenBackground + blackFont + doubleLineBreak + resetStyles);
        System.out.println("\t" +setBackgroundColor(health) + blackFont + "HEALTH:\t\t\t\t" + greenFont + health + "\t\t\t\t" + resetStyles);
        System.out.println("\t" +setBackgroundColor(health) + blackFont + "LOCATION:\t\t\t" + blueFont + location + "\t\t" + resetStyles);
        System.out.println("\t" +setBackgroundColor(health) + blackFont + "INVENTORY:\t\t\t\t\t\t\t" + resetStyles);
        if (inventory != null) {
            for (String item : inventory) {
                System.out.println("\t" +setBackgroundColor(health) + blackFont + "\t" + item + "\t\t\t" + resetStyles);
            }
        } else System.out.println("\t" +setBackgroundColor(health) + blackFont + "\t Your inventory is empty\t\t" + resetStyles);
        System.out.println(resetStyles);
        return;
    }


}
