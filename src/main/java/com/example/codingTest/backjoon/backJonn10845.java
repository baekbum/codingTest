package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class backJonn10845 {
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

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer> queue = new ArrayDeque<>();

        int cnt = Integer.valueOf(br.readLine());

        for (int i = 0; i < cnt; i++) {
            String[] commands = br.readLine().split(" ");
            System.out.println("commands[0] = " + commands[0]);
            switch (commands[0]) {
                case "push":
                    queue.add(Integer.valueOf(commands[1]));
                    break;
                case "pop":
                    if (queue.isEmpty()) System.out.println("-1");
                    else System.out.println(queue.poll());
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "empty":
                    if (queue.isEmpty()) System.out.println("1");
                    else System.out.println("0");
                    break;
                case "front":
                    if (queue.isEmpty()) System.out.println("-1");
                    else System.out.println(queue.peekFirst());
                    break;
                case "back":
                    if (queue.isEmpty()) System.out.println("-1");
                    else System.out.println(queue.peekLast());
                    break;
                default:
                    break;
            }
            System.out.println("-------------------------");
        }
    }
}
