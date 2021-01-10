package com.android.washer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

public class AddFriendlyNameActivity extends BaseActivity {

    private EditText friendlyNameEditText;
    private Button deleteButton, saveButton;
    private WasherModel washer;
    private String friendlyName;

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
        handleTextWatcher();
        setupTextField();
        setupButtons();
    }

    private void setupTextField() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        friendlyName = sharedPreferences.getString(washer.getId(), "");
        friendlyNameEditText.setText(friendlyName);
    }

    private void setupButtons() {
        saveButton.setEnabled(false);
        saveButton.setAlpha((float) 0.5);

        if (friendlyName.equals("")) {
            deleteButton.setEnabled(false);
            deleteButton.setAlpha((float) 0.5);
        } else {
            deleteButton.setEnabled(true);
            deleteButton.setAlpha((float) 1.0);
        }
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
                friendlyName = "";
                setupButtons();
                friendlyNameEditText.setText("");
                Toast.makeText(getApplicationContext(), "Η φιλική ονομασία διαγράφτηκε.", Toast.LENGTH_SHORT).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String newFriendlyName = friendlyNameEditText.getText().toString();
                editor.putString(washer.getId(), newFriendlyName);
                editor.apply();
                updateRecyclerView();
                friendlyName = newFriendlyName;
                setupButtons();
                Toast.makeText(getApplicationContext(), "Η φιλική ονομασία αποθηκεύτηκε!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleTextWatcher() {
        friendlyNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });
    }

    private void changeButtonState() {
        String text = friendlyNameEditText.getText().toString();
        if (text.equals("")) {
            saveButton.setEnabled(false);
            saveButton.setAlpha((float) 0.5);
        } else {
            saveButton.setEnabled(true);
            saveButton.setAlpha((float) 1.0);
        }
    }

    private void updateRecyclerView() {
        EventBus.getDefault().post("UPDATE_RECYCLER_VIEW");
    }
}
