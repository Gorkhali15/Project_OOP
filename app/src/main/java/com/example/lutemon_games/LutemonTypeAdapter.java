package com.example.lutemon_games;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonTypeAdapter extends RecyclerView.Adapter<LutemonTypeAdapter.ViewHolder> {
    private final String[] types;
    private final int[] images;
    private int selectedPosition = -1;

    public LutemonTypeAdapter(String[] types, int[] images) {
        this.types = types;
        this.images = images;
    }

    public String getSelectedType() {
        return selectedPosition >= 0 ? types[selectedPosition] : null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lutemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(types[position]);
        holder.icon.setImageResource(images[position]);
        holder.radioButton.setChecked(position == selectedPosition);
        holder.radioButton.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return types.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;
        RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            text = itemView.findViewById(R.id.statsText);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }
}

