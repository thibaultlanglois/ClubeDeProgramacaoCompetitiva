import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
       while(sc.hasNextInt()){
        int n = sc.nextInt();

       if(n == 0){ 
        sc.close(); 
        break;

    }
       else{
           System.out.println(findPair(n));
       }
    }

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

    public static int nOfPrimes (int n){
        int count = 0;
        for (int i = 1; i < n; i++){
            if (isPrime(i)){
                count++;
            }
        }
        return count;
    }

    public static int[] allPrimes (int n){
        int allP[] = new int[nOfPrimes(n)];
        int index = 0;
        for (int i = 0; i < n; i++){
            if (isPrime(i)){
                allP[index] = i;
                index++;
                }
            }
            return allP;
        }

    public static int findPair (int n){
        int[] allP = allPrimes(n);
        int left = 0;
        int right = allP.length - 1;
        int howManyPairs = 0;

        while (left <= right){
            int sum = allP[left] + allP[right];

            if(sum == n){
                howManyPairs++;
                left++;
                right--;
            }
            else if(sum < n){
                left++;
            }
            else{
                right--;
            }
        }
        return howManyPairs;
    }
}
