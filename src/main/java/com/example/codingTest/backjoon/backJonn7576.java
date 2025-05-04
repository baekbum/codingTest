package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn7576 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
//        ex.add("6 4" + lc);
//        ex.add("0 0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0 1");

//        ex.add("6 4" + lc);
//        ex.add("0 -1 0 0 0 0" + lc);
//        ex.add("-1 0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0 1");

//        ex.add("6 4" + lc);
//        ex.add("1 -1 0 0 0 0" + lc);
//        ex.add("0 -1 0 0 0 0" + lc);
//        ex.add("0 0 0 0 -1 0" + lc);
//        ex.add("0 0 0 0 -1 1");

//        ex.add("5 5" + lc);
//        ex.add("-1 1 0 0 0" + lc);
//        ex.add("0 -1 -1 -1 0" + lc);
//        ex.add("0 -1 -1 -1 0" + lc);
//        ex.add("0 -1 -1 -1 0" + lc);
//        ex.add("0 0 0 0 0");

        ex.add("2 2" + lc);
        ex.add("1 -1" + lc);
        ex.add("-1 1");


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] size = br.readLine().split(" ");
        int height = Integer.parseInt(size[1]);
        int width = Integer.parseInt(size[0]);

        // 익은 토마토 1, 익지 않은 토마토 0, 토마토 없음 -1

        int[][] box = new int[height][width];
        boolean[][] ripe = new boolean[height][width];
        List<Tomato> tomatoes = new ArrayList<>();
        int empty = 0;

        // 데이터 만드는 작업
        String s;
        int h = 0;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");

            for (int i = 0; i < data.length; i++) {
                box[h][i] = Integer.parseInt(data[i]);

                // 익은 토마토
                if (box[h][i] == 1) {
                    //ripe[h][i] = true;
                    tomatoes.add(new Tomato(h, i, 0));
                }
                // 비어있는 공간 확인
                if (box[h][i] == -1) empty++;
            }
            h++;
        }

        // 1. 모든 토마토가 익어있는 상황 1만 있는 상황
        // 2. 모든 토마토가 익지 못하는 상황 1이 없는 상황
        // 3. riped.size 는 0 < riped.size < (h * w) - empty 사이여야한다.

        if (tomatoes.size() == height * width - empty) { // 처음부터 모든 토마토가 익어있는 상황
            System.out.println("0");
        } else if (tomatoes.isEmpty()) { // 처음부터 익은 토마토가 하나도 없는 상황
            System.out.println("-1");
        } else {
            // bfs 진행
            int answer = bfs(box, ripe, tomatoes, height * width - empty);
            System.out.println("answer = " + answer);
        }
    }

    private static int bfs(int[][] box, boolean[][] ripe, List<Tomato> tomatoes, int maxRipeCnt) {
        int days = 0;
        final int H_MIN = 0;
        final int H_MAX = box.length;
        final int W_MIN = 0;
        final int W_MAX = box[0].length;
        int ripeCnt = 0;

        Deque<Tomato> deque = new ArrayDeque<>();
        tomatoes.stream().forEach(deque::add);

        while (!deque.isEmpty()) {
            Tomato t = deque.pollFirst();

            // 토마토가 익지 않았을 경우만 진행
            if (!ripe[t.getHeight()][t.getWidth()]) {
                // 해당 칸에 토마토가 없으면 진행 X
                if (box[t.getHeight()][t.getWidth()] != -1) {
                    //System.out.println("Height = " + t.getHeight() + ", Width = " + t.getWidth() + ", Days = " + t.getDays());
                    ripe[t.getHeight()][t.getWidth()] = true;
                    ripeCnt++;

                    if (t.getDays() > days) days = t.getDays();

                    // 상하좌우
                    if (H_MIN <= t.getHeight()-1) {
                        deque.add(new Tomato(t.getHeight()-1, t.getWidth(), t.getDays() + 1));
                    }

                    if (t.getHeight()+1 < H_MAX) {
                        deque.add(new Tomato(t.getHeight()+1, t.getWidth(), t.getDays() + 1));
                    }

                    if (W_MIN <= t.getWidth()-1) {
                        deque.add(new Tomato(t.getHeight(), t.getWidth()-1, t.getDays() + 1));
                    }

                    if (t.getWidth()+1 < W_MAX) {
                        deque.add(new Tomato(t.getHeight(), t.getWidth()+1, t.getDays() + 1));
                    }
                }
            }
        }

//        System.out.println("ripeCnt = " + ripeCnt);
//        System.out.println("maxRipeCnt = " + maxRipeCnt);

        return ripeCnt == maxRipeCnt ? days : -1;
    }

    private static class Tomato {
        private int height;
        private int width;
        private int days;

        public Tomato(int height, int width, int days) {
            this.height = height;
            this.width = width;
            this.days = days;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int getDays() {
            return days;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Tomato tomato = (Tomato) o;
            return height == tomato.height && width == tomato.width;
        }

        @Override
        public int hashCode() {
            int result = height;
            result = 31 * result + width;
            return result;
        }
    }
}
