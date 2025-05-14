package _1005;

import java.io.*;
import java.util.*;

public class Kwan {
    public static void main(String[] args) throws IOException {
        InputStream input1 = System.in;
        //InputStream input2 = new FileInputStream("boj/_1005/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(input1));
        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = nk[0];
            int K = nk[1];

            int[] buildTimeArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] constructOrder = new int[K][2];
            for(int j=0;j<K;j++){
                constructOrder[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(a->Integer.parseInt(a)-1).toArray();
            }

            int W = Integer.parseInt(br.readLine())-1;

            System.out.println(solve(N, K, W, buildTimeArr, constructOrder));
        }
    }

    private static int solve(int N, int K, int W, int[] buildTimeArr, int[][] constructOrder) {
        int[] toCount = new int[N];
        int[] accumulativeBuildTimeArr = new int[N];

        List<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<K;i++){
            int from = constructOrder[i][0];
            int to = constructOrder[i][1];
            graph[from].add(to);
            toCount[to]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<N;i++){
            if(toCount[i]>0) continue;

            accumulativeBuildTimeArr[i] = buildTimeArr[i];
            q.add(i);
        }

        while (!q.isEmpty()){
            int from = q.poll();

            for(int to: graph[from]){
                accumulativeBuildTimeArr[to] = Math.max(accumulativeBuildTimeArr[to], accumulativeBuildTimeArr[from]+buildTimeArr[to]);

                toCount[to]--;
                if(toCount[to] > 0) continue;
                q.add(to);
            }
        }

        return accumulativeBuildTimeArr[W];
    }
}