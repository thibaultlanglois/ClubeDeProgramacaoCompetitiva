import java.io.FileNotFoundException;
import java.util.Scanner;

class Main{
    public static final String EOL = System.lineSeparator(); 
    public static void main(String[] args) throws FileNotFoundException {
        String normal  = "AEHIJLMOSTUVWXYZ12358";
        String reverso = "A3HILJMO2TUVWXY51SEZ8";
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            StringBuilder sb1 = new StringBuilder(sc.nextLine());
            boolean palindromo = palindromo(sb1);
            boolean palavra_espelhada = palavra_espelhada(sb1,normal,reverso);

            if (palindromo && !palavra_espelhada){
                System.out.println(sb1 + " -- is a regular palindrome.");
            }
            else if(!palindromo && palavra_espelhada){
                System.out.println(sb1 + " -- is a mirrored string.");
            }
            else if(palindromo && palavra_espelhada){
                System.out.println(sb1 + " -- is a mirrored palindrome.");
            }
            else{
                System.out.println(sb1 + " -- is not a palindrome.");
            }
            System.out.println();
        }

        sc.close();
    }

    public static boolean palindromo(StringBuilder sb1){
        for(int i = 0; i < sb1.length() / 2; i++){
            if(sb1.charAt(i) != sb1.charAt(sb1.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    public static boolean palavra_espelhada(StringBuilder sb1, String normal, String reverso){
        StringBuilder espelhado = new StringBuilder();
        for(int i = sb1.length() - 1; i >= 0; i--){
            char c = sb1.charAt(i);
            int index = normal.indexOf(c);
            if (index == -1) return false; // caractere sem espelhamento
            espelhado.append(reverso.charAt(index));
        }
        return sb1.toString().equals(espelhado.toString());
    }

}
