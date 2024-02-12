package com.callForFire.utils.audio;


import java.io.IOException;

public class AudioPlayer {

    private static boolean isMuted = false;
    public static Process execute;
    private static Thread thread;

    private static final String audioCmd = "cmd /c start /B Data/audio/javaProjAudi.wav\n" +
            "afplay Data/audio/javaProjAudi.wav &";
    private static final String mortarFire = "cmd /c start /B Data/audio/hq-explosion-6288.mp3\n" +
            "afplay Data/audio/hq-explosion-6288.mp3 &";
    private static final String mute = "cmd /c start /B Data/audio/mute.mp3\n" +
            "afplay Data/audio/mute.mp3 &";

    public static void unmute() {
        isMuted = false;
        if (execute == null) {
            try {
                thread = new Thread(() -> {
                    try {
                        execute = Runtime.getRuntime().exec(audioCmd);
                        execute.waitFor();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (execute != null) {
            execute.destroy();
        }
    }

    public static void mute() {
        isMuted = true;
        try {
            execute = Runtime.getRuntime().exec(mute);
            execute.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int sleepTimeSetter() {
        int timer = 0;
        if (!isMuted) {
            timer = 91000;
        }
        else {
            timer = 100;
        }

        return timer;
    }

    public static void fireMortar() {
        if (!isMuted) {
            try {
                execute = Runtime.getRuntime().exec(mortarFire);
                execute.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean isIsMuted() {
        return isMuted;
    }

    public static void setIsMuted(boolean isMuted) {
        AudioPlayer.isMuted = isMuted;
        execute.destroy();
    }

}