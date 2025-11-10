package com.example.codingTest.backjoon;

import com.example.codingTest.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class backJonn14502 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    private static final ArrayList<Pos> VIRUS_LIST = new ArrayList<>();
    private static int MAX_SAFETY_AREA_CNT = 0;

    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        // 2 바이러스
        // 1 벽
        // 0 이동가능

//        ex.add("7 7" + lc);
//        ex.add("2 0 0 0 1 1 0" + lc);
//        ex.add("0 0 1 0 1 2 0" + lc);
//        ex.add("0 1 1 0 1 0 0" + lc);
//        ex.add("0 1 0 0 0 0 0" + lc);
//        ex.add("0 0 0 0 0 1 1" + lc);
//        ex.add("0 1 0 0 0 0 0" + lc);
//        ex.add("0 1 0 0 0 0 0");

        ex.add("3 3" + lc);
        ex.add("0 0 1" + lc);
        ex.add("2 0 2" + lc);
        ex.add("0 2 0" + lc);


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] info = br.readLine().split(" ");
        MAX_HEIGHT = Integer.parseInt(info[0]);
        MAX_WIDTH = Integer.parseInt(info[1]);

        int[][] map = new int[MAX_HEIGHT][MAX_WIDTH];

        for (int i = 0; i < MAX_HEIGHT; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < MAX_WIDTH; j++) {
                int data = Integer.parseInt(s[j]);
                map[i][j] = data;
                if (data == 2) VIRUS_LIST.add(new Pos(i, j));
            }
        }

        boolean[][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH];
        //Utils.printMap(map);

        makeWall(map, 0);

        System.out.println(MAX_SAFETY_AREA_CNT);
    }

    static void makeWall(int[][] map, int wallCnt) {

        if (wallCnt == 3) { // 벽이 다 세워졌을 경우
            int maxCnt = process(map);
            MAX_SAFETY_AREA_CNT = Math.max(MAX_SAFETY_AREA_CNT, maxCnt);
            return;
        }

        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 5; // 벽 세우기
                    //Utils.printMap(map);
                    makeWall(map, wallCnt+1);
                    map[i][j] = 0; // 벽 허물기
                }
            }
        }
    }

    static int process(int[][] map) {
        // 1. 바이러스를 퍼트린다.
        Deque<Pos> deque = new ArrayDeque<>(VIRUS_LIST);

        while (!deque.isEmpty()) {
            Pos virus = deque.pollFirst();
            int height = virus.getHeight();
            int width = virus.getWidth();

            // 동서남북
            if (validation(height, width+1) && map[height][width+1] == 0) {
                map[height][width+1] = 9;
                deque.add(new Pos(height, width+1));
            }

            if (validation(height, width-1) && map[height][width-1] == 0) {
                map[height][width-1] = 9;
                deque.add(new Pos(height, width-1));
            }

            if (validation(height+1, width) && map[height+1][width] == 0) {
                map[height+1][width] = 9;
                deque.add(new Pos(height+1, width));
            }

            if (validation(height-1, width) && map[height-1][width] == 0) {
                map[height-1][width] = 9;
                deque.add(new Pos(height-1, width));
            }
        }

        // 2. 안전지역 카운트를 한다.
        int maxSafetyAreaCnt = 0;

        for (int h = 0; h < MAX_HEIGHT; h++) {
            for (int w = 0; w < MAX_WIDTH; w++) {
                if (map[h][w] == 0) {
                    maxSafetyAreaCnt++;
                }

                if (map[h][w] == 9) { // 퍼진 바이러스 다시 롤백
                    map[h][w] = 0;
                }
            }
        }

        return maxSafetyAreaCnt;
    }

    static boolean validation(int h, int w) {
        return w < MAX_WIDTH
                && -1 < w
                && h < MAX_HEIGHT
                && -1 < h;
    }

    static class Pos {
        int height;
        int width;

        public Pos(int height, int width) {
            this.height = height;
            this.width = width;
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
    }
}