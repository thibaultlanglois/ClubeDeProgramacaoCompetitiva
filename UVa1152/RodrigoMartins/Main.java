
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int numberOfCases = sc.nextInt();
        sc.nextLine();

        boolean firstCase = true;

        for (int caseIndex = 0; caseIndex < numberOfCases; caseIndex++) {
            if (caseIndex > 0) {
                sc.nextLine();
            }

            int n = sc.nextInt();
            sc.nextLine();

            int[] A = new int[n];
            int[] B = new int[n];
            int[] C = new int[n];
            int[] D = new int[n];

            for (int i = 0; i < n; i++) {
                String[] line = sc.nextLine().split(" ");
                A[i] = Integer.parseInt(line[0]);
                B[i] = Integer.parseInt(line[1]);
                C[i] = Integer.parseInt(line[2]);
                D[i] = Integer.parseInt(line[3]);
            }

            Map<Integer, Integer> sumAB = new HashMap<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = A[i] + B[j];
                    sumAB.put(sum, sumAB.getOrDefault(sum, 0) + 1);
                }
            }

            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = C[i] + D[j];
                    count += sumAB.getOrDefault(-sum, 0);
                }
            }

            if (!firstCase) {
                System.out.println();
            }
            System.out.println(count);
            firstCase = false;
        }

        sc.close();
    }
}
