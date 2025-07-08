package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class backJonn1520 {
    private static int HEIGHT;
    private static int WIDTH;
    private static int[][] MAP;
    private static int[][] DP;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("4 5" + lc);
        ex.add("50 45 37 32 30" + lc);
        ex.add("35 50 40 20 25" + lc);
        ex.add("30 30 25 17 28" + lc);
        ex.add("27 24 22 15 10");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        HEIGHT = Integer.parseInt(size[0]);
        WIDTH = Integer.parseInt(size[1]);

        MAP = new int[HEIGHT][WIDTH];
        DP = new int[HEIGHT][WIDTH];



        for (int i = 0; i < HEIGHT; i++) {
            String[] data = br.readLine().split(" ");
            for (int j = 0; j < WIDTH; j++) {
                MAP[i][j] = Integer.parseInt(data[j]);
                DP[i][j] = 9;
            }
        }

        System.out.println(dfs(0, 0));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                sb.append(DP[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int dfs(int h, int w) {
        if (h == HEIGHT - 1 && w == WIDTH - 1) {
            return 1;
        }

        if (DP[h][w] != 9) {
            return DP[h][w];
        }

        DP[h][w] = 0;

        // 동서남북 순으로 이동
        if (moveValidation(h, w + 1) && MAP[h][w + 1] < MAP[h][w]) {
            DP[h][w] += dfs(h, w + 1);
        }

        if (moveValidation(h, w - 1) && MAP[h][w - 1] < MAP[h][w]) {
            DP[h][w] += dfs(h, w - 1);
        }

        if (moveValidation(h + 1, w) && MAP[h + 1][w] < MAP[h][w]) {
            DP[h][w] += dfs(h + 1, w);
        }

        if (moveValidation(h - 1, w) && MAP[h - 1][w] < MAP[h][w]) {
            DP[h][w] += dfs(h - 1, w);
        }

        return DP[h][w];
    }

    private static boolean moveValidation(int h, int w) {
        return -1 < h && h < HEIGHT && -1 < w && w < WIDTH;
    }
}