import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i,j,max;
        while (s.hasNext()) {
            i = s.nextInt();
            j = s.nextInt();
            max = maxInCycle(Math.min(i,j),Math.max(i,j));
            System.out.printf("%d %d %d%n", i, j, max);
        }
    }

    static int maxInCycle(int i,int j) {
        int max = 0;
        for(int k = i; k <= j; k++) {
            int now = cycleSize(k);
            if(max < now) max = now;
        }
        return max;
    }

    static int cycleSize(int n) {
        int counter = 1;
        while(n != 1) {
            if(n % 2 == 1) n = 3*n+1;
            else n = n/2;
            counter++;
        }
        return counter;
    }
}
