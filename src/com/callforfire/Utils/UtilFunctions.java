package com.callforfire.Utils;

import java.util.Scanner;

public class UtilFunctions {

    //pauses for x number of milliseconds
    public static void pauseFunction(int x) {
        try {
            Thread.sleep(x);
            // TODO: add a way to break out of the pause here
            // if (user presses 'spacebar') {
            //       Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void helpFunction() {
        // If help is detected in the input, display some helpful information
        System.out.println("----------BASIC HELP----------");
        System.out.println("Call for Fire is text based - you tell me what you want me to do\n");
        pauseFunction(500);
        System.out.println("To move around, tell me 'go north'.\n");
        pauseFunction(500);
        System.out.println("You can also use a verb (action word) and a noun (name of the thing) to interact with the environment around you.");
        pauseFunction(500);
        System.out.println("For example, you could tell me to 'pick up cheese' or 'look at the table' or 'talk to pvt-snuffy.\n");
        pauseFunction(500);
        System.out.println("If I don't understand you, I'll let you know,");
        pauseFunction(500);
        System.out.println("and then you should try using different words.\n");
        return;
    }

    public static void confirmAndQuitGame() {
        Scanner in = new Scanner(System.in);
        String answer;
        System.out.println("Are you sure you want to quit Call for Fire?\n");
        System.out.println("Enter Y for yes or N for no");
        answer = in.nextLine();
        // if they confirm, quit the game
        if (answer.equalsIgnoreCase("y".trim())) {
            System.out.println("Goodbye");
            pauseFunction(1000);
            System.exit(0);
        }
        else {
            return;
        }
    }

    public static void showInvalidCommand(String userInput) {
        // displays a general 'didn't understand that' with suggestions
        System.out.println("Sorry, I didn't understand your command of:\n\t '" + userInput + "'");
        pauseFunction(200);
        System.out.println("\nTry rephrasing your command, or you can type 'help' for more details.");
        pauseFunction(500);
        return;
    }

}