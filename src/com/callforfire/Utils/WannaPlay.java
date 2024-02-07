package com.callforfire.Utils;

import com.apps.util.Prompter;

import java.util.List;
import java.util.Scanner;

public class WannaPlay {
    boolean wannaPlay = false;
    String answer;
    public static boolean playNewGame() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String asking = prompter.prompt("Would you like to play a new game? (yes/no)\n>");
        return asking.equals("yes");
    }
}