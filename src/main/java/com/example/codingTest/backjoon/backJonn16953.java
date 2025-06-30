package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn16953 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new StringReader("100 40021"));
        String[] info = br.readLine().split(" ");
        long start = Integer.parseInt(info[0]);
        long target = Integer.parseInt(info[1]);

        System.out.println(bfs(start, target));
    }

    private static long bfs(long start, long target) {
        HashSet<Long> visited = new HashSet<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(start, 1));
        visited.add(start);

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            long value = node.getValue();
            long cnt = node.getCnt();

            if (value == target) {
                return cnt;
            }

            if (!visited.contains(value*2) && value*2 <= target) {
                deque.add(new Node(value*2, cnt+1));
            }

            if (!visited.contains(value*10+1) && value*10+1 <= target) {
                deque.add(new Node((value*10)+1, cnt+1));
            }
        }

        return -1;
    }

    private static class Node {
        long value;
        long cnt;

        public Node(long value, long cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        public long getValue() {
            return value;
        }

        public long getCnt() {
            return cnt;
        }
    }
}