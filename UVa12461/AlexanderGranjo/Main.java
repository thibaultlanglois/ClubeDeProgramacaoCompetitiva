import java.util.Scanner;
// UVa 12461
class Main {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            
            Fraction result = num(n);
            System.out.println(result.toString());
        }
        sc.close();
    }
    
   
    public static Fraction num(int n) {
        if (n == 1) return new Fraction(0, 1);
        else return new Fraction(1, 2);
    }
    
    static class Fraction {
        int num;
        int den;
        
    
        public Fraction(int num, int den) {
            this.num = num;
            this.den = den;
            simplifyFrac();
        }
        
        
        public Fraction addFrac(Fraction other) {
            int newNum = this.num * other.den + other.num * this.den;
            int newDenom = this.den * other.den;
            return new Fraction(newNum, newDenom);
        }

        
        private int gcd(int a, int b) {
            while (b != 0) {
                int t = b;
                b = a % b;
                a = t;
            }
            return a;
        }

        
        public Fraction multFrac(Fraction other) {
            int newNum = this.num * other.num;
            int newDenom = this.den * other.den;
            return new Fraction(newNum, newDenom);
        }
        
        
        private void simplifyFrac() {
            if (num == 0) {
                den = 1;
                return;
            }
            int g = gcd(Math.abs(num), den);
            num /= g;
            den /= g;
        }
        
        
        public String toString() {
            return num + "/" + den;
        }
    }
}
