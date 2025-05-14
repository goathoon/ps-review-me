package _1202;

import java.io.*;
import java.util.*;

class Taehoon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Jewel{
        int weight;
        int cost;

        Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int jewel = Integer.parseInt(st.nextToken());
        int bag = Integer.parseInt(st.nextToken());
        List<Long> capacities = new ArrayList<>();
        List<Jewel> jewels = new ArrayList<>();
        long ans = 0;

        PriorityQueue<Jewel> highCostJewels = new PriorityQueue<>((j1,j2) -> j2.cost - j1.cost);

        for (int j = 0; j < jewel; j++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(weight, cost));
        }

        for (int b = 0; b < bag; b++) {
            long capacity = Long.parseLong(br.readLine());
            capacities.add(capacity);
        }

        Collections.sort(jewels, (j1,j2) -> j1.weight - j2.weight);
        Collections.sort(capacities);

        int jewelIndex = 0;

        for (long capacity : capacities) {
            while (jewelIndex < jewel && jewels.get(jewelIndex).weight <= capacity) {
                Jewel validJewel = jewels.get(jewelIndex);
                highCostJewels.offer(validJewel);
                jewelIndex++;
            }

            if(!highCostJewels.isEmpty()) {
                Jewel highestCostJewel = highCostJewels.poll();
                ans += highestCostJewel.cost;
            }
        }

        System.out.println(ans);
    }
}

