package com.callForFire.utils.audio;

public class AudioPlayer {
    public static void audioPlayer(){
        try {
            String audioCmd = "cmd /c start wmplayer Data/audio/javaProjAudi.wav";

            Process execute = Runtime.getRuntime().exec(audioCmd);
            execute.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}