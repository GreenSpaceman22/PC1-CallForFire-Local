package com.callforfire.Utils;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HelpFunction {
    private static Scanner in = new Scanner(System.in);
    private static String answer;

    public static void helpFunction(List<String> parsedText) {
        // Check each word to see if it matches "help"
        for (String word : parsedText) {
            if (word == "help") {
                // If help is detected in the input display some helpful information
                //TODO move all this text to a JSON file
                System.out.println("----------BASIC HELP----------");
                System.out.println("Call for Fire is text based - you tell me what you want me to do");
                System.out.println("To move around, tell me 'go north'.");
                System.out.println("You can also use a verb (action word) and a noun (name of the thing) to interact with the environment around you.");
                System.out.println("For example, you could tell me to 'pick up cheese' or 'look at table'.");
                System.out.println("If I don't understand you, I'll let you know,");
                System.out.println("and then you should try using different words.");
                System.out.println("type 'more' if you want to see a complete list of actions you can take.");
                answer = in.nextLine();
                answer = answer.toLowerCase();
                // Display text of every verb available
                if (answer == "more") {
                    System.out.println("This is a list of all actions I understand:");
                    System.out.println("walk, run, grab, pick up, use, look, drop, talk...");
                }
            }
        }
        return;
    }

}
