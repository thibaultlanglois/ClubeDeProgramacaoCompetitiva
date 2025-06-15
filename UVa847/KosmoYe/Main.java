import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(determineWinner(n));
        }
        sc.close();
    }

    public static String determineWinner(long n) {

        boolean stanTurn = true;
        int upper = 1;
        
        while (true) {
            if (stanTurn) {
                upper *= 9;
            } else {
                upper *= 2;
            }
            
            if (n <= upper) {
                if (stanTurn) {
                    return "Stan wins.";
                } else {
                    return "Ollie wins.";
                }
            }
            
            stanTurn = !stanTurn;
        }
    }
}
