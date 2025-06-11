package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

public class backJonn1012 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("2" + lc);
        ex.add("10 8 17" + lc);
        ex.add("0 0" + lc);
        ex.add("1 0" + lc);
        ex.add("1 1" + lc);
        ex.add("4 2" + lc);
        ex.add("4 3" + lc);
        ex.add("4 5" + lc);
        ex.add("2 4" + lc);
        ex.add("3 4" + lc);
        ex.add("7 4" + lc);
        ex.add("8 4" + lc);
        ex.add("9 4" + lc);
        ex.add("7 5" + lc);
        ex.add("8 5" + lc);
        ex.add("9 5" + lc);
        ex.add("7 6" + lc);
        ex.add("8 6" + lc);
        ex.add("9 6" + lc);
        ex.add("10 10 1" + lc);
        ex.add("5 5");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int testCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i< testCnt; i++) {
            String[] info = br.readLine().split(" ");
            int width = Integer.parseInt(info[0]);
            int height = Integer.parseInt(info[1]);
            int cabbageCnt = Integer.parseInt(info[2]);

            int[][] map = new int[height][width];
            boolean[][] visit = new boolean[height][width];

            for (int c = 0; c < cabbageCnt; c++) {
                String[] pos = br.readLine().split(" ");
                int w = Integer.parseInt(pos[0]);
                int h = Integer.parseInt(pos[1]);

                map[h][w] = 1;
            }

            bfs(map, visit, cabbageCnt);
        }
    }

    private static void bfs(int[][] map, boolean[][] visit, int cabbageCnt) {
        int visitCnt = 0;
        int insect = 0;

        for (int height = 0; height < map.length; height++) {
            for (int width = 0; width < map[0].length; width++) {
                if (cabbageCnt == visitCnt) break;

                if (map[height][width] == 1 && !visit[height][width]) {
                    insect++;

                    Deque<Pos> deque = new ArrayDeque<>();
                    deque.add(new Pos(height, width));
                    visit[height][width] = true;

                    while (!deque.isEmpty()) {
                        Pos pos = deque.pollFirst();
                        int h = pos.getH();
                        int w = pos.getW();
                        visitCnt++;

                        // 동서남북 이동
                        if (w+1 < map[0].length && map[h][w+1] == 1 && !visit[h][w+1]) {
                            deque.add(new Pos(h, w+1));
                            visit[h][w+1] = true;
                        }

                        if (-1 < w-1 && map[h][w-1] == 1 && !visit[h][w-1]) {
                            deque.add(new Pos(h, w-1));
                            visit[h][w-1] = true;
                        }

                        if (h+1 < map.length && map[h+1][w] == 1 && !visit[h+1][w]) {
                            deque.add(new Pos(h+1, w));
                            visit[h+1][w] = true;
                        }

                        if (-1 < h-1 && map[h-1][w] == 1 && !visit[h-1][w]) {
                            deque.add(new Pos(h-1, w));
                            visit[h-1][w] = true;
                        }
                    }
                }
            }
        }

        System.out.println(insect);
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