import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int i, j, k, result;

        while (sc.hasNext()) {
            i = sc.nextInt();
            j = sc.nextInt();
            result = 0;

            for (k = Math.min(i, j); k <= Math.max(i, j); k++) {
                result = Math.max(ciclo(k), result);
            }
            System.out.println(String.format("%d %d %d", i, j, result));
        }

    }

    static int ciclo(int k) {
        int count = 1;
        while (k != 1) {
            if (k % 2 == 0) k /= 2;
            else k = 3 * k + 1;
            count++;
        }
        return count;
    }
}
