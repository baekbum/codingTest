package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1966 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("3" + lc);
        ex.add("1 0" + lc);
        ex.add("5" + lc);
        ex.add("4 2" + lc);
        ex.add("1 2 3 4" + lc);
        ex.add("6 0" + lc);
        ex.add("1 1 9 1 1 1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int cnt = Integer.parseInt(br.readLine());
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            String[] dataArr = br.readLine().split(" ");
            String[] prioritiesArr = br.readLine().split(" ");

            int totalCnt = Integer.parseInt(dataArr[0]);
            int targetIdx = Integer.parseInt(dataArr[1]);

            Deque<Number> deque = new ArrayDeque<>();
            Deque<Integer> priorDeque = new ArrayDeque<>();

            Arrays.stream(prioritiesArr)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(priorDeque::add);

            for (int j = 0; j < totalCnt; j++) {
                deque.add(new Number(j, Integer.parseInt(prioritiesArr[j])));
            }

            int order = 0;
            while (true) {
                Number number = deque.pollFirst();

                if (number.getPriority() != priorDeque.peek()) {
                    deque.addLast(number);
                } else {
                    order++;
                    priorDeque.pollFirst();

                    if (targetIdx == number.getIdx()) {
                        answer.add(order);
                        break;
                    }
                }
            }
        }

        answer.stream().forEach(System.out::println);
    }

    private static class Number {
        int idx;
        int priority;

        public Number(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }

        public int getIdx() {
            return idx;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "idx=" + idx +
                    ", priority=" + priority +
                    '}';
        }
    }
}
