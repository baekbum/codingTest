package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.function.Consumer;

public class backJonn11403 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("3" + lc);
//        ex.add("0 1 0" + lc);
//        ex.add("0 0 1" + lc);
//        ex.add("1 0 0");

        ex.add("7" + lc);
        ex.add("0 0 0 1 0 0 0" + lc);
        ex.add("0 0 0 0 0 0 1" + lc);
        ex.add("0 0 0 0 0 0 0" + lc);
        ex.add("0 0 0 0 1 1 0" + lc);
        ex.add("1 0 0 0 0 0 0" + lc);
        ex.add("0 0 0 0 0 0 1" + lc);
        ex.add("0 0 1 0 0 0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());

        int[][] map = new int[size][size];
        HashMap<Integer, ArrayList<Integer>> list = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String[] data = br.readLine().split(" ");
            list.put(i, new ArrayList<>());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(data[j]);

                if (map[i][j] == 1) {
                    list.get(i).add(j);
                }
            }
        }

        bfs(size, list);

    }

    private static void bfs(int size, HashMap<Integer, ArrayList<Integer>> list) {
        int[][] result = new int[size][size];

        // i는 출발지점
        for (int i = 0; i < size; i++) {
            HashSet<Integer> answer = new HashSet<>();
            Deque<Integer> deque = new ArrayDeque<>();
            list.get(i).stream()
                    .forEach(n -> {
                        deque.add(n);
                        answer.add(n);
                    });

            while (!deque.isEmpty()) {
                Integer number = deque.pollFirst();

                list.get(number).stream()
                        .filter(n -> !answer.contains(n))
                        .forEach(n -> {
                            deque.add(n);
                            answer.add(n);
                        });
            }
            
            int key = i;
            answer.stream().forEach(n -> {
                result[key][n] = 1;
            });
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(result[i][j]);
                if (j < size-1) sb.append(" ");
            }
            if (i < size-1) sb.append("\n");
        }
        System.out.println(sb);
    }
}