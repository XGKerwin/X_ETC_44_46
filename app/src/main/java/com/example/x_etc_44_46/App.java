package com.example.x_etc_44_46;

import android.app.Application;

import org.litepal.LitePal;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}