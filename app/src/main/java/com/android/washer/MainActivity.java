package com.android.washer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button scanButton, addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = findViewById(R.id.scanBtn);
        addButton = findViewById(R.id.addBtn);
        setupListeners();
    }

    private void setupListeners() {
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, ChooseProgramActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, ChooseProgramActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}