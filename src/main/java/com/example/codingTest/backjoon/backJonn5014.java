package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
//import java.io.InputStreamReader;

public class backJonn5014 {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //F, S, G, U, D
        BufferedReader br = new BufferedReader(new StringReader("100 2 1 1 0"));
        String[] info = br.readLine().split(" ");
        int totalFloor = Integer.parseInt(info[0]); // 건물 높이
        int start = Integer.parseInt(info[1]); // 시작점
        int goal = Integer.parseInt(info[2]); // 목적지
        int upBtn = Integer.parseInt(info[3]); // 올라감
        int downBtn = Integer.parseInt(info[4]); // 내려감

        Floor floor = new Floor(start, 0);

        Deque<Floor> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[totalFloor+1];
        deque.add(floor);
        visited[start] = true;

        boolean didFind = false;

        while (!deque.isEmpty()) {
            Floor f = deque.pollFirst();

            if (f.getFloor() == goal) {
                didFind = true;
                System.out.println(f.getAttempt());
                break;
            }

            int up = f.getFloor() + upBtn;
            int down = f.getFloor() - downBtn;

            // 업
            if (up <= totalFloor && !visited[up]) {
                deque.add(new Floor(up, f.getAttempt()+1));
                visited[up] = true;
            }
            // 다운
            if (0 < down && !visited[down]) {
                deque.add(new Floor(down, f.getAttempt()+1));
                visited[down] = true;
            }
        }

        if (!didFind) {
            System.out.println("use the stairs");
        }
    }

    private static class Floor {
        int floor;
        int attempt;

        public int getFloor() {
            return floor;
        }

        public int getAttempt() {
            return attempt;
        }

        public Floor(int floor, int attempt) {
            this.floor = floor;
            this.attempt = attempt;
        }
    }
}
