import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int totalCarros = sc.nextInt();
            if (totalCarros == 0) break;  // Termina o programa quando for zero

            while (true) {
                int primeiroCarro = sc.nextInt();
                if (primeiroCarro == 0) break;  // Termina o bloco atual de sequencias

                int[] ordemPretendida = new int[totalCarros];
                ordemPretendida[0] = primeiroCarro;

                // Ler o resto da ordem pretendida dos carros
                for (int i = 1; i < totalCarros; i++) {
                    ordemPretendida[i] = sc.nextInt();
                }

                String resposta = verificarOrdem(ordemPretendida, totalCarros);
                System.out.println(resposta);
            }
            System.out.println();  // Linha vazia para separar blocos
        }

        sc.close();
    }

    // Metodo que simula o estacionamento e verifica se a ordem e possivel
    static String verificarOrdem(int[] ordem, int n) {
        int proximoCarro = 1;   // Proximo carro que chega para estacionar
        int indiceAtual = 0;    // Indice da ordem pretendida
        Stack<Integer> estacionamento = new Stack<>();

        estacionamento.push(proximoCarro++);  // Comeca com o primeiro carro na pilha

        while (indiceAtual < n) {
            if (!estacionamento.isEmpty() && estacionamento.peek() == ordem[indiceAtual]) {
                estacionamento.pop();   // Sai da pilha se for o carro que se pretende
                indiceAtual++;
            } else {
                if (proximoCarro > n) {
                    // Ja nao ha mais carros para entrar e a ordem nao foi obtida
                    return "No";
                } else {
                    estacionamento.push(proximoCarro++);
                }
            }
        }
        return "Yes";  // Ordem conseguida
    }
}
