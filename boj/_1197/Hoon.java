package _1197;

import java.io.*;
import java.util.*;

class Hoon {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Edge> edges = new ArrayList<>();
    static int[] parent;

    static class Edge {
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        parent = new int[vertex + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        int ans = 0;

        settingEdges(edge);

        edges.sort(Comparator.comparingInt(e -> e.cost));

        for (Edge e : edges) {
            int start = e.start;
            int end = e.end;
            int cost = e.cost;
//            if(visited.contains(start) && visited.contains(end))
//                continue;
//            visited.add(start);
//            visited.add(end);
            if(getParent(start) != getParent(end)) {
                union(start, end);
                ans += cost;
            }

        }

        System.out.println(ans);
    }

    private static void settingEdges(int edge) throws IOException {
        for (int e = 0; e < edge; e++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, cost));
        }
    }

    private static void union(int a, int b) {
        int pA = getParent(a);
        int pB = getParent(b);
        if (pA < pB) {
            // b의 루트노드 아닌 b를 바꾼다면 문제가 생길 수 있음
            parent[pB] = pA;
        }
        else {
            parent[pA] = pB;
        }
    }

    private static int getParent(int x){
        if(parent[x] != x){
            parent[x] = getParent(parent[x]);
        }
        return parent[x];
    }
}