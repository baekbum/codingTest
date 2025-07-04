package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn16234 {
    private static int MIN;
    private static int MAX;
    private static int SIZE;
    private static int[][] MAP;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("2 20 50" + lc);
//        ex.add("50 30" + lc);
//        ex.add("20 40");

//        ex.add("3 14 67" + lc);
//        ex.add("46 91 49" + lc);
//        ex.add("33 20 72" + lc);
//        ex.add("9 0 49");

        ex.add("2 22 49" + lc);
        ex.add("81 31" + lc);
        ex.add("39 57");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] info = br.readLine().split(" ");
        SIZE = Integer.parseInt(info[0]);
        MIN = Integer.parseInt(info[1]);
        MAX = Integer.parseInt(info[2]);

        MAP = new int[SIZE][SIZE];

        for (int h = 0; h < SIZE; h++) {
            String[] data = br.readLine().split(" ");
            for (int w = 0; w < SIZE; w++) {
               MAP[h][w] = Integer.parseInt(data[w]);
            }
        }

        int days = 0;

        while (true) {
            ArrayList<ArrayList<Node>> result =  new ArrayList<>();
            boolean[][] visited =  new boolean[SIZE][SIZE];

            for (int height = 0; height < SIZE; height++) {
                for (int width = 0; width < SIZE; width++) {
                    if (visited[height][width]) continue;

                    ArrayList<Node> cities = new ArrayList<>();
                    dfs(height, width, visited, cities);

                    if (1 < cities.size()) {
                        result.add(cities);
                    }
                }
            }

            if (result.isEmpty()) break;
            days++;

            for (ArrayList<Node> cities : result) {
                double initAverage = cities.stream()
                    .mapToInt(Node::getP)
                    .average().getAsDouble();

                int average = (int) Math.floor(initAverage);

                cities.stream()
                    .forEach(node -> {
                        MAP[node.getH()][node.getW()] = average;
                    });
            }

//            StringBuilder sb = new StringBuilder();
//            for (int h = 0; h < SIZE; h++) {
//                for (int w = 0; w < SIZE; w++) {
//                    sb.append(MAP[h][w]).append(" ");
//                }
//                sb.append("\n");
//            }
//            System.out.println(sb);
        }

        System.out.println(days);
    }

    private static void dfs(int h, int w, boolean[][] visited, ArrayList<Node> cities) {
        cities.add(new Node(h, w, MAP[h][w]));
        visited[h][w] = true;

        // 동서남북으로 이동
        if (totalValidation(h, w+1, visited)) {
            if (populationValidation(Math.abs(MAP[h][w] - MAP[h][w+1]))) {
                dfs(h, w+1, visited, cities);
            }
        }

        if (totalValidation(h, w-1, visited)) {
            if (populationValidation(Math.abs(MAP[h][w] - MAP[h][w-1]))) {
                dfs(h, w-1, visited, cities);
            }
        }

        if (totalValidation(h+1, w, visited)) {
            if (populationValidation(Math.abs(MAP[h][w] - MAP[h+1][w]))) {
                dfs(h+1, w, visited, cities);
            }
        }

        if (totalValidation(h-1, w, visited)) {
            if (populationValidation(Math.abs(MAP[h][w] - MAP[h-1][w]))) {
                dfs(h-1, w, visited, cities);
            }
        }
    }

    private static boolean totalValidation(int h, int w, boolean[][] visited) {
        return moveValidation(h, w) && !visited[h][w];
    }

    private static boolean moveValidation(int h, int w) {
        return -1 < h && h < SIZE && -1 < w && w < SIZE;
    }

    private static boolean populationValidation(int population) {
        return MIN <= population && population <= MAX;
    }

    private static class Node {
        int h;
        int w;
        int p;

        public Node(int h, int w, int p) {
            this.h = h;
            this.w = w;
            this.p = p;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public int getP() {
            return p;
        }
    }
}