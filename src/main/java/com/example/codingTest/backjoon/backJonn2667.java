package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2667 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
        ex.add("7" + lc);
        ex.add("0110100" + lc);
        ex.add("0110101" + lc);
        ex.add("1110101" + lc);
        ex.add("0000111" + lc);
        ex.add("0100000" + lc);
        ex.add("0111110" + lc);
        ex.add("0111000");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());

        int[][] data = init(br, size);
        int[][] pass = new int[size][size];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (pass[i][j] == 0 && data[i][j] == 1) bfs(i, j, data, pass, list);
            }
        }

        System.out.println(list.size());
        list.stream().sorted().forEach(System.out::println);
    }

    private static int[][] init(BufferedReader br, int size) throws IOException {
        int[][] data = new int[size][size];

        for (int i = 0; i < size; i++) {
            String d = br.readLine();
            for (int j = 0; j < size; j++) {
                data[i][j] = (int) d.charAt(j) - '0';
            }
        }

        return data;
    }

    private static void bfs(int h, int w, int[][] data, int[][] pass, ArrayList<Integer> list) {
        int connectionCnt = 0;

        Deque<Position> deque = new ArrayDeque<>();
        deque.add(new Position(h, w));

        while (!deque.isEmpty()) {
            Position p = deque.pollFirst();
            int height = p.getHeight();
            int width = p.getWidth();

            // 1. 아직 방문하지 않음
            // 2. 데이터가 1인 경우
            if (pass[height][width] == 0 && data[height][width] == 1) {
                connectionCnt++;
                pass[height][width] = 1; // 방문 표시

                // 상하좌우 좌표 입력
                if (height-1 > -1) deque.add(new Position(height-1, width));
                if (height+1 < data.length) deque.add(new Position(height+1, width));
                if (width-1 > -1) deque.add(new Position(height, width-1));
                if (width+1 < data[0].length) deque.add(new Position(height, width+1));
            }
        }

        if (connectionCnt != 0) list.add(connectionCnt);
    }

    private static class Position {
        int height;
        int width;

        public Position(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
}
