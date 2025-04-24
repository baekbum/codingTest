package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

/**
 * 10
 *
 * 1
 * 3
 * 5
 * 4
 * 0
 * 0
 * 7
 * 0
 * 0
 * 6
 */
public class backJoon10773 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("10" + lc);
        stringBuilder.append("1" + lc + "3" + lc + "5" + lc + "4" + lc + "0" + lc + "0" + lc + "7" + lc + "0" + lc + "0" + lc + "6");

        BufferedReader br = new BufferedReader(new StringReader(stringBuilder.toString()));

        int len = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        String s;

        while ((s = br.readLine()) != null) {
            if (Integer.valueOf(s) == 0) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(Integer.valueOf(s));
            }
        }

        Integer answer = stack.stream()
                .reduce(0, (i1, i2) -> i1 + i2);

        System.out.println(answer);

        //stack.stream().forEach(System.out::println);
    }
}
