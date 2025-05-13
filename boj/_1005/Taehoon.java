package _1005;

import java.io.*;
import java.util.*;

class Taehoon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] buildTimes;
    static Integer lastBuildIdx;
    static int[] indegree;
    static PriorityQueue<Building> pq = new PriorityQueue<>((b1,b2)-> b1.time - b2.time);
    static List<List<Integer>> graph;

    static class Building {
        int idx;
        int time;

        Building (int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        int testcase = Integer.parseInt(br.readLine());

        for(int tc = 0 ; tc < testcase; tc++){
            settingInput();

            // 우선순위큐에 indegree 0 인 building 추가
            for(int idx = 1; idx < indegree.length; idx++){
                if(indegree[idx] == 0) {
                    pq.add(new Building(idx, buildTimes[idx]));
                }
            }

            // log(1000) * 1000 * log (1000)
            while(!pq.isEmpty()) {
                Building curBuilding = pq.poll();
                // 승리를 위한 건물번호가 현재 번호와 같다면 종료
                if(curBuilding.idx == lastBuildIdx) {
                    System.out.println(curBuilding.time);
                    break;
                }
                // 1000
                for (int next : graph.get(curBuilding.idx)) {
                    if(--indegree[next] == 0) {
                        // log(1000)
                        pq.add(new Building(next, curBuilding.time + buildTimes[next]));
                    }
                }
            }

            pq.clear();
            graph.clear();
            indegree = null;
            lastBuildIdx = null;
            buildTimes = null;
        }

    }

    static void settingInput() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int buildings = Integer.parseInt(st.nextToken());
        int orders = Integer.parseInt(st.nextToken());
        buildTimes = new int[buildings + 1];
        indegree = new int[buildings + 1];
        graph = new ArrayList<>(buildings + 1);
        for(int i = 0; i <= buildings + 1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int idx = 1; idx <= buildings; idx++) {
            buildTimes[idx] = Integer.parseInt(st.nextToken());
        }

        for(int o = 0; o < orders; o++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            indegree[end]++;
        }
        lastBuildIdx = Integer.parseInt(br.readLine());
    }
}