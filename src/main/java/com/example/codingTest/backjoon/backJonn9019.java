package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.function.Function;

public class backJonn9019 {

    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("3" + lc);
        ex.add("1234 3412" + lc);
        ex.add("1000 1" + lc);
        ex.add("1 16");

//        ex.add("1" + lc);
//        ex.add("0 9999");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int caseCnt = Integer.parseInt(br.readLine());
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < caseCnt; i++) {
            String[] data = br.readLine().split(" ");
            answer.add(bfs(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
        }

        answer.stream().forEach(System.out::println);
    }

    private static String bfs(int current, int target) {
        HashSet<Integer> exist = new HashSet<>();
        Deque<Number> deque = new ArrayDeque<>();
        deque.add(new Number(current, ""));

        Function<Integer, Integer> D_Func = doubleProcess();
        Function<Integer, Integer> S_Func = subProcess();
        Function<Integer, Integer> L_Func = leftProcess();
        Function<Integer, Integer> R_Func = rightProcess();

        while (!deque.isEmpty()) {
            Number n = deque.pollFirst();
            int value = n.getValue();

            if (value == target) {
                return n.getCommand();
            }

            int newD = D_Func.apply(value);
            int newS = S_Func.apply(value);
            int newL = L_Func.apply(value);
            int newR = R_Func.apply(value);

            if (!exist.contains(newD)) {
                exist.add(newD);
                deque.add(new Number(newD, n.getCommand() + "D"));
            }

            if (!exist.contains(newS)) {
                exist.add(newS);
                deque.add(new Number(newS, n.getCommand() + "S"));
            }

            if (!exist.contains(newL)) {
                exist.add(newL);
                deque.add(new Number(newL, n.getCommand() + "L"));
            }

            if (!exist.contains(newR)) {
                exist.add(newR);
                deque.add(new Number(newR, n.getCommand() + "R"));
            }
        }

        return "";
    }

    private static Function<Integer, Integer> doubleProcess() {
        return (current) -> {
            return current*2 > 9999 ? (current*2) % 10000 : current*2;
        };
    }

    private static Function<Integer, Integer> subProcess() {
        return (current) -> {
            return current == 0 ? 9999 : current-1;
        };
    }

    private static Function<Integer, Integer> leftProcess() {
        return (current) -> {
            return (current % 1000) * 10 + (current / 1000);
        };
    }

    private static Function<Integer, Integer> rightProcess() {
        return (current) -> {
            return (current % 10) * 1000 + (current / 10);
        };
    }

    private static class Number {
        int value;
        String command;

        public Number(int value, String command) {
            this.value = value;
            this.command = command;
        }

        public int getValue() {
            return value;
        }

        public String getCommand() {
            return command;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "value=" + value +
                    ", command='" + command + '\'' +
                    '}';
        }
    }
}
