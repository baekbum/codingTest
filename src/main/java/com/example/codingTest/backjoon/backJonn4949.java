package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn4949 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

//        ex.add("So when I die (the [first] I will see in (heaven) is a score list)." + lc);
//        ex.add("[ first in ] ( first out )." + lc);
//        ex.add("Half Moon tonight (At least it is better than no Moon at all]." + lc);
//        ex.add("A rope may form )( a trail in a maze." + lc);
//        ex.add("Help( I[m being held prisoner in a fortune cookie factory)]." + lc);
//        ex.add("([ (([( [ ] ) ( ) (( ))] )) ])." + lc);
//        ex.add(" ." + lc);
//        ex.add(".");

//        ex.add("])[[a [ab)a." + lc);
//        ex.add("((()))." + lc);
//        ex.add(" [[(b])]]." + lc);
//        ex.add("()." + lc);
//        ex.add("[[(([]))]]." + lc);
//        ex.add("(]((([[][][)(([[][b[[)[(([]b]] [[[[([])[[([ ] (a( ()]]))]]]](b)])]] )a]][][()[b)(()())])] )[() ([)a." + lc);
//        ex.add("((])." + lc);
//        ex.add(")(a])." + lc);
//        ex.add(".");

        ex.add(")[[]()))(." + lc);
        ex.add("(aaaaaaa)a." + lc);
        ex.add("([((()))])." + lc);
        ex.add("[[[[]]]]." + lc);
        ex.add(")][([]]][[." + lc);
        ex.add("[((()a)]." + lc);
        ex.add("((([[([([a([(([(([(([[[[[((([((([[[[(([([[[([(([[]]))])]]])]))]]]])))])))]]]]]))]))]))]))])])]])))a." + lc);
        ex.add("[)(]." + lc);
        ex.add("[]." + lc);
        ex.add("(([]))." + lc);
        ex.add(".");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        HashSet<String> open = new HashSet<>(Set.of("[", "("));
        List<String> answer = new ArrayList<>();

        String s;
        while ((s = br.readLine()) != null) {
            if (s.length() == 1 && s.charAt(0) == '.') break;

            Stack<String> stack = new Stack<>();
            boolean fail = false;

            for (int i = 0; i < s.length(); i++) {
                String el = String.valueOf(s.charAt(i));

                if (open.contains(el)) {
                    stack.push(el);
                } else if (el.equals("]")) {
                    if (stack.isEmpty()) {
                        fail = true;
                        break;
                    } else {
                        if (stack.peek().equals("[")) {
                            stack.pop();
                        } else {
                            fail = true;
                            break;
                        }
                    }
                } else if (el.equals(")")) {
                    if (stack.isEmpty()) {
                        fail = true;
                        break;
                    } else {
                        if (stack.peek().equals("(")) {
                            stack.pop();
                        } else {
                            fail = true;
                            break;
                        }
                    }
                }
            }

            if (fail) {
                answer.add("no");
            } else {
                if (stack.isEmpty()) answer.add("yes");
                else answer.add("no");
            }
        }

        answer.stream().forEach(System.out::println);
    }
}
