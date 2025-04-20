package com.example.lutemon_games;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ArenaLutemonAdapter extends RecyclerView.Adapter<ArenaLutemonAdapter.ViewHolder> {

    private final List<Lutemon> lutemons;
    private final int[] images;
    private final Set<Integer> selectedIndices = new HashSet<>();

    public ArenaLutemonAdapter(List<Lutemon> lutemons, int[] images) {
        this.lutemons = lutemons;
        this.images = images;
    }
    public List<Lutemon> getSelected() {
        List<Lutemon> selected = new ArrayList<>();
        for (Integer index : selectedIndices) {
            selected.add(lutemons.get(index));
        }
        return selected;
    }
    @NonNull
    @Override
    public ArenaLutemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lutemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArenaLutemonAdapter.ViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);
        holder.text.setText(l.getStats());
        holder.icon.setImageResource(getImageForType(l.type));
        holder.radioButton.setChecked(selectedIndices.contains(position));

        holder.radioButton.setOnClickListener(v -> {
            if (selectedIndices.contains(position)) {
                selectedIndices.remove(position);
            } else if (selectedIndices.size() < 2) {
                selectedIndices.add(position);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    private int getImageForType(String type) {
        switch (type.toLowerCase()) {
            case "white":
                return images[0];
            case "green":
                return images[1];
            case "pink":
                return images[2];
            case "orange":
                return images[3];
            case "black":
                return images[4];
            default:
                return images[0];
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            text = itemView.findViewById(R.id.statsText);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }
}