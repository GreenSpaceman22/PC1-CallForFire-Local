package src.main.java.com.callforfire.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WelcomeTitleDisplay {
    public static void render(String thingToRender) {
        String renderImage = thingToRender;
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
    }
}