package com.android.washer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String locale = sharedPreferences.getString("LANGUAGE", "el");
        changeLocale(getBaseContext(), locale);
        super.onCreate(savedInstanceState);
    }

    public void changeLocale(Context context, String locale) {
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(locale);
        res.updateConfiguration(conf, res.getDisplayMetrics());

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LANGUAGE", locale);
        editor.apply();
        editor.commit();
    }

    public String getCurrentLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String locale = sharedPreferences.getString("LANGUAGE", "el");
        return locale;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
