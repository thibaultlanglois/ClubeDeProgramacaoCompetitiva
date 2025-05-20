import java.util.Scanner;

class Main {

    public static void main (String[] args) {
        
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextInt()) {
                int[] garbage = new int[9];
                for (int i = 0; i < 9; i++) {
                    garbage[i] = sc.nextInt();
                }
                              
                int[] results = {BGC(garbage), BCG(garbage), CBG(garbage), CGB(garbage), GBC(garbage), GCB(garbage)};
                String[] permutations = {"BGC", "BCG", "CBG", "CGB", "GBC", "GCB"};
                
                int min = Integer.MAX_VALUE;
                String bestPermutation = "";
                
                for (int i = 0; i < results.length; i++) {
                    if (results[i] < min || (results[i] == min && permutations[i].compareTo(bestPermutation) < 0)) {
                        min = results[i];
                        bestPermutation = permutations[i];
                    }
                }
                System.out.println(bestPermutation + " " + min);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }


    }

    public static int BGC (int [] garbage) {
        return garbage[1] + garbage[2] + garbage[3] + garbage[5] + garbage[6] + garbage[7];
    }

    public static int BCG (int [] garbage) {
        return garbage[1] + garbage[2] + garbage[3] + garbage[4] + garbage[6] + garbage[8];
    }

    public static int CBG (int [] garbage) {
        return garbage[0] + garbage[1] + garbage[4] + garbage[5] + garbage[6] + garbage[8];
    }

    public static int CGB (int [] garbage) {
        return garbage[0] + garbage[1] + garbage[3] + garbage[5] + garbage[7] + garbage[8];
    }

    public static int GBC (int [] garbage) {
        return garbage[0] + garbage[2] + garbage[4] + garbage[5] + garbage[6] + garbage[7];
    }

    public static int GCB (int [] garbage) {
        return garbage[0] + garbage[2] + garbage[3] + garbage[4] + garbage[7] + garbage[8];
    }

}
