package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class backJonn1707 {
    static ArrayList<ArrayList<Integer>> adj;
    static int[] check;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("2" + lc);
        ex.add("3 2" + lc);
        ex.add("1 3" + lc);
        ex.add("2 3" + lc);
        ex.add("4 4" + lc);
        ex.add("1 2" + lc);
        ex.add("2 3" + lc);
        ex.add("3 4" + lc);
        ex.add("4 2");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        StringBuilder sb = new StringBuilder();

        int testCaseCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCnt; i++) {
            String[] infos = br.readLine().split(" ");
            int nodeCnt = Integer.parseInt(infos[0]); // V
            int edgeCnt = Integer.parseInt(infos[1]); // E

            // 데이터 구조 초기화 및 입력
            initData(nodeCnt, edgeCnt, br);

            isBipartite = true;

            // *** 중요: 모든 정점에 대해 순회하며 탐색 시작 (비연결 그래프 처리) ***
            for (int j = 1; j <= nodeCnt; j++) {
                if (check[j] == 0) { // 아직 방문하지 않은 정점이라면
                    // 새로운 연결 요소 시작, 1번 색깔로 시작
                    check[j] = 1;
                    DFS(j);
                }

                // 탐색 중 모순이 발견되면 바로 break
                if (!isBipartite) {
                    break;
                }
            }

            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    // DFS를 이용한 탐색
    static void DFS(int startNode) {
        // 이미 이분 그래프가 아님이 확인되었으면 더 이상 탐색하지 않음
        if (!isBipartite) {
            return;
        }

        // 현재 노드와 인접한 모든 노드 순회
        for (int neighbor : adj.get(startNode)) {
            if (check[neighbor] == 0) {
                // 인접 노드가 미방문이면, 현재 노드와 반대 색깔로 칠하고 탐색 계속
                check[neighbor] = check[startNode] * -1;
                DFS(neighbor);
            } else if (check[neighbor] == check[startNode]) {
                // 인접 노드가 방문되었는데, 현재 노드와 같은 색깔이면 이분 그래프가 아님
                isBipartite = false;
                return;
            }
        }
    }

    private static void initData(int nodeCnt, int edgeCnt, BufferedReader br) throws IOException {
        // 1번 정점부터 사용하기 위해 nodeCnt + 1 크기로 초기화
        adj = new ArrayList<>();
        for (int i = 0; i <= nodeCnt; i++) {
            adj.add(new ArrayList<>());
        }
        check = new int[nodeCnt + 1]; // 0: 미방문, 1: 색깔 1, -1: 색깔 -1

        for (int j = 0; j < edgeCnt; j++) {
            String[] data = br.readLine().split(" ");
            int from = Integer.parseInt(data[0]);
            int to = Integer.parseInt(data[1]);

            // 양방향 그래프이므로 양쪽에 추가
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
    }
}