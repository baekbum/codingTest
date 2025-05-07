package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.IntStream;

public class backJonn2583 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();
//        ex.add("5 7 3" + lc);
//        ex.add("0 2 4 4" + lc);
//        ex.add("1 1 2 5" + lc);
//        ex.add("4 0 6 2");

//        ex.add("1 3 1" + lc);
//        ex.add("1 0 2 1");

//        ex.add("4 4 1" + lc);
//        ex.add("0 0 1 1");

        ex.add("2 2 2" + lc);
        ex.add("0 0 1 1" + lc);
        ex.add("1 1 2 2");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] infos = br.readLine().split(" ");
        int width = Integer.parseInt(infos[1]);
        int height = Integer.parseInt(infos[0]);
        int line = Integer.parseInt(infos[2]);

        HashMap<String, Boolean> map = new HashMap<>();

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                map.put(w + "" + h, false);
            }
        }

        String s;
        while ((s = br.readLine()) != null) {
            String[] points = s.split(" ");
            String sw = points[0];
            String sh = points[1];
            String ew = points[2];
            String eh = points[3];

            for (int w = Integer.parseInt(sw); w < Integer.parseInt(ew); w++) {
                for (int h = Integer.parseInt(sh); h < Integer.parseInt(eh); h++) {
                    // 해당 구역을 true로 바꿈
                    map.put(w + "" + h, true);
                }
            }
        }

        List<Integer> answer = new ArrayList<>();

        for (String key : map.keySet()) {
            if (!map.get(key)) {
                int cnt = 0;

                Deque<String> deque = new ArrayDeque<>();
                deque.add(key);

                while (!deque.isEmpty()) {
                    String k = deque.pollFirst();

                    if (!map.get(k)) {
                        //System.out.println("k = " + k);
                        map.put(k, true);
                        cnt++;
                        int w = k.charAt(0) - '0';
                        int h = k.charAt(1) - '0';

                        // 상하좌우
                        if (h+1 < height) deque.add(w + "" + (h+1));
                        if (h-1 > -1) deque.add(w + "" + (h-1));
                        if (w-1 > -1) deque.add((w-1) + "" + h);
                        if (w+1 < width) deque.add((w+1) + "" + h);
                    }
                }
                //System.out.println("==========");
                answer.add(cnt);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size());
        sb.append("\n");

        answer.stream().sorted().forEach(k -> sb.append(k + " "));
        System.out.println(sb.toString().trim());
    }
}
