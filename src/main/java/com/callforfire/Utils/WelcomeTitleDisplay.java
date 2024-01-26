package src.main.java.com.callforfire.Utils.Images;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class WelcomeTitleDisplay {
    public static void render(String thingToRender) {
        String renderImage = thingToRender;
        Scanner scanner = new Scanner(System.in);
        String filePath = String.format("src/main/java/com/callforfire/Utils/Images/banners/%s.txt", renderImage);
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("press enter to continue.");
        scanner.nextLine();
        clearConsole();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}