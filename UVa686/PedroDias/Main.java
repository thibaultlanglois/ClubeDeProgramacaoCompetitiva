import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) 
            	break;
            System.out.println(Eng(n, criador(n))); 
        }
        sc.close();
	}
	
	public static int Eng(int n, int[] a) {
	    int p1 = 0;
	    int p2 = a.length - 1;
	    int counter = 0;

	    while (p1 <= p2) {  
	        if (a[p1] + a[p2] == n) {
	            counter++;
	            p1++;
	            p2--;
	        } else if (a[p1] + a[p2] < n) {
	            p1++;
	        } else {
	            p2--;
	        }
	    }
	    return counter;
	}
	
	public static int[] criador(int n) {
        int counter = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                counter++;
            }
        }

        int[] primes = new int[counter];
        counter = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primes[counter] = i;
                counter++;
            }
        }
        return primes;
    }
	
	public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true; 
        if (n % 2 == 0) return false; 

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

