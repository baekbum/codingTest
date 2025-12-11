package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
//import java.io.InputStreamReader;

public class backJonn16928 {
    private static HashMap<Integer, Integer> LADDER_AND_SNEAK = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("3 7" + lc); // 사다리 뱀 수
//        ex.add("32 62" + lc);
//        ex.add("42 68" + lc);
//        ex.add("12 98" + lc); // 여기까진 사다리
//        ex.add("95 13" + lc);
//        ex.add("97 25" + lc);
//        ex.add("93 37" + lc);
//        ex.add("79 27" + lc);
//        ex.add("75 19" + lc);
//        ex.add("49 47" + lc);
//        ex.add("67 17"); // 여기까진 뱀

        ex.add("1 1" + lc);
        ex.add("50 60" + lc);
        ex.add("90 30");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] info = br.readLine().split(" ");

        int ladderCnt = Integer.parseInt(info[0]);
        int sneakCnt = Integer.parseInt(info[1]);

        for (int i = 0; i < ladderCnt+sneakCnt; i++) {
            String[] l = br.readLine().split(" ");

            LADDER_AND_SNEAK.put(Integer.parseInt(l[0]), Integer.parseInt(l[1]));
        }

        Node first = new Node(1, 0);
        boolean[] visited = new boolean[101];
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(first);
        visited[1] = true;

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int p = node.getPlace();
            int a = node.getAttempt();

            if (p == 100) {
                System.out.println(a);
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int n = p+i;

                if (100 == n) {
                    deque.add(new Node(100, a+1));
                    visited[100] = true;
                    break;
                }

                if (visited[n]) continue;

                if (LADDER_AND_SNEAK.containsKey(n)) {
                    Integer next = LADDER_AND_SNEAK.get(n);
                    deque.add(new Node(next, a+1));
                    visited[next] = true;
                } else {
                    deque.add(new Node(n, a+1));
                    visited[n] = true;
                }
            }
        }
    }

    private static class Node {
        int place;
        int attempt;

        public Node(int place, int attempt) {
            this.place = place;
            this.attempt = attempt;
        }

        public int getPlace() {
            return place;
        }

        public int getAttempt() {
            return attempt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "place=" + place +
                    ", attempt=" + attempt +
                    '}';
        }
    }
}
