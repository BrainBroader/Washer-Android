package com.android.washer;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseWasherActivity extends Activity {

    private RecyclerView recyclerView;
    private ArrayList<WasherModel> washers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_washer_activity);
        setupData();
        setupRecyclerView();
    }

    private void setupData() {
        washers = new ArrayList<>();
        washers.add(new WasherModel("44343","JDHFHD","LG"));
        washers.add(new WasherModel("44344","JDHFHD2","LG"));
        washers.add(new WasherModel("44345","JDHFHD3","LG"));
        washers.add(new WasherModel("44346","JDHFHD4","LG"));
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.washersRV);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ChooseWasherRecyclerAdapter adapter = new ChooseWasherRecyclerAdapter(washers);
        recyclerView.setAdapter(adapter);
    }
}
