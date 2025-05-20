import java.util.Scanner;
// UVa 107
/**
 * Esta classe Main resolve o problema "Cat in the Hat". 
 * 
 * @author Tiago Gouveia (fc63782);
 * 
 * Dada a altura(h) do gato inicial e o num
 * de gatos trabalhadores, o objetivo é calcular:
 * 
 * 1..... Quantos gatos não estão a trabalhar (ou seja, basicamente os de altura maior que 1)
 * 2..... A altura total de todos os gatos empilhados
 * 
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final double rigor = 1e-10; // precisão para comparação de logs (variavel definida devido a organização);

        // enquanto h && w forem diferentes de 0
        while (true) {
            int h = sc.nextInt(); // input da altura do gato "chefe"
            int w = sc.nextInt(); // input do num total de gatos trabalhadores

            // se ambos forem 0, então acabou
            if (h == 0 && w == 0) {
                break;
            }

            int n = 2; // começamos com n = 2 (vamos tentar encontrar o valor certo)

            // se só houver um gato a trabalhar, então n = 1
            if (w == 1) {
                n = 1;
            } else {
                double logRatio = Math.log(h) / Math.log(w); // razão entre os logs da altura e do número de trabalhadores

                // vamos ajustando o valor de n até o log bater certo (dentro do rigor)
                while (Math.abs(Math.log(n + 1) / Math.log(n) - logRatio) > rigor) {
                    n++;
                }
            }

            int k = 0;
            int temp = h;

            // vamos dividir até encontrar o k certo (nível mais baixo da árvore de gatos)
            while (temp >= (n + 1)) {
                temp /= (n + 1);
                k++;
            }

            // calcular quantos gatos não estão a trabalhar (os de cima)
            int notWorking;
            if (n > 1) {
                double somaGeom = (Math.pow(n, k) - 1) / (n - 1); // fórmula da soma geométrica (prog. geom.);
                notWorking = (int) somaGeom;
            } else {
                double log2 = Math.log(h) / Math.log(2); //guardamos em double e depois;
                notWorking = (int) log2; // convertemos para int (ajuda-me em legibilidade);
            }

            // calcular a altura total dos gatos empilhados
            double potN = Math.pow(n, k + 1);
            double potNMais1 = Math.pow(n + 1, k);
            double alturaBase = h / potNMais1;
            double alturaTotal = potN * alturaBase;
            int height = (int) (h * (n + 1) - alturaTotal); // total = altura dos trabalhadores + não trabalhadores

            // print final com os dois resultados
            System.out.println(notWorking + " " + height);
        }
        sc.close();
    }
}
