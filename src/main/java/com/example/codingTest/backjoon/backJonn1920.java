package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 5
 4 1 5 2 3
 5
 1 3 7 9 5

 1
 1
 0
 0
 1
 */
public class backJonn1920 {
    public static void main(String[] args) throws IOException {
        String lineChange = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("5");
        stringBuilder.append(lineChange);
        stringBuilder.append("4 1 5 2 3");
        stringBuilder.append(lineChange);
        stringBuilder.append("5");
        stringBuilder.append(lineChange);
        stringBuilder.append("1 3 7 9 5");

        BufferedReader br = new BufferedReader(new StringReader(stringBuilder.toString()));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int targetCnt = Integer.valueOf(br.readLine());

        int[] targets = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .sorted()
                .toArray();

        int valuesCnt = Integer.valueOf(br.readLine());
        String[] values = br.readLine().split(" ");

        for (int i = 0; i < valuesCnt; i++) {
            int minIdx = 0, midIdx = 0;
            int maxIdx = values.length - 1;
            int currentVal = Integer.valueOf(values[i]);

            if (currentVal < targets[0] || targets[targets.length -1] < currentVal) {
                System.out.println("0");
                continue;
            }

            while (true) {
                midIdx = Math.round((float) (minIdx + maxIdx) / 2);

                if (midIdx == minIdx || midIdx == maxIdx) {
                    int lastMinVal = Integer.compare(currentVal, targets[minIdx]);
                    int lastMaxVal = Integer.compare(currentVal, targets[maxIdx]);

                    if (lastMinVal == 0 || lastMaxVal == 0) System.out.println("1");
                    else System.out.println("0");
                    break;
                }

                int result = Integer.compare(currentVal, targets[midIdx]);

                if (result == 0) {
                    System.out.println("1");
                    break;
                }

                if (result == -1) maxIdx = midIdx - 1;
                if (result == 1) minIdx = midIdx + 1;
            }

        }
    }
}
