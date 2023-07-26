package com.example.avansoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterConfig extends RecyclerView.Adapter<ViewHolderConfig> {

    Context context;
    List<ApiData> apiDataList;

    public AdapterConfig(Context context, List<ApiData> apiDataList) {
        this.context = context;
        this.apiDataList = apiDataList;
    }

    @NonNull
    @Override
    public ViewHolderConfig onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderConfig(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConfig holder, int position) {
        holder.nameView.setText(apiDataList.get(position).getName());
        holder.emailView.setText(apiDataList.get(position).getEmail());
        holder.ageView.setText(apiDataList.get(position).getAge());
        holder.idView.setText(apiDataList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return apiDataList.size();
    }
}
