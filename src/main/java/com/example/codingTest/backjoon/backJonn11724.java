package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn11724 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
//        ex.add("6 5" + lc);
//        ex.add("1 2" + lc);
//        ex.add("2 5" + lc);
//        ex.add("5 1" + lc);
//        ex.add("3 4" + lc);
//        ex.add("4 6");

//        ex.add("6 8" + lc);
//        ex.add("1 2" + lc);
//        ex.add("2 5" + lc);
//        ex.add("5 1" + lc);
//        ex.add("3 4" + lc);
//        ex.add("4 6" + lc);
//        ex.add("5 4" + lc);
//        ex.add("2 4" + lc);
//        ex.add("2 3");

        ex.add("6 2" + lc);
        ex.add("1 3" + lc);
        ex.add("2 3");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] info = br.readLine().split(" ");
        int nodeCnt = Integer.parseInt(info[0]);
        int nodeEdge = Integer.parseInt(info[1]);

        HashMap<Integer, ArrayList<Integer>> dataMap = new HashMap<>();

        // 데이터 맵 생성
        for (int i = 0; i < nodeCnt; i++) {
            dataMap.put(i+1, new ArrayList<>());
        }

        // 데이터 초기화
        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");
            int key1 = Integer.parseInt(data[0]);
            int key2 = Integer.parseInt(data[1]);

            dataMap.get(key1).add(key2);
            dataMap.get(key2).add(key1);
        }

        HashSet<Integer> usedSet = new HashSet<>();
        int cnt = 0;

        for (Integer key : dataMap.keySet()) {
            if (usedSet.contains(key)) {
                System.out.println(key + " is already existed");
                continue;
            }

            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(key);

            // 연결된 노드를 넣을때만 체크
            while (!deque.isEmpty()) {
                Integer current = deque.pollFirst();

                if (!usedSet.contains(current)) {
                    usedSet.add(current);

                    System.out.println("current = " + current);

                    for (Integer child : dataMap.get(current)) {
                        if (!usedSet.contains(child)) {
                            deque.add(child);
                        }
                    }
                } else {
                    System.out.println(current + " is already existed");
                }
            }

            cnt++;

            if (dataMap.size() == usedSet.size()) break;
        }

        System.out.println("cnt = " + cnt);
    }
}
