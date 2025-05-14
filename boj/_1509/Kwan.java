package _1509;

import java.io.*;
import java.util.Map;

public class Kwan {
    static int[] dp;
    static int[][] dp2;
    static String str;

    public static void main(String[] args) throws IOException {
        InputStream input1 = System.in;
        InputStream input2 = new FileInputStream("boj/_1509/input.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(input2));
        str = br.readLine();
        System.out.println(solve(str));
    }

    private static int solve(String str) {
        int length = str.length();
        dp = new int[length];
        dp2 = new int[length][length];

        dp[0] = 1;
        for(int i=1;i<length;i++){
            dp[i] = dp[i-1]+1;

            if(isPalin(0,i)) {
                dp[i] = 1;
                continue;
            }

            for(int j=0;j<i;j++){
                if(!isPalin(j+1,i)) continue;
                dp[i] = Math.min(dp[i],dp[j]+1);
            }
        }

        return dp[length-1];
    }

    private static boolean isPalin(int s, int e){
        if(s >= e) return true;

        if(dp2[s][e] != 0) return dp2[s][e] > 0 ? true : false;

        if(str.charAt(s) != str.charAt(e)) {
            dp2[s][e] = -1;
        }else if(str.charAt(s) == str.charAt(e)) {
            //dp[s][e] = 1;
            dp2[s][e] = isPalin(s+1,e-1) ? 1 : -1;
        }

        return dp2[s][e] > 0 ? true : false;
    }
}
