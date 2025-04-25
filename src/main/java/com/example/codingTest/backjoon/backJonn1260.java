package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class backJonn1260 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
//        ex.add("4 5 1" + lc);
//        ex.add("1 2" + lc);
//        ex.add("1 3" + lc);
//        ex.add("1 4" + lc);
//        ex.add("2 4" + lc);
//        ex.add("3 4");

        ex.add("5 5 3" + lc);
        ex.add("5 4" + lc);
        ex.add("5 2" + lc);
        ex.add("1 2" + lc);
        ex.add("3 4" + lc);
        ex.add("3 1");

//        ex.add("6 5 1" + lc);
//        ex.add("1 2" + lc);
//        ex.add("1 3" + lc);
//        ex.add("1 4" + lc);
//        ex.add("2 4" + lc);
//        ex.add("5 6");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] info = br.readLine().split(" ");

        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();

        // 데이터 만드는 과정
        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");
            int current = Integer.valueOf(data[0]);
            int connect = Integer.valueOf(data[1]);

            if (!map.containsKey(current)) {
                map.put(current, new TreeSet<>(Set.of(connect)));
            } else {
                map.get(current).add(connect);
            }

            if (!map.containsKey(connect)) {
                map.put(connect, new TreeSet<>(Set.of(current)));
            } else {
                map.get(connect).add(current);
            }
        }

        StringBuilder answer = new StringBuilder();
        int initKey = Integer.valueOf(info[2]);

        ArrayList<Integer> dfsList = new ArrayList<>();
        dfs(initKey, map, dfsList);
        dfsList.stream().forEach(v -> answer.append(v + " "));
        answer.toString().trim();

        answer.append("\n");

        ArrayList<Integer> bfsList = new ArrayList<>();
        bfs(initKey, map, bfsList);
        bfsList.stream().forEach(v -> answer.append(v + " "));
        answer.toString().trim();

        System.out.println(answer);
    }

    private static void dfs(int key, HashMap<Integer, TreeSet<Integer>> map, ArrayList<Integer> list) {
        // 키가 존재하지 않으면, 최초 입력
        if (!list.contains(key)) list.add(key);

        // 해당 키의 연결된 노드를 가져옴
        if (map.containsKey(key)) {
            TreeSet<Integer> child = map.get(key);

            if (child != null) {
                for (Integer c : child) {
                    if (!list.contains(c)) {
                        dfs(c, map, list);
                    }
                }
            }
        }
    }

    private static void bfs(int key, HashMap<Integer, TreeSet<Integer>> map, ArrayList<Integer> list) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(key);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            if (!list.contains(current)) list.add(current);

            if (map.containsKey(current)) {
                map.get(current).stream()
                        .filter(child -> !list.contains(child))
                        .forEach(queue::add);
            }
        }
    }
}
