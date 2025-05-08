package boj._1197;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Kwan {

    public static class Edge{
        int a;
        int b;
        int cost;
        public Edge(int a,int b,int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    public static int[] parent;
    public static int V;
    public static int E;
    public static int answer;
    public static int count;

    public static void main(String[] args) throws IOException {
        InputStream input1 = System.in;
        InputStream input2 = new FileInputStream("boj/_1197/input.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(input2));
        StringTokenizer st = new StringTokenizer(br.readLine() , " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        answer = 0;
        count = 0;

        parent = new int[V+1];
        for(int i=0;i<=V;i++){
            parent[i] = i;
        }
        int[] temp = new int[3];
        Edge[] edges = new Edge[E];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<3;j++){
                temp[j] = Integer.parseInt(st.nextToken());
            }

            edges[i] = new Edge(temp[0],temp[1],temp[2]);
        }

        Arrays.sort(edges, Comparator.comparingInt(a -> a.cost));

        for(int i=0;i<E;i++){
            tryMerge(edges[i]);

            if(count == V) break;
        }

        System.out.println(answer);
    }

    private static void tryMerge(Edge edge) {
        int p1 = getParent(edge.a);
        int p2 = getParent(edge.b);

        if(p1 == p2) return;

        int p = Math.min(p1,p2);
        int c = p1+p2-p;
        parent[c] = p;
        count++;
        answer += edge.cost;
    }

    private static int getParent(int i) {
        while(parent[i] != i){
            int temp = i;
            i = parent[i];
            parent[temp] = parent[i];
        }

        return i;
    }
}
