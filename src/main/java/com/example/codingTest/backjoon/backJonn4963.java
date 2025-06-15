package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class backJonn4963 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("1 1" + lc);
        ex.add("0" + lc);
        ex.add("2 2" + lc);
        ex.add("0 1" + lc);
        ex.add("1 0" + lc);
        ex.add("3 2" + lc);
        ex.add("1 1 1" + lc);
        ex.add("1 1 1" + lc);
        ex.add("5 4" + lc);
        ex.add("1 0 1 0 0" + lc);
        ex.add("1 0 0 0 0" + lc);
        ex.add("1 0 1 0 1" + lc);
        ex.add("1 0 0 1 0" + lc);
        ex.add("5 4" + lc);
        ex.add("1 1 1 0 1" + lc);
        ex.add("1 0 1 0 1" + lc);
        ex.add("1 0 1 0 1" + lc);
        ex.add("1 0 1 1 1" + lc);
        ex.add("5 5" + lc);
        ex.add("1 0 1 0 1" + lc);
        ex.add("0 0 0 0 0" + lc);
        ex.add("1 0 1 0 1" + lc);
        ex.add("0 0 0 0 0" + lc);
        ex.add("1 0 1 0 1" + lc);
        ex.add("0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String s;
        while ((s = br.readLine()) != null) {
            String[] size = s.split(" ");
            MAX_HEIGHT = Integer.parseInt(size[1]);
            MAX_WIDTH = Integer.parseInt(size[0]);

            int[][] map = new int[MAX_HEIGHT][MAX_WIDTH];

            for (int i = 0; i < MAX_HEIGHT; i++) {
                String[] data = br.readLine().split(" ");
                for (int j = 0; j < MAX_WIDTH; j++) {
                    map[i][j] = Integer.parseInt(data[j]);
                }
            }

            if (0 < MAX_HEIGHT && 0 < MAX_WIDTH) {
                bfs(map);
            }
        }
    }

    private static void bfs(int[][] map) {
        boolean[][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH];
        Deque<Pos> deque = new ArrayDeque<>();
        BiConsumer<Integer, Integer> addQueue = addQueue(deque, visited);
        int cnt = 0;

        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    deque.add(new Pos(i, j));
                    visited[i][j] = true;
                    cnt++;

                    while (!deque.isEmpty()) {
                        Pos pos = deque.pollFirst();
                        int h = pos.getH();
                        int w = pos.getW();

                        // 시계 방향으로
                        if (isValid(h-1, w, map, visited)) addQueue.accept(h-1, w);
                        if (isValid(h-1, w+1, map, visited)) addQueue.accept(h-1, w+1);
                        if (isValid(h, w+1, map, visited)) addQueue.accept(h, w+1);
                        if (isValid(h+1, w+1, map, visited)) addQueue.accept(h+1, w+1);
                        if (isValid(h+1, w, map, visited)) addQueue.accept(h+1, w);
                        if (isValid(h+1, w-1, map, visited)) addQueue.accept(h+1, w-1);
                        if (isValid(h, w-1, map, visited)) addQueue.accept(h, w-1);
                        if (isValid(h-1, w-1, map, visited)) addQueue.accept(h-1, w-1);

//                        StringBuilder sb = new StringBuilder();
//                        for (int y = 0; y < MAX_HEIGHT; y++) {
//                            for (int x = 0; x < MAX_WIDTH; x++) {
//                                if (visited[y][x]) {
//                                    sb.append(visited[y][x]).append("  ");
//                                } else {
//                                    sb.append(visited[y][x]).append(" ");
//                                }
//                            }
//                            sb.append("\n");
//                        }
//                        System.out.println(sb);
//
//                        System.out.println("cnt = " + cnt);
                    }

                    //System.out.println("========================");
                    deque.clear();
                }
            }
        }

        System.out.println(cnt);
    }

    private static BiConsumer<Integer, Integer> addQueue(Deque<Pos> deque, boolean[][] visited) {
        return (h, w) -> {
            deque.add(new Pos(h, w));
            visited[h][w] = true;
        };
    }

    private static boolean isValid(int h, int w, int[][] map, boolean[][] visited) {
        return (-1 < h && h < MAX_HEIGHT && -1 < w && w < MAX_WIDTH && map[h][w] == 1 && !visited[h][w]);
    }

    private static class Pos {
        int h;
        int w;

        public Pos(int h, int w) {
            this.h = h;
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", w=" + w +
                    '}';
        }
    }
}