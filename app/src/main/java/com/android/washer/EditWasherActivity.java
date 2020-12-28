package com.android.washer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditWasherActivity extends Activity {

    private TextView headerTextView;
    private RecyclerView recyclerView;
    private LinearLayout emptyStateView;
    private Button scanAgainButton;
    private List<WasherModel> washers;
    private EditWasherRecyclerAdapter adapter;
    private ProgressDialog progressDialog;
    private BottomSheetDialog bottomSheetDialog;

    private final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_washer_activity);
        recyclerView = findViewById(R.id.editWashersRV);
        emptyStateView = findViewById(R.id.editWasherEmptyStateView);
        headerTextView = findViewById(R.id.editWasherTV);
        scanAgainButton = findViewById(R.id.editWasherScanAgainBtn);
        setupData();
    }

    private void setupData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.CONFIG)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<WasherModel>> call = api.getWashers();

        progressDialog = new ProgressDialog(EditWasherActivity.this);
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

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.editWashersRV);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EditWasherRecyclerAdapter(washers, getApplicationContext());
        recyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.separator_line));
        recyclerView.addItemDecoration(divider);
        showEmptyStateIfEmpty();
        handleAdapterListener();
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

    private void handleAdapterListener() {
        adapter.setOnItemButtonOnClickListener(new EditWasherRecyclerAdapter.OnItemButtonClickListener() {
            @Override
            public void onItemClick(WasherModel model) {
                bottomSheetDialog = new BottomSheetDialog(EditWasherActivity.this, R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_modal,
                        findViewById(R.id.bottom_sheet));
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
                handleBottomSheetListeners(sheetView, model);
            }
        });
    }

    private void handleBottomSheetListeners(View view, WasherModel model) {
        TextView choice1 = view.findViewById(R.id.bottomSheetChoice1);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecyclerView(model, choice1.getText().toString());
                bottomSheetDialog.dismiss();
            }
        });

        TextView choice2 = view.findViewById(R.id.bottomSheetChoice2);
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecyclerView(model, choice2.getText().toString());
                bottomSheetDialog.dismiss();
            }
        });

        TextView choice3 = view.findViewById(R.id.bottomSheetChoice3);
        view.findViewById(R.id.bottomSheetChoice3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecyclerView(model, choice3.getText().toString());
                bottomSheetDialog.dismiss();
            }
        });

        TextView choice4 = view.findViewById(R.id.bottomSheetChoice4);
        view.findViewById(R.id.bottomSheetChoice4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                startActivity(new Intent(EditWasherActivity.this, AddFriendlyNameActivity.class));
            }
        });

        view.findViewById(R.id.closeSheetImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void updateRecyclerView(WasherModel model, String friendlyName) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(model.getId(), friendlyName);
        editor.apply();
        adapter.notifyDataSetChanged();
    }

    private void handleScanAgain() {
        scanAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupData();
            }
        });
    }
}
