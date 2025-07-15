package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1967 {
    public static HashMap<Integer, ArrayList<Node>> MAP = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("12" + lc);
//        ex.add("1 2 3" + lc);
//        ex.add("1 3 2" + lc);
//        ex.add("2 4 5" + lc);
//        ex.add("3 5 11" + lc);
//        ex.add("3 6 9" + lc);
//        ex.add("4 7 1" + lc);
//        ex.add("4 8 7" + lc);
//        ex.add("5 9 15" + lc);
//        ex.add("5 10 4" + lc);
//        ex.add("6 11 6" + lc);
//        ex.add("6 12 10");

        ex.add("6" + lc);
        ex.add("1 2 23" + lc);
        ex.add("2 3 19" + lc);
        ex.add("2 5 21" + lc);
        ex.add("3 4 21" + lc);
        ex.add("5 6 8");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int maxNumber = Integer.parseInt(br.readLine());

        MAP = new HashMap<>();

        for (int i = 0; i < maxNumber; i++) {
            MAP.put(i + 1, new ArrayList<>());
        }

        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");
            int from = Integer.parseInt(data[0]);
            int to = Integer.parseInt(data[1]);
            int cost = Integer.parseInt(data[2]);
            Node fromNode = new Node(from, to, cost);
            Node toNode = new Node(to, from, cost);

            MAP.get(from).add(fromNode);
            MAP.get(to).add(toNode);
        }

        System.out.println(bfs(maxNumber));
    }

    private static int bfs(int maxNumber) {
        if (maxNumber == 1) return 0;

        int startPoint = findEndPointOfRoot();
        int maxCost = 0;

        Deque<Node> deque = new ArrayDeque<>(MAP.get(startPoint));
        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int prior = node.getFrom();
            int current = node.getTo();
            int sumCost = node.getCost();

            if (maxCost < sumCost) maxCost = sumCost;

            for (Node next : MAP.get(current)) {
                if (prior != next.getTo()) {
                    deque.add(new Node(next.getFrom(), next.getTo(), sumCost + next.getCost()));
                }
            }
        }

        return maxCost;
    }

    private static int findEndPointOfRoot() {
        Deque<Node> deque = new ArrayDeque<>(MAP.get(1));

        int maxCost = 0;
        int endPoint = 0;

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int prior = node.getFrom();
            int current = node.getTo();
            int sumCost = node.getCost();

            if (maxCost < sumCost) {
                maxCost = sumCost;
                endPoint = current;
            }

            for (Node next : MAP.get(current)) {
                if (prior != next.getTo()) {
                    deque.add(new Node(next.getFrom(), next.getTo(), sumCost + next.getCost()));
                }
            }
        }

        return endPoint;
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