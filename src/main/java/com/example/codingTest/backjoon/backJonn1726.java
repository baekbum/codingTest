package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.function.BiFunction;

public class backJonn1726 {

    private static HashMap<Integer, HashMap<Integer, Integer>> cntMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("5 6" + lc);
        ex.add("0 0 0 0 0 0" + lc);
        ex.add("0 1 1 0 1 0" + lc);
        ex.add("0 1 0 0 0 0" + lc);
        ex.add("0 0 1 1 1 0" + lc);
        ex.add("1 0 0 0 0 0" + lc);
        ex.add("4 2 3" + lc);
        ex.add("2 4 1");

//        ex.add("5 6" + lc);
//        ex.add("1 0 0 0 0 0" + lc);
//        ex.add("0 0 1 0 1 0" + lc);
//        ex.add("0 1 0 0 0 0" + lc);
//        ex.add("0 0 1 1 1 0" + lc);
//        ex.add("1 0 0 0 0 0" + lc);
//        ex.add("4 2 3" + lc);
//        ex.add("2 4 1");

//        ex.add("5 5" + lc);
//        ex.add("0 0 0 0 0" + lc);
//        ex.add("1 1 1 1 1" + lc);
//        ex.add("0 1 0 0 0" + lc);
//        ex.add("0 0 1 1 1" + lc);
//        ex.add("1 0 0 0 0" + lc);
//        ex.add("1 1 1" + lc);
//        ex.add("1 5 2");

//        ex.add("5 6" + lc);
//        ex.add("0 0 0 0 0 0" + lc);
//        ex.add("0 1 1 0 1 0" + lc);
//        ex.add("1 1 0 0 0 0" + lc);
//        ex.add("0 0 1 1 1 0" + lc);
//        ex.add("1 0 0 0 0 0" + lc);
//        ex.add("4 2 3" + lc);
//        ex.add("2 4 1");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        int height = Integer.parseInt(size[0]);
        int width = Integer.parseInt(size[1]);

        int[][] map = new int[height][width];
        int[][][] visit = new int[map.length][map[0].length][4];

        for (int i = 0; i < height; i++) {
            String[] data = br.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(data[j]);
            }
        }

        String[] r = br.readLine().split(" ");
        String[] d = br.readLine().split(" ");

        Thing robot = new Thing(Integer.parseInt(r[0])-1, Integer.parseInt(r[1])-1, Integer.parseInt(r[2]), 0);
        Thing destination = new Thing(Integer.parseInt(d[0])-1, Integer.parseInt(d[1])-1, Integer.parseInt(d[2]), null);

        ArrayList<Integer> answer = bfs(map, visit, robot, destination);
        System.out.println(answer.stream().mapToInt(v -> v).min().getAsInt());
    }

    private static ArrayList<Integer> bfs(int[][] map, int[][][] visit , Thing r, Thing destination) {
        initCntMap();
        ArrayList<Integer> answer = new ArrayList<>();
        Deque<Thing> deque = new ArrayDeque<>();
        deque.add(r);

        while (!deque.isEmpty()) {
            Thing robot = deque.pollFirst();
            int h = robot.getH();
            int w = robot.getW();
            int d = robot.getD();

            // 도착했을때
            if (h == destination.getH() && w == destination.getW()) {
                answer.add(d == destination.getD() ? robot.getCnt() : robot.getCnt() + cntMap.get(d).get(destination.getD()));
            }

            // 동서남북
            available(map, visit, deque, robot, 1);
            available(map, visit, deque, robot, 2);
            available(map, visit, deque, robot, 3);
            available(map, visit, deque, robot, 4);
        }

        return answer;
    }

    private static void available(int[][] map, int[][][] visit, Deque<Thing> deque, Thing robot, int command) {
        int h = robot.getH();
        int w = robot.getW();
        int d = robot.getD();
        int cnt = robot.getCnt();

        if (robot.getD() != command) cnt += turn(d, command);

        // 동서남북
        switch (command) {
            case 1:
                for (int i = 1; i < 4; i++) {
                    if (map[0].length-1 < w+i || map[h][w+i] == 1) break;
                    if (visit[h][w+i][d-1] == 0) {
                        visit[h][w+i][d-1] = cnt+1;
                        deque.add(new Thing(h, w+i, 1, cnt+1));
                    } else {
                        if (visit[h][w+i][d-1] > cnt+1) {
                            visit[h][w+i][d-1] = cnt+1;
                            deque.add(new Thing(h, w+i, 1, cnt+1));
                        }
                    }
                }
                break;
            case 2:
                for (int i = 1; i < 4; i++) {
                    if (w-i < 0 || map[h][w-i] == 1) break;
                    if (visit[h][w-i][d-1] == 0) {
                        visit[h][w-i][d-1] = cnt+1;
                        deque.add(new Thing(h, w-i, 2, cnt+1));
                    } else {
                        if (visit[h][w-i][d-1] > cnt+1) {
                            visit[h][w-i][d-1] = cnt+1;
                            deque.add(new Thing(h, w-i, 2, cnt+1));
                        }
                    }
                }
                break;
            case 3:
                for (int i = 1; i < 4; i++) {
                    if (map.length-1 < h+i || map[h+i][w] == 1) break;
                    if (visit[h+i][w][d-1] == 0) {
                        visit[h+i][w][d-1] = cnt+1;
                        deque.add(new Thing(h+i, w, 3, cnt+1));
                    } else {
                        if (visit[h+i][w][d-1] > cnt+1) {
                            visit[h+i][w][d-1] = cnt+1;
                            deque.add(new Thing(h+i, w, 3, cnt+1));
                        }
                    }
                }
                break;
            case 4:
                for (int i = 1; i < 4; i++) {
                    if (h-i < 0 || map[h-i][w] == 1) break;
                    if (visit[h-i][w][d-1] == 0) {
                        visit[h-i][w][d-1] = cnt+1;
                        deque.add(new Thing(h-i, w, 4, cnt+1));
                    } else {
                        if (visit[h-i][w][d-1] > cnt+1) {
                            visit[h-i][w][d-1] = cnt+1;
                            deque.add(new Thing(h-i, w, 4, cnt+1));
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private static int turn(int current, int point) {
        return cntMap.get(current).get(point);
    }

    private static void initCntMap() {
        // 동서남북
        cntMap.put(1, new HashMap<>() {{ put(1, 0); put(2, 2); put(3, 1); put(4, 1);}});
        cntMap.put(2, new HashMap<>() {{ put(1, 2); put(2, 0); put(3, 1); put(4, 1);}});
        cntMap.put(3, new HashMap<>() {{ put(1, 1); put(2, 1); put(3, 0); put(4, 2);}});
        cntMap.put(4, new HashMap<>() {{ put(1, 1); put(2, 1); put(3, 2); put(4, 0);}});
    }

    private static class Thing {
        int h;
        int w;
        int d;
        Integer cnt;

        public Thing(int h, int w, int d, Integer cnt) {
            this.h = h;
            this.w = w;
            this.d = d;
            this.cnt = cnt;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public int getD() {
            return d;
        }

        public Integer getCnt() {
            return cnt;
        }

        @Override
        public String toString() {
            return "Thing{" +
                    "h=" + h +
                    ", w=" + w +
                    ", d=" + d +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
