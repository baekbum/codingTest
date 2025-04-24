package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class backJonn10866 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();
        ex.add("15" + lc);
        ex.add("push_back 1" + lc);
        ex.add("push_front 2" + lc);
        ex.add("front" + lc);
        ex.add("back" + lc);
        ex.add("size" + lc);
        ex.add("empty" + lc);
        ex.add("pop_front" + lc);
        ex.add("pop_back" + lc);
        ex.add("pop_front" + lc);
        ex.add("size" + lc);
        ex.add("empty" + lc);
        ex.add("pop_back" + lc);
        ex.add("push_front 3" + lc);
        ex.add("empty" + lc);
        ex.add("front");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        Deque<Integer> deque = new ArrayDeque<>();

        String s;
        br.readLine();
        while ((s = br.readLine()) != null) {
            String[] split = s.split(" ");

            switch (split[0]) {
                case "push_front":
                    deque.addFirst(Integer.valueOf(split[1]));
                    break;
                case "push_back":
                    deque.addLast(Integer.valueOf(split[1]));
                    break;
                case "pop_front":
                    if (deque.isEmpty()) System.out.println("-1");
                    else System.out.println(deque.pollFirst());
                    break;
                case "pop_back":
                    if (deque.isEmpty()) System.out.println("-1");
                    else System.out.println(deque.pollLast());
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if (deque.isEmpty()) System.out.println("1");
                    else System.out.println("0");
                    break;
                case "front":
                    if (deque.isEmpty()) System.out.println("-1");
                    else System.out.println(deque.peekFirst());
                    break;
                case "back":
                    if (deque.isEmpty()) System.out.println("-1");
                    else System.out.println(deque.peekLast());
                    break;
                default:
                    break;
            }
        }

    }
}
