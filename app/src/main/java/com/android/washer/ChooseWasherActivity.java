package com.android.washer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseWasherActivity extends BaseActivity implements ChooseWasherRecyclerAdapter.SelectedWasher {

    private TextView headerTextView;
    private RecyclerView recyclerView;
    private LinearLayout emptyStateView;
    private ImageView emptyStateImageView;
    private Button scanAgainButton;
    private List<WasherModel> washers;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_washer_activity);
        this.getSupportActionBar().setTitle(getResources().getString(R.string.start_washing_string));
        recyclerView = findViewById(R.id.washersRV);
        emptyStateView = findViewById(R.id.washerEmptyStateView);
        emptyStateImageView = findViewById(R.id.emtpyStateImageView);
        headerTextView = findViewById(R.id.chooseWasherTV);
        scanAgainButton = findViewById(R.id.scanAgainBtn);
        setupData();
        WashSingleton.sharedInstance.reset();
    }

    private void setupData() {
        if (!isNetworkConnected()) {
            showEmptyState();
            emptyStateImageView.setImageResource(R.drawable.no_network_connection);
            return;
        }
        hideEmptyState();
        resetEmptyState();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.CONFIG)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<WasherModel>> call = api.getWashers();

        progressDialog = new ProgressDialog(ChooseWasherActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        call.enqueue(new Callback<List<WasherModel>>() {
            @Override
            public void onResponse(Call<List<WasherModel>> call, Response<List<WasherModel>> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    Log.e("response failed", String.valueOf(response.code()));
                }
                washers = new ArrayList<>();
                washers = response.body();
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<List<WasherModel>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("response failed", t.getMessage());
            }
        });
    }

    private void resetEmptyState() {
        emptyStateImageView.setImageResource(R.drawable.no_result_empty_state_icon);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.washersRV);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ChooseWasherRecyclerAdapter adapter = new ChooseWasherRecyclerAdapter(washers, this, this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.separator_line));
        recyclerView.addItemDecoration(divider);
        showEmptyStateIfEmpty();
    }

    private void showEmptyStateIfEmpty() {
        if (washers.isEmpty()) {
            emptyStateView.setVisibility(View.VISIBLE);
            scanAgainButton.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            handleScanAgain();
        } else {
            emptyStateView.setVisibility(View.INVISIBLE);
            scanAgainButton.setVisibility(View.INVISIBLE);
            headerTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void showEmptyState() {
        emptyStateView.setVisibility(View.VISIBLE);
        scanAgainButton.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        handleScanAgain();
    }

    private void hideEmptyState() {
        emptyStateView.setVisibility(View.INVISIBLE);
        scanAgainButton.setVisibility(View.INVISIBLE);
        headerTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void handleScanAgain() {
        scanAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupData();
            }
        });
    }

    @Override
    public void didSelectWasher(WasherModel washer) {
        WashSingleton.sharedInstance.washerModel = washer;
        startActivity(new Intent(ChooseWasherActivity.this, ChooseProgramActivity.class));
    }
}
