package com.android.washer;

import android.content.Context;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

public class AppSettingsActivity extends BaseActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        radioGroup = findViewById(R.id.radioGroup);
        handleRadioGroup();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private void handleRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button_1) {
                    LocaleHelper.setLocale(getApplicationContext(), "el");
                } else {
                    LocaleHelper.setLocale(getApplicationContext(), "el");
                }
                Runtime.getRuntime().exit(0);
            }
        });
    }
}
