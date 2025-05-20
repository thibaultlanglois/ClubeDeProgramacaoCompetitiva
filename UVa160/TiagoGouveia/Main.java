import java.util.Scanner;
// UVa 160
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lista dos primos que vamos usar para contar no fatorial
        int[] primos = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                        31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                        73, 79, 83, 89, 97};

        int n = sc.nextInt();

        while (n != 0) {
            int[] contagem = new int[primos.length];

            // Vamos fatorizar todos os números de 2 até n
            for (int i = 2; i <= n; i++) {
                int num = i;
                for (int j = 0; j < primos.length; j++) {
                    while (num % primos[j] == 0) {
                        contagem[j]++;
                        num = num / primos[j];
                    }
                }
            }

            // Escrever tipo "   5! =" alinhado à direita
            if (n < 10) {
                System.out.print("  " + n + "! =");
            } else if (n < 100) {
                System.out.print(" " + n + "! =");
            } else {
                System.out.print(n + "! =");
            }

            int contaPrint = 0;
            for (int i = 0; i < contagem.length; i++) {
                if (contagem[i] > 0) {
                    // De 15 em 15 números, muda de linha
                    if (contaPrint == 15) {
                        System.out.println();
                        System.out.print("      ");
                        contaPrint = 0;
                    }
                    
                    if (contagem[i] < 10) {
                        System.out.print("  " + contagem[i]);
                    } else if (contagem[i] < 100) {
                        System.out.print(" " + contagem[i]);
                    } else {
                        System.out.print(contagem[i]);
                    }

                    contaPrint++;
                }
            }

            System.out.println();
            n = sc.nextInt();
        }

        sc.close();
    }
}
