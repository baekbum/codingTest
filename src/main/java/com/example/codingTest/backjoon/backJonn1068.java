package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
//import java.io.InputStreamReader;

public class backJonn1068 {
    private static TreeMap<Integer, Node> MAP;
    private static Integer ANSWER = 0;
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

//        ex.add("5" + lc);
//        ex.add("-1 0 0 1 1" + lc);
//        ex.add("2");

//        ex.add("4" + lc);
//        ex.add("-1 0 1 0" + lc);
//        ex.add("2");

        ex.add("4" + lc);
        ex.add("2 3 1 -1" + lc);
        ex.add("3");


        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));
        int nodeCnt = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");

        MAP = new TreeMap<>();
        HashMap<Integer, Integer> childMap = new HashMap<>();
        int startNum = 0;

        for (int i = 0; i < nodeCnt; i++) {
            MAP.put(i, new Node(i));
        }

        for (int i = 0; i < nodeCnt; i++) {
            int parentValue = Integer.parseInt(s[i]);
            int me = i;

            MAP.get(me).setParent(parentValue);

            if (parentValue != -1) {
                MAP.get(parentValue).getChild().add(me);
            } else {
                startNum = me;
            }
        }

        int removeNum = Integer.parseInt(br.readLine());

        if (startNum == removeNum) {
            System.out.println(0);
        } else {
            Node node = MAP.get(removeNum); // 지우고자 하는 노드
            Node parentNode = MAP.get(node.getParent()); // 부모 노드
            parentNode.getChild().remove(removeNum); // 부모 노드의 자식 리스트에서 지우고자 하는 노드를 삭제

            dfs(startNum);

            System.out.println(ANSWER);
        }
    }

    private static void dfs(int num) {
        if (MAP.get(num).getChild().isEmpty()) {
            ANSWER++;
            return;
        }

        for (Integer child : MAP.get(num).getChild()) {
            dfs(child);
        }
    }


    private static class Node {
        int parent;
        int me;
        HashSet<Integer> child;

        public Node(int me) {
            this.parent = -1;
            this.me = me;
            this.child = new HashSet<>();
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getMe() {
            return me;
        }

        public HashSet<Integer> getChild() {
            return child;
        }
    }
}