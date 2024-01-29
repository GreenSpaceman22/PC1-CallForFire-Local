package com.callforfire.Utils;

import com.apps.util.Console;

import java.util.List;
import java.util.Scanner;

public class InvalidCommand {

    // method called when the game doesn't understand the input
    // TODO break this up into two methods:
        // TODO one to handle invalid verbs
        // TODO and one to handle invalid nouns
    public static void showInvalidCommand(List<String> parsedText) {
        // TODO should probably add this to a JSON
        // displays a general help text and returns
        System.out.println("Sorry, I didn't understand that.\n");
        Console.pause(200);
        System.out.println("Try rephrasing your command.\n");
        Console.pause(200);
        System.out.println("Or you can type 'help' for the help menu, or 'quit' to quit the game.");
        return;
    }
}