package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class backJonn10026 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("5" + lc);
        ex.add("RRRBB" + lc);
        ex.add("GGBBB" + lc);
        ex.add("BBBRR" + lc);
        ex.add("BBRRR" + lc);
        ex.add("RRRRR");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int size = Integer.parseInt(br.readLine());

        String[][] visibleMap = new String[size][size];
        String[][] invisibleMap = new String[size][size];

        for (int h = 0; h < size; h++) {
            String color = br.readLine();
            for (int w = 0; w < size; w++) {
                visibleMap[h][w] = String.valueOf(color.charAt(w));
                invisibleMap[h][w] = String.valueOf(color.charAt(w));

                if (String.valueOf(color.charAt(w)).equals("G")) {
                    invisibleMap[h][w] = "R";
                }
            }
        }

        HashMap<String, Integer> visiable = bfs(visibleMap);
        HashMap<String, Integer> invisiable = bfs(invisibleMap);

        Integer sum1 = visiable.keySet().stream()
                .map(visiable::get)
                .reduce(0, Integer::sum);

        Integer sum2 = invisiable.keySet().stream()
                .map(invisiable::get)
                .reduce(0, Integer::sum);

        System.out.println(sum1 + " " + sum2);
    }

    private static HashMap<String, Integer> bfs(String[][] map) {
        int size = map.length;
        boolean[][] visited = new boolean[size][size];
        HashMap<String, Integer> colorMap = initColorMap();

        for (int h = 0; h < size; h++) {
            for (int w = 0; w < size; w++) {
                if (!visited[h][w]) {
                    Deque<Pos> deque = new ArrayDeque<>();
                    deque.add(new Pos(h, w));
                    visited[h][w] = true;

                    String c = map[h][w];
                    colorMap.put(c, colorMap.get(c) + 1);

                    while (!deque.isEmpty()) {
                        Pos pos = deque.pollFirst();
                        int height = pos.getH();
                        int width = pos.getW();

                        // 동서남북 순으로
                        if (width+1 < size && !visited[height][width+1] && map[height][width+1].equals(c)) {
                            deque.add(new Pos(height, width+1));
                            visited[height][width+1] = true;
                        }

                        if (-1 < width-1 && !visited[height][width-1] && map[height][width-1].equals(c)) {
                            deque.add(new Pos(height, width-1));
                            visited[height][width-1] = true;
                        }

                        if (height+1 < size && !visited[height+1][width] && map[height+1][width].equals(c)) {
                            deque.add(new Pos(height+1, width));
                            visited[height+1][width] = true;
                        }

                        if (-1 < height-1 && !visited[height-1][width] && map[height-1][width].equals(c)) {
                            deque.add(new Pos(height-1, width));
                            visited[height-1][width] = true;
                        }
                    }
                }
            }
        }

        return colorMap;
    }

    private static HashMap<String, Integer> initColorMap() {
        HashMap<String, Integer> colorMap = new HashMap<>();
        colorMap.put("R", 0);
        colorMap.put("B", 0);
        colorMap.put("G", 0);

        return colorMap;
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
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", w=" + w +
                    '}';
        }
    }
}