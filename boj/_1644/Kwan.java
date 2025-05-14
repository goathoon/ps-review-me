package _1644;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Kwan {
    //dp 1개가 필요할듯
    //1. 팰린드롬 판단여부는 딱 한번만 하고,
    //2. 해당 부분문자열이 최대 몇개의 팰린드롬으로만 구성될지만 판단하면됨
    static int value;
    static boolean[] isSosu;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        InputStream input1 = System.in;
        InputStream input2 = new FileInputStream("boj/_1644/input.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(input2));
        value = Integer.parseInt(br.readLine());
        isSosu = new boolean[value+1];
        list = new ArrayList();

        System.out.println(solve(value));
    }

    private static int solve(int value) {
        sosuInit();

        int left = 0;
        int right = 0;

        if(list.size() == 0) return 0;

        int sum = list.get(0);
        int count = 0;
        int size = list.size();

        while(true){
            if(sum < value){
                if(++right == size) break;

                sum += list.get(right);
                continue;
            }

            if(sum > value){
                sum -= list.get(left++);
                continue;
            }

            if(sum == value){
                count++;
                if(++right == size) break;

                sum += list.get(right);
            }
        }

        return count;
    }

    private static void sosuInit() {

        for (int i=2;i<=value;i++){
            isSosu[i] = true;
        }

        for(int i=2;i<=value;i++){
            if(!isSosu[i]) continue;
            list.add(i);
            for(int j=i*2;j<=value;j+=i){
                isSosu[j] = false;
            }
        }
    }

}
