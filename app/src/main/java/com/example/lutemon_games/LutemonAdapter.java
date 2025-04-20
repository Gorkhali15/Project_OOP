package com.example.lutemon_games;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class  LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.LutemonViewHolder> {
    private final List<Lutemon> lutemonList;
    private final int[] images;
    private int selectedPosition = -1;

    public LutemonAdapter(List<Lutemon> lutemonList, int[] images) {
        this.lutemonList = lutemonList;
        this.images = images;
    }

    public Lutemon getSelectedLutemon() {
        return selectedPosition >= 0 ? lutemonList.get(selectedPosition) : null;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lutemon, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon l = lutemonList.get(position);
        holder.stats.setText(l.getStats());

        int img;
        switch (l.type.toLowerCase()) {
            case "white":
                img = images[0];
                break;
            case "green":
                img = images[1];
                break;
            case "pink":
                img = images[2];
                break;
            case "orange":
                img = images[3];
                break;
            case "black":
                img = images[4];
                break;
            default:
                img = images[0];
                break;
        }
        holder.icon.setImageResource(img);

        holder.radioButton.setChecked(position == selectedPosition);
        holder.radioButton.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return lutemonList.size();
    }

    static class LutemonViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView stats;
        RadioButton radioButton;

        LutemonViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            stats = itemView.findViewById(R.id.statsText);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }
}

