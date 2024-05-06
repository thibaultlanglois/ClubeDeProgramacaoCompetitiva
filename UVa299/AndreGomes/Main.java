import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt(); // Número de casos de teste
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(); // Número de carruagens
            int[] carriages = new int[n];
            for (int j = 0; j < n; j++) {
                carriages[j] = scanner.nextInt();
            }
            int swaps = countSwaps(carriages);
            System.out.println("Optimal train swapping takes " + swaps + " swaps.");
        }

        scanner.close();
    }

    // Função que conta o número mínimo de trocas necessárias para ordenar os carros
    private static int countSwaps(int[] arr) {
        int swaps = 0;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
        } while (swapped);
        return swaps;
    }
}
