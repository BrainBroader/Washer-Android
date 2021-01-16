package com.android.washer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity{

    TextView programTV, speedTV, tempTV;
    Button startButton;

    String program;
    String speed;
    String temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        this.getSupportActionBar().setTitle("Επιβεβαίωση Ρυθμίσεων");

        Bundle bundle = getIntent().getExtras();
        program = bundle.getString("Program");
        speed = bundle.getString("Speed");
        temperature = bundle.getString("Temperature");

        ConnectViews();
        SetupListeners();

        //Intent intent = getIntent();
        //String program_name = intent.getStringExtra(MainActivity.PROGRAM_NAME);
        //String spin = intent.getStringExtra(MainActivity.SPIN);
        //String temperature = intent.getStringExtra(MainActivity.TEMP);


        //TextView programName = findViewById(R.id.program_name);
        //.setText(program_name);
        //TextView speed = findViewById(R.id.spin);
        //speed.setText(spin);
        //TextView temp = findViewById(R.id.temperature);
        //temp.setText(temperature);


    }

    private void SetupListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}