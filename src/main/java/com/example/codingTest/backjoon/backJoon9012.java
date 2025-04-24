package com.example.codingTest.backjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class backJoon9012 {
    public static void main(String[] args) throws IOException {
        String lineChange = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("6");
        stringBuilder.append(lineChange);
        stringBuilder.append("(())())");
        stringBuilder.append(lineChange);
        stringBuilder.append("(((()())()");
        stringBuilder.append(lineChange);
        stringBuilder.append("(()())((()))");
        stringBuilder.append(lineChange);
        stringBuilder.append("((()()(()))(((())))()");
        stringBuilder.append(lineChange);
        stringBuilder.append("()()()()(()()())()");
        stringBuilder.append(lineChange);
        stringBuilder.append("(()((())()(");



        BufferedReader br = new BufferedReader(new StringReader(stringBuilder.toString()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        String symbol_1 = "(";
        String symbol_2 = ")";

        ArrayList<String> answer = new ArrayList<>();
        String s;
        while ((s = br.readLine()) != null) {
            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                map.put(String.valueOf(s.charAt(i)), map.getOrDefault(String.valueOf(s.charAt(i)), 0) + 1);
            }

            if (map.get(symbol_1) == map.get(symbol_2)) {
                answer.add("YES");
            } else {
                answer.add("NO");
            }
        }
        //System.out.println(String.join("\n", answer));

        bw.write(String.join("\n", answer));
        br.close();

        // 최종 출력
        bw.flush();
        bw.close();
    }
}
