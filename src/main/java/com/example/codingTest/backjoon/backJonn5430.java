package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class backJonn5430 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("4" + lc);
//        ex.add("RDD" + lc);
//        ex.add("4" + lc);
//        ex.add("[1,2,3,4]" + lc);
//        ex.add("DD" + lc);
//        ex.add("1" + lc);
//        ex.add("[42]" + lc);
//        ex.add("RRD" + lc);
//        ex.add("6" + lc);
//        ex.add("[1,1,2,3,5,8]" + lc);
//        ex.add("D" + lc);
//        ex.add("0" + lc);
//        ex.add("[]");

        ex.add("3" + lc);
        ex.add("R" + lc);
        ex.add("2" + lc);
        ex.add("[94,100]" + lc);
        ex.add("D" + lc);
        ex.add("4" + lc);
        ex.add("[6,6,6,6]" + lc);
        ex.add("DDRDR" + lc);
        ex.add("0" + lc);
        ex.add("[]");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        StringBuilder sb = new StringBuilder();
        int totalCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < totalCnt; i++) {
            String command = br.readLine();
            int sampleCnt = Integer.parseInt(br.readLine());

            String s = br.readLine();

            if (s.equals("[]") && !command.contains("D")) {
                sb.append("[]");
                if (i < totalCnt-1) sb.append("\n");
                continue;
            }

            if (s.equals("[]") && command.contains("D")) {
                sb.append("error");
                if (i < totalCnt-1) sb.append("\n");
                continue;
            }

            sb.append("[");
            String[] data = s.substring(1, s.length() - 1).split(",");

            Deque<Integer> deque = new ArrayDeque<>();
            Arrays.stream(data)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .forEach(deque::add);

            boolean isReverse = false;
            boolean isEmpty = false;

            for (int j = 0; j < command.length(); j++) {
                char c = command.charAt(j);

                if (c == 'R') {
                    isReverse = isReverse ? false : true;
                } else {
                    if (deque.isEmpty()) {
                        isEmpty = true;
                        break;
                    }

                    if (isReverse) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (isEmpty) {
                sb.deleteCharAt(sb.length()-1);
                sb.append("error");
                if (i < totalCnt-1) sb.append("\n");
                continue;
            }

            while (!deque.isEmpty()) {
                sb.append(isReverse ? deque.pollLast() : deque.pollFirst());
                if (!deque.isEmpty()) sb.append(",");
            }

            sb.append("]");
            if (i < totalCnt-1) sb.append("\n");
        }

        System.out.println(sb);
    }
}