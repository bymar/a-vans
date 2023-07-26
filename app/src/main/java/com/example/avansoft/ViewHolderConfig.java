package com.example.avansoft;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderConfig extends RecyclerView.ViewHolder {

    TextView nameView, emailView, ageView, idView;
    public ViewHolderConfig(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        emailView = itemView.findViewById(R.id.email);
        ageView = itemView.findViewById(R.id.age);
        idView = itemView.findViewById(R.id.userid);
    }
}
