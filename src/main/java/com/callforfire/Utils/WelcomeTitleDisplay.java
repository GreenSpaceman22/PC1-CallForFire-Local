package com.callforfire.Utils;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class WelcomeTitleDisplay {
    private static final Prompter prompter = new Prompter(new Scanner(System.in));
    private static List<String> fileToRead = null;
    public static void render(String thingToRender) {
        try {
            fileToRead = Files.readAllLines(Path.of("Images/banners/"+thingToRender+".txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String line : fileToRead) {
            System.out.println(line);
        }

        String displayTransition = prompter.prompt("\n\nPress enter to continue\n> ");
        Console.clear();
        if(WannaPlay.playNewGame()) {
            String userInput = prompter.prompt("You awaken with a jolt. \nThe acrid scent of smoke and the distant thud of explosions assaulting your senses. \nYou find yourself in a mortar pit, surrounded by sandbags and the chaos of battle. \nThe crackle of your radio pierces the air. \nUrgent voices relay requests for fire support. \nYou grasp the radio, and a voice on the other end relays coordinates. \nYour comrades need your mortar fire to hold the enemy back. \nIt seems your mortar tube, base plate, and ammo are scattered. \nYou must locate and assemble your mortar to provide the much-needed support.\n The radio continues its relentless chatter. \nYou look to your team for help. \n \nPress enter to continue\n> ");

            Console.clear();
        }
        else {
            System.exit(0);
        }


    }
}