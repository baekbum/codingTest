package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn11286 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("18" + lc);
        ex.add("1" + lc);
        ex.add("-1" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("1" + lc);
        ex.add("1" + lc);
        ex.add("-1" + lc);
        ex.add("-1" + lc);
        ex.add("2" + lc);
        ex.add("-2" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int cnt = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        TreeSet<Integer> minSet = new TreeSet<>();

        for (int i = 0; i < cnt; i++) {
            int value = Integer.parseInt(br.readLine());

            if (value != 0) {
                int key = Math.abs(value);

                if(!map.containsKey(key)) {
                    PriorityQueue<Integer> queue = new PriorityQueue<>();
                    queue.add(value);

                    map.put(key, queue);
                } else {
                    map.get(key).add(value);
                }

                minSet.add(key);
            } else {
                if (minSet.isEmpty()) {
                    sb.append(0);
                } else {
                    Integer min = minSet.first();
                    sb.append(map.get(min).poll());

                    if (map.get(min).isEmpty()) minSet.remove(min);
                }

                if (i < cnt-1) {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}