package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseTemperature extends AppCompatActivity {

    ImageView temp20_infoButton, temp40_infoButton, temp60_infoButton, temp80_infoButton; //Info Buttons in Cards
    CardView temp20_card, temp40_card, temp60_card, temp80_card; //Cards
    TextView temp20_TV, temp40_TV, temp60_TV, temp80_TV; //Text Inside Cards
    Button continue_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_temperature);
        HideActionBar();

        ConnectViews();
        ClickToContinue();
    }

    private void ClickToContinue() {
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent  = new Intent(ChooseTemperature.this, .class);
                //ChooseTemperature.this.startActivity(intent);

            }
        });
    }

    private void ConnectViews() {
        temp20_card = findViewById(R.id.temp20_card);
        temp40_card = findViewById(R.id.temp40_card);
        temp60_card = findViewById(R.id.temp60_card);
        temp80_card = findViewById(R.id.temp80_card);

        temp20_infoButton = findViewById(R.id.temp20_infoButton);
        temp40_infoButton = findViewById(R.id.temp40_infoButton);
        temp60_infoButton = findViewById(R.id.temp60_infoButton);
        temp80_infoButton = findViewById(R.id.temp80_infoButton);

        temp20_TV = findViewById(R.id.temp20_TV);
        temp40_TV = findViewById(R.id.temp40_TV);
        temp60_TV = findViewById(R.id.temp60_TV);
        temp80_TV = findViewById(R.id.temp80_TV);

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