package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class backJonn2468 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
//        ex.add("5" + lc);
//        ex.add("6 8 2 6 2" + lc);
//        ex.add("3 2 3 4 6" + lc);
//        ex.add("6 7 3 3 2" + lc);
//        ex.add("7 2 5 3 6" + lc);
//        ex.add("8 9 5 2 7");

//        ex.add("7" + lc);
//        ex.add("9 9 9 9 9 9 9" + lc);
//        ex.add("9 2 1 2 1 2 9" + lc);
//        ex.add("9 1 8 7 8 1 9" + lc);
//        ex.add("9 2 7 9 7 2 9" + lc);
//        ex.add("9 1 8 7 8 1 9" + lc);
//        ex.add("9 2 1 2 1 2 9" + lc);
//        ex.add("9 9 9 9 9 9 9");

        ex.add("2" + lc);
        ex.add("1 1" + lc);
        ex.add("1 1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());

        // 초기 데이터 생성
        HashMap<String, Object> result = init(br, size);
        int[][] data = (int[][]) result.get("data");
        TreeSet<Integer> heightSet = (TreeSet<Integer>) result.get("heightSet");
        int maxCnt = 0;

        //heightSet.stream().forEach(System.out::println);

        // 1. 침수되지 않은 구역 리스트 만들기
        // i는 침수되는 높이
        for (Integer i : heightSet) {
            System.out.println("i = " + i);
            boolean[][] pass = new boolean[size][size];
            int cnt = 0;

            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    // 1. 아직 방문하지 않았고
                    // 2. 침수 높이보다 높은 곳만
                    if (!pass[j][k] && data[j][k] > i) {
                        dfs(data, pass, j, k, i);
                        cnt++;
                    }
                }
            }

            System.out.println("sink cnt = " + cnt);
            System.out.println("=================");

            maxCnt = Math.max(maxCnt, cnt);
        }

        System.out.println(maxCnt == 0 ? "1" : maxCnt);
    }

    private static void dfs(int[][] data, boolean[][] pass, int h, int w, int sinkHeight) {
        System.out.println("[" + h + "]" + "[" + w + "]");
        pass[h][w] = true;

        // 상하좌우 확인
        if (validationCheck("TOP", h-1, w, data, pass, sinkHeight))
            dfs(data, pass, h-1, w, sinkHeight);

        if (validationCheck("BOTTOM", h+1, w, data, pass, sinkHeight))
            dfs(data, pass, h+1, w, sinkHeight);

        if (validationCheck("LEFT", h, w-1, data, pass, sinkHeight))
            dfs(data, pass, h, w-1, sinkHeight);

        if (validationCheck("RIGHT", h, w+1, data, pass, sinkHeight))
            dfs(data, pass, h, w+1, sinkHeight);
    }

    private static boolean validationCheck(String command, int h, int w, int[][] data, boolean[][] pass, int sinkHeight) {
        switch (command) {
            case "TOP":
                return (h > -1) && (!pass[h][w]) && (data[h][w] > sinkHeight);
            case "BOTTOM":
                return (h < data.length) && (!pass[h][w]) && (data[h][w] > sinkHeight);
            case "LEFT":
                return (w > -1) && (!pass[h][w]) && (data[h][w] > sinkHeight);
            case "RIGHT":
                return (w < data[0].length) && (!pass[h][w]) && (data[h][w] > sinkHeight);
            default:
                return false;
        }
    }

    private static HashMap<String, Object> init(BufferedReader br, int size) throws IOException{
        HashMap<String, Object> dataMap = new HashMap<>();

        int[][] map = new int[size][size];
        int height = 0;
        TreeSet<Integer> heightSet = new TreeSet<>();

        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");

            for (int i = 0; i < data.length; i++) {
                int value = Integer.parseInt(data[i]);
                heightSet.add(value);

                map[height][i] = value;
            }

            height++;
        }

        dataMap.put("data", map);
        dataMap.put("heightSet", heightSet);

        return dataMap;
    }
}
