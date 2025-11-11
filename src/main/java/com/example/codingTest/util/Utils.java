package com.example.codingTest.util;

import java.util.function.BiFunction;

public class Utils {

    public static void printMap(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < map.length; h++) {
            for (int w = 0; w < map[0].length; w++) {
                sb.append(map[h][w]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void printMap(int[][][] map) {
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < map[0][0].length; f++) {
            for (int h = 0; h < map.length; h++) {
                for (int w = 0; w < map[0].length; w++) {
                    sb.append(map[h][w][f]);
                    sb.append(" ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void printMap(String[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < map.length; h++) {
            for (int w = 0; w < map[0].length; w++) {
                sb.append(map[h][w]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static BiFunction<Integer, Integer, Boolean> validation(int maxH, int maxW) {
        return (h, w) -> {
            return h < maxH
                    && -1 < h
                    && w < maxW
                    && -1 < w;
        };
    }

    public static TriFunction<Integer, Integer, Integer, Boolean> validation(int maxH, int maxW, int maxF) {
        return (h, w, f) -> {
            return h < maxH
                    && -1 < h
                    && w < maxW
                    && -1 < w
                    && f < maxF
                    && -1 < f;
        };
    }
}
