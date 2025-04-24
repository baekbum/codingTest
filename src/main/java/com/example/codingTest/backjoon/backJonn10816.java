package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class backJonn10816 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
        ex.add("10" + lc);
        ex.add("6 3 2 10 10 10 -10 -10 7 3" + lc);
        ex.add("8" + lc);
        ex.add("10 9 -5 2 3 4 5 -10");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        String[] numberArr = br.readLine().split(" ");

        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder answer = new StringBuilder();

        for (String s : numberArr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        br.readLine();
        String[] targetArr = br.readLine().split(" ");

        for (String s : targetArr) {
            if (map.containsKey(s)) answer.append(map.get(s));
            else answer.append("0");

            answer.append(" ");
        }

        System.out.println(answer.toString().trim());
    }
}
