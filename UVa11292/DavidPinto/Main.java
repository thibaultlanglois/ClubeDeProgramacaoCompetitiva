import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

class Main {
//Uva 11292
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int heads = input.nextInt();
        int warriors = input.nextInt();
        do {
            if (warriors >= heads) {
                int[] headsDim = new int[heads];
                for (int i = 0; i < heads; i++) {
                    headsDim[i] = input.nextInt();
                }
                Arrays.sort(headsDim);
                int[] warriorsHeight = new int[warriors];
                for (int i = 0; i < warriors; i++) {
                    warriorsHeight[i] = input.nextInt();
                }
                Arrays.sort(warriorsHeight);
                int gold = 0, k = 0, d = 0;
                while (d < heads && k < warriors) {
                    while (k < warriors&&headsDim[d] > warriorsHeight[k]) {
                        k++;
                    }
                    if (k == warriors) {
                        break;
                    }
                    gold += warriorsHeight[k];
                    d++;
                    k++;
                }
                if(d==heads) {
                    System.out.printf("%d\n", gold);
                }
                else {
                    System.out.println("Loowater is doomed!");
                }
            }
            else {
                System.out.println("Loowater is doomed!");
            }
            heads = input.nextInt();
            warriors = input.nextInt();
        } while (heads != 0 && warriors != 0);
    }
}
