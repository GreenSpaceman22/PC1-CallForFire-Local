package com.callForFire.utils.audio;

public class AudioPlayer {

    public static boolean isMuted = false;
    public static Process execute;

    public static String audioCmd = "cmd /c start /B Data/audio/javaProjAudi.wav";

    public static void audioPlayer() {
        if (execute == null) {
            try {
                System.out.println("Opening audio");
                String audioCmd = "cmd /c start /B Data/audio/javaProjAudi.wav";

                if (!isMuted) {
                    Thread thread = new Thread(() -> {
                        try {
                            while (!isMuted) {
                                execute = Runtime.getRuntime().exec(audioCmd);
                                execute.waitFor();
                                Thread.sleep(91000);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (execute != null) {
            execute.destroy();
        }
    }
}