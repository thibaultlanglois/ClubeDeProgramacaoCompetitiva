import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class Main {

    private static int[] coins = { 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Locale.setDefault(Locale.US);

        while (true) {
            double input = Double.parseDouble(sc.nextLine());
            if (input == 0.00)
                break;
            double value = Math.round(input * 100);
            int amount = (int) value;

            long max = max(amount);

            String formattedValue = String.format("%.2f", input);
            
            while (formattedValue.length() < 6) {
                formattedValue = " " + formattedValue;
            }
            System.out.print(formattedValue);
            System.out.println(String.format("%17d", max));
        }

        sc.close();
    }

    public static long max(int amount) {
        int count = 0;

        for (int coin : coins)
            if (coin <= amount)
                count++;
            else
                break;
        
        
        long[][] dp = new long[count][amount / 5 + 1]; // every amount is a multiple of 5
        // fill first positions with 1
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < dp.length && coins[i] <= amount; i++) {
            for (int j = 1; j < dp[i].length; j++) {

                if (coins[i] > 5 * j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int position = (5 * j - coins[i]) / 5;
                    dp[i][j] = dp[i - 1][j] + dp[i][position];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
