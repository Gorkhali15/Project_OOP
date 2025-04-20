package com.example.lutemon_games;
public class BattleManager {
    public static String battle(Lutemon a, Lutemon b) {
        StringBuilder log = new StringBuilder();
        while (a.isAlive() && b.isAlive()) {
            b.defend(a.attack());
            log.append(a.getName()).append(" attacks ").append(b.getName()).append("\n");
            log.append(b.getStats()).append("\n");

            if (!b.isAlive()) {
                log.append(b.getName()).append(" has died.\n");
                a.gainExperience();
                Storage.arena.add(a);
                return log.toString();
            } else {
                log.append(b.getName()).append(" managed to avoid death.\n");
                Lutemon temp = a;
                a = b;
                b = temp;
            }
        }
        return log.toString();
    }
}
