package com.callforfire.GameEngines.SupportEngines;

import com.callforfire.GameEngines.PlayerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JSON_Writer {
    private static final String PLAYER_FILE_PATH = "Data/Player.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writePlayerToFile(PlayerEngine player) {
        try (FileWriter writer = new FileWriter(PLAYER_FILE_PATH)) {
            gson.toJson(player, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}