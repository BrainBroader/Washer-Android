package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseSpeedActivity extends AppCompatActivity {

    ImageView infoButton_400, infoButton_800, infoButton_1000, infoButton_1200, infoButton_1600; //Info Buttons in Cards
    CardView turns400_card, turns800_card, turns1000_card, turns1200_card, turns1600_card; //Cards
    TextView turns400_TV, turns800_TV, turns1000_TV, turns1200_TV, turns1600_TV; //Text Inside Cards
    Button continue_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_speed);
        HideActionBar();

        ConnectViews();
        SetupListeners();

    }

    private void SetupListeners() {

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ChooseSpeedActivity.this, ChooseTemperature.class);
                ChooseSpeedActivity.this.startActivity(intent);
            }
        });
    }

    private void ConnectViews() {
        turns400_card = findViewById(R.id.turns400_card);
        turns800_card = findViewById(R.id.turns800_card);
        turns1000_card = findViewById(R.id.turns1000_card);
        turns1200_card = findViewById(R.id.turns1200_card);
        turns1600_card = findViewById(R.id.turns1600_card);

        infoButton_400 = findViewById(R.id.infoButton_400);
        infoButton_800 = findViewById(R.id.infoButton_800);
        infoButton_1000 = findViewById(R.id.infoButton_1000);
        infoButton_1200 = findViewById(R.id.infoButton_1200);
        infoButton_1600 = findViewById(R.id.infoButton_1600);

        turns400_TV = findViewById(R.id.turns400_TV);
        turns800_TV = findViewById(R.id.turns800_TV);
        turns1000_TV = findViewById(R.id.turns1000_TV);
        turns1200_TV = findViewById(R.id.turns1200_TV);
        turns1600_TV = findViewById(R.id.turns1600_TV);

        continue_button = findViewById(R.id.continue_button);
    }

    private void HideActionBar() {
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}