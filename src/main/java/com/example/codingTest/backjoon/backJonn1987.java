package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1987 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("2 4" + lc);
//        ex.add("CAAB" + lc);
//        ex.add("ADCB");

//        ex.add("3 6" + lc);
//        ex.add("HFDFFB" + lc);
//        ex.add("AJHGDH" + lc);
//        ex.add("DGAGEH");

        ex.add("5 5" + lc);
        ex.add("IEFCJ" + lc);
        ex.add("FHFKC" + lc);
        ex.add("FFALF" + lc);
        ex.add("HFGCF" + lc);
        ex.add("HMCHH");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] sizeInfo = br.readLine().split(" ");
        int height = Integer.parseInt(sizeInfo[0]);
        int width = Integer.parseInt(sizeInfo[1]);

        String[][] map = new String[height][width];

        for (int h = 0; h < height; h++) {
            String s = br.readLine();

            for (int w = 0; w < width; w++) {
                map[h][w] = String.valueOf(s.charAt(w));
            }
        }

        TreeSet<Integer> answer = new TreeSet<>();
        dfs(map, new HashSet<>(), 0, 0, 1, answer);

        OptionalInt max = answer.stream()
                .mapToInt(value -> value)
                .max();

        System.out.println(max.orElse(1));
    }

    private static void dfs(String[][] map, HashSet<String> visit, int h, int w, int cnt, TreeSet<Integer> answer) {
        String current = map[h][w];

        if (visit.contains(current)) {
            //System.out.println("alphabet : " + current + ", (" + h + "," +  w + "), cnt : " + cnt);
            answer.add(cnt-1);
            return;
        }

        //System.out.println("alphabet : " + current + ", (" + h + "," +  w + "), cnt : " + cnt);

        visit.add(current);

        //상하좌우
        if (-1 < h-1) dfs(map, visit, h-1, w, cnt+1, answer);
        if (h+1 < map.length) dfs(map, visit, h+1, w, cnt+1, answer);
        if (-1 < w-1) dfs(map, visit, h, w-1, cnt+1, answer);
        if (w+1 < map[0].length) dfs(map, visit, h, w+1, cnt+1, answer);

        visit.remove(current);
    }
}
