package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 첫째 줄에 두 정수 N, M이 주어진다. 다음 M개의 줄에는 N개의 정수로 벽에 대한 정보가 주어진다.
 * 벽에 대한 정보는 한 정수로 주어지는데,
 * 서쪽에 벽이 있을 때는 1을,
 * 북쪽에 벽이 있을 때는 2를,
 * 동쪽에 벽이 있을 때는 4를,
 * 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다. 참고로 이진수의 각 비트를 생각하면 쉽다. 따라서 이 값은 0부터 15까지의 범위 안에 있다.
 */
public class backJonn2234 {
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("7 4" + lc);
//        ex.add("11 6 11 6 3 10 6" + lc);
//        ex.add("7 9 6 13 5 15 5" + lc);
//        ex.add("1 10 12 7 13 7 5" + lc);
//        ex.add("13 11 10 8 10 12 13");

        ex.add("4 1" + lc);
        ex.add("15 15 15 15");


//        ex.add("7 4" + lc);
//        ex.add("15 15 15 15 15 15 15" + lc);
//        ex.add("15 15 15 15 15 15 15" + lc);
//        ex.add("15 15 15 15 15 15 15" + lc);
//        ex.add("15 15 15 15 15 15 15");

//        ex.add("7 4" + lc);
//        ex.add("3 2 2 2 2 2 6" + lc);
//        ex.add("1 0 0 0 0 0 4" + lc);
//        ex.add("1 0 0 0 0 0 4" + lc);
//        ex.add("9 8 8 8 8 8 12");

//        ex.add("7 3" + lc);
//        ex.add("11 10 10 10 10 10 6" + lc);
//        ex.add("15 15 15 15 15 15 13" + lc);
//        ex.add("11 10 10 10 10 10 14");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        int height = Integer.parseInt(size[1]);
        int width = Integer.parseInt(size[0]);

        int[][] walls = new int[height][width];
        int[][] valueArr = new int[height][width];

        int h = 0;
        String s;
        while ((s = br.readLine()) != null) {
            String[] data = s.split(" ");

            for (int w = 0; w < width; w++) {
                walls[h][w] = Integer.parseInt(data[w]);
            }
            h++;
        }

        dfs(walls, valueArr);
    }

    private static void dfs(int[][] walls, int[][] valueArr) {

        HashSet<Pos> visit = new HashSet<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for (int h = 0; h < walls.length; h++) {
            for (int w = 0; w < walls[0].length; w++) {
                Pos start = new Pos(h, w);
                if (visit.contains(start)) continue;

                ArrayList<Pos> list = new ArrayList<>();
                Deque<Pos> deque = new ArrayDeque<>();
                deque.add(start);

                int cnt = 0;

                while (!deque.isEmpty()) {
                    Pos pos = deque.pollFirst();
                    int height = pos.getH();
                    int width = pos.getX();

                    if (!visit.contains(pos)) {
                        cnt++;
                        visit.add(pos);
                        list.add(pos);
                        int value = walls[height][width];
                        initWall(pos, value);

                        //System.out.println("height = " + height + ", width = " + width + ", value = " + value);

                        if (!pos.isN() && -1 < height-1) deque.add(new Pos(height-1, width));
                        if (!pos.isS() && height+1 < walls.length) deque.add(new Pos(height+1, width));
                        if (!pos.isW() && -1 < width-1) deque.add(new Pos(height, width-1));
                        if (!pos.isE() && width+1 < walls[0].length) deque.add(new Pos(height, width+1));
                    }
                }

                answer.add(cnt);

                int finalCnt = cnt;
                list.stream().forEach(p -> {
                    valueArr[p.getH()][p.getX()] = finalCnt;
                });
                //System.out.println("====================================");
            }
        }

        System.out.println(answer.size());
        System.out.println(answer.stream().mapToInt(Integer::intValue).max().getAsInt());

        int maxCnt = valueArr[0][0];

        for (int h = 0; h < valueArr.length; h++) {
            for (int w = 0; w < valueArr[0].length; w++) {
                // 상하좌우 비교
                if (-1 < h-1 && valueArr[h][w] != valueArr[h-1][w]) {
                    maxCnt = Math.max(valueArr[h][w] + valueArr[h-1][w], maxCnt);
                }

                if (h+1 < valueArr.length && valueArr[h][w] != valueArr[h+1][w]) {
                    maxCnt = Math.max(valueArr[h][w] + valueArr[h+1][w], maxCnt);
                }

                if (-1 < w-1 && valueArr[h][w] != valueArr[h][w-1]) {
                    maxCnt = Math.max(valueArr[h][w] + valueArr[h][w-1], maxCnt);
                }

                if (w+1 < valueArr[0].length && valueArr[h][w] != valueArr[h][w+1]) {
                    maxCnt = Math.max(valueArr[h][w] + valueArr[h][w+1], maxCnt);
                }
            }
        }

        System.out.println(valueArr[0][0] == maxCnt ? maxCnt + maxCnt : maxCnt);

//        StringBuilder sb = new StringBuilder();
//        for (int h = 0; h < valueArr.length; h++) {
//            for (int w = 0; w < valueArr[0].length; w++) {
//                sb.append(valueArr[h][w]).append(" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
    }

    private static void initWall(Pos pos, int value) {
        switch (value) {
            case 1:
                pos.setW(true);
                break;
            case 2:
                pos.setN(true);
                break;
            case 3:
                pos.setW(true);
                pos.setN(true);
                break;
            case 4:
                pos.setE(true);
                break;
            case 5:
                pos.setW(true);
                pos.setE(true);
                break;
            case 6:
                pos.setN(true);
                pos.setE(true);
                break;
            case 7:
                pos.setW(true);
                pos.setN(true);
                pos.setE(true);
                break;
            case 8:
                pos.setS(true);
                break;
            case 9:
                pos.setW(true);
                pos.setS(true);
                break;
            case 10:
                pos.setN(true);
                pos.setS(true);
                break;
            case 11:
                pos.setW(true);
                pos.setN(true);
                pos.setS(true);
                break;
            case 12:
                pos.setE(true);
                pos.setS(true);
                break;
            case 13:
                pos.setW(true);
                pos.setE(true);
                pos.setS(true);
                break;
            case 14:
                pos.setN(true);
                pos.setE(true);
                pos.setS(true);
                break;
            case 15:
                pos.setW(true);
                pos.setN(true);
                pos.setE(true);
                pos.setS(true);
                break;
            default:
                break;
        }
    }

    private static class Pos {
        int h;
        int x;
        boolean n;
        boolean s;
        boolean w;
        boolean e;

        public Pos(int h, int x) {
            this.h = h;
            this.x = x;
        }

        public int getH() {
            return h;
        }

        public int getX() {
            return x;
        }

        public boolean isN() {
            return n;
        }

        public void setN(boolean n) {
            this.n = n;
        }

        public boolean isS() {
            return s;
        }

        public void setS(boolean s) {
            this.s = s;
        }

        public boolean isW() {
            return w;
        }

        public void setW(boolean w) {
            this.w = w;
        }

        public boolean isE() {
            return e;
        }

        public void setE(boolean e) {
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Pos pos = (Pos) o;
            return h == pos.h && x == pos.x;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + x;
            return result;
        }
    }
}
