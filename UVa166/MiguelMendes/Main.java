
import java.util.Arrays;
import java.util.Scanner;

/*
 * Making Change
 *
 *  You are given a list of coins of different denominations and a target amount of money.
 */
class Main {

    private static int[] coinValues = {5, 10, 20, 50, 100, 200};
    private static int[][] memo = new int[41][2];

    public static void Runner() {
        Scanner scn = new Scanner(System.in);
        initMemo();
        StringBuilder strB = new StringBuilder();
        boolean first = true;
        while(scn.hasNext()) {
            int[] coins = new int[6]; // 5c, 10c, 20c, 50c, 1d, 2d
            for(int i = 0; i < coins.length; i++) {
                coins[i] = scn.nextInt();
            }
            if (Arrays.stream(coins).sum() == 0) {
                break;
            }
            if(!first) {
                strB.append(System.lineSeparator());
            } else {
                first = false;
            }

            strB.append(String.format("%3d",solver((int) (scn.nextDouble() * 100 + 0.5), coins)));
        }
        scn.close();
        System.out.println(strB.toString());
    }

    /**
     * Initialize pattern Table
     */
    private static void initMemo() {
        for(int i = 0; i < 41; i++) {
            int val = 5 * i;
            memo[i][0] = val;
            memo[i][1] = initMemoHelper(val);
        }
    }

    /**
     * Helper function to initialize the pattern table
     * @param value value to initialize
     * @return number of coins needed to make the value
     */
    private static int initMemoHelper(int value) {
        int counter = 0;
        int valueCopy = value;
        int index = coinValues.length - 1;
        while(valueCopy > 0) {
            if(coinValues[index] > valueCopy) {
                index--;
            } else {
                valueCopy -= coinValues[index];
                counter++;
            }
        }
        return counter;
    }

    /**
     * Solver function
     * @param payment payment to make
     * @param coins coins available
     * @return minimum number of coins needed to make the payment
     */
    private static int solver(int payment, int[] coins) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < memo.length; i++) {
            int result = 0;
            int value = payment + memo[i][0]; // consider possible trader change like (2 4 2 2 1 0 0.95) user gives 1d and trader gives 5c
            for(int j = coinValues.length - 1; j>=0; j--) {
                int numCoins = coins[j];
                while(numCoins > 0 && value - coinValues[j] >= 0) {
                    numCoins--;
                    value -= coinValues[j];
                    result++;
                }
                if(value ==0) {
                    result += memo[i][1];
                    min = Math.min(min, result);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Runner();
    }
}
