import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;

class Main {
    static int max = 7489;
    static int[] coins = {1, 5, 10, 25, 50}; 
    static int[] dp = new int[max + 1]; 

    public static void main(String[] args){
        Scanner sc = new Scanner (new BufferedReader(new InputStreamReader(System.in)));

        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= max; j++) {
                dp[j] += dp[j - coin];
            }
        }
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(dp[n]); 
        }
    }
}
