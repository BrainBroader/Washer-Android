package com.android.washer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.itangqi.waveloadingview.WaveLoadingView;

public class WashActivity extends AppCompatActivity {

    WaveLoadingView waveLoadingView;
    Button cancelButton;
    ImageView doneImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wash_activity);

        waveLoadingView = findViewById(R.id.waveLoadingView);
        cancelButton = findViewById(R.id.cancelWashButton);
        doneImageView = findViewById(R.id.doneIcon);
        setupProgressBar();
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
        new CountDownTimer(maxTime, 100) {
            public void onTick(long millisUntilFinished) {
                int currentProg = ++progress[0];
                waveLoadingView.setProgressValue(currentProg);
                if (currentProg >= 100) {
                    this.cancel();
                    didWashFinish();
                }
            }
            public void onFinish() {
                waveLoadingView.setProgressValue(100);
                this.cancel();
                didWashFinish();
            }
        }.start();
    }

    private void didWashFinish() {
        waveLoadingView.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        doneImageView.setVisibility(View.VISIBLE);
    }
}