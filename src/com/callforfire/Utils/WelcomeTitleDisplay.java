package com.callforfire.Utils;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
        Gson gson = new Gson();
        try {
            JsonObject json = gson.fromJson(new FileReader("Data/GameText.json"), JsonObject.class);
            String welcome = json.get("intro").getAsString();
            String nextLine = json.get("next").getAsString();

            String displayTransition = prompter.prompt(nextLine);
            Console.clear();
            if(WannaPlay.playNewGame()) {
                String userInput = prompter.prompt(welcome);

                Console.clear();
            }
            else {
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}