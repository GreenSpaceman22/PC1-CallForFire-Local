package com.callForFire.utils;

import java.util.Scanner;

public class WannaPlay {
    boolean wannaPlay = false;
    String answer;
    public static boolean playNewGame() {
        Scanner scanner = new Scanner(System.in);
        String asking;
        do {
            System.out.print("Would you like to play a new game? (yes/no)\n> ");
            asking = scanner.nextLine().trim().toLowerCase();
        } while (!asking.equals("yes") && !asking.equals("no"));
        return asking.equals("yes");
    }
}