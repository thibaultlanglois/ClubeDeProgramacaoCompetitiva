import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
//10928
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int p = input.nextInt();
            int min = Integer.MAX_VALUE;
            boolean[] pBest = new boolean[p];
            input.nextLine();
            for (int j = 0; j < p; j++) {
                int current = input.nextLine().split(" ").length;
                if (min > current) {
                    pBest = new boolean[p];
                    pBest[j] = true;
                    min = current;
                }
                if (min == current) {
                    pBest[j] = true;
                }
            }
            int index = 1;
            boolean first = true;
            for (boolean x : pBest) {
                if (x) {
                    if (first) {
                        System.out.print(index);
                        first = false;
                    }
                    else {
                        System.out.print(" " + index);
                    }
                }
                index++;
            }
            System.out.println();
        }
    }
}
