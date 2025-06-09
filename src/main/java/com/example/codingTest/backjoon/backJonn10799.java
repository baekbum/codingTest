package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader("()(((()())(())()))(())"));

        Deque<Stick> deque = new ArrayDeque<>();
        int sum = 0;

        String razer = br.readLine();

        for (int i = 0; i < razer.length(); i++) {
            if (razer.charAt(i) == '(') {
                if (razer.charAt(i+1) == ')') {
                    deque.stream().forEach(Stick::addCut);
                    i++;
                    continue;
                }

                deque.add(new Stick(0));
            } else {
                if (!deque.isEmpty()) {
                    int cuttingCnt = deque.pollLast().getCut() + 1;
                    sum += cuttingCnt;
                }
            }
        }

        System.out.println(sum);
    }

    private static class Stick {
        int cut = 0;

        public Stick(int cut) {
            this.cut = cut;
        }

        public int getCut() {
            return cut;
        }

        public void addCut() {
            this.cut++;
        }
    }
}