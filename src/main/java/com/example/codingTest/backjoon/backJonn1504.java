package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;

public class backJonn1504 {
    private static HashMap<Integer, ArrayList<Node>> MAP = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("4 6" + lc);
//        ex.add("1 2 3" + lc);
//        ex.add("2 3 3" + lc);
//        ex.add("3 4 1" + lc);
//        ex.add("1 3 5" + lc);
//        ex.add("2 4 5" + lc);
//        ex.add("1 4 4" + lc);
//        ex.add("2 3");

        ex.add("3 1" + lc);
        ex.add("2 3 7" + lc);
        ex.add("1 2");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] info = br.readLine().split(" ");
        int edgeCnt = Integer.parseInt(info[0]);
        int lineCnt = Integer.parseInt(info[1]);

        for (int i = 0; i < lineCnt; i++) {
            String[] data = br.readLine().split(" ");
            if (!MAP.containsKey(Integer.parseInt(data[0]))) MAP.put(Integer.parseInt(data[0]), new ArrayList<>());
            if (!MAP.containsKey(Integer.parseInt(data[1]))) MAP.put(Integer.parseInt(data[1]), new ArrayList<>());

            MAP.get(Integer.parseInt(data[0])).add(new Node(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            MAP.get(Integer.parseInt(data[1])).add(new Node(Integer.parseInt(data[1]), Integer.parseInt(data[0]), Integer.parseInt(data[2])));
        }

        if (lineCnt == 0) {
            System.out.println(-1);
        } else {
            String[] essentialKey = br.readLine().split(" ");
            int v1 = Integer.parseInt(essentialKey[0]);
            int v2 = Integer.parseInt(essentialKey[1]);

            // 1 -> v1 -> v2 -> destination
            // 1 -> v2 -> v1 -> destination
            // 중에 비용이 적은 쪽을 선택한다.

            boolean failFlag = false;

            int result1 = costProcess(1, v1, edgeCnt);
            int result2 = costProcess(v1, v2, edgeCnt);
            int result3 = costProcess(v2, edgeCnt, edgeCnt);

            int result4 = costProcess(1, v2, edgeCnt);
            int result5 = costProcess(v2, v1, edgeCnt);
            int result6 = costProcess(v1, edgeCnt, edgeCnt);

            if (result1 == Integer.MAX_VALUE || result2 == Integer.MAX_VALUE || result3 == Integer.MAX_VALUE || // 목적지에 도달할 수 없는 케이스 검증
                    result4 == Integer.MAX_VALUE || result5 == Integer.MAX_VALUE || result6 == Integer.MAX_VALUE)
                failFlag = true;


            int sum1 = result1 + result2 + result3;
            int sum2 = result4 + result5 + result6;
//            System.out.println("sum1 = " + sum1);
//            System.out.println("sum2 = " + sum2);

            int answer = Math.min(sum1, sum2);

            System.out.println(failFlag ? -1 : answer);
        }
    }

    private static int costProcess(int start, int end, int costMapSize) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.getCost(), o2.getCost());
        });

        int[] costMap = initCostMap(costMapSize, start);

        if (MAP.containsKey(start)) {
            queue.addAll(MAP.get(start));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int from = node.getFrom();
            int to = node.getTo();
            int cost = node.getCost();

            if (cost < costMap[to]) {
                costMap[to] = cost;

                for (Node next : MAP.get(to)) {
                    queue.add(new Node(next.getFrom(), next.getTo(),next.getCost() + cost));
                }
            }
        }

//        for (int i = 0; i < costMap.length; i++) {
//            System.out.println("costMap[" + i + "] = " + costMap[i]);
//        }

        return costMap[end];
    }

    private static int[] initCostMap(int size, int initPoint) {
        int[] costMap = new int[size+1];
        for (int i = 0; i < costMap.length; i++) {
            if (i == initPoint) {
                costMap[i] = 0;
            } else {
                costMap[i] = Integer.MAX_VALUE;
            }
        }
        return costMap;
    }

    private static class Node {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }
}