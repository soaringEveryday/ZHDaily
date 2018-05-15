package com.jason.zhdaily;

import android.app.Application;
import android.content.Context;

public class CustomApplication extends Application {

    static CustomApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
