import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;

public class Main {
   static final int LIMIT = 300000 + 11;
   static long[] primes = new long[LIMIT]; // array para ver que números são primos
   static long[] p = new long[LIMIT]; // array para guardar os numeros primos
   static double[] fib = new double[LIMIT]; // array que guarda os números fibonnaci

   public static void main(String[] args) throws FileNotFoundException {
      Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
      generatePrimes(); // Calcula todos os números primos até ao LIMIT
      generateFibonnaci(); // Calcula a sequência dos números de fibonnaci primos

      while (scanner.hasNextLong()) {
         long n = scanner.nextLong();
         System.out.println((long) solve(n));
      }

      scanner.close();
   }

   public static void generatePrimes() {
      // Preenche o array assumindo que todos são primos
      for (int i = 0; i < LIMIT; i++) {
         primes[i] = 1; // 1 == true && 0 == false
      }
      primes[0] = primes[1] = 0;

      // Crivo de Eratóstenes
      for (int i = 2; i * i < LIMIT; i++) {
         if (primes[i] == 1) {
            for (int j = i * i; j < LIMIT; j += i) {
               primes[j] = 0;
            }
         }
      }
      primes[1] = 1;

      // Guardar todos os números primos no array p[]
      int count = 0;
      for (int i = 0; i < LIMIT; i++) {
         if (primes[i] == 1) {
            p[count] = i;
            count++;
         }
      }

      p[1] = 3;
      p[2] = 4;
   }

   public static void generateFibonnaci() {
      fib[1] = 1;
      fib[2] = 1;
      boolean flag = false;

      for (int i = 3; i < LIMIT; i++) {
         fib[i] = fib[i - 1] + (flag ? fib[i - 2] / 10.0 : fib[i - 2]);
         flag = false;
         while (fib[i] >= 1_000_000_000L) {
            fib[i] /= 10.0;
            flag = true;
         }
      }
   }

   // Método para resolver um caso de teste
   public static double solve(long n) {
      return fib[(int) p[(int) n]];
   }

}
