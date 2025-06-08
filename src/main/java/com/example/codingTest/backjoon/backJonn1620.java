package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class backJonn1620 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("26 5" + lc);
        ex.add("Bulbasaur" + lc);
        ex.add("Ivysaur" + lc);
        ex.add("Venusaur" + lc);
        ex.add("Charmander" + lc);
        ex.add("Charmeleon" + lc);
        ex.add("Charizard" + lc);
        ex.add("Squirtle" + lc);
        ex.add("Wartortle" + lc);
        ex.add("Blastoise" + lc);
        ex.add("Caterpie" + lc);
        ex.add("Metapod" + lc);
        ex.add("Butterfree" + lc);
        ex.add("Weedle" + lc);
        ex.add("Kakuna" + lc);
        ex.add("Beedrill" + lc);
        ex.add("Pidgey" + lc);
        ex.add("Pidgeotto" + lc);
        ex.add("Pidgeot" + lc);
        ex.add("Rattata" + lc);
        ex.add("Raticate" + lc);
        ex.add("Spearow" + lc);
        ex.add("Fearow" + lc);
        ex.add("Ekans" + lc);
        ex.add("Arbok" + lc);
        ex.add("Pikachu" + lc);
        ex.add("Raichu" + lc);
        ex.add("25" + lc);
        ex.add("Raichu" + lc);
        ex.add("3" + lc);
        ex.add("Pidgey" + lc);
        ex.add("Kakuna");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] info = br.readLine().split(" ");
        int totalCnt = Integer.parseInt(info[0]);
        int testCnt = Integer.parseInt(info[1]);

        HashMap<Integer, String> intMap = new HashMap<>();
        HashMap<String, Integer> StringMap = new HashMap<>();

        for (int i = 0; i < totalCnt; i++) {
            String name = br.readLine();
            intMap.put(i+1, name);
            StringMap.put(name, i+1);
        }

        for (int i = 0; i < testCnt; i++) {
            String value = br.readLine();

            try {
                System.out.println(intMap.get(Integer.parseInt(value)));
            } catch (Exception e) {
                System.out.println(StringMap.get(value));
            }
        }
    }
}