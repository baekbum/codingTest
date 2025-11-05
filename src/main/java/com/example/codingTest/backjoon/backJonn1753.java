package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class backJonn1753 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5 6" + lc);
        ex.add("1" + lc);
        ex.add("5 1 1" + lc);
        ex.add("1 2 2" + lc);
        ex.add("1 3 3" + lc);
        ex.add("2 3 4" + lc);
        ex.add("2 4 5" + lc);
        ex.add("3 4 6");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] countInfo = br.readLine().split(" ");

        int nodeCnt = Integer.parseInt(countInfo[0]);
        int edgeCnt = Integer.parseInt(countInfo[1]);
        int startNode = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();

        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");
            int key1 = Integer.parseInt(data[0]);
            int key2 = Integer.parseInt(data[1]);
            int weight = Integer.parseInt(data[2]);

            if (!map.containsKey(key1)) {
                map.put(key1, new ArrayList<>());
            }

            map.get(key1).add(new Node(key2, weight));
        }

        bfs(startNode, map, new int[nodeCnt+1]);
    }

    private static void bfs(int startNode, HashMap<Integer, ArrayList<Node>> map, int[] answer) {
        for (int i = 1; i < answer.length; i++) {
            answer[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.getWeight() - n2.getWeight());

        pq.add(new Node(startNode, 0));
        answer[startNode] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (map.containsKey(node.getValue())) {
                for (Node n : map.get(node.getValue())) {
                    int v = n.getValue();
                    int w = n.getWeight();
                    int sumWeight = node.getWeight() + w;

                    if (sumWeight < answer[v]) {
                        answer[v] = sumWeight;
                        pq.add(new Node(v, sumWeight));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            if (answer[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(answer[i]);
            }

            if (i+1 != answer.length) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }


    static class Node {
        private int value;
        private int weight;

        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}