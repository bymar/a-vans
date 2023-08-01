package com.example.avansoft.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avansoft.domain.UserData;
import com.example.avansoft.R;

import java.util.List;

public class AdapterConfig extends RecyclerView.Adapter<ViewHolderConfig> {

    private Context context;
    private List<UserData> userDataList;

    public AdapterConfig(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ViewHolderConfig onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderConfig(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConfig holder, int position) {
        holder.nameView.setText(userDataList.get(position).getName());
        holder.emailView.setText(userDataList.get(position).getEmail());
        holder.ageView.setText(userDataList.get(position).getAge());
        holder.idView.setText(userDataList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }
}
