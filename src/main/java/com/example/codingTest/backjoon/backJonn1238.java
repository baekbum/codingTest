package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1238 {
    private static int DESTINATION;
    private static HashMap<Integer, ArrayList<Node>> MAP = new HashMap<>();
    private static int TOWN_CNT;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("4 8 2" + lc);
        ex.add("1 2 4" + lc);
        ex.add("1 3 2" + lc);
        ex.add("1 4 7" + lc);
        ex.add("2 1 1" + lc);
        ex.add("2 3 5" + lc);
        ex.add("3 1 2" + lc);
        ex.add("3 4 4" + lc);
        ex.add("4 2 3");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] info = br.readLine().split(" ");
        TOWN_CNT = Integer.parseInt(info[0]);
        int totalCnt = Integer.parseInt(info[1]);
        DESTINATION = Integer.parseInt(info[2]);

        // 맵 초기화
        for (int i = 1; i <= TOWN_CNT; i++) {
            MAP.put(i, new ArrayList<>());
        }

        for (int i = 0; i < totalCnt; i++) {
            String[] data = br.readLine().split(" ");
            MAP.get(Integer.parseInt(data[0])).add(new Node(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
        }

        HashMap<Integer, int[]> costMap = new HashMap<>();

        for (int i = 1; i <= TOWN_CNT; i++) {
            costMap.put(i, process(i));
        }

        int answer = 0;

        for (int home = 1; home <= TOWN_CNT; home++) {
            if (home == DESTINATION) continue;

            int[] go = costMap.get(home);
            int[] back = costMap.get(DESTINATION);

            int totalCost = go[DESTINATION] + back[home];

            if (answer < totalCost) answer = totalCost;
        }

        System.out.println(answer);
    }

    private static int[] process(int home) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            return Integer.compare(n1.getTime(), n2.getTime());
        });

        int[] cost = new int[TOWN_CNT+1];
        for (int i = 0; i < TOWN_CNT+1; i++) {
            if (home != i) {
                cost[i] = Integer.MAX_VALUE;
            }
        }

        queue.add(new Node(home, home, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int from = node.getFrom();
            int to = node.getTo();
            int time = node.getTime();

            for (Node n : MAP.get(to)) {
                int sumTime = n.getTime()+time;
                if (sumTime < cost[n.getTo()]) {
                    Node newNode = new Node(n.getFrom(), n.getTo(), sumTime);
                    queue.add(newNode);
                    cost[n.getTo()] = sumTime;
                }
            }
        }

        return cost;
    }

    private static class Node {
        int from;
        int to;
        int time;

        public Node(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getTime() {
            return time;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;
            return from == node.from && to == node.to;
        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", time=" + time +
                    '}';
        }
    }
}