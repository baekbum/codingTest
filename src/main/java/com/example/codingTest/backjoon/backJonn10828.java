package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * push X: 정수 X를 스택에 넣는 연산이다.
 * pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * size: 스택에 들어있는 정수의 개수를 출력한다.
 * empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
 * top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 */
public class backJonn10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < cnt; i++) {
            String s = br.readLine();

            System.out.println("s = " + s);

            String[] split = s.split(" ");

            switch (split[0]) {
                case "push":
                    stack.push(Integer.valueOf(split[1]));
                    break;
                case "pop":
                    if (stack.empty()) System.out.println("-1");
                    else System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println((stack.empty() ? "1" : "0"));
                    break;
                case "top":
                    System.out.println(stack.empty() ? "-1" : stack.peek());
                    break;
                default:
                    break;
            }
        }
    }
}
