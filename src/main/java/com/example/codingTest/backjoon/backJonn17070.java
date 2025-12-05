package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
//import java.io.InputStreamReader;

public class backJonn17070 {
    private static int[][] MAP;

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("3" + lc);
        ex.add("0 0 0" + lc);
        ex.add("0 0 0" + lc);
        ex.add("0 0 0");

//        ex.add("4" + lc);
//        ex.add("0 0 0 0" + lc);
//        ex.add("0 0 0 0" + lc);
//        ex.add("0 0 0 0" + lc);
//        ex.add("0 0 0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int size = Integer.parseInt(br.readLine());

        MAP = new int[size][size];

        for (int i = 0; i < size; i++) {
            String[] s = br.readLine().split(" ");

            for (int j = 0; j < size; j++) {
                MAP[i][j] = Integer.parseInt(s[j]);
            }
        }

        long[][][] DP = new long[size][size][3]; // 0: 가로, 1: 세로, 2: 대각선

        // 초기값 설정: (0, 1)에 가로 파이프 1개
        DP[0][1][0] = 1;

        for (int h = 0; h < size; h++) {
            for (int w = 2; w < size; w++) {
                // 현재 칸이 벽이면 모든 방향으로 이동 불가능
                if (MAP[h][w] == 1) continue;

                // 가로
                DP[h][w][0] = DP[h][w-1][0] + DP[h][w-1][2];

                // 세로
                if (h > 0) {
                    DP[h][w][1] = DP[h-1][w][1] + DP[h-1][w][2];
                }

                // 대각선
                if (h > 0 && MAP[h-1][w] == 0 && MAP[h][w-1] == 0) {
                    DP[h][w][2] = DP[h-1][w-1][0] + DP[h-1][w-1][1] + DP[h-1][w-1][2];
                }
            }
        }

        long answer = DP[size-1][size-1][0] + DP[size-1][size-1][1] + DP[size-1][size-1][2];
        System.out.println(answer);
    }
}