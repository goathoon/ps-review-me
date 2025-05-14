package _1202;

import java.io.*;
import java.util.*;

public class Kwan {
    public static void main(String[] args) throws IOException {
        InputStream input1 = System.in;
        InputStream input2 = new FileInputStream("boj/_1202/input.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(input1));
        StringTokenizer st = new StringTokenizer(br.readLine() , " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] bag = new int[K];

        PriorityQueue<int[]> jewely = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine() , " ");
            int[] temp = new int[2];
            temp[0] = Integer.parseInt(st.nextToken());
            temp[1] = Integer.parseInt(st.nextToken());
            jewely.add(temp);
        }

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine() , " ");
            bag[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bag);

        PriorityQueue<int[]> canSteal = new PriorityQueue<>((a,b)->b[1]-a[1]);
        long answer = 0;
        for(int i=0;i<K;i++){
            while(!jewely.isEmpty() && jewely.peek()[0] <= bag[i]){
                canSteal.add(jewely.poll());
            }

            if(canSteal.isEmpty()) continue;
            answer += canSteal.poll()[1];
        }

        System.out.println(answer);
    }
}
