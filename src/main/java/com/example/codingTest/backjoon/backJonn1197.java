package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1197 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("3 3" + lc);
//        ex.add("1 2 1" + lc);
//        ex.add("2 3 2" + lc);
//        ex.add("1 3 3");

        ex.add("4 3" + lc);
        ex.add("1 2 12" + lc);
        ex.add("2 3 -16" + lc);
        ex.add("1 4 8");


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] info = br.readLine().split(" ");
        int cnt = Integer.parseInt(info[0]);
        int edge = Integer.parseInt(info[1]);

        int[] parent = new int[cnt+1];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        List<Node> list = new ArrayList<>();

        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");
            int current = Integer.parseInt(data[0]);
            int next = Integer.parseInt(data[1]);
            int weight = Integer.parseInt(data[2]);

            list.add(new Node(current, next, weight));
        }

        list.sort((n1, n2) -> {
            return Integer.compare(n1.getWeight(), n2.getWeight());
        });

        int mstWeight = 0; // 최소 신장 트리의 총 가중치
        List<Node> mstNodes = new ArrayList<>(); // 최소 신장 트리에 포함된 간선들

        for (Node node : list) {
            int current = node.getCurrent();;
            int next = node.getNext();
            int weight = node.getWeight();

            if (find(current, parent) != find(next, parent)) {
                union(current, next, parent); // 두 정점을 연결 (하나의 집합으로 합침)
                mstWeight += weight;   // 가중치 추가
                mstNodes.add(node);         // MST 간선 리스트에 추가
            }
        }

        System.out.println( mstWeight);
    }

    // Union-Find - find 연산
    public static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent); // 경로 압축
    }

    // Union-Find - union 연산
    public static void union(int x, int y, int[] parent) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX != rootY) {
            parent[rootY] = rootX; // 한 쪽을 다른 쪽의 부모로 만듦 (간단한 구현)
        }
    }

    private static class Node {
        int current;
        int next;
        int weight;

        public Node(int current, int next, int weight) {
            this.current = current;
            this.next = next;
            this.weight = weight;
        }

        public int getCurrent() {
            return current;
        }

        public int getNext() {
            return next;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "current=" + current +
                    ", next=" + next +
                    ", weight=" + weight +
                    '}';
        }
    }
}