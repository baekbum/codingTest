package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
//import java.io.InputStreamReader;

public class backJonn14940 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    private static int[][] MAP;
    private static int[][] COST;

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("15 15" + lc);
        ex.add("2 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 0 0 0 0 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 0 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 0 1 0 0 0" + lc);
        ex.add("1 1 1 1 1 1 1 1 1 1 0 1 1 1 1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] mapSizeInfo = br.readLine().split(" ");

        MAX_HEIGHT = Integer.parseInt(mapSizeInfo[0]);
        MAX_WIDTH = Integer.parseInt(mapSizeInfo[1]);

        MAP = new int[MAX_HEIGHT][MAX_WIDTH];
        COST = new int[MAX_HEIGHT][MAX_WIDTH];
        boolean[][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH];
        Node start = null;

        for (int h = 0; h < MAX_HEIGHT; h++) {
            String[] line = br.readLine().split(" ");
            for (int w = 0; w < MAX_WIDTH; w++) {
                int i = Integer.parseInt(line[w]);

                MAP[h][w] = i;
                if (i == 2) start = new Node(h, w, 0);
            }
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start.getH()][start.getW()] = true;
        COST[start.getH()][start.getW()] = start.getC();

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int h = node.getH();
            int w = node.getW();;
            int c = node.getC();;

            // 동서남북
            if (validation(h, w+1) && !visited[h][w+1] && MAP[h][w+1] == 1) {
                visited[h][w+1] = true;
                COST[h][w+1] = c+1;
                deque.add(new Node(h, w+1, c+1));
            }

            if (validation(h, w-1) && !visited[h][w-1] && MAP[h][w-1] == 1) {
                visited[h][w-1] = true;
                COST[h][w-1] = c+1;
                deque.add(new Node(h, w-1, c+1));
            }

            if (validation(h+1, w) && !visited[h+1][w] && MAP[h+1][w] == 1) {
                visited[h+1][w] = true;
                COST[h+1][w] = c+1;
                deque.add(new Node(h+1, w, c+1));
            }

            if (validation(h-1, w) && !visited[h-1][w] && MAP[h-1][w] == 1) {
                visited[h-1][w] = true;
                COST[h-1][w] = c+1;
                deque.add(new Node(h-1, w, c+1));
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int h = 0; h < MAX_HEIGHT; h++) {
            for (int w = 0; w < MAX_WIDTH; w++) {
                if (MAP[h][w] == 1 && COST[h][w] == 0) {
                    COST[h][w] = -1;
                }

                sb.append(COST[h][w]);
                sb.append(" ");
            }

            if (h < MAX_HEIGHT-1) sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean validation(int h, int w) {
        return -1 < h &&
                h < MAX_HEIGHT &&
                -1 < w &&
                w < MAX_WIDTH;
    }

    private static class Node {
        int h;
        int w;
        int c;

        public Node(int h, int w, int c) {
            this.h = h;
            this.w = w;
            this.c = c;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "h=" + h +
                    ", w=" + w +
                    ", c=" + c +
                    '}';
        }
    }
}