package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class backJonn1926 {
    public static int HEIGHT;
    public static int WIDTH;
    public static int[][] MAP;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("6 5" + lc);
//        ex.add("1 1 0 1 1" + lc);
//        ex.add("0 1 1 0 0" + lc);
//        ex.add("0 0 0 0 0" + lc);
//        ex.add("1 0 1 1 1" + lc);
//        ex.add("0 0 1 1 1" + lc);
//        ex.add("0 0 1 1 1");

        ex.add("4 2" + lc);
        ex.add("0 0" + lc);
        ex.add("0 0" + lc);
        ex.add("0 0" + lc);
        ex.add("0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        HEIGHT = Integer.parseInt(size[0]);
        WIDTH = Integer.parseInt(size[1]);

        MAP = new int[HEIGHT][WIDTH];

        int pictureCnt = 0;

        for (int h = 0; h < HEIGHT; h++) {
            String[] data = br.readLine().split(" ");
            for (int w = 0; w < WIDTH; w++) {
                MAP[h][w] = Integer.parseInt(data[w]);
                if (MAP[h][w] == 1) pictureCnt++;
            }
        }


        System.out.println(bfs());

    }

    private static String bfs() {
        int pictureCnt = 0;
        int maxSize = 0;

        boolean[][] visited = new boolean[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (MAP[i][j] != 0 && !visited[i][j]) {
                    Deque<Node> deque = new ArrayDeque<>();
                    deque.add(new Node(i, j));
                    visited[i][j] = true;

                    pictureCnt++;

                    int size = 0;

                    while (!deque.isEmpty()) {
                        Node node = deque.pollFirst();
                        int h = node.getH();
                        int w = node.getW();

                        size++;

                        // 동서남북 순으로 이동
                        if (moveValidation(h, w+1)) {
                            if (MAP[h][w+1] == 1 && !visited[h][w+1]) {
                                deque.add(new Node(h, w+1));
                                visited[h][w+1] = true;
                            }
                        }

                        if (moveValidation(h, w-1)) {
                            if (MAP[h][w-1] == 1 &&!visited[h][w-1]) {
                                deque.add(new Node(h, w-1));
                                visited[h][w-1] = true;
                            }
                        }

                        if (moveValidation(h+1, w)) {
                            if (MAP[h+1][w] == 1 && !visited[h+1][w]) {
                                deque.add(new Node(h+1, w));
                                visited[h+1][w] = true;
                            }
                        }

                        if (moveValidation(h-1, w)) {
                            if (MAP[h-1][w] == 1 &&!visited[h-1][w]) {
                                deque.add(new Node(h-1, w));
                                visited[h-1][w] = true;
                            }
                        }
                    }

                    maxSize = Integer.max(maxSize, size);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pictureCnt);
        sb.append("\n");
        sb.append(maxSize);

        return sb.toString();
    }

    private static boolean moveValidation(int h, int w) {
        return -1 < h && h < HEIGHT && -1 < w && w < WIDTH;
    }

    private static class Node {
        int h;
        int w;

        public Node(int h, int w) {
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
            return "Node{" +
                    "h=" + h +
                    ", w=" + w +
                    '}';
        }
    }
}