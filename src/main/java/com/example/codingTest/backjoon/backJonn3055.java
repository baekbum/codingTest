package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn3055 {
    private static int MAX_HEIGHT = 0;
    private static int MAX_WIDTH = 0;
    private static Pos destination;
    private static Pos departure;

    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("3 3" + lc);
//        ex.add("D.*" + lc);
//        ex.add("..." + lc);
//        ex.add(".S.");

//        ex.add("3 3" + lc);
//        ex.add("D.*" + lc);
//        ex.add("..." + lc);
//        ex.add("..S");

//        ex.add("3 6" + lc);
//        ex.add("D...*." + lc);
//        ex.add(".X.X.." + lc);
//        ex.add("....S.");

        ex.add("5 4" + lc);
        ex.add(".D.*" + lc);
        ex.add("...." + lc);
        ex.add("..X." + lc);
        ex.add("S.*." + lc);
        ex.add("....");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        String[] info = br.readLine().split(" ");
        MAX_HEIGHT = Integer.parseInt(info[0]);
        MAX_WIDTH = Integer.parseInt(info[1]);

        String[][] map = new String[MAX_HEIGHT][MAX_WIDTH];

        Deque<Pos> waterQueue = new ArrayDeque<>();

        int h = 0;
        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split("");

            for (int w = 0; w < MAX_WIDTH; w++) {
                map[h][w] = data[w];
                if (data[w].equals("D")) destination = new Pos(h, w, null);
                if (data[w].equals("S")) departure = new Pos(h, w, 0);
                if (data[w].equals("*")) waterQueue.add(new Pos(h, w, null));
            }
            h++;
        }

        int answer = bfs(map, waterQueue);

        System.out.println(answer != 0 ? answer : "KAKTUS");
    }

    private static int bfs(String[][] map, Deque<Pos> waterQueue) {
        Deque<Pos> hedgeQueue = new ArrayDeque<>();
        hedgeQueue.add(departure);

        System.out.println("init map");
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                sb1.append(map[i][j]).append(" ");
            }
            sb1.append("\n");
        }
        System.out.println(sb1);

        while (true) {
            ArrayList<Pos> hedgeList = new ArrayList<>();
            hedgeList.addAll(hedgeQueue);
            hedgeQueue.clear();

            if (hedgeList.isEmpty()) return 0;

            for (Pos hedge : hedgeList) {
                if (hedge.equals(destination)) return hedge.getCnt();
                if (!map[hedge.getH()][hedge.getW()].equals("S")) continue;

                if (-1 < hedge.getH() - 1) {
                    Pos newHedge = new Pos(hedge.getH() - 1, hedge.getW(), hedge.getCnt() + 1);
                    if (hedgeValidation(map, newHedge)) {
                        map[hedge.getH() - 1][hedge.getW()] = "S";
                        hedgeQueue.add(newHedge);
                    }
                }
                if (hedge.getH() + 1 < MAX_HEIGHT) {
                    Pos newHedge = new Pos(hedge.getH() + 1, hedge.getW(), hedge.getCnt() + 1);
                    if (hedgeValidation(map, newHedge)) {
                        map[hedge.getH() + 1][hedge.getW()] = "S";
                        hedgeQueue.add(newHedge);
                    }
                }
                if (-1 < hedge.getW() - 1) {
                    Pos newHedge = new Pos(hedge.getH(), hedge.getW() - 1, hedge.getCnt() + 1);
                    if (hedgeValidation(map, newHedge)) {
                        map[hedge.getH()][hedge.getW() - 1] = "S";
                        hedgeQueue.add(newHedge);
                    }
                }
                if (hedge.getW() + 1 < MAX_WIDTH) {
                    Pos newHedge = new Pos(hedge.getH(), hedge.getW() + 1, hedge.getCnt() + 1);
                    if (hedgeValidation(map, newHedge)) {
                        map[hedge.getH()][hedge.getW() + 1] = "S";
                        hedgeQueue.add(newHedge);
                    }
                }
            }

            System.out.println("hedge move");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < MAX_HEIGHT; i++) {
                for (int j = 0; j < MAX_WIDTH; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);

            ArrayList<Pos> waterList = new ArrayList<>();
            waterList.addAll(waterQueue);
            waterQueue.clear();

            for (Pos water : waterList) {
                if (-1 < water.getH()-1) {
                    Pos newWater = new Pos(water.getH()-1, water.getW(), null);
                    if (waterValidation(map, newWater)) {
                        map[water.getH()-1][water.getW()] = "*";
                        waterQueue.add(newWater);
                    }
                }
                if (water.getH()+1 < MAX_HEIGHT) {
                    Pos newWater = new Pos(water.getH()+1, water.getW(), null);
                    if (waterValidation(map, newWater)) {
                        map[water.getH()+1][water.getW()] = "*";
                        waterQueue.add(newWater);
                    }
                }
                if (-1 < water.getW()-1) {
                    Pos newWater = new Pos(water.getH(), water.getW()-1, null);
                    if (waterValidation(map, newWater)) {
                        map[water.getH()][water.getW()-1] = "*";
                        waterQueue.add(newWater);
                    }
                }
                if (water.getW()+1 < MAX_WIDTH) {
                    Pos newWater = new Pos(water.getH(), water.getW()+1, null);
                    if (waterValidation(map, newWater)) {
                        map[water.getH()][water.getW()+1] = "*";
                        waterQueue.add(newWater);
                    }
                }
            }

            System.out.println("water move");
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < MAX_HEIGHT; i++) {
                for (int j = 0; j < MAX_WIDTH; j++) {
                    sb2.append(map[i][j]).append(" ");
                }
                sb2.append("\n");
            }
            System.out.println(sb2);
            System.out.println("=============");
        }
    }

    private static boolean hedgeValidation(String[][] map, Pos hedge) {
        int h = hedge.getH();
        int w = hedge.getW();

        return map[h][w].equals(".") || map[h][w].equals("D");
    }

    private static boolean waterValidation(String[][] map, Pos water) {
        int h = water.getH();
        int w = water.getW();

        return map[h][w].equals(".") || map[h][w].equals("S");
    }


    private static class Pos {
        int h;
        int w;
        Integer cnt;

        public Pos(int h, int w, Integer cnt) {
            this.h = h;
            this.w = w;
            this.cnt = cnt;
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

        public Integer getCnt() {
            return cnt;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", w=" + w +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
