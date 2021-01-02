package com.android.washer;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class EditWasherRecyclerAdapter extends RecyclerView.Adapter<EditWasherRecyclerAdapter.ViewHolder> {

    private List<WasherModel> washers;
    private OnItemButtonClickListener listener;
    private Context context;

    private final String SHARED_PREFS = "sharedPrefs";

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView washerItemDescrTextView;
        private TextView washerItemIdTextView;
        private ImageView editImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            washerItemDescrTextView = itemView.findViewById(R.id.editWasherItemDescriptionTV);
            washerItemIdTextView = itemView.findViewById(R.id.editWasherItemIdTV);
            editImageView = itemView.findViewById(R.id.editWasherButton);

            editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(washers.get(position));
                    }
                }
            });
        }
    }

    public EditWasherRecyclerAdapter(List<WasherModel> washers, Context context) {
        this.washers = washers;
        this.context = context;
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
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String friendlyName = sharedPreferences.getString(washers.get(position).getId(), "");

        if (friendlyName != "" ) {
            holder.washerItemIdTextView.setText(friendlyName + " - " + washers.get(position).getId());
        } else {
            holder.washerItemIdTextView.setText("ID: " + washers.get(position).getId());
        }
    }

    @Override
    public int getItemCount() {
        return washers.size();
    }

    public interface OnItemButtonClickListener {
        void onItemClick(WasherModel model);
    }

    public void setOnItemButtonOnClickListener(OnItemButtonClickListener listener) {
        this.listener = listener;
    }
}
