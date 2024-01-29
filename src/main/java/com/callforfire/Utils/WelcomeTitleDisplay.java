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

        String userInput = prompter.prompt("Whatchu wanna do cuh");

        Console.clear();
    }
}