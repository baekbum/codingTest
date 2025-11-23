package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1005 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("2" + lc);
//        ex.add("4 4" + lc);
//        ex.add("10 1 100 10" + lc);
//        ex.add("1 2" + lc);
//        ex.add("1 3" + lc);
//        ex.add("2 4" + lc);
//        ex.add("3 4" + lc);
//        ex.add("4" + lc);
//        ex.add("8 8" + lc);
//        ex.add("10 20 1 5 8 7 1 43" + lc);
//        ex.add("1 2" + lc);
//        ex.add("1 3" + lc);
//        ex.add("2 4" + lc);
//        ex.add("2 5" + lc);
//        ex.add("3 6" + lc);
//        ex.add("5 7" + lc);
//        ex.add("6 7" + lc);
//        ex.add("7 8" + lc);
//        ex.add("7");

        ex.add("1" + lc);
        ex.add("6 1" + lc);
        ex.add("25 74 55 10 52 87" + lc);
        ex.add("5 6" + lc);
        ex.add("4");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int testCaseCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCnt; i++) {
            String[] infos = br.readLine().split(" ");
            int towerCnt = Integer.parseInt(infos[0]); // 건물 개수
            int ruleCnt = Integer.parseInt(infos[1]); // 규칙 개수

            int[] buildTime = new int[towerCnt+1];
            String[] timeInfo = br.readLine().split(" ");

            for (int t = 1; t <= timeInfo.length; t++) {
                buildTime[t] = Integer.parseInt(timeInfo[t-1]);
            }

            HashMap<Integer, Node> nodeMap = new HashMap<>();

            for (int r = 0; r < ruleCnt; r++) {
                String[] data = br.readLine().split(" ");
                int from = Integer.parseInt(data[0]);
                int to = Integer.parseInt(data[1]);

                if (!nodeMap.containsKey(from)) nodeMap.put(from, new Node(from));
                if (!nodeMap.containsKey(to)) nodeMap.put(to, new Node(to));

                nodeMap.get(from).getPost().add(nodeMap.get(to));
                nodeMap.get(to).getPrevious().add(nodeMap.get(from));
            }

            int target = Integer.parseInt(br.readLine());

            Deque<Node> deque = findStartNode(nodeMap, buildTime);

            while (!deque.isEmpty()) {
                Node current = deque.pollFirst();

                // 다음 노드들을 다 순회하고 해당 노드의 값을 업데이트 한다?

                if (!current.getPost().isEmpty()) {
                    for (Node next : current.getPost()) {
                        if (next.getSum() < current.getSum() + buildTime[next.getKey()]) {
                            next.setSum(current.getSum() + buildTime[next.getKey()]);
                            deque.add(next);
                        }
                    }
                }
            }

            System.out.println(!nodeMap.containsKey(target) ? buildTime[target] : nodeMap.get(target).getSum());
        }
    }

    private static Deque<Node> findStartNode(HashMap<Integer, Node> nodeMap, int[] buildTime) {
        Deque<Node> deque = new ArrayDeque<>();
        TreeMap<Integer, ArrayList<Node>> degreeMap = new TreeMap<>();

        for (Integer k : nodeMap.keySet()) { // 진입 차수가 0인 노드 찾기
            Node node = nodeMap.get(k);

            if (node.getPrevious().isEmpty()) {
                node.setSum(buildTime[node.getKey()]);
                deque.add(node);
            }
        }

        return deque;
    }

    private static class Node {
        int key;
        ArrayList<Node> previous;
        ArrayList<Node> post;
        int degree;
        int sum;

        public Node(int key) {
            this.key = key;
            this.previous = new ArrayList<>();
            this.post = new ArrayList<>();
            this.degree = 0;
            this.sum = 0;
        }

        public int getKey() {
            return key;
        }

        public ArrayList<Node> getPrevious() {
            return previous;
        }

        public ArrayList<Node> getPost() {
            return post;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", sum=" + sum +
                    '}';
        }
    }
}