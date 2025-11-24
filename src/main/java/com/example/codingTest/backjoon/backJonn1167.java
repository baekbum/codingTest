package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1167 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5" + lc);
        ex.add("1 3 2 -1" + lc);
        ex.add("2 4 4 -1" + lc);
        ex.add("3 1 2 4 3 -1" + lc);
        ex.add("4 2 4 3 3 5 6 -1" + lc);
        ex.add("5 4 6 -1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int nodeCnt = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
        for (int i = 1; i <= nodeCnt; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int c = 0; c < nodeCnt; c++) {
            String[] strInfo = br.readLine().split(" ");
            int from = Integer.parseInt(strInfo[0]);

            int i = 1;
            while (Integer.parseInt(strInfo[i]) != -1) {
                int to = Integer.parseInt(strInfo[i]);
                int distance = Integer.parseInt(strInfo[i+1]);

                map.get(from).add(new Node(to, distance));
                i += 2;
            }
        }

        int[] cost = new int[nodeCnt +1];
        Integer next = bfs(1, nodeCnt, map, cost);

        cost = new int[nodeCnt +1];
        Integer result = bfs(next, nodeCnt, map, cost);

        System.out.println(cost[result]);
    }

    private static Integer bfs(int startNode, int nodeCnt, HashMap<Integer, ArrayList<Node>> map, int[] cost) {
        int maxDistance = 0;
        int maxNodeNum = 0;

        boolean[] visited = new boolean[nodeCnt +1];
        visited[startNode] = true;

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(startNode, 0));

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int current = node.getTo();

            for (Node next : map.get(current)) {
                if (!visited[next.getTo()]) {
                    if (cost[next.getTo()] < cost[current] + next.getDistance()) {
                        cost[next.getTo()] = cost[current] + next.getDistance();
                        deque.add(new Node(next.getTo(), cost[next.getTo()]));
                        visited[next.getTo()] = true;
                    }
                }
            }
        }

        for (int i = 1; i < cost.length; i++) {
            if (maxDistance < cost[i]) {
                maxDistance = cost[i];
                maxNodeNum = i;
            }
        }

        return maxNodeNum;
    }

    private static class Node {
        int to;
        int distance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        public int getTo() {
            return to;
        }

        public int getDistance() {
            return distance;
        }
    }
}