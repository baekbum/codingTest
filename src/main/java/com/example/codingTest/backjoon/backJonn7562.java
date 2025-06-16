package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class backJonn7562 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("3" + lc);
//        ex.add("8" + lc);
//        ex.add("0 0" + lc);
//        ex.add("7 0" + lc);
//        ex.add("100" + lc);
//        ex.add("0 0" + lc);
//        ex.add("30 50" + lc);
//        ex.add("10" + lc);
//        ex.add("1 1" + lc);
//        ex.add("1 1");

        ex.add("1" + lc);
        ex.add("4" + lc);
        ex.add("0 0" + lc);
        ex.add("3 3" + lc);
//        ex.add("4" + lc);
//        ex.add("3 3" + lc);
//        ex.add("0 0" + lc);

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int testCaseCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCnt; i++) {
            int size = Integer.parseInt(br.readLine());
            MAX_HEIGHT = size;
            MAX_WIDTH = size;
            boolean[][] visited = new boolean[size][size];

            String[] start = br.readLine().split(" ");
            String[] target = br.readLine().split(" ");

            bfs(visited, new Pos(Integer.parseInt(start[0]), Integer.parseInt(start[1]), 0), new Pos(Integer.parseInt(target[0]), Integer.parseInt(target[1]), 0));
        }
    }

    private static void bfs(boolean[][] visited, Pos start, Pos target) {
        Deque<Pos> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start.getH()][start.getW()] = true;
        Consumer<Pos> addQueue = addQueue(deque, visited);

        while (!deque.isEmpty()) {
            Pos pos = deque.pollFirst();
            int h = pos.getH();
            int w = pos.getW();
            int c = pos.getCnt();

            System.out.println("pos = " + pos);

            if (h == target.getH() && w == target.getW()) {
                System.out.println(c);
                break;
            }

            // 시계방향으로 1시부터 출발
            if (isValid(h-2, w+1, visited)) addQueue.accept(new Pos(h-2, w+1, c+1));
            if (isValid(h-1, w+2, visited)) addQueue.accept(new Pos(h-1, w+2, c+1));
            if (isValid(h+1, w+2, visited)) addQueue.accept(new Pos(h+1, w+2, c+1));
            if (isValid(h+2, w+1, visited)) addQueue.accept(new Pos(h+2, w+1, c+1));

            if (isValid(h+2, w-1, visited)) addQueue.accept(new Pos(h+2, w-1, c+1));
            if (isValid(h+1, w-2, visited)) addQueue.accept(new Pos(h+1, w-2, c+1));
            if (isValid(h-1, w-2, visited)) addQueue.accept(new Pos(h-1, w-2, c+1));
            if (isValid(h-2, w-1, visited)) addQueue.accept(new Pos(h-2, w-1, c+1));

        }
    }

    private static boolean isValid(int h, int w, boolean[][] visited) {
        return (-1 < h && h < MAX_HEIGHT && -1 < w && w < MAX_WIDTH && !visited[h][w]);
    }

    private static Consumer<Pos> addQueue(Deque<Pos> deque, boolean[][] visited) {
        return (pos) -> {
            deque.add(pos);
            visited[pos.getH()][pos.getW()] = true;
        };
    }

    private static class Pos {
        int h;
        int w;
        int cnt;

        public Pos(int h, int w, int cnt) {
            this.h = h;
            this.w = w;
            this.cnt = cnt;
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

        @Override
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", w=" + w +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}