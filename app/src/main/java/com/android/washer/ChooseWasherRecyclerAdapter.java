package com.android.washer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChooseWasherRecyclerAdapter extends RecyclerView.Adapter<ChooseWasherRecyclerAdapter.ViewHolder> {

    private List<WasherModel> washers;
    private SelectedWasher selectedWasher;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView washerItemDescrTextView;
        private TextView washerItemIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            washerItemDescrTextView = itemView.findViewById(R.id.washerItemDescriptionTV);
            washerItemIdTextView = itemView.findViewById(R.id.washerItemIdTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedWasher.didSelectWasher(washers.get(getAdapterPosition()));
                }
            });
        }
    }

    public ChooseWasherRecyclerAdapter(List<WasherModel> washers, SelectedWasher selectedWasher) {
        this.washers = washers;
        this.selectedWasher = selectedWasher;
    }

    @NonNull
    @Override
    public ChooseWasherRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.washer_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseWasherRecyclerAdapter.ViewHolder holder, int position) {
        String washerText = washers.get(position).getBrand() + " " + washers.get(position).getModel();
        holder.washerItemDescrTextView.setText(washerText);
        holder.washerItemIdTextView.setText("ID: " + washers.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return washers.size();
    }

    public interface SelectedWasher {
        void didSelectWasher(WasherModel washer);
    }
}
