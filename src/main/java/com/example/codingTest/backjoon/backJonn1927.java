package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class backJonn1927 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("9" + lc);
        ex.add("0" + lc);
        ex.add("12345678" + lc);
        ex.add("1" + lc);
        ex.add("2" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("0" + lc);
        ex.add("32");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        br.readLine();

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        String s;
        while ((s = br.readLine()) != null) {
            int value = Integer.parseInt(s);

            if (value == 0) {
                if (queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll());
                }
            } else {
                queue.add(value);
            }
        }
    }
}