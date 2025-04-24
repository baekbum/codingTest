package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1874 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
//        ex.add("8" + lc);
//        ex.add("4" + lc);
//        ex.add("3" + lc);
//        ex.add("6" + lc);
//        ex.add("8" + lc);
//        ex.add("7" + lc);
//        ex.add("5" + lc);
//        ex.add("2" + lc);
//        ex.add("1");

        ex.add("5" + lc);
        ex.add("1" + lc);
        ex.add("2" + lc);
        ex.add("5" + lc);
        ex.add("3" + lc);
        ex.add("4");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Deque<Integer> queue = new ArrayDeque<>();

        int cnt = Integer.valueOf(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Deque<Integer> current = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            Integer target = Integer.valueOf(br.readLine());
            list.add(target);
            queue.add(target);
        }

        ArrayList<String> answer = new ArrayList<>();
        boolean isExcess = false;

        for (int i = 0; i < cnt; i++) {
            // 검증해야할 타겟
            Integer t = list.get(i);

            System.out.println("Need to verify : " + t);

            // 데이터가 있는지 확인
            if (current.contains(t)) {
                while (true) {
                    answer.add("-");
                    System.out.println("[OUT] FROM current : " + current.peekLast());
                    if (t == current.pollLast()) break;
                }
                continue;
            } else {
                // 데이터가 존재하지 않을때 NO 체크
                if (queue.isEmpty() || !queue.contains(t)) {
                    isExcess = true;
                    break;
                }
            }

            // 정렬된 큐를 current 큐에 올리는 작업
            while (true) {
                Integer value = queue.poll();

                if (t >= value) {
                    current.add(value);
                    System.out.println("[IN] TO current : " + value);
                    answer.add("+");
                }

                if (t == value) {
                    current.pollLast();
                    System.out.println("[OUT] FROM current : " + value);
                    answer.add("-");
                    break;
                }
            }
        }

        if (isExcess) {
            System.out.println("NO");
        } else {
            answer.stream().forEach(System.out::println);
        }
    }
}
