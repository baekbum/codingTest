package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2178 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
//        ex.add("4 6" + lc);
//        ex.add("101111" + lc);
//        ex.add("101010" + lc);
//        ex.add("101011" + lc);
//        ex.add("111011");

//        ex.add("4 6" + lc);
//        ex.add("110110" + lc);
//        ex.add("110110" + lc);
//        ex.add("111111" + lc);
//        ex.add("111101");

//        ex.add("2 25" + lc);
//        ex.add("1011101110111011101110111" + lc);
//        ex.add("1110111011101110111011101");

        ex.add("7 7" + lc);
        ex.add("1011111" + lc);
        ex.add("1110001" + lc);
        ex.add("1000001" + lc);
        ex.add("1000001" + lc);
        ex.add("1000001" + lc);
        ex.add("1000001" + lc);
        ex.add("1111111");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] info = br.readLine().split(" ");
        int height = Integer.parseInt(info[0]);
        int width = Integer.parseInt(info[1]);

        int[][] miro = new int[height][width];
        Integer[][] check = new Integer[height][width];
        int row = 0;

        // 데이터 만드는 과정
        String s;
        while ((s = br.readLine()) != null) {
            for (int i = 0; i < width; i++) {
                miro[row][i] = s.charAt(i) - '0';
            }
            row++;
        }

        TreeSet<Integer> sumSet = new TreeSet<>();

        dfs(miro, check, 0, 0, 1, sumSet);

        System.out.println("check[height-1][width-1] = " + check[height - 1][width - 1]);
    }

    private static void dfs(int[][] miro, Integer[][] check, int height, int width, int sum, TreeSet<Integer> set) {
        // 목표 지점에 도달했다면 set에 이동 카운드를 저장하고 리턴;
        if (height == miro.length && width == miro[0].length) {
            set.add(sum);
            return;
        }

        // 맵 크기를 넘어가면 리턴
        if (height == -1 || width ==  -1) return;
        if (height == miro.length || width == miro[0].length) return;

        // 막힌 길이였으면 리턴
        if (miro[height][width] == 0) return;

        // 지나온 길이지만 더 빠른 경로로 왔다면 통과
        if (check[height][width] != null) {
            if (check[height][width] > sum) {
                check[height][width] = sum;
            } else {
                return;
            }
        } else {
            check[height][width] = sum;
        }

        System.out.println("[" + height + "]" + "[" + width + "] = " + check[height][width]);

        // 하좌상우로 이동
        dfs(miro, check, height+1, width, sum + 1, set);
        dfs(miro, check, height, width-1, sum + 1, set);
        dfs(miro, check, height-1, width, sum + 1, set);
        dfs(miro, check, height, width+1, sum + 1, set);
    }

}
