package uva100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Accepted version.
 *
 * @author tl
 */
public class Main {

    public static void main1(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(
                System.in)));
        // long t1 = System.currentTimeMillis();
        while (input.hasNextLong()) {
            long max = 0;
            long ii = input.nextLong();
            long jj = input.nextLong();
            long i = ii < jj ? ii : jj;
            long j = ii < jj ? jj : ii;
            for (long k = i; k <= j; k++) {
                long n = countCycles_V4(k);
                if (n > max) {
                    max = n;
                }
            }
            System.out.println(ii + " " + jj + " " + max);
        }
        // System.out.println("time: " + (System.currentTimeMillis() - t1));
        System.exit(0);
    }
    private static Map<Long, Long> tableCC = new HashMap<>();

    private static long countCycles_V4(long k) {
        if (tableCC.containsKey(k)) {
            // System.out.println("Bingo ! " + k);
            return tableCC.get(k);
        } else {
            if (k == 1) {
                return 1;
            } else if (k % 2 == 0) {
                long n = 1 + countCycles_V4(k / 2);
                tableCC.put(k, n);
                return n;
            } else {
                long n = 1 + countCycles_V4(k * 3 + 1);
                return n;
            }
        }
    }

// A version based on Integer instead of Long.
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(
                System.in)));
        // long t1 = System.currentTimeMillis();
        while (input.hasNextInt()) {
            int max = 0;
            int ii = input.nextInt();
            int jj = input.nextInt();
            int i = ii < jj ? ii : jj;
            int j = ii < jj ? jj : ii;
            for (int k = i; k <= j; k++) {
                int n = countCycles_V5(k);
                // System.out.println("k: " + k + " n: " + n);
                if (n > max) {
                    max = n;
                }
            }
            System.out.println(ii + " " + jj + " " + max);
        }
        // System.out.println("time: " + (System.currentTimeMillis() - t1));
        System.exit(0);
    }

    private static Map<Integer, Integer> tableCCInt = new HashMap<>();

    private static int countCycles_V5(int k) {
        if (tableCCInt.containsKey(k)) {
            return tableCCInt.get(k);
        } else {
            if (k == 1) {
                return 1;
            } else if (k % 2 == 0) {
                int n = 1 + countCycles_V5(k / 2);
                tableCCInt.put(k, n);
                return n;
            } else {
                int n = 1 + countCycles_V5(k * 3 + 1);
                tableCCInt.put(k, n);
                return n;
            }
        }
    }
}
