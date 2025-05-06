package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.IntStream;

public class backJonn1389 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();
        ex.add("5 5" + lc);
        ex.add("1 3" + lc);
        ex.add("1 4" + lc);
        ex.add("4 5" + lc);
        ex.add("4 3" + lc);
        ex.add("3 2");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] infos = br.readLine().split(" ");
        int total = Integer.parseInt(infos[0]);

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        IntStream.rangeClosed(1, total)
                .forEach(i -> map.put(i, new ArrayList<>()));

        String s;
        while ((s = br.readLine()) != null) {
            String[] relatives = s.split(" ");

            map.get(Integer.parseInt(relatives[0])).add(Integer.parseInt(relatives[1]));
            map.get(Integer.parseInt(relatives[1])).add(Integer.parseInt(relatives[0]));
        }

        int user = 0;
        int minSum = 0;

        for (Integer key : map.keySet()) {
            HashMap<Integer, Integer> innerMap = new HashMap<>();
            Deque<Integer> deque = new ArrayDeque<>();

            deque.add(key);
            innerMap.put(key, 0);

            while (!deque.isEmpty()) {
                Integer current = deque.pollFirst();

                if (innerMap.size() == total) {
                    int sum = 0;
                    for (Integer i : innerMap.keySet()) {
                        //System.out.println("i = " + i + ", count = " + innerMap.get(i));
                        sum += innerMap.get(i);
                    }
                    //System.out.println("sum = " + sum);
                    //System.out.println("===================");

                    if (minSum == 0) {
                        user = key;
                        minSum = sum;
                    }

                    if (sum < minSum) {
                        user = key;
                        minSum = sum;
                    }

                    if (sum == minSum && key < user) {
                        user = key;
                    }

                    break;
                }

                map.get(current).stream()
                        .filter(k -> !innerMap.containsKey(k))
                        .forEach(k -> {
                            deque.add(k);
                            innerMap.put(k, innerMap.get(current)+1);
                        });
            }
        }

        System.out.println(user);
    }
}
