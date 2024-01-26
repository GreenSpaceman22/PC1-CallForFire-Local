package com.callforfire.Utils;

import java.util.List;
import java.util.Scanner;

public class QuitGame {

    private static Scanner in = new Scanner(System.in);
    private static String answer;

    // method to quit the game using the parsedText list passed in from the GameEngine
    public static void confirmAndQuitGame(List<String> parsedText) {
        // Check each word to see if it matches "quit"
        for (String word : parsedText) {
            if (word == "quit") {
                // If quit is found, ask for confirmation if 'quit' is detected in the input
                System.out.println("Are you sure you want to quit Call for Fire?");
                System.out.println("Enter Y for yes or N for no");
                answer = in.nextLine();
                // if they confirm, quit the game
                if (answer == "y" || answer =="Y") {
                    System.exit(0);
                }
                // If they don't confirm, keep going
                else {
                    return;
                }
            }
        }

    }
}
