package com.zzu.ehome.ehome.app;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Mersens on 2016/9/28.
 */

public class App extends Application {
    public static List<Activity> mList = new LinkedList<Activity>();
    private static App mApp;
    public static App getInstance() {
        if (mApp == null) {
            synchronized (App.class) {
                if (mApp == null) {
                    mApp = new App();
                }
            }
        }
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
