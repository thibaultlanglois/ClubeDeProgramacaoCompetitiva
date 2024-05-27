import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCounter = 1;
        int numberOfIntsToSort = scanner.nextInt();
        while (numberOfIntsToSort != 0) {
            int[] values = new int[numberOfIntsToSort];
            int[] indexes = new int[numberOfIntsToSort];
            boolean[] done = new boolean[numberOfIntsToSort];
            for (int i = 0; i < numberOfIntsToSort; i++) {
                values[i] = scanner.nextInt();
            }
            for (int i = 0; i < numberOfIntsToSort; i++) {
                indexes[i] = i;
            }
            for (int i = 0; i < numberOfIntsToSort; i++) {
                for (int j = i + 1; j < numberOfIntsToSort; j++) {
                    if (values[indexes[i]] > values[indexes[j]]) {
                        int temp = indexes[i];
                        indexes[i] = indexes[j];
                        indexes[j] = temp;
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < numberOfIntsToSort; i++) {
                min = Math.min(min, values[i]);
            }
            int ans = 0;
            for (int i = 0; i < numberOfIntsToSort; i++) {
                if (!done[i]) {
                    int min2 = values[i];
                    int index = i;
                    int sum = 0;
                    int size = 0;
                    while (!done[index]) {
                        done[index] = true;
                        sum += values[index];
                        min2 = Math.min(min2, values[index]);
                        index = indexes[index];
                        size++;
                    }
                    ans += Math.min(sum + (size - 2) * min2, sum + min2 + (size + 1) * min);
                }
            }
            System.out.printf("Case %d: %d\n\n", caseCounter++, ans);
            numberOfIntsToSort = scanner.nextInt();
        }
    }
}
