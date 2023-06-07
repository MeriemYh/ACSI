package com.example.Classes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Desease> deseases;

    public MyAdapter(List<Desease> deseases) {
        this.deseases=deseases;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView symptomeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            symptomeTextView = itemView.findViewById(R.id.symptoms);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.desease_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Desease desease = deseases.get(position);
        holder.nameTextView.setText(desease.name);
        holder.symptomeTextView.setText(desease.symptomes);

    }

    @Override
    public int getItemCount() {
        return deseases.size();
    }
    public List<Desease> getDeseases() {
        return deseases;
    }

    public void setDeseases(List<Desease> deseases) {
        this.deseases = deseases;
    }


}
