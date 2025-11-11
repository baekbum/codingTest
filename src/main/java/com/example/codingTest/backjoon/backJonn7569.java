package com.example.codingTest.backjoon;

import com.example.codingTest.util.TriFunction;
import com.example.codingTest.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class backJonn7569 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    private static int MAX_FLOOR;
    private static ArrayList<Tomato> TOMATO_LIST = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5 3 1" + lc);
        ex.add("0 -1 0 0 0" + lc);
        ex.add("-1 -1 0 1 1" + lc);
        ex.add("0 0 0 1 1");

//        ex.add("5 3 2" + lc);
//        ex.add("0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0" + lc);
//        ex.add("0 0 1 0 0" + lc);
//        ex.add("0 0 0 0 0");

        ex.add("4 3 2" + lc);
        ex.add("1 1 1 1" + lc);
        ex.add("1 1 1 1" + lc);
        ex.add("1 1 1 1" + lc);
        ex.add("1 1 1 1" + lc);
        ex.add("-1 -1 -1 -1" + lc);
        ex.add("1 1 1 -1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] sizeInfo = br.readLine().split(" ");
        MAX_HEIGHT = Integer.parseInt(sizeInfo[1]);
        MAX_WIDTH = Integer.parseInt(sizeInfo[0]);
        MAX_FLOOR = Integer.parseInt(sizeInfo[2]);

        int[][][] map = new int[MAX_HEIGHT][MAX_WIDTH][MAX_FLOOR];
        // 익은 토마토 1
        // 익지 않은 토마토 0
        // 토마토 없음 -1
        for (int f = 0; f < MAX_FLOOR; f++) {
            for (int h = 0; h < MAX_HEIGHT; h++) {
                String[] tomatoStr = br.readLine().split(" ");
                for (int w = 0; w < MAX_WIDTH; w++) {
                    int tomato = Integer.parseInt(tomatoStr[w]);
                    map[h][w][f] = tomato;
                    if (tomato == 1) TOMATO_LIST.add(new Tomato(h, w, f, 0));
                }
            }
        }

        bfs(map);
    }

    static void bfs(int[][][] map) {
        Deque<Tomato> deque = new ArrayDeque<>(TOMATO_LIST);
        boolean[][][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH][MAX_FLOOR];
        TriFunction<Integer, Integer, Integer, Boolean> validation = Utils.validation(MAX_HEIGHT, MAX_WIDTH, MAX_FLOOR);
        int maxDays = 0;

        while (!deque.isEmpty()) {
            Tomato tomato = deque.pollFirst();

            int height = tomato.getHeight();
            int width = tomato.getWidth();
            int floor = tomato.getFloor();
            int days = tomato.getDays();

            visited[height][width][floor] = true;
            maxDays = Math.max(maxDays, days);

            // 동서남북 위, 아래
            if (validation.apply(height, width+1, floor) && !visited[height][width+1][floor] && map[height][width+1][floor] == 0) {
                visited[height][width+1][floor] = true;
                deque.add(new Tomato(height, width+1, floor, days+1));
            }

            if (validation.apply(height, width-1, floor) && !visited[height][width-1][floor] && map[height][width-1][floor] == 0) {
                visited[height][width-1][floor] = true;
                deque.add(new Tomato(height, width-1, floor, days+1));
            }

            if (validation.apply(height+1, width, floor) && !visited[height+1][width][floor] && map[height+1][width][floor] == 0) {
                visited[height+1][width][floor] = true;
                deque.add(new Tomato(height+1, width, floor, days+1));
            }

            if (validation.apply(height-1, width, floor) && !visited[height-1][width][floor] && map[height-1][width][floor] == 0) {
                visited[height-1][width][floor] = true;
                deque.add(new Tomato(height-1, width, floor, days+1));
            }

            if (validation.apply(height, width, floor-1) && !visited[height][width][floor-1] && map[height][width][floor-1] == 0) {
                visited[height][width][floor-1] = true;
                deque.add(new Tomato(height, width, floor-1, days+1));
            }

            if (validation.apply(height, width, floor+1) && !visited[height][width][floor+1] && map[height][width][floor+1] == 0) {
                visited[height][width][floor+1] = true;
                deque.add(new Tomato(height, width, floor+1, days+1));
            }
        }

        boolean isFail = false;

        for (int f = 0; f < MAX_FLOOR; f++) {
            for (int h = 0; h < MAX_HEIGHT; h++) {
                for (int w = 0; w < MAX_WIDTH; w++) {
                    if (map[h][w][f] == 0 && !visited[h][w][f]) {
                        isFail = true;
                    }
                }
            }
        }

        System.out.println(isFail ? -1 : maxDays);
    }

    static TriFunction<Integer, Integer, Integer, Boolean> validation(int maxH, int maxW, int maxF) {
        return (h, w, f) -> {
            return h < maxH
                    && -1 < h
                    && w < maxW
                    && -1 < w
                    && f < maxF
                    && -1 < f;
        };
    }

    static class Tomato {
        int height;
        int width;
        int floor;
        int days;

        public Tomato(int height, int width, int floor, int days) {
            this.height = height;
            this.width = width;
            this.floor = floor;
            this.days = days;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        @Override
        public String toString() {
            return "Tomato{" +
                    "height=" + height +
                    ", width=" + width +
                    ", floor=" + floor +
                    ", days=" + days +
                    '}';
        }
    }
}