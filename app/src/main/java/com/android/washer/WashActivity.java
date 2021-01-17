package com.android.washer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.concurrent.TimeUnit;

import me.itangqi.waveloadingview.WaveLoadingView;

public class WashActivity extends AppCompatActivity {

    WaveLoadingView waveLoadingView;
    Button cancelButton;
    ImageView statusImageView;
    CountDownTimer countDownTimer;
    TextView headerDurationTextView, durationTextView, finishedTextView;

    int duration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wash_activity);

        Bundle bundle = getIntent().getExtras();
        duration = bundle.getInt("Duration");

        waveLoadingView = findViewById(R.id.waveLoadingView);
        cancelButton = findViewById(R.id.cancelWashButton);
        statusImageView = findViewById(R.id.statusIcon);
        headerDurationTextView = findViewById(R.id.headerTitleTextView);
        durationTextView = findViewById(R.id.durationTextView);
        finishedTextView = findViewById(R.id.finishedTextView);
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
        final int maxTime = duration;
        final String hoursString = getApplicationContext().getResources().getString(R.string.hours);
        final String minutesString = getApplicationContext().getResources().getString(R.string.minutes);
        final String minuteString = getApplicationContext().getResources().getString(R.string.minute);
        final String secondsString = getApplicationContext().getResources().getString(R.string.seconds);

        headerDurationTextView.setVisibility(View.VISIBLE);

        //increase progress every 500ms
        countDownTimer = new CountDownTimer(maxTime, 100) {
            public void onTick(long millisUntilFinished) {
                int currentProg = ++progress[0];

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

                String timeRemaining;
                if (hours > 0) {
                    timeRemaining = hours + " " + hoursString + " " + minutes + " " + minutesString;
                } else {
                    if (minutes == 0) {
                        timeRemaining = minutes + " " + minuteString;
                    } else if (minutes == 1) {
                        timeRemaining = seconds + " " + secondsString;
                    } else {
                        timeRemaining = minutes + " " + minutesString;
                    }
                }
                durationTextView.setText(timeRemaining);

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
        headerDurationTextView.setVisibility(View.GONE);
        durationTextView.setVisibility(View.GONE);
        finishedTextView.setVisibility(View.VISIBLE);
    }

    private void handleCancel() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(WashActivity.this)
                    .setTitle(R.string.cancel)
                    .setMessage(R.string.cancel_question)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            countDownTimer.cancel();
                            didWashFinish();
                            statusImageView.setBackgroundResource(R.drawable.cancel_icon);
                            finishedTextView.setText(R.string.wash_not_finished);
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    })
                    .show();
            }
        });
    }
}