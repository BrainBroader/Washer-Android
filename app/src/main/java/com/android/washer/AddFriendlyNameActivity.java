package com.android.washer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

public class AddFriendlyNameActivity extends AppCompatActivity {

    private EditText friendlyNameEditText;
    private Button deleteButton, saveButton;
    private WasherModel washer;

    private final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friendly_name_activity);

        Gson gson = new Gson();
        washer = gson.fromJson(getIntent().getStringExtra("washerJson"), WasherModel.class);

        this.getSupportActionBar().setTitle("Φιλική ονομασία");

        friendlyNameEditText = findViewById(R.id.friendlyNameEditText);
        deleteButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);
        handleButtonListeners();
        setupTextField();
    }

    private void setupTextField() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String friendlyName = sharedPreferences.getString(washer.getId(), "");
        friendlyNameEditText.setText(friendlyName);
    }

    private void handleButtonListeners() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(washer.getId(), "");
                editor.apply();
                updateRecyclerView();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(washer.getId(), friendlyNameEditText.getText().toString());
                editor.apply();
                updateRecyclerView();
            }
        });
    }

    private void updateRecyclerView() {
        EventBus.getDefault().post("UPDATE_RECYCLER_VIEW");
    }
}
