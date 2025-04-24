package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.IntStream;
import java.io.InputStreamReader;

public class backJonn1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader("7 3"));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numberArr = br.readLine().split(" ");
        int endNumber = Integer.valueOf(numberArr[0]);
        int circleNumber = Integer.valueOf(numberArr[1]) - 1;
        int index = circleNumber;

        List<Integer> list = new LinkedList<>();

        IntStream.rangeClosed(1, endNumber)
                .forEach(list::add);

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (!list.isEmpty()) {
            System.out.println("current list size = " + list.size());
            System.out.println("current index = " + index);
            System.out.println("remove value = " + list.get(index));

            sb.append(list.get(index));
            list.remove(index);
            if (!list.isEmpty()) {
                sb.append(", ");

                int nextIndex = index + circleNumber;
                // 다음 인덱스 계산하는 과정
                // 배열 사이즈보다 인덱스가 작다면 ㅇㅋ 문제 없음
                if (nextIndex < list.size()) {
                    index = nextIndex;
                } else {
                    index = nextIndex - list.size();
                }

                if (index >= list.size()) {
                    index = list.size() % index;
                }

                System.out.println("next index = " + index);
                //System.out.println("next index value = " + list.get(index));
                System.out.println("-------------------------------");
            }
        }

        sb.append(">");
        System.out.println("---------------------------");
        System.out.println(sb.toString());
    }
}
