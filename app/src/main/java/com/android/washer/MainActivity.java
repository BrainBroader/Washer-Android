package com.android.washer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    private Button myWashersButton, optionsButton, startButton, minimizedActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ConnectViews();
        setupListeners();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String text) {
        if (text.equals("MINIMIZE_FUNCTION")) {
            initMinimize();
        }
    };

    private void initMinimize() {
        Log.d("MINIMIZE", "MPHKA");
        minimizedActivityButton.setVisibility(View.VISIBLE);
    }

    private void ConnectViews() {
        myWashersButton = findViewById(R.id.myWashersButton);
        optionsButton = findViewById(R.id.optionsButton);
        startButton = findViewById(R.id.startWashingButton);
        minimizedActivityButton = findViewById(R.id.minimizedActivityButton);
    }

    private void setupListeners() {
        myWashersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditWasherActivity.class));
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AppSettingsActivity.class));
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChooseWasherActivity.class));
            }
        });

        minimizedActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
