package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2606 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
        ex.add("7" + lc);
        ex.add("6" + lc);
        ex.add("1 2" + lc);
        ex.add("2 3" + lc);
        ex.add("1 5" + lc);
        ex.add("5 2" + lc);
        ex.add("5 6" + lc);
        ex.add("4 7");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 1; i <= size ; i++) {
            map.put(i, new ArrayList<>());
        }

        String s;
        while ((s = br.readLine()) != null) {
            String[] computer = s.split(" ");

            map.get(Integer.parseInt(computer[0])).add(Integer.parseInt(computer[1]));
            map.get(Integer.parseInt(computer[1])).add(Integer.parseInt(computer[0]));
        }

        for (Integer key : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(key).append(" = ");

            map.get(key).stream()
                    .forEach(c -> sb.append(c + " "));

            System.out.println(sb);
        }

        HashSet<Integer> check = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int cnt = 0;
        deque.add(1);

        while (!deque.isEmpty()) {
            Integer key = deque.pollFirst();

            if (!check.contains(key)) {
                check.add(key);
                cnt++;
            }

            map.get(key).stream()
                    .filter(k -> !check.contains(k))
                    .forEach(deque::add);
        }

        System.out.println(cnt - 1);
    }
}
