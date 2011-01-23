package com.manageroid.application;

import android.app.Application;
import android.content.Context;

public class ManageroidApp extends Application {
	
	private static ManageroidApp instance;

    public ManageroidApp() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
