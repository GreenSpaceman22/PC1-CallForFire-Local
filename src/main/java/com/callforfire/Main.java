package com.callforfire;

import com.callforfire.App.CallForFire_App;

public class Main {
    private final CallForFire_App callForFireApp;

    public Main(CallForFire_App callForFireApp) {
        this.callForFireApp = callForFireApp;
    }

    public static void main(String[] args) {
        // Instantiate Main with a new instance of CallForFire_App
        new Main(new CallForFire_App()).callForFireApp.run();
    }
}
