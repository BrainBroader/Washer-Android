package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseTemperatureActivity extends AppCompatActivity {

    private ImageView temp20_infoButton, temp40_infoButton, temp60_infoButton, temp90_infoButton; //Info Buttons in Cards
    private CardView temp20_card, temp40_card, temp60_card, temp90_card; //Cards
    private TextView temp20_TV, temp40_TV, temp60_TV, temp90_TV; //Text Inside Cards
    private Button continue_button;

    private String program, speed, temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_temperature);
        this.getSupportActionBar().setTitle("Επιλογή θερμοκρασίας");

        Bundle bundle = getIntent().getExtras();
        program = bundle.getString("Program");
        speed = bundle.getString("Speed");

        ConnectViews();
        SetupListeners();
    }

    private void SetupListeners() {
        SetupCards();
        SetupInfoButtons();
        GoToConfrimActivity();
    }

    private void GoToConfrimActivity() {
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperature == null) {
                    Toast.makeText(ChooseTemperatureActivity.this, "Select an option!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("PROGRAM: ", program);
                Log.d("SPEED: ", speed);
                Log.d("TEMPERATURE: ", temperature);

                Intent intent  = new Intent(ChooseTemperatureActivity.this, VerificationActivity.class);
                intent.putExtra("Program", program);
                intent.putExtra("Speed", speed);
                intent.putExtra("Temperature", temperature);
                startActivity(intent);
            }
        });
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
                temperature = getResources().getString(R.string.twenty);
            }
        });

        temp40_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp40_card.setVisibility(View.VISIBLE);
                temperature = getResources().getString(R.string.fourty);
            }
        });

        temp60_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp60_card.setVisibility(View.VISIBLE);
                temperature = getResources().getString(R.string.sixty);
            }
        });

        temp90_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp90_card.setVisibility(View.VISIBLE);
                temperature = getResources().getString(R.string.ninety);
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
}