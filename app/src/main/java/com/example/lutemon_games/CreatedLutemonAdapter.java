package com.example.lutemon_games;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.*;

public class CreatedLutemonAdapter extends RecyclerView.Adapter<CreatedLutemonAdapter.ViewHolder> {
    private final List<Lutemon> lutemons;
    private final int[] images;
    private final Set<Integer> selectedIndices = new HashSet<>();

    public CreatedLutemonAdapter(List<Lutemon> lutemons, int[] images) {
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
    public CreatedLutemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lutemon, parent, false);
        return new CreatedLutemonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatedLutemonAdapter.ViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);
        holder.text.setText(l.getStats());
        holder.icon.setImageResource(getImageForLutemon(l));
        holder.radioButton.setChecked(selectedIndices.contains(position));

        holder.radioButton.setOnClickListener(v -> {
            if (selectedIndices.contains(position)) {
                selectedIndices.remove(position);
            } else {
                selectedIndices.add(position);
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    private int getImageForLutemon(Lutemon l) {
        if (l instanceof WhiteLutemon) {
            return images[0];
        } else if (l instanceof GreenLutemon) {
            return images[1];
        } else if (l instanceof PinkLutemon) {
            return images[2];
        } else if (l instanceof OrangeLutemon) {
            return images[3];
        } else if (l instanceof BlackLutemon) {
            return images[4];
        } else {
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
