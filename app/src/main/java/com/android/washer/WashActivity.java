package com.android.washer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import me.itangqi.waveloadingview.WaveLoadingView;

public class WashActivity extends AppCompatActivity {

    WaveLoadingView waveLoadingView;
    Button cancelButton;
    ImageView statusImageView;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wash_activity);

        waveLoadingView = findViewById(R.id.waveLoadingView);
        cancelButton = findViewById(R.id.cancelWashButton);
        statusImageView = findViewById(R.id.statusIcon);
        setupProgressBar();
        handleCancel();
    }

    private void setupProgressBar() {
        //avoid weird effect in start of animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                waveLoadingView.setWaveColor(R.color.purple_700);
                handleProgressBar();
            }
        }, 1000);
    }

    private void handleProgressBar() {
        int[] progress = {0};
        final int maxTime = 100000;

        //increase progress every 500ms
        countDownTimer = new CountDownTimer(maxTime, 100) {
            public void onTick(long millisUntilFinished) {
                int currentProg = ++progress[0];
                waveLoadingView.setProgressValue(currentProg);
                if (currentProg >= 100) {
                    this.cancel();
                    didWashFinish();
                    statusImageView.setBackgroundResource(R.drawable.done_icon);
                }
            }

            public void onFinish() {
                waveLoadingView.setProgressValue(100);
                this.cancel();
                didWashFinish();
                statusImageView.setBackgroundResource(R.drawable.done_icon);
            }
        }.start();
    }

    private void didWashFinish() {
        waveLoadingView.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        statusImageView.setVisibility(View.VISIBLE);
    }

    private void handleCancel() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(WashActivity.this)
                    .setTitle("Ακύρωση")
                    .setMessage("Είσαι σίγουρος ότι θες να σταματήσεις την πλύση;")
                    .setPositiveButton("ΝΑΙ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            countDownTimer.cancel();
                            didWashFinish();
                            statusImageView.setBackgroundResource(R.drawable.cancel_icon);
                        }
                    })
                    .setNegativeButton("ΟΧΙ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    })
                    .show();
            }
        });
    }
}