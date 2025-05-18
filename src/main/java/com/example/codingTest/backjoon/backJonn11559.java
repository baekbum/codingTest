package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
 * 이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
 * R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
 */
public class backJonn11559 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add("......" + lc);
        ex.add(".Y...." + lc);
        ex.add(".YG..." + lc);
        ex.add("RRYG.." + lc);
        ex.add("RRYGG.");

//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add("......" + lc);
//        ex.add(".YG..." + lc);
//        ex.add("R.YG.." + lc);
//        ex.add("RRYG..");

//        ex.add("......" + lc);
//        ex.add("..YY.." + lc);
//        ex.add(".YGB.." + lc);
//        ex.add(".BGY.." + lc);
//        ex.add(".BGY.." + lc);
//        ex.add(".BGY.." + lc);
//        ex.add(".GBY.." + lc);
//        ex.add(".GBBY." + lc);
//        ex.add(".BGGG." + lc);
//        ex.add(".YGGY." + lc);
//        ex.add(".YGGY." + lc);
//        ex.add(".YBBB.");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[][] puyo = new String[12][6];

        int h = 0;
        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split("");

            for (int w = 0; w < 6; w++) {
                puyo[h][w] = data[w];
            }
            h++;
        }

        System.out.println(bfs(puyo));
        //bfs(puyo);
    }

    private static int bfs(String[][] puyo) {
        int cnt = 0;

        while (true) {
            System.out.println("screen");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < puyo.length; i++) {
                for (int j = 0; j < puyo[0].length; j++) {
                    sb.append(puyo[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);

            Deque<Pos> deque = new ArrayDeque<>();
            ArrayList<Pos> list = new ArrayList<>();

            for (int height = 0; height < puyo.length; height++) {
                deque.clear();
                for (int width = 0; width < puyo[0].length; width++) {
                    String value = puyo[height][width];
                    Pos pos = new Pos(height, width);

                    if (list.contains(pos)) continue;

                    if (!value.equals(".")) {
                        ArrayList<Pos> stack = new ArrayList<>();
                        deque.add(pos);

                        while (!deque.isEmpty()) {
                            Pos p = deque.pollFirst();

                            if (!stack.contains(p)) {
                                stack.add(p);

                                int h = p.getH();
                                int w = p.getW();
                                String current = puyo[h][w];

                                if (-1 < h - 1) {
                                    if (current.equals(puyo[h - 1][w]))
                                        add(deque, puyo, h - 1, w);
                                }
                                if (h + 1 < 12) {
                                    if (current.equals(puyo[h + 1][w]))
                                        add(deque, puyo, h + 1, w);
                                }
                                if (-1 < w - 1) {
                                    if (current.equals(puyo[h][w - 1]))
                                        add(deque, puyo, h, w - 1);
                                }
                                if (w + 1 < 6) {
                                    if (current.equals(puyo[h][w + 1]))
                                        add(deque, puyo, h, w + 1);
                                }
                            }
                        }

                        if (4 <= stack.size()) {
                            list.addAll(stack);
                        }
                    }
                }
            }

            if (list.isEmpty()) return cnt;

            // 터트리는 작업
            list.stream().forEach(p -> {
                puyo[p.getH()][p.getW()] = ".";
            });

            cnt++;

            System.out.println("boom");
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < puyo.length; i++) {
                for (int j = 0; j < puyo[0].length; j++) {
                    sb1.append(puyo[i][j]).append(" ");
                }
                sb1.append("\n");
            }
            System.out.println(sb1);

            // 남은 알파벳을 내리는 작업
            for (int w = 0; w < puyo[0].length; w++) {
                int h = 11;
                int dot = 0;

                while(-1 < h) {
                    if (puyo[h][w].equals(".")) {
                        dot++;
                    } else {
                        if (dot != 0 && h+dot < puyo.length) {
                            puyo[h+dot][w] = puyo[h][w];
                            puyo[h][w] = ".";
                        }
                    }
                    h--;
                }
            }


        }
    }

    private static HashMap<Integer, Integer> initMap() {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            countMap.put(i, 0);
        }
        return countMap;
    }

    private static void add(Deque<Pos> deque, String[][] puyo, int h, int w) {
        Pos newPos = new Pos(h, w);
        deque.add(newPos);
    }

    private static class Pos {
        int h;
        int w;

        public Pos(int h, int w) {
            this.h = h;
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Pos pos = (Pos) o;
            return h == pos.h && w == pos.w;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }
    }
}
