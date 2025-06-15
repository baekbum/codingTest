package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn11725 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("7" + lc);
//        ex.add("1 6" + lc);
//        ex.add("6 3" + lc);
//        ex.add("3 5" + lc);
//        ex.add("4 1" + lc);
//        ex.add("2 4" + lc);
//        ex.add("4 7");

        ex.add("12" + lc);
        ex.add("1 2" + lc);
        ex.add("1 3" + lc);
        ex.add("2 4" + lc);
        ex.add("3 5" + lc);
        ex.add("3 6" + lc);
        ex.add("4 7" + lc);
        ex.add("4 8" + lc);
        ex.add("5 9" + lc);
        ex.add("5 10" + lc);
        ex.add("6 11" + lc);
        ex.add("6 12");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            map.put(i+1, new ArrayList<>());
        }

        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");

            map.get(Integer.parseInt(data[0])).add(Integer.parseInt(data[1]));
            map.get(Integer.parseInt(data[1])).add(Integer.parseInt(data[0]));
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);

        int[] visited = new int[size+1];

        while (!deque.isEmpty()) {
            Integer parent = deque.pollFirst();

            for (Integer child : map.get(parent)) {
                if (visited[child] == 0) {
                    visited[child] = parent;
                    deque.add(child);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < visited.length; i++) {
            sb.append(visited[i]);

            if (i < visited.length-1) {
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}