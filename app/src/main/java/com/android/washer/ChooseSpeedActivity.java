package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class ChooseSpeedActivity extends BaseActivity {

    private ImageView infoButton_400, infoButton_800, infoButton_1000, infoButton_1200, infoButton_1600; //Info Buttons in Cards
    private CardView turns400_card, turns800_card, turns1000_card, turns1200_card, turns1600_card; //Cards
    private TextView turns400_TV, turns800_TV, turns1000_TV, turns1200_TV, turns1600_TV; //Text Inside Cards
    private Button continue_button;

    private String[] descriptionData = {"Πρόγραμμα", "Θερμοκρασία", "Στροφές", "Επιβεβαίωση"};
    private String program, temperature, speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_speed);
        this.getSupportActionBar().setTitle("Επιλογή στροφών");

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        Bundle bundle = getIntent().getExtras();
        program = bundle.getString("Program");
        temperature = bundle.getString("Temperature");

        ConnectViews();
        SetupListeners();

    }

    private void SetupListeners() {
        SetupCards();
        GoToChooseTemperatureActivity();
    }

    private void GoToChooseTemperatureActivity() {
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ChooseSpeedActivity.this, VerificationActivity.class);
                intent.putExtra("Program", program);
                intent.putExtra("Speed", speed);
                intent.putExtra("Temperature", temperature);
                ChooseSpeedActivity.this.startActivity(intent);
            }
        });
    }

    private void SetupCards() {

        turns400_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                turns400_card.setVisibility(View.VISIBLE);
                speed = getResources().getString(R.string.four_hundrend);
                EnableButton();
            }
        });

        turns800_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                turns800_card.setVisibility(View.VISIBLE);
                speed = getResources().getString(R.string.eight_hundrend);
                EnableButton();
            }
        });

        turns1000_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                turns1000_card.setVisibility(View.VISIBLE);
                speed = getResources().getString(R.string.one_thousand);
                EnableButton();
            }
        });

        turns1200_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                turns1200_card.setVisibility(View.VISIBLE);
                speed = getResources().getString(R.string.one_thousand_two);
                EnableButton();
            }
        });

        turns1600_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                turns1600_card.setVisibility(View.VISIBLE);
                speed = getResources().getString(R.string.one_thousand_six);
                EnableButton();
            }
        });
    }

    private void ClearPickedCards() {
        turns400_card.setVisibility(View.INVISIBLE);
        turns800_card.setVisibility(View.INVISIBLE);
        turns1000_card.setVisibility(View.INVISIBLE);
        turns1200_card.setVisibility(View.INVISIBLE);
        turns1600_card.setVisibility(View.INVISIBLE);
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
        DisableButton();
    }

    private void EnableButton() {
        if (!continue_button.isEnabled()) {
            continue_button.setEnabled(true);
            continue_button.setAlpha((float) 1.0);
        }
    }

    private void DisableButton() {
        continue_button.setEnabled(false);
        continue_button.setAlpha((float) 0.5);
    }
}
