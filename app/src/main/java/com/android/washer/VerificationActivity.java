package com.android.washer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.concurrent.TimeUnit;

public class VerificationActivity extends BaseActivity {

    TextView programTV, speedTV, tempTV, durationTV, washerNameTV;
    Button startButton;

    String[] descriptionData;
    int duration;

    private final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        this.getSupportActionBar().setTitle(getResources().getString(R.string.verification_title));

        descriptionData = new String[]{getResources().getString(R.string.program),
                getResources().getString(R.string.temperature),
                getResources().getString(R.string.spin),
                getResources().getString(R.string.verification_title)};

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        InitUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onBackPressed();
        return true;
    }

    public void InitUI() {
        SetupDuration();
        ConnectViews();
        GoToWashActivity();
        TimeSetup(duration);
    }

    private void GoToWashActivity() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WashSingleton.getInstance().Duration = duration;
                startActivity(new Intent(VerificationActivity.this, WashActivity.class));
            }
        });
    }

    private void ConnectViews() {
        programTV = findViewById(R.id.program_name);
        speedTV = findViewById(R.id.spin);
        tempTV = findViewById(R.id.temperature);
        startButton = findViewById(R.id.start_button);
        durationTV = findViewById(R.id.duration);
        washerNameTV = findViewById(R.id.washerName);

        WasherModel washerModel = WashSingleton.getInstance().washerModel;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String friendlyName = sharedPreferences.getString(washerModel.getId(), "");

        if (friendlyName != "" ) {
            washerNameTV.setText(friendlyName + " - " + washerModel.getBrand() + " " + washerModel.getModel());
        } else {
            washerNameTV.setText(washerModel.getId() + " - " + washerModel.getBrand() + " " + washerModel.getModel());
        }

        String program = getResources().getString(R.string.ver_program)+ " " + WashSingleton.getInstance().Program;
        String speed = getResources().getString(R.string.ver_speed)+ " " + WashSingleton.getInstance().Rpm;
        String temperature = getResources().getString(R.string.ver_temp)+ " " + WashSingleton.getInstance().Temperature;

        programTV.setText(program);
        speedTV.setText(speed);
        tempTV.setText(temperature);
    }

    private void SetupDuration() {
        if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.fast))) {
            duration = 720000;
        } else if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.eco))) {
            duration = 8600000;
        } else if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.cotton))) {
            duration = 1500000;
        } else if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.synthetic))) {
            duration = 3700000;
        } else if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.vul))) {
            duration = 5500000;
        } else if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.mallina))) {
            duration = 7200000;
        } else if (WashSingleton.getInstance().Program.equals(getResources().getString(R.string.white))) {
            duration = 9900000;
        }
    }

    private void TimeSetup(long millisUntilFinished) {
        final String hoursString = getResources().getString(R.string.hours);
        final String hourString = getResources().getString(R.string.hour);
        final String minutesString = getResources().getString(R.string.minutes);
        final String minuteString = getResources().getString(R.string.minute);
        final String secondsString = getResources().getString(R.string.seconds);

        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

        String timeRemaining;
        if (hours > 0) {
            if (hours == 1) {
                timeRemaining = hours + " " + hourString;
            } else  {
                timeRemaining = hours + " " + hoursString;
            }

            if (minutes == 0) {

            } else if (minutes == 1) {
                timeRemaining = timeRemaining + " " + getResources().getString(R.string.and) + " " + minutes + " " + minuteString;
            } else {
                timeRemaining = timeRemaining + " " + getResources().getString(R.string.and) + " " + minutes + " " + minutesString;
            }

        } else {
            if (minutes == 0) {
                timeRemaining = minutes + " " + minuteString;
            } else if (minutes == 1) {
                timeRemaining = seconds + " " + secondsString;
            } else {
                timeRemaining = minutes + " " + minutesString;
            }
        }
        timeRemaining = getResources().getString(R.string.duration2) + " " +timeRemaining;
        durationTV.setText(timeRemaining);
    }
}
