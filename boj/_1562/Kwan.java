package _1562;

import java.io.*;

public class Kwan {
    static int[][][] dp;
    static int value;
    static int divider = 1000000000;

    public static void main(String[] args) throws IOException {
        InputStream input1 = System.in;
        InputStream input2 = new FileInputStream("boj/_1562/input.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(input2));
        value = Integer.parseInt(br.readLine());

        dp = new int[10][2][1024]; //시작수, 자릿수,
        for (int i = 0; i < 10; i++) {
            dp[i][1][1<<i] = 1;
        }
        System.out.println(solve(value));
    }

    private static int solve(int value) {
        for(int i=2;i<=value;i++){
            subSolve(i);
        }

        int sum = 0;
        for(int i=1;i<10;i++){
            sum += dp[i][value%2][1023];
            sum %= divider;
        }

        return sum;
    }

    private static void subSolve(int value) {

        for(int i=0;i<10;i++){
            for(int j=0;j<1024;j++){
                dp[i][value%2][j] = 0;
            }
        }

        for(int i=0;i<10;i++){
            int bit = (1<<i);

            for(int j=0;j<1024;j++){
                int newBit = j | bit;
                if(i>0) dp[i][value%2][newBit] += dp[i-1][(value-1)%2][j];
                dp[i][value%2][newBit] %= divider;

                if(i<9) dp[i][value%2][newBit] += dp[i+1][(value-1)%2][j];
                dp[i][value%2][newBit] %= divider;
            }
        }
    }
}
