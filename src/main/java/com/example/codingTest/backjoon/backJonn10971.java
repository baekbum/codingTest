package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn10971 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
        ex.add("4" + lc);
        ex.add("0 10 15 20" + lc);
        ex.add("5 0 9 10" + lc);
        ex.add("6 13 0 12" + lc);
        ex.add("8 8 9 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());

        int[][] map = new int[size][size];

        String s;
        int i = 0;
        while ((s = br.readLine()) != null) {
            String[] cost = s.split(" ");

            for (int j=0; j < size; j++) {
                map[i][j] = Integer.parseInt(cost[j]);
            }
            i++;
        }

        HashSet<Integer> answer = new HashSet<>();
        dfs(map, new HashSet<>(), 0, 0, answer);
        System.out.println(answer.stream().mapToInt(c -> c).min().getAsInt());
        //answer.stream().forEach(System.out::println);


//        StringBuilder sb = new StringBuilder();
//        for (int j = 0; j < size; j++) {
//            for (int k = 0; k < size; k++) {
//                sb.append(map[j][k]).append(" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
    }

    private static void dfs(int[][] costMap, HashSet<Integer> visit, int current, int cost, HashSet<Integer> sum) {
        if (visit.size() == costMap.length-1 && costMap[current][0] != 0) {
            sum.add(cost + costMap[current][0]);
        }

        for (int i = 0; i < costMap.length; i++) {
            // 이미 방문했거나, 이동할 수 없는 케이는 제외
            if (!visit.contains(i) && costMap[current][i] != 0) {
                visit.add(current);

                dfs(costMap, visit, i, cost + costMap[current][i], sum);
                visit.remove(current);
            }
        }
    }
}
