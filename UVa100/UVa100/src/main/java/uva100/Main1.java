package uva100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tl
 */
class Main1 {

    // Runtime error ...
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        String line = input.readLine();
        while (line != null && line.length() > 2) {
            int max = 0;
            //34      90 → ['34', '90']
            //    34    90 → ['', '34', '90]
            // s.trim() → remover os carateres brancos no inicio
            // e no fim da string
            String[] values = line.split("[ \t]+");
            int i = Integer.parseInt(values[0]);
            int j = Integer.parseInt(values[1]);
            // wrong ! i may be > j !!
            for (int k = i; k <= j; k++) {
                int n = countCycles_V1(k);
                if (n > max) {
                    max = n;
                }
            }
            System.out.println(i + " " + j + " " + max);
            line = input.readLine();
        }
        input.close();
        System.exit(0);
    }

    /**
     * iterative implementation
     *
     * @param k
     * @return
     */
    private static int countCycles_V1(int k) {
        int count = 1;
        while (k != 1) {
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = 3 * k + 1;
            }
            count++;
        }
        return count;
    }

    private static Map<Integer, Integer> table = new HashMap<>();

    /**
     * Let's try to cache the values, just in case. (it is not enough !)
     *
     * @param k
     * @return
     */
    private static int countCycles_V2(int k) {
        if (table.containsKey(k)) {
            // System.out.println("Bingo ! " + k);
            return table.get(k);
        } else {
            int n = countCycles_V1(k);
            table.put(k, n);
            return n;
        }
    }

    /**
     * Recursive version
     *
     * @param k
     * @return
     */
    private static int countCycles_V3(int k) {
        if (k == 1) {
            return 1;
        } else if (k % 2 == 0) {
            return 1 + countCycles_V3(k / 2);
        } else {
            return 1 + countCycles_V3(k * 3 + 1);
        }
    }

    private static Map<Integer, Integer> tableCC = new HashMap<>();

    /**
     * This one is better !
     *
     * @param k
     * @return
     */
    private static int countCycles_V4(int k) {
        if (tableCC.containsKey(k)) {
            return tableCC.get(k);
        } else {
            if (k == 1) {
                return 1;
            } else if (k % 2 == 0) {
                int n = 1 + countCycles_V4(k / 2);
                tableCC.put(k, n);
                return n;
            } else {
                int n = 1 + countCycles_V4(k * 3 + 1);
                tableCC.put(k, n);
                return n;
            }
        }
    }
}
