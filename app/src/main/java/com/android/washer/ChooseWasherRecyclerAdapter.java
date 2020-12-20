package com.android.washer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseWasherRecyclerAdapter extends RecyclerView.Adapter<ChooseWasherRecyclerAdapter.ViewHolder> {

    private List<WasherModel> washers;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView washerItemTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            washerItemTextView = itemView.findViewById(R.id.washerItemTextView);
        }
    }

    public ChooseWasherRecyclerAdapter(List<WasherModel> washers) {
        this.washers = washers;
    }

    @NonNull
    @Override
    public ChooseWasherRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.washer_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseWasherRecyclerAdapter.ViewHolder holder, int position) {
        String model = washers.get(position).getModel();
        holder.washerItemTextView.setText(model);
    }

    @Override
    public int getItemCount() {
        return washers.size();
    }
}
