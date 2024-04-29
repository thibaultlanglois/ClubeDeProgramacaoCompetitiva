// package clube;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            if (n == 0) 
                break; // Sai do loop se o imput for 0
            int m = 1; // Número de estações que vão ser fechadas sequencialmente
            while (true) {
                if (simulate(n, m) == 13) {
                    // Se a estação 13 for a última a ser fechada depois de fechar m estações
                    System.out.println(m);
                    break;
                }
                m++;
            }
        }
        scanner.close();
    }

    // Simula o fechamento de m estações sequencialmente e retorna a estação que faltou 
    private static int simulate(int n, int m) {
        boolean[] stations = new boolean[n];
        int remaining = n;
        int current = 0; // Index da estação atual

        while (remaining > 1) {
            stations[current] = true; // Marca a estação atual como fechada
            remaining--;

            int count = 0;
            while (count < m) {
                current = (current + 1) % n; // Move para a próxima estação
                if (!stations[current]) {
                    count++;
                }
            }
        }

        // Encontra a última estação que faltou
        for (int i = 0; i < n; i++) {
            if (!stations[i]) {
                return i + 1;
            }
        }

        return -1; // Isto nunca vai acontecer
    }
}
