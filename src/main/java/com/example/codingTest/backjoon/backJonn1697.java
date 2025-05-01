package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader("5 17"));

        String[] info = br.readLine().split(" ");
        int me = Integer.parseInt(info[0]);
        int target = Integer.parseInt(info[1]);

        TreeSet<Integer> answer = bfs(target, me);

        System.out.println(answer.pollFirst());
    }

    private static TreeSet<Integer> bfs(int target, int me) {
        final int MIN = 0;
        final int MAX = 100_00;
        Deque<Pos> deque = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        TreeSet<Integer> answer = new TreeSet<>();

        deque.add(new Pos(me, 0));

        while (!deque.isEmpty()) {
            Pos pos = deque.pollFirst();
            System.out.println("pos.getValue() = " + pos.getValue());
            System.out.println("pos.getSec() = " + pos.getSec());
            System.out.println("===========");
            if (target == pos.getValue()) {
                answer.add(pos.getSec());
                continue;
            }

            if (!set.contains(pos.getValue())) {
                set.add(pos.getValue());

                // 빼기
                if (MIN <= pos.getValue()-1) {
                    deque.add(new Pos(pos.getValue()-1, pos.getSec()+1));
                }

                // 더하기
                if (pos.getValue() < target) {
                    deque.add(new Pos(pos.getValue()+1, pos.getSec()+1));
                }

                // 곱하기
                if (pos.getValue() < target) {
                    deque.add(new Pos(pos.getValue()*2, pos.getSec()+1));
                }
            }
        }

        return answer;
    }

    private static class Pos {
        private int value;
        private int sec;

        public Pos(int value, int sec) {
            this.value = value;
            this.sec = sec;
        }

        public int getValue() {
            return value;
        }

        public int getSec() {
            return sec;
        }
    }
}
