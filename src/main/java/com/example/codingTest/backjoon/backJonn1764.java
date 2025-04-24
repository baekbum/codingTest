package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.stream.IntStream;

public class backJonn1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashSet<String> set = new HashSet<>();
        HashSet<String> answer = new HashSet<>();

        String s;

        while ((s = br.readLine()) != null) {
            if (!set.contains(s)) {
                set.add(s);
            } else {
                answer.add(s);
            }
        }

        System.out.println(answer.size());
        answer.stream().sorted().forEach(System.out::println);
    }
}
