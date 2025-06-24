package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2252 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("3 2" + lc);
//        ex.add("1 3" + lc);
//        ex.add("2 3");

//        ex.add("4 2" + lc);
//        ex.add("4 2" + lc);
//        ex.add("3 1");

        ex.add("6 5" + lc);
        ex.add("1 6" + lc);
        ex.add("4 5" + lc);
        ex.add("3 6" + lc);
        ex.add("1 5" + lc);
        ex.add("3 4");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] info = br.readLine().split(" ");
        int studentCnt = Integer.parseInt(info[0]);
        int compareCnt = Integer.parseInt(info[1]);

        // 타겟 , 타겟보다 앞에 있는 수
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] inDegree = new int[studentCnt + 1];

        for (int i = 0; i < studentCnt; i++) {
            map.put(i + 1, new ArrayList<>());
        }

        for (int i = 0; i < compareCnt; i++) {
            String[] data = br.readLine().split(" ");
            int front = Integer.parseInt(data[0]);
            int back = Integer.parseInt(data[1]);

            map.get(front).add(back);
            inDegree[back]++;
        }

        List<Integer> answer = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= studentCnt; i++) {
            if (inDegree[i] == 0) {
                deque.add(i);
                answer.add(i);
            }
        }

        while (!deque.isEmpty()) {
            Integer front = deque.pollFirst();

            map.get(front).stream()
                    .forEach(back -> {
                        inDegree[back]--;

                        if (inDegree[back] == 0) {
                            deque.add(back);
                            answer.add(back);
                        }
                    });

        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i));
            if (i < answer.size()-1) sb.append(" ");
        }

        System.out.println(sb);
    }
}