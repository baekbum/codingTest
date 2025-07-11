package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2573 {
    private static int HEIGHT;
    private static int WIDTH;
    private static int[][] MAP;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5 7" + lc);
        ex.add("0 0 0 0 0 0 0" + lc);
        ex.add("0 2 4 5 3 0 0" + lc);
        ex.add("0 3 0 2 5 2 0" + lc);
        ex.add("0 7 6 2 4 0 0" + lc);
        ex.add("0 0 0 0 0 0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        HEIGHT = Integer.parseInt(size[0]);
        WIDTH = Integer.parseInt(size[1]);

        MAP = new int[HEIGHT][WIDTH];

        ArrayList<Glacier> list = new ArrayList<>();

        for (int h = 0; h < HEIGHT; h++) {
            String[] data = br.readLine().split(" ");
            for (int w = 0; w < WIDTH; w++) {
                MAP[h][w] = Integer.parseInt(data[w]);
                if (MAP[h][w] != 0) list.add(new Glacier(h, w));
            }
        }

        System.out.println(bfs(list));
    }

    /**
     * 1. 빙하가 2 덩어리로 쪼개졌는지 확인하는 작업
     * 2. 빙하가 녹는 작업 반복
     */
    private static int bfs(ArrayList<Glacier> list) {
        int year = 0;

        while (true) {
            printMap();

            // 1번 작업
            int connectCnt = visitedCheck();
            if (1 < connectCnt) return year;
            if (connectCnt == 0) return 0;

            // 2번 작업
            MAP = melting(list);
            year++;
        }
    }

    private static int[][] melting(ArrayList<Glacier> list) {
        int[][] newMap = new int[HEIGHT][WIDTH];
        ArrayList<Glacier> removeList = new ArrayList<>();

        for (Glacier g : list) {
            int h = g.getH();
            int w = g.getW();

            int nearSeaCnt = 0;

            // 동서남북으로 확인
            if (MAP[h][w+1] == 0) nearSeaCnt++;
            if (MAP[h][w-1] == 0) nearSeaCnt++;
            if (MAP[h+1][w] == 0) nearSeaCnt++;
            if (MAP[h-1][w] == 0) nearSeaCnt++;

            newMap[h][w] = Math.max(MAP[h][w] - nearSeaCnt, 0);
            if (newMap[h][w] == 0) removeList.add(new Glacier(h, w));
        }

        list.removeAll(removeList);
        return newMap;
    }

    private static int visitedCheck() {
        boolean[][] visited = new boolean[HEIGHT][WIDTH];
        int connectCnt = 0;

        for (int h = 1; h < HEIGHT-1; h++) {
            for (int w = 1; w < WIDTH-1; w++) {
                if (MAP[h][w] != 0 && !visited[h][w]) {
                    Deque<Glacier> deque = new ArrayDeque<>();
                    deque.add(new Glacier(h, w));
                    visited[h][w] = true;

                    connectCnt++;

                    while (!deque.isEmpty()) {
                        Glacier g = deque.pollFirst();
                        // 동서남북순으로 이동
                        if (moveValidation(g.getH(), g.getW()+1) && MAP[g.getH()][g.getW()+1] != 0 && !visited[g.getH()][g.getW()+1]) {
                            deque.add(new Glacier(g.getH(), g.getW()+1));
                            visited[g.getH()][g.getW()+1] = true;
                        }

                        if (moveValidation(g.getH(), g.getW()-1) && MAP[g.getH()][g.getW()-1] != 0 && !visited[g.getH()][g.getW()-1]) {
                            deque.add(new Glacier(g.getH(), g.getW()-1));
                            visited[g.getH()][g.getW()-1] = true;
                        }

                        if (moveValidation(g.getH()+1, g.getW()) && MAP[g.getH()+1][g.getW()] != 0 && !visited[g.getH()+1][g.getW()]) {
                            deque.add(new Glacier(g.getH()+1, g.getW()));
                            visited[g.getH()+1][g.getW()] = true;
                        }

                        if (moveValidation(g.getH()-1, g.getW()) && MAP[g.getH()-1][g.getW()] != 0 && !visited[g.getH()-1][g.getW()]) {
                            deque.add(new Glacier(g.getH()-1, g.getW()));
                            visited[g.getH()-1][g.getW()] = true;
                        }
                    }
                }
            }
        }

        return connectCnt;
    }

    private static boolean moveValidation(int h, int w) {
        return 0 < h && h < HEIGHT-1 && 0 < w && w < WIDTH-1;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                sb.append(MAP[h][w]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static class Glacier {
        int h;
        int w;

        public Glacier(int h, int w) {
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
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Glacier glacier = (Glacier) o;
            return h == glacier.h && w == glacier.w;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }

        @Override
        public String toString() {
            return "Glacier{" +
                    "h=" + h +
                    ", w=" + w +
                    '}';
        }
    }
}