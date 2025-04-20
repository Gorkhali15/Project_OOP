package com.example.lutemon_games;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    public static ArrayList<Lutemon> home = new ArrayList<>();
    public static ArrayList<Lutemon> training = new ArrayList<>();
    public static ArrayList<Lutemon> arena = new ArrayList<>();

    public static void moveToTraining(Lutemon l) {
        home.remove(l);
        training.add(l);
    }

    public static void moveToArena(Lutemon l) {
        home.remove(l);
        arena.add(l);
    }

    public static void moveToHome(Lutemon l) {
        training.remove(l);
        arena.remove(l);
        home.add(l);
        l.heal();
    }

    private static final String FILENAME = "lutemons.dat";

    public static void save(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(home);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            home = (ArrayList<Lutemon>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
