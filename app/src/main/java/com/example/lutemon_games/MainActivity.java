package com.example.lutemon_games;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView output;
    LutemonTypeAdapter typeAdapter;
    CreatedLutemonAdapter createdAdapter;
    CreatedLutemonAdapter arenaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);

        String[] types = {"White", "Green", "Pink", "Orange", "Black"};
        int[] images = {
                R.drawable.white_lutemon,
                R.drawable.green_lutemon,
                R.drawable.pink_lutemon,
                R.drawable.orange_lutemon,
                R.drawable.black_lutemon
        };

        // Select type list
        typeAdapter = new LutemonTypeAdapter(types, images);
        RecyclerView typeList = findViewById(R.id.lutemonTypeList);
        typeList.setLayoutManager(new LinearLayoutManager(this));
        typeList.setAdapter(typeAdapter);

        // Created Lutemon list
        createdAdapter = new CreatedLutemonAdapter(Storage.home, images);
        RecyclerView createdList = findViewById(R.id.createdLutemonList);
        createdList.setLayoutManager(new LinearLayoutManager(this));
        createdList.setAdapter(createdAdapter);

        // Arena Lutemon list
        arenaAdapter = new CreatedLutemonAdapter(Storage.arena, images);
        RecyclerView arenaList = findViewById(R.id.arenaLutemonList);
        arenaList.setLayoutManager(new LinearLayoutManager(this));
        arenaList.setAdapter(arenaAdapter);

        findViewById(R.id.createButton).setOnClickListener(v -> {
            String selectedType = typeAdapter.getSelectedType();
            if (selectedType == null) {
                output.setText("Select a Lutemon type first.");
                return;
            }

            String name = selectedType + "_" + System.currentTimeMillis();
            Lutemon l;
            switch (selectedType) {
                case "White": l = new WhiteLutemon(name); break;
                case "Green": l = new GreenLutemon(name); break;
                case "Pink": l = new PinkLutemon(name); break;
                case "Orange": l = new OrangeLutemon(name); break;
                case "Black": l = new BlackLutemon(name); break;
                default: l = null;
            }

            if (l != null) {
                Storage.home.add(l);
                createdAdapter.notifyDataSetChanged();
                output.setText("Created " + l.getStats());
            }
        });

        findViewById(R.id.sendToArenaButton).setOnClickListener(v -> {
            List<Lutemon> selected = createdAdapter.getSelected();
            if (selected.isEmpty()) {
                output.setText("Select Lutemons to send.");
                return;
            }

            for (Lutemon l : selected) {
                Storage.moveToArena(l);
            }

            createdAdapter.notifyDataSetChanged();
            arenaAdapter.notifyDataSetChanged();
            output.setText("Sent to arena: " + selected.size());
        });

        findViewById(R.id.startBattleButton).setOnClickListener(v -> {
            if (Storage.arena.size() >= 2) {
                Lutemon a = Storage.arena.remove(0);
                Lutemon b = Storage.arena.remove(0);
                String result = BattleManager.battle(a, b);
                Storage.home.add(a);
                Storage.home.add(b);
                output.setText(result);
                createdAdapter.notifyDataSetChanged();
                arenaAdapter.notifyDataSetChanged();
            } else {
                output.setText("Not enough Lutemons in the arena.");
            }
        });
    }
}
