import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {


	static final int coinValues[] = { 5, 10, 20, 50, 100, 200 };

	public static void main(String[] args) {

		// to not resize the result array
		ArrayList<Integer> results = new ArrayList<>();

		Scanner sc = new Scanner(System.in);

		int[] coins = new int[6];

		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			if (line.equals("0 0 0 0 0 0")) {
				break;
			} else {
				String[] elements = line.split(" ");

				for (int i = 0; i < coins.length; i++) {
					coins[i] = Integer.parseInt(elements[i]);
				}
				int availableMoney = coins[0] * 5 + coins[1] * 10 + coins[2] * 20 + coins[3] * 50 + coins[4] * 100
						+ coins[5] * 200;
				double doublePrice = Math.round(Double.parseDouble(elements[6]) * 100);

				int price = (int) doublePrice;

				int coinsTotalPay = Integer.MAX_VALUE;

				// case where the customer pays the exact amount
				if (canPayCheck(price, coins))
					coinsTotalPay = pay(coinValues, coins, price);

				// case where the customer gets change
				int coinsTotalChange = minChange(availableMoney, coinValues, coins, price);

				results.add(Math.min(coinsTotalPay, coinsTotalChange));

			}
		}

		sc.close();
		for (int result : results)
			System.out.printf("%3d%n", result);

		System.exit(0);
	}

	public static int change(int coinValues[], int price, int moneyGiven) {

		int length = coinValues.length;
		int changeCoins = 0;
		int pricePay = price;

		for (int i = length - 1; i >= 0 && moneyGiven >= pricePay;) {
			if (moneyGiven - coinValues[i] >= pricePay) {
				moneyGiven -= coinValues[i];
				changeCoins++;

			} else {
				// conditional i decrement so it doesnt skip unused coins
				i--;
			}

		}
		// returns the ammount of coins used in the change method
		return changeCoins;
	}

	public static int pay(int coinValues[], int coins[], int price) {

		int payCoins = 0;
		int length = coinValues.length;
		int pricePay = price;
		int[] changeCoins = Arrays.copyOf(coins, length);

		for (int i = length - 1; pricePay > 0 && i >= 0;) {
			if (coinValues[i] <= pricePay && changeCoins[i] > 0) {

				pricePay -= coinValues[i];
				changeCoins[i]--;
				payCoins++;

			} else {
				// conditional i decrement so it doesnt skip unused coins
				i--;
			}
		}
		// returns the ammount of coins used in the pay method
		return payCoins;
	}

	// method checks how many coins are used in the minimal "change" situation
	public static int minChange(int availableMoney, int coinValues[], int coins[], int price) {
		int minChange = 0;
		int coinsTemp[] = Arrays.copyOf(coins, coinValues.length);

		int minChangeCoins = Integer.MAX_VALUE / 3;
		int minPayCoins = Integer.MAX_VALUE / 3;
		int length = (availableMoney - price);

		// not all matrix positions will be used
		// only the ones where the customer could pay the exact amount
		int results[][] = new int[(length)][2];
		for (int i = length - 1; i >= 0 && availableMoney > price; i--) {

			int currentChangeCoins = change(coinValues, price, availableMoney);

			int currentPayCoins = pay(coinValues, coinsTemp, availableMoney);

			// can the customer pay the exact amount
			if (canPayCheck(availableMoney, coinsTemp)) {

				results[i][0] = currentChangeCoins;
				results[i][1] = currentPayCoins;
			}
			// multiples of 5
			availableMoney -= 5;

		}

		for (int i = results.length - 1; i >= 0; i--) {
			int currentChangeCoins = results[i][0];
			int currentPayCoins = results[i][1];

			// checks if the total of current coins used is lower than the minimum
			// checks if a matrix position is empty
			if ((currentChangeCoins + currentPayCoins <= minChangeCoins + minPayCoins) && results[i][0] > 0) {

				minChangeCoins = currentChangeCoins;
				minPayCoins = currentPayCoins;

			}
		}

		minChange = minChangeCoins + minPayCoins;

		// returns the minimum total of coins used in a "change" situation
		return minChange;
	}

	// checks whether the customer can pay the exact amount
	public static boolean canPayCheck(int price, int[] coins) {

		int length = coinValues.length;
		int pricePay = price;

		int[] changeCoins = Arrays.copyOf(coins, length);

		for (int i = length - 1; pricePay > 0 && i >= 0;) {
			if (coinValues[i] <= pricePay && changeCoins[i] > 0) {

				pricePay -= coinValues[i];

				changeCoins[i]--;

			} else {
				i--;
			}
		}

		return pricePay == 0;
	}

}
