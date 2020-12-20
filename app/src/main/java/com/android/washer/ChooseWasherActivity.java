package com.android.washer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseWasherActivity extends Activity {

    private RecyclerView recyclerView;
    private List<WasherModel> washers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_washer_activity);
        setupData();
    }

    private void setupData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.CONFIG)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<WasherModel>> call = api.getWashers();

        call.enqueue(new Callback<List<WasherModel>>() {
            @Override
            public void onResponse(Call<List<WasherModel>> call, Response<List<WasherModel>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response failed", String.valueOf(response.code()));
                }
                washers = response.body();
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<List<WasherModel>> call, Throwable t) {
                Log.e("response failed", t.getMessage());
            }
        });
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
