package com.example.codingTest.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
//import java.io.InputStreamReader;

public class backJonn5639 {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lc = "\n";
        ArrayList<String> ex = new ArrayList<>();

        ex.add("50" + lc);
        ex.add("30" + lc);
        ex.add("24" + lc);
        ex.add("5" + lc);
        ex.add("28" + lc);
        ex.add("45" + lc);
        ex.add("98" + lc);
        ex.add("52" + lc);
        ex.add("60");

        String sample = ex.stream()
                .reduce("", (s1, s2) -> s1 + s2);

        BufferedReader br = new BufferedReader(new StringReader(sample));

        int start = Integer.parseInt(br.readLine());
        Node root = new Node(null, start, null, null);

        String s;

        while ((s = br.readLine()) != null) {
            int value = Integer.parseInt(s);

            Deque<Node> deque = new ArrayDeque<>();
            deque.add(root);

            Node child = new Node(null, value, null, null);

            while (!deque.isEmpty()) {
                Node parent = deque.pollFirst();
                int parentValue = parent.getMe();

                if (value < parentValue) {
                    // 왼쪽
                    if (parent.getLeft() == null) {
                        parent.setLeft(child);
                        child.setParent(parent);
                        break;
                    }

                    deque.add(parent.getLeft());
                } else {
                    // 오른쪽

                    if (parent.getRight() == null) {
                        parent.setRight(child);
                        child.setParent(parent);
                        break;
                    }

                    deque.add(parent.getRight());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> visited = new HashSet<>();

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);

        while (!deque.isEmpty()) {
            Node me = deque.pollFirst();

            if (me.getLeft() != null && !visited.contains(me.getLeft().getMe())) {
                // 왼쪽 자식이 존재하고 아직 방문하지 않은 곳일 경우
                deque.add(me.getLeft());
                continue;
            }

            if (me.getRight() != null && !visited.contains(me.getRight().getMe())) {
                // 오른쪽 자식이 존재하고 아직 방문하지 않은 곳일 경우
                deque.add(me.getRight());
                continue;
            }

            visited.add(me.getMe());

            sb.append(me.getMe());
            sb.append("\n");

            if (me.getParent() != null) {
                deque.add(me.getParent());
            }
        }

        System.out.println(sb);
    }

    private static class Node {
        Node parent;
        Integer me;
        Node left;
        Node right;

        public Node(Node parent, Integer me, Node left, Node right) {
            this.parent = parent;
            this.me = me;
            this.left = left;
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Integer getMe() {
            return me;
        }

        public void setMe(Integer me) {
            this.me = me;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}