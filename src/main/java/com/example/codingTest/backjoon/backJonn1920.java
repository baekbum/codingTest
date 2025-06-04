package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

public class backJonn1920 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("5" + lc);
        ex.add("4 1 5 2 3" + lc);
        ex.add("5" + lc);
        ex.add("1 3 7 9 5");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        br.readLine();
        String[] card = br.readLine().split(" ");

        HashSet<Integer> cardSet = new HashSet<>();
        for (String c : card) {
            cardSet.add(Integer.parseInt(c));
        }

        int cnt = Integer.parseInt(br.readLine());
        String[] hands = br.readLine().split(" ");

        for (int i = 0; i < cnt; i++) {
            if (cardSet.contains(Integer.parseInt(hands[i]))) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
