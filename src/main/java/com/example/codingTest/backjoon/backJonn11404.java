package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn11404 {
    private static int MAX_HEIGHT;
    private static int MAX_WIDTH;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5" + lc);
        ex.add("14" + lc);
        ex.add("1 2 2" + lc);
        ex.add("1 3 3" + lc);
        ex.add("1 4 1" + lc);
        ex.add("1 5 10" + lc);
        ex.add("2 4 2" + lc);
        ex.add("3 4 1" + lc);
        ex.add("3 5 1" + lc);
        ex.add("4 5 3" + lc);
        ex.add("3 5 10" + lc);
        ex.add("3 1 8" + lc);
        ex.add("1 4 2" + lc);
        ex.add("5 1 7" + lc);
        ex.add("3 4 2" + lc);
        ex.add("5 2 4");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int cityCnt = Integer.parseInt(br.readLine());
        int busCnt = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Node>> map = initData(cityCnt, busCnt, br);
        int[][] answer = initAnswer(cityCnt);

        for (int i = 1; i <= cityCnt ; i++) {
            int from = i;

            Deque<Node> deque = new ArrayDeque<>();
            deque.add(new Node(from, 0, 0));
            answer[from][from] = 0;

            while (!deque.isEmpty()) {
                Node node = deque.pollFirst();

                for (Node next : map.get(node.getTo())) {
                    int to = next.getTo();
                    int cost = next.getCost();
                    int sum = node.getSum();;

                    if (answer[from][to] > sum + cost) {
                        deque.add(new Node(to, cost, sum + cost));
                        answer[from][to] = sum + cost;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < cityCnt+1; i++) {
            for (int j = 1; j < cityCnt+1; j++) {
                sb.append(answer[i][j] != Integer.MAX_VALUE ? answer[i][j] : 0);
                if (j < cityCnt) sb.append(" ");
            }
            if (i < cityCnt) sb.append("\n");
        }

        System.out.println(sb);
    }

    private static HashMap<Integer, ArrayList<Node>> initData(int cityCnt, int busCnt, BufferedReader br) throws IOException {
        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();

        for (int i = 0; i < cityCnt; i++) {
            map.put(i+1, new ArrayList<>());
        }

        for (int i = 0; i < busCnt; i++) {
            String[] data = br.readLine().split(" ");
            int from = Integer.parseInt(data[0]);
            int to = Integer.parseInt(data[1]);
            int cost = Integer.parseInt(data[2]);

            map.get(from).add(new Node(to, cost, 0));
        }

        return map;
    }

    private static int[][] initAnswer(int cityCnt) {
        int[][] answer = new int[cityCnt+1][cityCnt+1];

        for (int i = 0; i < cityCnt+1; i++) {
            for (int j = 0; j < cityCnt+1; j++) {
                answer[i][j] = Integer.MAX_VALUE;
            }
        }

        return answer;
    }

    private static class Node {
        int to;
        int cost;
        int sum;

        public Node(int to, int cost, int sum) {
            this.to = to;
            this.cost = cost;
            this.sum = sum;
        }

        public int getTo() {
            return to;
        }

        public int getCost() {
            return cost;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", cost=" + cost +
                    ", sum=" + sum +
                    '}';
        }
    }
}