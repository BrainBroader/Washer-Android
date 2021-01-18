package com.android.washer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

public class AppSettingsActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private Button saveButton;
    private String selectedLanguage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        radioGroup = findViewById(R.id.radioGroup);
        saveButton = findViewById(R.id.saveButton);
        setupView();
        handleRadioGroup();
        handleSaveButton();
    }

    private void setupView() {
        saveButton.setEnabled(false);
        saveButton.setAlpha((float) 0.5);

        if (getCurrentLanguage().equals("en")) {
            RadioButton radioButton = findViewById(R.id.radio_button_2);
            radioButton.setChecked(true);
        }
    }

    private void handleRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button_1) {
                    selectedLanguage = "el";
                } else {
                    selectedLanguage = "en";
                }
                updateSaveButtonStatus(selectedLanguage);
            }
        });
    }

    private void updateSaveButtonStatus(String selectedLanguage) {
        if (selectedLanguage.equals(getCurrentLanguage())) {
            saveButton.setEnabled(false);
            saveButton.setAlpha((float) 0.5);
        } else {
            saveButton.setEnabled(true);
            saveButton.setAlpha((float) 1.0);
        }
    }

    private void handleSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLocale(getBaseContext(), selectedLanguage);
                Runtime.getRuntime().exit(0);
            }
        });
    }
}
