package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class backJonn1916 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("5" + lc);
//        ex.add("8" + lc);
//        ex.add("1 2 2" + lc);
//        ex.add("1 3 3" + lc);
//        ex.add("1 4 1" + lc);
//        ex.add("1 5 10" + lc);
//        ex.add("2 4 2" + lc);
//        ex.add("3 4 1" + lc);
//        ex.add("3 5 1" + lc);
//        ex.add("4 5 3" + lc);
//        ex.add("1 5");

        ex.add("9" + lc);
        ex.add("9" + lc);
        ex.add("7 4 55" + lc);
        ex.add("4 2 84" + lc);
        ex.add("2 5 6" + lc);
        ex.add("5 8 90" + lc);
        ex.add("8 3 81" + lc);
        ex.add("3 9 56" + lc);
        ex.add("2 1 93" + lc);
        ex.add("1 3 20" + lc);
        ex.add("2 7 74" + lc);
        ex.add("7 9");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int cityCnt = Integer.parseInt(br.readLine());
        int busyCnt = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Info>> map = new HashMap<>();
        for (int i = 0; i < cityCnt; i++) {
            map.put(i+1, new ArrayList<>());
        }

        for (int i = 0; i < busyCnt; i++) {
            String[] data = br.readLine().split(" ");
            int start = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);
            int cost = Integer.parseInt(data[2]);

            map.get(start).add(new Info(end, cost));
        }

        int[] cost = new int[cityCnt+1];
        boolean[] visited = new boolean[cityCnt+1];

        for (int i = 1; i < cost.length; i++) {
            cost[i] = Integer.MAX_VALUE;
        }

        String[] init = br.readLine().split(" ");
        int initStart = Integer.parseInt(init[0]);
        int initDestination = Integer.parseInt(init[1]);

        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.getCost(), o2.getCost());
        });

        queue.add(new Info(initStart, 0));
        cost[initStart] = 0;

        while (!queue.isEmpty()) {
            Info current = queue.poll();

            if (visited[current.getValue()]) continue;
            visited[current.getValue()] = true;

            for (Info next : map.get(current.getValue())) {
                int sumCost = cost[current.getValue()]; // 현재까지 누적된 코스트
                int requiredCost = next.getCost(); // 다음 도시로 가는데 필요한 코스트
                int expectedCost = sumCost + requiredCost; // 다음 도시로 간다고 가정하면 누적되는 코스트
                int nextCityCost = cost[next.getValue()]; // 현재 가려고 하는 도시의 누적된 코스트

                if (expectedCost < nextCityCost) { // 최소비용으로 갱신 가능하면 이동한다.
                    queue.add(new Info(next.getValue(), expectedCost));
                    cost[next.getValue()] = expectedCost; // 다음 도시에 현재 누적 비용과 다음 도시로 이동할 때 필요한 비용을 추가해서 넣는다.
                }
            }
        }

        System.out.println(cost[initDestination]);
    }

    private static class Info {
        int value;
        int cost;

        public Info(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }

        public int getValue() {
            return value;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "value=" + value +
                    ", cost=" + cost +
                    '}';
        }
    }
}