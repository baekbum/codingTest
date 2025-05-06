package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.IntStream;

public class backJonn2644 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();
        /*ex.add("9" + lc);
        ex.add("7 3" + lc);
        ex.add("7" + lc);
        ex.add("1 2" + lc);
        ex.add("1 3" + lc);
        ex.add("2 7 " + lc);
        ex.add("2 8" + lc);
        ex.add("2 9" + lc);
        ex.add("4 5" + lc);
        ex.add("4 6");*/

        ex.add("9" + lc);
        ex.add("8 6" + lc);
        ex.add("7" + lc);
        ex.add("1 2" + lc);
        ex.add("1 3" + lc);
        ex.add("2 7 " + lc);
        ex.add("2 8" + lc);
        ex.add("2 9" + lc);
        ex.add("4 5" + lc);
        ex.add("4 6");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int total = Integer.parseInt(br.readLine());
        String[] target = br.readLine().split(" ");
        br.readLine();

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        IntStream.rangeClosed(1, total)
                .forEach(i -> map.put(i, new ArrayList<>()));

        String s;
        while ((s = br.readLine()) != null) {
            String[] relatives = s.split(" ");

            map.get(Integer.parseInt(relatives[0])).add(Integer.parseInt(relatives[1]));
            map.get(Integer.parseInt(relatives[1])).add(Integer.parseInt(relatives[0]));
        }

        int me = Integer.parseInt(target[0]);
        int relative = Integer.parseInt(target[1]);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(me);

        HashMap<Integer, Integer> exist = new HashMap<>(); // 번호와 촌수
        exist.put(me, 0);

        boolean found = false;


        while (!deque.isEmpty()) {
            Integer current = deque.pollFirst();

            if (current ==  relative) {
                found = true;
                System.out.println(exist.get(current));
            }


            ArrayList<Integer> relatives = map.get(current);

            for (Integer r : relatives) {
                if (!exist.containsKey(r)) {
                    deque.add(r);
                    exist.put(r, exist.get(current)+1);
                }
            }
        }

        if (!found) System.out.println("-1");

    }
}
