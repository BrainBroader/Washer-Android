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

    ImageView temp20_infoButton, temp40_infoButton, temp60_infoButton, temp90_infoButton; //Info Buttons in Cards
    CardView temp20_card, temp40_card, temp60_card, temp90_card; //Cards
    TextView temp20_TV, temp40_TV, temp60_TV, temp90_TV; //Text Inside Cards
    Button continue_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_temperature);
        HideActionBar();

        ConnectViews();
        SetupListeners();
    }

    private void SetupListeners() {
        SetupCards();
        SetupInfoButtons();
    }

    private void openDialog(String title, String description){
        InfoDialog infoDialog = new InfoDialog(title, description);
        infoDialog.show(getSupportFragmentManager(), "info button");
    }

    private void SetupCards() {

        temp20_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp20_card.setVisibility(View.VISIBLE);
            }
        });

        temp40_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp40_card.setVisibility(View.VISIBLE);
            }
        });

        temp60_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp60_card.setVisibility(View.VISIBLE);
            }
        });

        temp90_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp90_card.setVisibility(View.VISIBLE);
            }
        });

    }

    private void SetupInfoButtons() {

        temp20_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.twenty);
                String description = "Details about this";
                openDialog(title, description);
            }
        });

        temp40_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.fourty);
                String description = "Details about this";
                openDialog(title, description);
            }
        });

        temp60_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.sixty);
                String description = "Details about this";
                openDialog(title, description);
            }
        });

        temp90_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.ninety);
                String description = "Details about this";
                openDialog(title, description);
            }
        });

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent  = new Intent(ChooseTemperature.this, .class);
                //ChooseTemperature.this.startActivity(intent);

            }
        });
    }

    private void ClearPickedCards() {
        temp20_card.setVisibility(View.INVISIBLE);
        temp40_card.setVisibility(View.INVISIBLE);
        temp60_card.setVisibility(View.INVISIBLE);
        temp90_card.setVisibility(View.INVISIBLE);
    }

    private void ConnectViews() {
        temp20_card = findViewById(R.id.temp20_card);
        temp40_card = findViewById(R.id.temp40_card);
        temp60_card = findViewById(R.id.temp60_card);
        temp90_card = findViewById(R.id.temp90_card);

        temp20_infoButton = findViewById(R.id.temp20_infoButton);
        temp40_infoButton = findViewById(R.id.temp40_infoButton);
        temp60_infoButton = findViewById(R.id.temp60_infoButton);
        temp90_infoButton = findViewById(R.id.temp90_infoButton);

        temp20_TV = findViewById(R.id.temp20_TV);
        temp40_TV = findViewById(R.id.temp40_TV);
        temp60_TV = findViewById(R.id.temp60_TV);
        temp90_TV = findViewById(R.id.temp90_TV);

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