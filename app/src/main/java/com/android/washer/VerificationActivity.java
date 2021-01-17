package com.android.washer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class VerificationActivity extends AppCompatActivity{

    TextView programTV, speedTV, tempTV;
    Button startButton;

    String[] descriptionData = {"Πρόγραμμα", "Θερμοκρασία", "Στροφές", "Επιβεβαίωση"};
    String program;
    String speed;
    String temperature;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        this.getSupportActionBar().setTitle("Επιβεβαίωση Ρυθμίσεων");

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        Bundle bundle = getIntent().getExtras();
        program = bundle.getString("Program");
        speed = bundle.getString("Speed");
        temperature = bundle.getString("Temperature");

        SetupDuration();
        ConnectViews();
        SetupListeners();
    }

    private void SetupListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(VerificationActivity.this, WashActivity.class);
                intent.putExtra("Duration", duration);
                startActivity(intent);
            }
        });
    }

    private void ConnectViews() {
        programTV = findViewById(R.id.program_name);
        speedTV = findViewById(R.id.spin);
        tempTV = findViewById(R.id.temperature);
        startButton = findViewById(R.id.start_button);

        program = getResources().getString(R.string.ver_program)+ " " + program;
        speed = getResources().getString(R.string.ver_speed)+ " " + speed;
        temperature = getResources().getString(R.string.ver_temp)+ " " +temperature;

        programTV.setText(program);
        speedTV.setText(speed);
        tempTV.setText(temperature);
    }

    private void SetupDuration() {
        if (program.equals(getResources().getString(R.string.fast))) {
            duration = 720000;
        } else if (program.equals(getResources().getString(R.string.eco))) {
            duration = 8600000;
        } else if (program.equals(getResources().getString(R.string.cotton))) {
            duration = 1500000;
        } else if (program.equals(getResources().getString(R.string.synthetic))) {
            duration = 3700000;
        } else if (program.equals(getResources().getString(R.string.vul))) {
            duration = 5500000;
        } else if (program.equals(getResources().getString(R.string.mallina))) {
            duration = 7200000;
        } else if (program.equals(getResources().getString(R.string.white))) {
            duration = 9900000;
        }
    }
}