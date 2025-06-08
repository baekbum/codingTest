package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class backJonn18258 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("15" + lc);
        ex.add("push 1" + lc);
        ex.add("push 2" + lc);
        ex.add("front" + lc);
        ex.add("back" + lc);
        ex.add("size" + lc);
        ex.add("empty" + lc);
        ex.add("pop" + lc);
        ex.add("pop" + lc);
        ex.add("pop" + lc);
        ex.add("size" + lc);
        ex.add("empty" + lc);
        ex.add("pop" + lc);
        ex.add("push 3" + lc);
        ex.add("empty" + lc);
        ex.add("front");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int cnt = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cnt; i++) {
            String[] info = br.readLine().split(" ");

            switch (info[0]) {
                case "push" :
                    deque.add(Integer.parseInt(info[1]));
                    break;
                case "pop" :
                    sb.append(deque.isEmpty() ? -1 : deque.pollFirst());
                    break;
                case "size" :
                    sb.append(deque.size());
                    break;
                case "empty" :
                    sb.append(deque.isEmpty() ? 1 : 0);
                    break;
                case "front" :
                    sb.append(deque.isEmpty() ? -1 : deque.peekFirst());
                    break;
                case "back" :
                    sb.append(deque.isEmpty() ? -1 : deque.peekLast());
                    break;
                default:
                    break;
            }

            if (!info[0].equals("push") && i < cnt-1)
                sb.append("\n");
        }

        System.out.println(sb);
    }
}