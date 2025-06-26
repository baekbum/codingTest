package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.function.BiConsumer;

public class backJonn13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader("0 100000"));

        String[] info = br.readLine().split(" ");
        int big = Integer.parseInt(info[0]); // 형 위치
        int little = Integer.parseInt(info[1]); // 동생 위치

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(big, 0));

        HashSet<Integer> visited = new HashSet<>();
        visited.add(big);

        BiConsumer<Integer, Integer> consumer = consumer(deque, visited);

        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            int current = node.getCurrent();
            int cnt = node.getCnt();

            if (current == little) {
                System.out.println(cnt);
                break;
            }

            if (current < little && current*2 <= 100_000) {
                consumer.accept(current*2, cnt);
            }
            if (-1 < current) {
                consumer.accept(current-1, cnt+1);
            }
            if (current < little) {
                consumer.accept(current+1, cnt+1);
            }
        }
    }

    private static BiConsumer<Integer, Integer> consumer(Deque<Node> deque, HashSet<Integer> visited) {
        return (value, cnt) -> {
            if (!visited.contains(value)) {
                deque.add(new Node(value, cnt));
                visited.add(value);
            }
        };
    }

    private static class Node {
        int current;
        int cnt;

        public Node(int current, int cnt) {
            this.current = current;
            this.cnt = cnt;
        }

        public int getCurrent() {
            return current;
        }

        public int getCnt() {
            return cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "current=" + current +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}