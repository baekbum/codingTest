package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class backJonn10815 {

    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("5" + lc);
        ex.add("6 3 2 10 -10" + lc);
        ex.add("8" + lc);
        ex.add("10 9 -5 2 3 4 5 -10");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        List<Integer> answer = new ArrayList<>();

        br.readLine();
        String[] userCard = br.readLine().split(" ");

        HashSet<Integer> userCardSet = new HashSet<>();
        for (String card : userCard) {
            userCardSet.add(Integer.parseInt(card));
        }

        br.readLine();
        String[] dealerCard = br.readLine().split(" ");

        for (String card : dealerCard) {
            if (userCardSet.contains(Integer.parseInt(card))) {
                answer.add(1);
            } else {
                answer.add(0);
            }
        }

        StringBuilder sb = new StringBuilder();

        answer.stream().forEach(c -> sb.append(c).append(" "));

        System.out.println(sb.toString().trim());
    }
}
