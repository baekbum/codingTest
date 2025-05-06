package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn1525 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();
        ex.add("1 0 3" + lc);
        ex.add("4 2 5" + lc);
        ex.add("7 8 6");

//        ex.add("3 6 0" + lc);
//        ex.add("8 1 2" + lc);
//        ex.add("7 4 5");

//        ex.add("1 2 3" + lc);
//        ex.add("4 5 0" + lc);
//        ex.add("6 7 8");


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        StringBuilder sb  = new StringBuilder();

        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");
            for (int i = 0; i < data.length; i++) {
                sb.append(data[i]);
            }
        }

        System.out.println(bfs(sb.toString()));
    }

    public static int bfs(String init) {
        String correct = "123456780";
        int[] move = new int[]{-3, +3, -1, +1}; // 상하좌우
        HashMap<String, Integer> exist = new HashMap<>();
        exist.put(init, 0);

        Deque<String> deque = new ArrayDeque<>();
        deque.add(init);

        while (!deque.isEmpty()) {
            String number = deque.pollFirst();
            int zero = number.indexOf('0');

            // 정렬 완료 되면 종료
            if (correct.equals(number))
                return exist.get(number);


            for (int m : move) {
                int pos = zero + m;

                // 범위 안에 존재할 때만 실행
                if (0 <= pos && pos <= 8) {
                    char[] charArray = number.toCharArray();
                    charArray[zero] = charArray[pos];
                    charArray[pos] = '0';

                    String newNumber = new String(charArray);

                    if (!exist.containsKey(newNumber)) {
                        deque.add(newNumber);
                        exist.put(newNumber, exist.get(number)+1);
                    }
                }
            }
        }

        return -1;
    }
}
