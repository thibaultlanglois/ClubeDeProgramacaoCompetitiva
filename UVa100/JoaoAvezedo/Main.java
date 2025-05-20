import java.util.Scanner;
import java.util.Arrays;


/*
 * Classe que calcula o comprimento do ciclo para um dado nÃºmero
 * Dados i e j, verifica qual o numero no intervalo [i, j] que maior ciclo percorreu
 */

public class CycleLength {

/*
 * Calcula o comprimento do ciclo para um numero n de acordo com as regras
 * 
 * @param n O numero para calcular o comprimento do ciclo
 * @return O comprimento do ciclo
 */
    public static int cycleLength(int n) {

        int length = 1;

        while(n != 1){
            if(n % 2 == 1){
                n = 3 *n + 1;
            }
            else{
                n /= 2;
            }

            length++;
        }
        return length;
       
    }

/* Funcao main responsavel por receber do input de utilizador os inteiros i e j, construir um array com o tamanho do intervalo
 * utiliza a classe Arrays para ordenar o array e verificar o maior ciclo percorrido no intervalo
 * 
 */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while(sc.hasNextInt()){

            try{
                
                int i = sc.nextInt();
                int j = sc.nextInt();
    
                //Trocar o conteudo de j com i, caso o valor de j seja menor que o valor de i
                if(i > j){
                    int temp = i;
                    i = j;
                    j = temp;
                }
    
                int [] armazenarComprimento = new int [j - i + 1];
    
                for(int k = i; k <= j; k++){
                    armazenarComprimento[k - i] = cycleLength(k);
                    
                }
    
                int max = Arrays.stream(armazenarComprimento).max().getAsInt();
    
                System.out.println(i + " " + j + " " + max);


            } catch(Exception e){
                // Caso o utilizador introduza um valor invalido
                System.out.println("Entrada invalida, tente novamente");
            }
        }
        sc.close();
    }
}
