import java.util.Scanner;

class Main {

    public static int gcd(int a, int b) {
    int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    
    public static int pow(int exponent) {
        

        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseN = 1;
        int j,k,num,den,gcd;
        String input, decimal, parts[];
        
        while (true) {
            input = scanner.nextLine().trim();
            if (input.equals("-1")) break; 

            parts = input.split("\\s+");
            j = Integer.parseInt(parts[0]);
            decimal = parts[1].substring(2);

            k = decimal.length() - j;

            if (j == 0) {
                num = Integer.parseInt(decimal);
                den = pow(k);
                gcd = gcd(num, den);
                num /= gcd;
                den /= gcd;
                System.out.printf("Case %d: %d/%d%n", caseN++, num, den);
            } else {
                num = Integer.parseInt(decimal.substring(0, k + j)) - (k == 0 ? 0 : Integer.parseInt(decimal.substring(0, k)));
                den = pow(k + j) - pow(k);

                gcd = gcd(num, den);
                num /= gcd;
                den /= gcd;

                System.out.printf("Case %d: %d/%d%n", caseN++, num, den);
            }
        }

        scanner.close();
    }
}
