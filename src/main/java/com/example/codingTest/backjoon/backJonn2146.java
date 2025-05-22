package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn2146 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";

        ArrayList<String> ex = new ArrayList<>();

        ex.add("10" + lc);
        ex.add("1 1 1 0 0 0 0 1 1 1" + lc);
        ex.add("1 1 1 1 0 0 0 0 1 1" + lc);
        ex.add("1 0 1 1 0 0 0 0 1 1" + lc);
        ex.add("0 0 1 1 1 0 0 0 0 1" + lc);
        ex.add("0 0 0 1 0 0 0 0 0 1" + lc);
        ex.add("0 0 0 0 0 0 0 0 0 1" + lc);
        ex.add("0 0 0 0 0 0 0 0 0 0" + lc);
        ex.add("0 0 0 0 1 1 0 0 0 0" + lc);
        ex.add("0 0 0 0 1 1 1 0 0 0" + lc);
        ex.add("0 0 0 0 0 0 0 0 0 0");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());
        int[][] map =  new int[size][size];

        for (int h = 0; h < size; h++) {
            String[] data = br.readLine().split(" ");
            for (int w = 0; w < size; w++) {
                map[h][w] = Integer.parseInt(data[w]);
            }
        }

        List<ArrayList<Ground>> lands = initLand(map);

        int min = size * size;

        for (int i = 0; i < lands.size()-1; i++) {
            ArrayList<Ground> current = lands.get(i);

            for (int j = i+1; j < lands.size(); j++) {
                ArrayList<Ground> next = lands.get(j);

                for (Ground c : current) {
                    for (Ground n : next) {
                        int h = Math.abs(c.getH() - n.getH());
                        int w = Math.abs(c.getW() - n.getW());

//                        if (min > h+w) {
//                            System.out.println("c.getH() = " + c.getH() + ", n.getH() = " + n.getH());
//                            System.out.println("c.getW() = " + c.getW() + ", n.getW() = " + n.getW());
//                        }

                        min = Math.min(min, h + w);
                    }
                }
            }
        }

        System.out.println(min - 1);


//        StringBuilder sb = new StringBuilder();
//        for (int h = 0; h < size; h++) {
//            for (int w = 0; w < size; w++) {
//                sb.append(map[h][w]).append(" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
    }

    private static List<ArrayList<Ground>> initLand(int[][] map) {
        List<ArrayList<Ground>> list = new ArrayList<>();
        boolean[][] visit = new boolean[map.length][map[0].length];
        int cnt = 0;

        for (int h = 0; h < map.length; h++) {
            for (int w = 0; w < map[0].length; w++) {
                if (map[h][w] == 1 && !visit[h][w] ) {
                    ArrayList<Ground> grounds = new ArrayList<>();
                    Deque<Ground> deque = new ArrayDeque<>();
                    deque.add(new Ground(h, w));

                    while (!deque.isEmpty()) {
                        Ground ground = deque.pollFirst();
                        int height = ground.getH();
                        int width = ground.getW();

                        if (!visit[height][width]) {
                            grounds.add(ground);
                            visit[height][width] = true;

                            if (-1 < height-1 && map[height-1][width] == 1) {
                                deque.add(new Ground(height-1, width));
                            }

                            if (height+1 < map.length && map[height+1][width] == 1) {
                                deque.add(new Ground(height+1, width));
                            }

                            if (-1 < width-1 && map[height][width-1] == 1) {
                                deque.add(new Ground(height, width-1));
                            }

                            if (width+1 < map[0].length && map[height][width+1] == 1) {
                                deque.add(new Ground(height, width+1));
                            }
                        }
                    }

                    if (!grounds.isEmpty()) list.add(grounds);
                }
            }
        }

        return list;
    }

    private static class Ground {
        int h;
        int w;

        public Ground(int h, int w) {
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

            Ground ground = (Ground) o;
            return h == ground.h && w == ground.w;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }
    }
}
