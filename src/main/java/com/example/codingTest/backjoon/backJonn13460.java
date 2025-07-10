package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class backJonn13460 {
    private static int HEIGHT;
    private static int WIDTH;
    private static String[][] MAP;
    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("5 5" + lc);
//        ex.add("#####" + lc);
//        ex.add("#..B#" + lc);
//        ex.add("#.#.#" + lc);
//        ex.add("#RO.#" + lc);
//        ex.add("#####");

//        ex.add("7 7" + lc);
//        ex.add("#######" + lc);
//        ex.add("#...RB#" + lc);
//        ex.add("#.#####" + lc);
//        ex.add("#.....#" + lc);
//        ex.add("#####.#" + lc);
//        ex.add("#O....#" + lc);
//        ex.add("#######");

        ex.add("6 4" + lc);
        ex.add("####" + lc);
        ex.add("#.O#" + lc);
        ex.add("#.R#" + lc);
        ex.add("#..#" + lc);
        ex.add("#.B#" + lc);
        ex.add("####");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        String[] size = br.readLine().split(" ");
        HEIGHT = Integer.parseInt(size[0]);
        WIDTH = Integer.parseInt(size[1]);

        MAP = new String[HEIGHT][WIDTH];

        Pos red = null;
        Pos blue = null;

        for (int i = 0; i < HEIGHT; i++) {
            String data = br.readLine();
            for (int j = 0; j < WIDTH; j++) {
                String value = String.valueOf(data.charAt(j));

                if (value.equals("R")) red = new Pos(i, j, "R");
                if (value.equals("B")) blue = new Pos(i, j, "B");

                if (value.equals("R") || value.equals("B")) value = ".";

                MAP[i][j] = value;
            }
        }

//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < HEIGHT; i++) {
//            for (int j = 0; j < WIDTH; j++) {
//                sb.append(MAP[i][j]).append(" ");
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);

        System.out.println(bfs(red, blue));
    }

    private static int bfs(Pos red, Pos blue) {
        Deque<Node> deque = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();
        deque.add(new Node(red, blue, 0));
        visited.add(new Node(red, blue, 0));

        //동서남북으로 이동
        while (!deque.isEmpty()) {
            Node node = deque.pollFirst();
            Pos r = node.getRed();
            Pos b = node.getBlue();
            int cnt = node.getCnt();

            int result = 0;

            Node east = moveBall(r, b, cnt, "E");
            result = stateCheckAndAddQueue(visited, deque, east);
            if (0 < result) return 10 < result ? -1 : result;

            Node west = moveBall(r, b, cnt, "W");
            result = stateCheckAndAddQueue(visited, deque, west);
            if (0 < result) return 10 < result ? -1 : result;

            Node south = moveBall(r, b, cnt, "S");
            result = stateCheckAndAddQueue(visited, deque, south);
            if (0 < result) return 10 < result ? -1 : result;

            Node north = moveBall(r, b, cnt, "N");
            result = stateCheckAndAddQueue(visited, deque, north);
            if (0 < result) return 10 < result ? -1 : result;
        }

        return -1;
    }

    private static int stateCheckAndAddQueue(HashSet<Node> visited, Deque<Node> deque, Node node) {
        Pos red = node.getRed();
        Pos blue = node.getBlue();

        if (isDestination(red.getH(), red.getW()) && isDestination(blue.getH(), blue.getW())) {
            // 둘 다 홀인한 상태 (실패) 큐에 추가 X
            visited.add(node);
            return -1;
        } else if (!isDestination(red.getH(), red.getW()) && !isDestination(blue.getH(), blue.getW())) {
            // 둘 다 홀인하지 않은 상태 (진행) 큐에 추가 O
            if (!visited.contains(node)) {
                visited.add(node);
                deque.add(node);
            }
            return 0;
        } else if (!isDestination(red.getH(), red.getW()) && isDestination(blue.getH(), blue.getW())) {
            // 파란색만 홀인한 상태 (실패) 큐에 추가 X
            visited.add(node);
            return -1;
        } else {
            // 빨간색만 홀인한 상태 (성공) 카운트 반환
            return node.getCnt();
        }
    }

    private static Node moveBall(Pos red, Pos blue, int moveCnt, String direction) {
        Pos first = null;
        Pos second = null;

        Deque<Pos> order = setOrder(red, blue, direction);
        first = order.pollFirst();
        second = order.pollFirst();
        boolean holeIn = false;

        while (true) {
            int h = first.getH();
            int w = first.getW();
            String c = first.getColor();

            if (direction.equals("E")) {
                if (!moveValidation(h, w+1)) break;
                w++;
            } else if (direction.equals("W")) {
                if (!moveValidation(h, w-1)) break;
                w--;
            } else if (direction.equals("S")) {
                if (!moveValidation(h+1, w)) break;
                h++;
            } else if (direction.equals("N")) {
                if (!moveValidation(h-1, w)) break;
                h--;
            }

            first = new Pos(h, w, c);

            if (isDestination(h, w)) {
                holeIn = true;
                break;
            }
        }

        while (true) {
            int h = second.getH();
            int w = second.getW();
            String c = second.getColor();

            if (direction.equals("E")) {
                if (!moveValidation(h, w+1)) break;
                if (!holeIn && first.isSameHeight(h) && first.isSameWidth(w+1)) break; // 먼저 움직이 공이 홀인 상태가 아닌 경우에만 내 바로 앞에 공이 있는지 체크함

                w++;
            } else if (direction.equals("W")) {
                if (!moveValidation(h, w-1)) break;
                if (!holeIn && first.isSameHeight(h) && first.isSameWidth(w-1)) break;

                w--;
            } else if (direction.equals("S")) {
                if (!moveValidation(h+1, w)) break;
                if (!holeIn && first.isSameHeight(h+1) && first.isSameWidth(w)) break;

                h++;
            } else if (direction.equals("N")) {
                if (!moveValidation(h-1, w)) break;
                if (!holeIn && first.isSameHeight(h-1) && first.isSameWidth(w)) break;

                h--;
            }

            second = new Pos(h, w, c);

            if (isDestination(h, w)) break;
        }

        Pos newRed = first.getColor().equals("R") ? first : second;
        Pos newBlue = second.getColor().equals("B") ? second : first;

        return new Node(newRed, newBlue, moveCnt+1);
    }

    private static Deque<Pos> setOrder(Pos red, Pos blue, String direction) {
        Deque<Pos> deque = new ArrayDeque<>();

        if (direction.equals("E")) {
            if (red.getW() > blue.getW()) {
                deque.add(red);
                deque.add(blue);
            } else {
                deque.add(blue);
                deque.add(red);
            }
        } else if (direction.equals("W")) {
            if (red.getW() > blue.getW()) {
                deque.add(blue);
                deque.add(red);
            } else {
                deque.add(red);
                deque.add(blue);
            }
        } else if (direction.equals("S")) {
            if (red.getH() > blue.getH()) {
                deque.add(red);
                deque.add(blue);
            } else {
                deque.add(blue);
                deque.add(red);
            }
        } else {
            if (red.getH() > blue.getH()) {
                deque.add(blue);
                deque.add(red);
            } else {
                deque.add(red);
                deque.add(blue);
            }
        }

        return deque;
    }

    private static boolean isWall(int h, int w) {
        return MAP[h][w].equals("#");
    }

    private static boolean isDestination(int h, int w) {
        return MAP[h][w].equals("O");
    }

    private static boolean moveValidation(int h, int w) {
        return MAP[h][w].equals(".") || MAP[h][w].equals("O");
    }

    private static class Pos {
        int h;
        int w;
        String color;

        public Pos(int h, int w, String color) {
            this.h = h;
            this.w = w;
            this.color = color;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public String getColor() {
            return color;
        }

        public boolean isSameHeight(int h) {
            return this.h == h;
        }

        public boolean isSameWidth(int w) {
            return this.w == w;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Pos pos = (Pos) o;
            return h == pos.h && w == pos.w && Objects.equals(color, pos.color);
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            result = 31 * result + Objects.hashCode(color);
            return result;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", w=" + w +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    private static class Node {
        Pos red;
        Pos blue;
        int cnt;

        public Node(Pos red, Pos blue, int cnt) {
            this.red = red;
            this.blue = blue;
            this.cnt = cnt;
        }

        public Pos getRed() {
            return red;
        }

        public Pos getBlue() {
            return blue;
        }

        public int getCnt() {
            return cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;
            return Objects.equals(red, node.red) && Objects.equals(blue, node.blue);
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(red);
            result = 31 * result + Objects.hashCode(blue);
            return result;
        }
    }
}