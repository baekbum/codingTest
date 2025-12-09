package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
//import java.io.InputStreamReader;

public class backJonn1976 {
    private static int[] PARENTS;

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("3" + lc);
        ex.add("3" + lc);
        ex.add("0 1 0" + lc);
        ex.add("1 0 1" + lc);
        ex.add("0 1 0" + lc);
        ex.add("1 2 3");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int citiesCnt = Integer.parseInt(br.readLine());
        int scheduledCitiesCnt = Integer.parseInt(br.readLine());

        PARENTS = new int[citiesCnt+1];
        for (int i = 1; i <= citiesCnt ; i++) {
            PARENTS[i] = i;
        }

        for (int i = 1; i <= citiesCnt; i++) {
            String[] info = br.readLine().split(" ");

            // 0 1 2
            for (int j = 0; j < citiesCnt; j++) {
                int connect = Integer.parseInt(info[j]);

                if (connect == 1) {
                    union(i, j+1);
                }
            }
        }

        String[] s = br.readLine().split(" ");
        int[] sequence = new int[scheduledCitiesCnt];

        for (int i = 0; i < s.length; i++) {
            sequence[i] = Integer.parseInt(s[i]);
        }

        boolean isSame = true;
        int parent = findParent(sequence[0]);

        for (int i = 1; i < sequence.length; i++) {
            if (parent != findParent(sequence[i])) {
                isSame = false;
                break;
            }
        }

        System.out.println(isSame ? "YES" : "NO");
    }

    private static void union(int x, int y) {
        Integer left = findParent(x);
        Integer right = findParent(y);

        if (left != right) {
            PARENTS[right] = left;
        }
    }

    private static Integer findParent(int x) {
        if (PARENTS[x] == x) {
            return x;
        }

        return PARENTS[x] = findParent(PARENTS[x]);
    }
}
