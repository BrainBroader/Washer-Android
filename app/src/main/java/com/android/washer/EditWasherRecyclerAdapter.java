package com.android.washer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EditWasherRecyclerAdapter extends RecyclerView.Adapter<EditWasherRecyclerAdapter.ViewHolder> {

    private List<WasherModel> washers;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView washerItemDescrTextView;
        private TextView washerItemIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            washerItemDescrTextView = itemView.findViewById(R.id.editWasherItemDescriptionTV);
            washerItemIdTextView = itemView.findViewById(R.id.editWasherItemIdTV);
        }
    }

    public EditWasherRecyclerAdapter(List<WasherModel> washers) {
        this.washers = washers;
    }

    @NonNull
    @Override
    public EditWasherRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_washer_item, parent, false);
        return new EditWasherRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EditWasherRecyclerAdapter.ViewHolder holder, int position) {
        String washerText = washers.get(position).getBrand() + " " + washers.get(position).getModel();
        holder.washerItemDescrTextView.setText(washerText);
        holder.washerItemIdTextView.setText("ID: " + washers.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return washers.size();
    }
}
