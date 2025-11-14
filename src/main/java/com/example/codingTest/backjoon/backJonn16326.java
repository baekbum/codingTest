package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn16326 {
    private static int MAX_SIZE;
    private static int[][] MAP;
    private static HashMap<Integer, ArrayList<Fish>> FISH_MAP;

    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("4" + lc);
//        ex.add("4 3 2 1" + lc);
//        ex.add("0 0 0 0" + lc);
//        ex.add("0 0 9 0" + lc);
//        ex.add("1 2 3 4");

        ex.add("9" + lc);
        ex.add("1 0 2 0 1 1 1 1 1" + lc);
        ex.add("2 1 0 0 1 1 2 1 1" + lc);
        ex.add("1 1 2 1 1 1 1 1 1" + lc);
        ex.add("1 3 1 1 4 1 0 3 1" + lc);
        ex.add("5 1 1 3 1 0 3 1 1" + lc);
        ex.add("0 3 1 1 3 1 1 3 0" + lc);
        ex.add("1 1 1 1 0 1 1 1 1" + lc);
        ex.add("1 1 1 1 1 1 1 1 9" + lc);
        ex.add("3 1 1 3 1 1 2 1 1");


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        MAX_SIZE = Integer.parseInt(br.readLine());

        MAP = new int[MAX_SIZE][MAX_SIZE];
        Shark shark = null;

        FISH_MAP = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            FISH_MAP.put(i, new ArrayList<>());
        }

        for (int h = 0; h < MAX_SIZE; h++) {
            String[] lines = br.readLine().split(" ");
            for (int w = 0; w < MAX_SIZE; w++) {
                MAP[h][w] = Integer.parseInt(lines[w]);

                switch (MAP[h][w]) {
                    case 1:
                        FISH_MAP.get(1).add(new Fish(h, w,  1,0));
                        break;
                    case 2:
                        FISH_MAP.get(2).add(new Fish(h, w,  2,0));
                        break;
                    case 3:
                        FISH_MAP.get(3).add(new Fish(h, w,  3,0));
                        break;
                    case 4:
                        FISH_MAP.get(4).add(new Fish(h, w,  4,0));
                        break;
                    case 5:
                        FISH_MAP.get(5).add(new Fish(h, w,  5,0));
                        break;
                    case 6:
                        FISH_MAP.get(6).add(new Fish(h, w,  6,0));
                        break;
                    case 9:
                        shark = new Shark(h, w, 2, 0, 0);
                        MAP[h][w] = 0;
                        break;
                    default:
                        break;
                }
            }
        }

        bfs(shark);
    }

    private static void bfs(Shark shark) {
        while (true) {
            Fish fish = findFish(shark);
            if (fish == null) break;
            //System.out.println("fish = " + fish);

            shark.eatFish(fish);
            MAP[shark.getH()][shark.getW()] = 0;
            FISH_MAP.get(fish.getS()).remove(fish);
        }

        System.out.println(shark.getT());
    }

    private static Fish findFish(Shark shark) {
        PriorityQueue<Fish> fishQueue = getFishQueue();

        // 아기 상어가 먹을 수 있는 먹이 좌표 구하기
        for (int i = 1; i < shark.getS() && i < 7; i++) {
            for (Fish fish : FISH_MAP.get(i)) {
                // 타겟 구하기
                Deque<Shark> deque = new ArrayDeque<>();
                boolean[][] visited = new boolean[MAX_SIZE][MAX_SIZE];
                deque.add(new Shark(shark.getH(), shark.getW(), shark.getS(), 0 ,0));
                visited[shark.getH()][shark.getW()] = true;

                while (!deque.isEmpty()) {
                    Shark s = deque.pollFirst();
                    int h = s.getH();
                    int w = s.getW();
                    int t = s.getT();

                    if (s.getH() == fish.getH() && s.getW() == fish.getW()) {
                        fishQueue.add(new Fish(fish.getH(), fish.getW(), fish.getS(), t));
                        break;
                    }

                    // 상좌하우
                    if (validation(h-1, w) && !visited[h-1][w] && MAP[h-1][w] <= s.getS()) {
                        deque.add(new Shark(h-1, w, s.getS(), s.getC(), s.getT() + 1));
                        visited[h-1][w] = true;
                    }

                    if (validation(h, w-1) && !visited[h][w-1] && MAP[h][w-1] <= s.getS()) {
                        deque.add(new Shark(h, w-1, s.getS(), s.getC(), s.getT() + 1));
                        visited[h][w-1] = true;
                    }

                    if (validation(h+1, w) && !visited[h+1][w] && MAP[h+1][w] <= s.getS()) {
                        deque.add(new Shark(h+1, w, s.getS(), s.getC(), s.getT() + 1));
                        visited[h+1][w] = true;
                    }

                    if (validation(h, w+1) && !visited[h][w+1] && MAP[h][w+1] <= s.getS()) {
                        deque.add(new Shark(h, w+1, s.getS(), s.getC(), s.getT() + 1));
                        visited[h][w+1] = true;
                    }
                }
            }
        }

        return fishQueue.poll();
    }

    private static PriorityQueue<Fish> getFishQueue() {
        return new PriorityQueue<>((f1, f2) -> {
            // 1. 거리가 가까운 물고기 우선 (최소값 우선)
            if (f1.getD() != f2.getD()) {
                return f1.getD() - f2.getD();
            }

            // 2. 거리가 같다면, 가장 위에 있는 물고기 우선 (height 최소값 우선)
            if (f1.getH() != f2.getH()) {
                return f1.getH() - f2.getH();
            }

            // 3. 거리와 height가 같다면, 가장 왼쪽에 있는 물고기 우선 (width 최소값 우선)
            return f1.getW() - f2.getW();
        });
    }

    private static boolean validation(int h, int w) {
        return -1 < h
                && h < MAX_SIZE
                && -1 < w
                && w < MAX_SIZE;
    }

    private static class Shark {
        int h; // height
        int w; // width
        int s; // 크기
        int c; // 누적
        int t; // 시간

        public Shark(int h, int w, int s, int c, int t) {
            this.h = h;
            this.w = w;
            this.s = s;
            this.c = c;
            this.t = t;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public int getS() {
            return s;
        }

        public int getC() {
            return c;
        }

        public int getT() {
            return t;
        }

        public void eatFish(Fish fish) {
            this.h = fish.getH();
            this.w = fish.getW();

            this.c++;
            if (this.c == this.s) {
                this.s++;
                this.c = 0;
            }

            this.t += fish.getD();
        }
    }

    private static class Fish {
        int h;
        int w;
        int s;
        int d;

        public Fish(int h, int w, int s, int d) {
            this.h = h;
            this.w = w;
            this.s = s;
            this.d = d;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public int getS() {
            return s;
        }

        public int getD() {
            return d;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Fish fish = (Fish) o;
            return h == fish.h && w == fish.w;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "h=" + h +
                    ", w=" + w +
                    ", s=" + s +
                    '}';
        }
    }
}