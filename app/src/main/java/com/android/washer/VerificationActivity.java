package com.android.washer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        Intent intent = getIntent();
        String program_name = intent.getStringExtra(MainActivity.PROGRAM_NAME);
        String spin = intent.getStringExtra(MainActivity.SPIN);
        String temperature = intent.getStringExtra(MainActivity.TEMP);


        TextView programName = findViewById(R.id.program_name);
        programName.setText(program_name);
        TextView speed = findViewById(R.id.spin);
        speed.setText(spin);
        TextView temp = findViewById(R.id.temperature);
        temp.setText(temperature);

        final Button start = findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }




}