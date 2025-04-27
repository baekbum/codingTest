package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLOutput;
import java.util.*;

public class backJonn10451 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
        ex.add("2" + lc);
        ex.add("8" + lc);
        ex.add("3 2 7 8 1 4 5 6" + lc);
        ex.add("10" + lc);
        ex.add("2 1 3 4 5 6 7 9 10 8");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        ArrayList<String> answer = new ArrayList<>();
        int caseCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCnt; i++) {
            int cnt = Integer.parseInt(br.readLine());
            String[] values = br.readLine().split(" ");

            // 초기 데이터 만들기
            HashMap<Integer, Integer> map = initData(cnt, values);

            // 검증
            System.out.println("********************** start **********************");
            answer.add(processing(map));
            System.out.println("********************** end **********************");
        }

        System.out.println(answer.stream().reduce("", (s1, s2) -> s1 + " " + s2).trim());


    }

    private static HashMap<Integer, Integer> initData(int cnt, String[] values) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // 데이터 만들기 과정
        for (int j = 0; j < cnt; j++) {
            map.put(j + 1, Integer.parseInt(values[j]));
        }

        return map;
    }

    private static String processing(HashMap<Integer, Integer> map) {
        HashSet<Integer> exist = new HashSet<>();
        int circleCnt = 0;

        for (int i = 0; i < map.size(); i++) {
            if (exist.contains(i+1)) continue;;

            Deque<Integer> circle = new ArrayDeque<>();
            HashSet<Integer> storage = new HashSet<>();
            int initValue = i+1;
            System.out.println("init value = " + initValue + "\n");
            exist.add(initValue);
            circle.add(map.get(initValue));

            while (!circle.isEmpty()) {
                Integer value = circle.pollFirst();
                System.out.println("current value = " + value);
                System.out.println("next value = " + map.get(value) + "\n");
                // 첫번째 값과 같다면 스탑
                if (initValue == value) {
                    circleCnt++;
                    System.out.println("======[" + initValue + "] cycle end======" );
                    break;
                } else {
                    // 해당 값이 이미 존재하면 스탑
                    if (storage.contains(value)) break;

                    storage.add(value);
                    circle.add(map.get(value));
                    exist.add(value);
                }
            }
        }

        return String.valueOf(circleCnt);
    }
}
