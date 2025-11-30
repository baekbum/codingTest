package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1922 {
    private static  int[] PARENTS;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("6" + lc);
        ex.add("9" + lc);
        ex.add("1 2 5" + lc);
        ex.add("1 3 4" + lc);
        ex.add("2 3 2" + lc);
        ex.add("2 4 7" + lc);
        ex.add("3 4 6" + lc);
        ex.add("3 5 11" + lc);
        ex.add("4 5 3" + lc);
        ex.add("4 6 8" + lc);
        ex.add("5 6 8");

//        ex.add("2" + lc);
//        ex.add("2" + lc);
//        ex.add("2 2 1" + lc);
//        ex.add("1 2 1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int computerCnt = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            queue.add(new Node(from, to, cost));
        }

        PARENTS = new int[computerCnt + 1];
        for (int i = 1; i <= computerCnt; i++) {
            PARENTS[i] = i; // 모든 노드를 자기 자신으로 초기화
        }

        long totalCost = 0; // MST의 총 비용 합계
        int edgeCount = 0; // 선택된 간선의 수 (N-1개가 목표)

        // 크루스칼 알고리즘 실행
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 두 노드의 루트(대표)를 찾습니다.
            int rootA = find(node.from);
            int rootB = find(node.to);

            // 루트가 다를 경우 (사이클이 형성되지 않을 경우) Union 수행
            if (rootA != rootB) {
                union(rootA, rootB);
                totalCost += node.cost; // 비용 추가
                edgeCount++;

                // N-1개의 간선을 모두 선택하면 종료 (최적화)
                if (edgeCount == computerCnt - 1) {
                    break;
                }
            }
        }

        System.out.println(totalCost);
    }


    private static int find(int target) {
        if (PARENTS[target] == target) {
            return target;
        }

        return PARENTS[target] = find(PARENTS[target]);
    }


    private static void union(int rootA, int rootB) {
        PARENTS[rootB] = rootA;
    }

    private static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            // 비용이 낮은 노드를 우선순위 높게 (오름차순) 정렬
            return Integer.compare(this.cost, other.cost);
        }
    }
}