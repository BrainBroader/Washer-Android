package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseProgramActivity extends AppCompatActivity {

    ImageView fast_infoButton, eco_infoButton, cotton_infoButton, synthetic_infoButton, vul_infoButton, mallina_infoButton, white_infoButton; //Info Buttons in Cards
    CardView fastProgram_card, ecoProgram_card, cottonProgram_card, syntheticProgram_card, vulProgram_card, mallinaProgram_card, whiteProgram_card; //Cards
    TextView fastProgram_TV, ecoProgram_TV, cottonProgram_TV, syntheticProgram_TV, vulProgram_TV, mallinaProgram_TV, whiteProgram_TV; //Text Inside Cards
    Button continue_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_program);
        HideActionBar();

        ConnectViews();
        SetupListeners();
    }

    private void SetupListeners() {

        SetupInfoButtons();
        SetupCards();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ChooseProgramActivity.this, ChooseSpeedActivity.class);
                ChooseProgramActivity.this.startActivity(intent);
            }
        });
    }

    private void openDialog(String title, String description){
        InfoDialog infoDialog = new InfoDialog(title, description);
        infoDialog.show(getSupportFragmentManager(), "info button");
    }

    private void SetupCards() {

        fastProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                fastProgram_card.setVisibility(View.VISIBLE);
            }
        });

        ecoProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                ecoProgram_card.setVisibility(View.VISIBLE);
            }
        });

        cottonProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                cottonProgram_card.setVisibility(View.VISIBLE);
            }
        });

        syntheticProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                syntheticProgram_card.setVisibility(View.VISIBLE);
            }
        });

        vulProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                vulProgram_card.setVisibility(View.VISIBLE);
            }
        });

        mallinaProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                mallinaProgram_card.setVisibility(View.VISIBLE);
            }
        });

        whiteProgram_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearPickedCards();
                whiteProgram_card.setVisibility(View.VISIBLE);
            }
        });

    }

    private void ClearPickedCards() {
        fastProgram_card.setVisibility(View.INVISIBLE);
        ecoProgram_card.setVisibility(View.INVISIBLE);
        cottonProgram_card.setVisibility(View.INVISIBLE);
        syntheticProgram_card.setVisibility(View.INVISIBLE);
        vulProgram_card.setVisibility(View.INVISIBLE);
        mallinaProgram_card.setVisibility(View.INVISIBLE);
        whiteProgram_card.setVisibility(View.INVISIBLE);
    }

    private void SetupInfoButtons() {
        fast_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.fast_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });

        eco_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.eco_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });

        cotton_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.cotton_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });

        synthetic_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.synthetic_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });

        vul_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.vul_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });

        mallina_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.mallina_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });

        white_infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.white_program);
                String description = "Details about this program";
                openDialog(title, description);
            }
        });
    }

    private void ConnectViews() {
        fastProgram_card = findViewById(R.id.fastProgram_card);
        ecoProgram_card = findViewById(R.id.ecoProgram_card);
        cottonProgram_card = findViewById(R.id.cottonProgram_card);
        syntheticProgram_card = findViewById(R.id.syntheticProgram_card);
        vulProgram_card = findViewById(R.id.vulProgram_card);
        mallinaProgram_card = findViewById(R.id.mallinaProgram_card);
        whiteProgram_card = findViewById(R.id.whiteProgram_card);

        fast_infoButton = findViewById(R.id.fast_infoButton);
        eco_infoButton = findViewById(R.id.eco_infoButton);
        cotton_infoButton = findViewById(R.id.cotton_infoButton);
        synthetic_infoButton = findViewById(R.id.synthetic_infoButton);
        vul_infoButton = findViewById(R.id.vul_infoButton);
        mallina_infoButton = findViewById(R.id.mallina_infoButton);
        white_infoButton = findViewById(R.id.white_infoButton);

        fastProgram_TV = findViewById(R.id.fastProgram_TV);
        ecoProgram_TV = findViewById(R.id.ecoProgram_TV);
        cottonProgram_TV = findViewById(R.id.cottonProgram_TV);
        syntheticProgram_TV = findViewById(R.id.syntheticProgram_TV);
        vulProgram_TV = findViewById(R.id.vulProgram_TV);
        mallinaProgram_TV = findViewById(R.id.mallinaProgram_TV);
        whiteProgram_TV = findViewById(R.id.whiteProgram_TV);

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