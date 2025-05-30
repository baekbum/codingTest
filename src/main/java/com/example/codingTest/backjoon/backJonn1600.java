package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1600 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

//        ex.add("1" + lc);
//        ex.add("4 4" + lc);
//        ex.add("0 0 0 0" + lc);
//        ex.add("1 0 0 0" + lc);
//        ex.add("0 0 1 0" + lc);
//        ex.add("0 1 0 0");

//        ex.add("2" + lc);
//        ex.add("5 2" + lc);
//        ex.add("0 0 1 1 0" + lc);
//        ex.add("0 0 1 1 0");

//        ex.add("2" + lc);
//        ex.add("8 12" + lc);
//        ex.add("0 0 0 0 0 0 0 0" + lc);
//        ex.add("0 1 1 1 1 1 1 0" + lc);
//        ex.add("0 1 1 1 1 1 1 0" + lc);
//        ex.add("0 1 1 0 0 0 0 0" + lc);
//        ex.add("0 1 1 0 1 1 1 1" + lc);
//        ex.add("0 1 1 0 1 1 1 1" + lc);
//        ex.add("0 1 1 0 0 0 0 0" + lc);
//        ex.add("0 1 1 0 1 1 1 0" + lc);
//        ex.add("0 1 1 1 1 1 1 0" + lc);
//        ex.add("1 1 0 0 0 0 1 1" + lc);
//        ex.add("1 1 1 1 1 1 1 1" + lc);
//        ex.add("1 1 1 0 1 1 0 0");

        ex.add("1" + lc);
        ex.add("7 8" + lc);
        ex.add("0 0 0 0 0 0 0" + lc);
        ex.add("1 1 1 1 1 1 0" + lc);
        ex.add("1 1 1 1 1 1 0" + lc);
        ex.add("0 0 0 1 1 1 0" + lc);
        ex.add("0 1 1 1 0 0 0" + lc);
        ex.add("0 1 1 1 1 1 1" + lc);
        ex.add("0 1 1 1 1 1 1" + lc);
        ex.add("0 0 0 0 0 0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int initHorseCnt = Integer.parseInt(br.readLine());
        String[] size = br.readLine().split(" ");
        MAX_HEIGHT = Integer.parseInt(size[1]);
        MAX_WIDTH = Integer.parseInt(size[0]);

        int[][] map = new int[MAX_HEIGHT][MAX_WIDTH];
        boolean[][][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH][initHorseCnt+1];

        for (int i = 0; i < MAX_HEIGHT; i++) {
            String[] data = br.readLine().split(" ");
            for (int j = 0; j < MAX_WIDTH; j++) {
                map[i][j] = Integer.parseInt(data[j]);
            }
        }

        bfs(map, visited, initHorseCnt);
    }

    private static void bfs(int[][] map, boolean[][][] visited, int initHorseCnt) {
        Deque<Monkey> deque = new ArrayDeque<>();
        deque.add(new Monkey(0, 0, 0, initHorseCnt));

        for (int i = 0; i < initHorseCnt; i++) {
            visited[0][0][i] = true;
        }

        ArrayList<Integer> answer = new ArrayList<>();

        while (!deque.isEmpty()) {
            Monkey monkey = deque.pollFirst();
            int h = monkey.getH();
            int w = monkey.getW();
            int cnt = monkey.getCnt();
            int horseCnt = monkey.getHorseCnt();

            if (h == MAX_HEIGHT-1 && w == MAX_WIDTH-1) {
                answer.add(cnt);
            }

            // 인접한 동서남북으로 이동
            if (validPos(h, w+1)) {
                if (!visited[h][w+1][horseCnt] && map[h][w+1] == 0) {
                    visited[h][w+1][horseCnt] = true;

                    deque.add(new Monkey(h, w+1, cnt+1, horseCnt));
                }
            }

            if (validPos(h, w-1)) {
                if (!visited[h][w-1][horseCnt] && map[h][w-1] == 0) {
                    visited[h][w-1][horseCnt] = true;

                    deque.add(new Monkey(h, w-1, cnt+1, horseCnt));
                }
            }

            if (validPos(h+1, w)) {
                if (!visited[h+1][w][horseCnt] && map[h+1][w] == 0) {
                    visited[h+1][w][horseCnt] = true;

                    deque.add(new Monkey(h+1, w, cnt+1, horseCnt));
                }
            }

            if (validPos(h-1, w)) {
                if (!visited[h-1][w][horseCnt] && map[h-1][w] == 0) {
                    visited[h-1][w][horseCnt] = true;

                    deque.add(new Monkey(h-1, w, cnt+1, horseCnt));
                }
            }

            if (horseCnt > 0)
                moveAsHorse(map, visited, monkey, deque);

//            deque.stream().forEach(System.out::println);
//            System.out.println();
        }

        if (answer.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(answer.stream().mapToInt(c -> c).min().getAsInt());
        }
    }

    private static boolean validPos (int h, int w) {
        return -1 < h && h < MAX_HEIGHT && -1 < w && w < MAX_WIDTH;
    }

    private static void moveAsHorse(int[][] map, boolean[][][] visited, Monkey monkey, Deque<Monkey> deque) {
        ArrayList<Monkey> list = new ArrayList<>();
        int h = monkey.getH();
        int w = monkey.getW();
        int cnt = monkey.getCnt();
        int horseCnt = monkey.getHorseCnt();
        // 시계 방향으로

        list.add(new Monkey(h-2, w+1, cnt+1, horseCnt-1));
        list.add(new Monkey(h-1, w+2, cnt+1, horseCnt-1));

        list.add(new Monkey(h+1, w+2, cnt+1, horseCnt-1));
        list.add(new Monkey(h+2, w+1, cnt+1, horseCnt-1));

        list.add(new Monkey(h+2, w-1, cnt+1, horseCnt-1));
        list.add(new Monkey(h+1, w-2, cnt+1, horseCnt-1));

        list.add(new Monkey(h-1, w-2, cnt+1, horseCnt-1));
        list.add(new Monkey(h-2, w-1, cnt+1, horseCnt-1));

        list.stream()
                .filter(m -> validPos(m.getH(), m.getW()))
                .filter(m -> map[m.getH()][m.getW()] == 0)
                .filter(m -> !visited[m.getH()][m.getW()][horseCnt-1])
                .forEach(m -> {
                    visited[m.getH()][m.getW()][horseCnt-1] = true;
                    deque.add(m);
                });
    }

    private static class Monkey {
        int h;
        int w;
        int cnt;
        int horseCnt;

        public Monkey(int h, int w, int cnt, int horseCnt) {
            this.h = h;
            this.w = w;
            this.cnt = cnt;
            this.horseCnt = horseCnt;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public int getCnt() {
            return cnt;
        }

        public int getHorseCnt() {
            return horseCnt;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "h=" + h +
                    ", w=" + w +
                    ", cnt=" + cnt +
                    ", horseCnt=" + horseCnt +
                    '}';
        }
    }
}
