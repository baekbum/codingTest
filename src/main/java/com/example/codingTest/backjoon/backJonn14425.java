package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

public class backJonn14425 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5 11" + lc);
        ex.add("baekjoononlinejudge" + lc);
        ex.add("startlink" + lc);
        ex.add("codeplus" + lc);
        ex.add("sundaycoding" + lc);
        ex.add("codingsh" + lc);
        ex.add("baekjoon" + lc);
        ex.add("codeplus" + lc);
        ex.add("codeminus" + lc);
        ex.add("startlink" + lc);
        ex.add("starlink" + lc);
        ex.add("sundaycoding" + lc);
        ex.add("codingsh" + lc);
        ex.add("codinghs" + lc);
        ex.add("sondaycoding" + lc);
        ex.add("startrink" + lc);
        ex.add("icerink");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        HashSet<String> set = new HashSet<>();
        int cnt = 0;

        String[] info = br.readLine().split(" ");

        for (int i = 0; i < Integer.parseInt(info[0]); i++) {
            set.add(br.readLine());
        }

        for (int i = 0; i < Integer.parseInt(info[1]); i++) {
            if (set.contains(br.readLine())) cnt++;
        }

        System.out.println(cnt);
    }
}