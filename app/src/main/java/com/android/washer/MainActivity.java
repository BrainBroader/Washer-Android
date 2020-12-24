package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String PROGRAM_NAME = "com.example.android.washer.extra.PROGRAM_NAME";
    public static final String SPIN = "com.example.android.washer.extra.SPIN";
    public static final String TEMP = "com.example.android.washer.extra.TEMP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchVerification(v);
            }
        });
    }

    public void launchVerification(View view){
        Intent intent = new Intent(this, com.android.washer.VerificationActivity.class);
        String programName = "Πρόγραμμα: Βαμβακερά";
        intent.putExtra(PROGRAM_NAME, programName);
        String speed = "Στροφές: 800";
        intent.putExtra(SPIN, speed);
        String temp = "Θερμοκρασία: 40";
        intent.putExtra(TEMP, temp);
        startActivity(intent);
    }


}