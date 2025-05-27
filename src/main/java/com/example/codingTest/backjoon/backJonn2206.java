package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class backJonn2206 {
    private static int MAX_HEIGHT = 0;
    private static int MAX_WIDTH = 0;

    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("6 4" + lc);
        ex.add("0100" + lc);
        ex.add("1110" + lc);
        ex.add("1000" + lc);
        ex.add("0000" + lc);
        ex.add("0111" + lc);
        ex.add("0000");

//        ex.add("4 4" + lc);
//        ex.add("0111" + lc);
//        ex.add("1111" + lc);
//        ex.add("1111" + lc);
//        ex.add("1110");

//        ex.add("10 4" + lc);
//        ex.add("0000" + lc);
//        ex.add("1110" + lc);
//        ex.add("1100" + lc);
//        ex.add("1001" + lc);
//        ex.add("1011" + lc);
//        ex.add("1000" + lc);
//        ex.add("0000" + lc);
//        ex.add("0110" + lc);
//        ex.add("0101" + lc);
//        ex.add("0100");


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        MAX_HEIGHT = Integer.parseInt(size[0]);
        MAX_WIDTH = Integer.parseInt(size[1]);

        int[][] map = new int[MAX_HEIGHT][MAX_WIDTH];

        for (int i = 0; i < MAX_HEIGHT; i++) {
            String[] data = br.readLine().split("");
            for (int j = 0; j < MAX_WIDTH; j++) {
                map[i][j] = Integer.parseInt(data[j]);
            }
        }

        System.out.println(bfs(map));
    }

    private static int bfs(int[][] map) {
        boolean[][][] visit = new boolean[MAX_HEIGHT][MAX_WIDTH][2];
        Deque<Pos> deque = new ArrayDeque<>();
        deque.add(new Pos(0,0,1, false));
        visit[0][0][0] = true;
        visit[0][0][1] = true;

        while (!deque.isEmpty()) {
            Pos pos = deque.pollFirst();
            int h = pos.getH();
            int w = pos.getW();
            int cnt = pos.getCnt();

            //System.out.println(pos);

            if (pos.getH() == map.length-1 && pos.getW() == map[0].length-1) {
                return pos.getCnt();
            }

            // 동서남북
            if (validation(h, w+1)) {
                addQueue(map, h, w+1, pos, visit, deque, cnt);
            }

            if (validation(h, w-1)) {
                addQueue(map, h, w-1, pos, visit, deque, cnt);
            }

            if (validation(h+1, w)) {
                addQueue(map, h+1, w, pos, visit, deque, cnt);
            }

            if (validation(h-1, w)) {
                addQueue(map, h-1, w, pos, visit, deque, cnt);
            }
        }

        return -1;
    }

    private static void addQueue(int[][] map, int h, int w, Pos pos, boolean[][][] visit, Deque<Pos> deque, int cnt) {
        if (!pos.isHasCrushed()) { // 뚫을 수 있는 상태
            if (map[h][w] == 0) { // 벽이 없음
                if (!visit[h][w][0]) { // 지나간 적 없음
                    visit[h][w][0] = true;
                    deque.add(new Pos(h, w, cnt + 1, false));
                }
            } else { // 벽이 있음
                if (!visit[h][w][1]) { // 지나간 적 없음
                    visit[h][w][1] = true;
                    deque.add(new Pos(h, w, cnt + 1, true));
                }
            }
        } else { // 뚫을 수 없는 상태
            if (map[h][w] == 0 && !visit[h][w][1]) { // 벽이 없고 지나간 적이 없는 경우
                visit[h][w][1] = true;
                deque.add(new Pos(h, w, cnt + 1, true));
            }
        }
    }


    private static boolean validation(int h, int w) {
        return (-1 < h && h < MAX_HEIGHT && -1 < w && w < MAX_WIDTH);
    }

    private static class Pos {
        int h;
        int w;
        int cnt;
        boolean hasCrushed;

        public Pos(int h, int w, int cnt, boolean hasCrushed) {
            this.h = h;
            this.w = w;
            this.cnt = cnt;
            this.hasCrushed = hasCrushed;
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

        public boolean isHasCrushed() {
            return hasCrushed;
        }

        public void setHasCrushed(boolean hasCrushed) {
            this.hasCrushed = hasCrushed;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", w=" + w +
                    ", cnt=" + cnt +
                    ", hasCrushed=" + hasCrushed +
                    '}';
        }
    }
}
