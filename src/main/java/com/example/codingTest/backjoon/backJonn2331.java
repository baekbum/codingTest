package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2331 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader("57 2"));

        String[] commands = br.readLine().split(" ");
        int initValue = Integer.parseInt(commands[0]);
        int square = Integer.parseInt(commands[1]);

        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int answer = 0;
        
        deque.add(initValue);
        
        while (true) {
            Integer value = deque.pollFirst();
            System.out.println("value = " + value);

            if (list.contains(value)) {
                answer = list.indexOf(value);
                break;
            }
            list.add(value);

            String strValue = String.valueOf(value);
            int newValue = 0;

            for (int i = 0; i < strValue.length(); i++) {
                int digit = Character.getNumericValue(strValue.charAt(i));
                newValue += (int) Math.pow(digit, square);
            }

            deque.add(newValue);
        }

        System.out.println("answer = " + answer);
    }
}
