package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class backJoon2164 {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("6");

        BufferedReader br = new BufferedReader(new StringReader(stringBuilder.toString()));

        Integer number = Integer.valueOf(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        IntStream.rangeClosed(1, number)
                .forEach(deque::add);

        while (deque.size() > 1) {
            deque.pollFirst(); // 버리는 용도
            deque.addLast(deque.pollFirst()); // 맨 뒤로 보내는 용도
        }

        deque.stream().forEach(System.out::println);
    }
}
