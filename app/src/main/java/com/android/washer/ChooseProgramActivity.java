package com.android.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseProgramActivity extends AppCompatActivity {

    LinearLayout programStage_layout; //Program
    LinearLayout turnStage_layout; //Turns
    LinearLayout temperatureStage_layout; //Temperature
    LinearLayout confirmStage_layout; //Confirm

    TextView stage1TextView, stage2TextView, stage3TextView, stage4TextView;
    ImageView stage1ImageView, stage2ImageView, stage3ImageView, stage4ImageView;

    ImageView fast_infoButton, eco_infoButton, cotton_infoButton, synthetic_infoButton, vul_infoButton, mallina_infoButton, white_infoButton; //Info Buttons in Cards

    TextView fastProgram_card, ecoProgram_card, cottonProgram_card, syntheticProgram_card, vulProgram_card, mallinaProgram_card, whiteProgram_card; //Cards




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HideActionBar();
        setContentView(R.layout.activity_choose_program);

        InitViews();
        setupListeners();
    }

    private void setupListeners() {
        programStage_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickedColors(stage1TextView, stage1ImageView);
            }
        });

        turnStage_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickedColors(stage2TextView, stage2ImageView);
            }
        });

        temperatureStage_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickedColors(stage3TextView, stage3ImageView);
            }
        });

        confirmStage_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickedColors(stage4TextView, stage4ImageView);
            }
        });
    }

    private void ClickedColors(TextView tv, ImageView iv) {
        ClearStagesColor();
        tv.setTextColor(ContextCompat.getColor(ChooseProgramActivity.this, R.color.purple_500));
        iv.setColorFilter(ContextCompat.getColor(ChooseProgramActivity.this, R.color.purple_500));
    }

    private void ClearStagesColor() {
        stage1TextView.setTextColor(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage1ImageView.setColorFilter(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage2TextView.setTextColor(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage2ImageView.setColorFilter(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage3TextView.setTextColor(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage3ImageView.setColorFilter(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage4TextView.setTextColor(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
        stage4ImageView.setColorFilter(ContextCompat.getColor(ChooseProgramActivity.this, R.color.defaultColor));
    }

    private void InitViews() {
        InitStagesBar();
        InitCards();
        InitInfoButtons();
    }

    private void InitStagesBar() {
        programStage_layout = findViewById(R.id.programStage_layout);
        stage1TextView = findViewById(R.id.stage1TextView);
        stage1ImageView = findViewById(R.id.stage1ImageView);

        turnStage_layout = findViewById(R.id.turnStage_layout);
        stage2TextView = findViewById(R.id.stage2TextView);
        stage2ImageView = findViewById(R.id.stage2ImageView);

        temperatureStage_layout = findViewById(R.id.temperatureStage_layout);
        stage3TextView = findViewById(R.id.stage3TextView);
        stage3ImageView = findViewById(R.id.stage3ImageView);

        confirmStage_layout = findViewById(R.id.confirmStage_layout);
        stage4TextView = findViewById(R.id.stage4TextView);
        stage4ImageView = findViewById(R.id.stage4ImageView);
    }

    private void InitInfoButtons() {
        fast_infoButton = findViewById(R.id.fast_infoButton);
        eco_infoButton = findViewById(R.id.eco_infoButton);
        cotton_infoButton = findViewById(R.id.cotton_infoButton);
        synthetic_infoButton = findViewById(R.id.synthetic_infoButton);
        vul_infoButton = findViewById(R.id.vul_infoButton);
        mallina_infoButton = findViewById(R.id.mallina_infoButton);
        white_infoButton = findViewById(R.id.white_infoButton);
    }

    private void InitCards() {
        fastProgram_card = findViewById(R.id.fastProgram_card);
        ecoProgram_card = findViewById(R.id.ecoProgram_card);
        cottonProgram_card = findViewById(R.id.cottonProgram_card);
        syntheticProgram_card = findViewById(R.id.syntheticProgram_card);
        vulProgram_card = findViewById(R.id.vulProgram_card);
        mallinaProgram_card = findViewById(R.id.mallinaProgram_card);
        whiteProgram_card = findViewById(R.id.whiteProgram_card);
    }

    private void HideActionBar() {
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}