// package fcul.cprog.uva166;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * UVa166 https://onlinejudge.org/external/1/166.pdf The problem we will be
 * concerned with will be to minimise the number of coins that change hands at
 * such a transaction, given that the shopkeeper has an adequate supply of all
 * coins. (The set of New Zealand coins comprises 5c, 10c, 20c, 50c, $1 and $2.)
 * Thus if we need to pay 55c, and we do not hold a 50c coin, we could pay this
 * as 2 × 20c + 10c + 5c to make a total of 4 coins. If we tender $1 we will
 * receive 45c in change which also involves 4 coins, but if we tender $1.05 ($1
 * + 5c), we get 50c change and the total number of coins that changes hands is
 * only 3. Write a program that will read in the resources available to you and
 * the amount of the purchase and will determine the minimum number of coins
 * that change hands
 *
 * <pre>
 * Sample Input
 * 2 4 2 2 1 0 0.95
 * 2 4 2 0 1 0 0.55
 * 0 0 0 0 0 0
 * </pre>
 * <pre>
 * Sample Output
 * 2
 * 3
 * </pre>
 *
 * Output will consist of a series of lines, one for each situation defined in
 * the input. Each line will consist of the minimum number of coins that change
 * hands right justified in a field 3 characters wide
 *
 * @author tl
 */
class Main {

    // private static final int[] coinsValues = {200, 100, 50, 20, 10, 5};
    private static final int[] coinsValues = {5, 10, 20, 50, 100, 200};
    private static final int[][] changeNbCoins = new int[5][6000];

    /**
     * for pretty printing trace (debug).
     *
     * @param n
     * @return
     */
    private static String space(int n) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s.append(".");
        }
        return s.toString();
    }

    /**
     * Returns the minimum number of coin needed to sum the value amount, given
     * an infinite supply of coins.
     *
     * @param coinIndice a coin indice initially 0
     * @param value      the amount of money
     * @return
     */
    private static int sellerCoinsNoOptim(int coinIndice, int value) {
        if (coinIndice >= coinsValues.length) {
            return 100000;
        }
        if (value == 0) {
            return 0;
        }
        int diff = value - coinsValues[coinIndice];
        if (diff < 0) {
            // the coin «coinIndice» is too large
            // try with next coin
            return sellerCoinsNoOptim(coinIndice + 1, value);
        }
        if (diff > 0) {
            return Math.min(
                    // try use again iCoin
                    1 + sellerCoinsNoOptim(coinIndice, diff),
                    // or try to use next coin
                    sellerCoinsNoOptim(coinIndice + 1, value)
            );
        } else {
            return 1;
        }
    }

    private static int sellerCoinsWithMemo(int iCoin, int value) {
        if (changeNbCoins[iCoin][value] == 0) {
            changeNbCoins[iCoin][value] = sellerCoinsNoOptim(iCoin, value);
        }
        return changeNbCoins[iCoin][value];
    }

    /**
     * Generates all the possible ways to sum an amount >= price given a set of
     * coins. While doing this, compute the minimum number of coins in the
     * change given an infinite set of coins. Return the minimum value of the
     * sum.
     *
     * @param wallet     available coins
     * @param coinIndice a coin indice
     * @param value      the amount of money
     * @param r
     * @return
     */
    private static AmountNbCoins solveAuxWithTrace(int[] wallet,
                                                   int coinIndice, int value,
                                                   AmountNbCoins paidSoFar,
                                                   AmountNbCoins min,
                                                   int level) {
        if (coinIndice < coinsValues.length) {
            System.out.format("%sin solveAux(wallet=%s, coin=%d, value=%d, paid:%s, min=%s)\n",
                              space(level),
                              Arrays.toString(wallet),
                              coinsValues[coinIndice],
                              value,
                              paidSoFar.toString(),
                              min.toString());
        }
        if (coinIndice > 5) {
            return min;
        }
        if (wallet[coinIndice] > 0) {
            // use 1 coin
            wallet[coinIndice]--;
            paidSoFar = (paidSoFar != null ? paidSoFar : new AmountNbCoins(0, 0));
            AmountNbCoins paid = new AmountNbCoins(coinsValues[coinIndice] + paidSoFar.amount,
                                                   1 + paidSoFar.nbCoins);
            if (paid.amount >= value && paid.nbCoins <= min.nbCoins) {
                System.out.println("Compute number of coins in change: "
                                   + " change = " + (paid.amount - value));
                int nbCoinsInChange = sellerCoinsNoOptim(0, paid.amount - value);
                System.out.print("To pay change " + nbCoinsInChange + " coins are necessary.");
                int totalNbCoins = nbCoinsInChange + paid.nbCoins;
                System.out.println(" Total nb of coins :" + totalNbCoins);
                if (totalNbCoins < min.nbCoins) {
                    paid.nbCoins = totalNbCoins;
                    min = paid;
                }
            } else {
                System.out.format("Paid amount is inferior to price %d < %d || %d > %d\n", paid.amount, value, paid.nbCoins, min.nbCoins);
            }
            AmountNbCoins p1
                    = solveAuxWithTrace(wallet, coinIndice, value, paid, min, level + 1);
            // put the coin that was removed in the wallet.
            wallet[coinIndice]++;
            AmountNbCoins p2
                    = solveAuxWithTrace(wallet, coinIndice + 1, value, paidSoFar, min, level + 1);
            if (p2.amount >= value && p1.amount >= value) {
                System.out.format("%sr1: %s\n", space(level), (p1.nbCoins < p2.nbCoins ? p1 : p2).toString());
                return (p1.nbCoins < p2.nbCoins ? p1 : p2);
            } else if (p1.amount >= value) {
                System.out.format("%sr2: %s\n", space(level), p1.toString());
                return p1;
            } else {
                System.out.format("%sr3: %s\n", space(level), p2.toString());
                return p2;
            }
        } else {
            AmountNbCoins p3 = solveAuxWithTrace(wallet, coinIndice + 1, value, paidSoFar, min, level + 1);
            System.out.format("%sr4: %s\n", space(level), p3.toString());
            return p3;
        }
    }

    private static AmountNbCoins solveAuxWithTrace2(int[] wallet,
                                                    int coinIndice, int value,
                                                    AmountNbCoins paidSoFar,
                                                    AmountNbCoins min,
                                                    int level) {
        if (coinIndice < coinsValues.length) {
            System.out.format("%sin solveAux(wallet=%s, coin=%d, value=%d, paid:%s, min=%s)\n",
                              space(level),
                              Arrays.toString(wallet),
                              coinsValues[coinIndice],
                              value,
                              paidSoFar.toString(),
                              min.toString());
        }
        if (coinIndice > 5) {
            return min;
        }
        if (wallet[coinIndice] > 0) {
            // use 1 coin
            wallet[coinIndice]--;
            paidSoFar = (paidSoFar != null ? paidSoFar : new AmountNbCoins(0, 0));
            // update the amount paid:
            AmountNbCoins paid = new AmountNbCoins(coinsValues[coinIndice] + paidSoFar.amount,
                                                   1 + paidSoFar.nbCoins);
            if (paid.amount >= value && paid.nbCoins <= min.nbCoins) {
                System.out.println("Compute number of coins in change ("
                                   + (paid.amount - value) + ")");
                int nbCoinsInChange = sellerCoinsNoOptim(0, paid.amount - value);
                System.out.print("To pay change " + nbCoinsInChange + " coins are necessary.");
                int totalNbCoins = nbCoinsInChange + paid.nbCoins;
                System.out.println(" Total nb of coins :" + totalNbCoins);
                if (totalNbCoins < min.nbCoins) {
                    paid.nbCoins = totalNbCoins;
                    min = paid;
                }
                // return paid;
            }
            //else {
            //    System.out.format("Paid amount is inferior to price %d < %d || %d > %d\n", paid.amount, value, paid.nbCoins, min.nbCoins);
            // }
            AmountNbCoins p1
                    = solveAuxWithTrace2(wallet, coinIndice, value, paid, min, level + 1);
            // put the coin that was removed in the wallet.
            wallet[coinIndice]++;
            AmountNbCoins p2
                    = solveAuxWithTrace2(wallet, coinIndice + 1, value, paidSoFar, min, level + 1);
            if (p2.amount >= value && p1.amount >= value) {
                System.out.format("%sr1: %s\n", space(level), (p1.nbCoins < p2.nbCoins ? p1 : p2).toString());
                return (p1.nbCoins < p2.nbCoins ? p1 : p2);
            } else if (p1.amount >= value) {
                System.out.format("%sr2: %s\n", space(level), p1.toString());
                return p1;
            } else {
                System.out.format("%sr3: %s\n", space(level), p2.toString());
                return p2;
            }
        } else {
            AmountNbCoins p3 = solveAuxWithTrace2(wallet, coinIndice + 1, value, paidSoFar, min, level + 1);
            System.out.format("%sr4: %s\n", space(level), p3.toString());
            return p3;
        }
    }

    private static AmountNbCoins solveAux(int[] wallet,
                                          int coinIndice,
                                          int value,
                                          AmountNbCoins p,
                                          AmountNbCoins min) {
        if (coinIndice > 5) {
            return min;
        }
        if (wallet[coinIndice] > 0) {
            // use 1 coin of type coinIndice
            wallet[coinIndice]--;
            p = (p != null ? p : new AmountNbCoins(0, 0));
            AmountNbCoins paidSoFar = new AmountNbCoins(coinsValues[coinIndice] + p.amount,
                                                        1 + p.nbCoins);
            if (paidSoFar.amount >= value && paidSoFar.nbCoins <= min.nbCoins) {
                int nbCoinsInChange = sellerCoinsWithMemo(0, paidSoFar.amount - value);
                int totalNbCoins = nbCoinsInChange + paidSoFar.nbCoins;
                if (totalNbCoins < min.nbCoins) {
                    paidSoFar.nbCoins = totalNbCoins;
                    min = paidSoFar;
                }
            } else {

            }
            AmountNbCoins p1
                    = solveAux(wallet, coinIndice, value, paidSoFar, min);
            wallet[coinIndice]++;
            AmountNbCoins p2
                    = solveAux(wallet, coinIndice + 1, value, p, min);
            if (p2.amount >= value && p1.amount >= value) {
                return (p1.nbCoins < p2.nbCoins ? p1 : p2);
            } else if (p1.amount >= value) {
                return p1;
            } else {
                return p2;
            }
        } else {
            AmountNbCoins p3 = solveAux(wallet, coinIndice + 1, value, p, min);
            return p3;
        }
    }

    /**
     * Finds the minimum number of coin exchanges for a transaction.
     *
     * @param wallet
     * @param coinIndice
     * @param value
     * @return
     */
    private static int solve(int[] wallet,
                             int coinIndice,
                             int value) {
        //AmountNbCoins p = solveAuxWithTrace2(wallet, 0, value, new AmountNbCoins(0, 0), new AmountNbCoins(0, 100000), 0);
        AmountNbCoins p = solveAux(wallet, 0, value, new AmountNbCoins(0, 0), new AmountNbCoins(0, 100000));
        return p.nbCoins;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        int[] wallet = new int[6];
        Scanner in = null;
        Scanner insol = null;
        Scanner kb = null;
        /*
        if (args.length == 0) {
            // mode contest
            in = new Scanner(System.in);
        } else {
            // mode debug
            kb = new Scanner(System.in);
            in = new Scanner(new BufferedReader(new FileReader(args[0])));
            insol = new Scanner(new BufferedReader(new FileReader(args[1])));
        }
         */
        // insol = new Scanner(new FileReader("data/output4.dat"));
        // in = new Scanner(new FileReader("data/input2.dat"));
        in = new Scanner(System.in);
        boolean end = false;
        int i = 0;
        int value = 0;
        while (!end) {
            wallet[0] = in.nextInt();
            wallet[1] = in.nextInt();
            wallet[2] = in.nextInt();
            wallet[3] = in.nextInt();
            wallet[4] = in.nextInt();
            wallet[5] = in.nextInt();
//            wallet[5] = in.nextInt();
//            wallet[4] = in.nextInt();
//            wallet[3] = in.nextInt();
//            wallet[2] = in.nextInt();
//            wallet[1] = in.nextInt();
//            wallet[0] = in.nextInt();
            // a the end of the file we have: 0 0 0 0 0 0
            if (wallet[0] == 0 && wallet[1] == 0 && wallet[2] == 0
                && wallet[3] == 0 && wallet[4] == 0 && wallet[5] == 0) {
                end = true;
            } else {
                value = Math.round(100 * in.nextFloat());
                if (true) { // mode contest
                    System.out.format("%3d\n", solve(wallet, 0, value));
                } else {
                    long result = solve(wallet, 0, value);
                    // System.out.println((i++) + ": " + value + " " + Arrays.toString(myCoins) + " -> " + result + " " + insol.nextLine());
                    // System.out.println(result);
                }
            }
        }
        in.close();
        // insol.close();
        // System.out.println("done.");
        System.exit(0);
    }
}

class AmountNbCoins {

    int amount;
    int nbCoins;

    AmountNbCoins(int a, int b) {
        amount = a;
        nbCoins = b;
    }

    @Override
    public String toString() {
        return "{" + amount + ", " + nbCoins + "}";
    }
}
