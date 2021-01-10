package com.android.washer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InfoDialog extends AppCompatDialogFragment {

    String title;
    String description;

    TextView popUpTitleTextView, popUpDescriptionTextView;
    Button popUpButton;
    ImageView closePopUpImageView;

    public InfoDialog(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_modal, null);

        CreateViews(view);
        SetupViews();

        builder.setView(view);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private void CreateViews(View view) {
        popUpTitleTextView = view.findViewById(R.id.popUpTitleTextView);
        popUpDescriptionTextView = view.findViewById(R.id.popUpDescriptionTextView);
        popUpButton = view.findViewById(R.id.popUpButton);
        closePopUpImageView = view.findViewById(R.id.closePopUpImageView);
    }

    private void SetupViews() {
        if (title != null) {
            popUpTitleTextView.setText(title);
        }

        if (description != null) {
            popUpDescriptionTextView.setText(description);
        }

        popUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        closePopUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
