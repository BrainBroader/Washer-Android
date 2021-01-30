package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class ChooseTemperatureActivity extends BaseActivity {

    private ImageView temp20_infoButton, temp40_infoButton, temp60_infoButton, temp90_infoButton; //Info Buttons in Cards
    private CardView temp20_card, temp40_card, temp60_card, temp90_card; //Cards
    private TextView temp20_TV, temp40_TV, temp60_TV, temp90_TV; //Text Inside Cards
    private Button continue_button;

    private String[] descriptionData = {"Πρόγραμμα", "Θερμοκρασία", "Στροφές", "Επιβεβαίωση"};
    private String program, temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_temperature);
        this.getSupportActionBar().setTitle("Επιλογή θερμοκρασίας");

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        Bundle bundle = getIntent().getExtras();
        program = bundle.getString("Program");

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
                Log.d("PROGRAM: ", program);
                Log.d("TEMPERATURE: ", temperature);

                Intent intent  = new Intent(ChooseTemperatureActivity.this, ChooseSpeedActivity.class);
                intent.putExtra("Program", program);
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
                EnableButton();
            }
        });

        temp40_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp40_card.setVisibility(View.VISIBLE);
                temperature = getResources().getString(R.string.fourty);
                EnableButton();
            }
        });

        temp60_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp60_card.setVisibility(View.VISIBLE);
                temperature = getResources().getString(R.string.sixty);
                EnableButton();
            }
        });

        temp90_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                temp90_card.setVisibility(View.VISIBLE);
                temperature = getResources().getString(R.string.ninety);
                EnableButton();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onBackPressed();
        return true;
    }
}
