package com.android.washer;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base, "el"));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.setLocale(this, "el");
    }
}
