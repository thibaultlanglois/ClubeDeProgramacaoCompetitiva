import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            while(sc.hasNext()) {
                int i,j,max=0;
                i = sc.nextInt();
                j = sc.nextInt();
                for(int k = Math.min(i, j); k <= Math.max(i, j); k++) {
                    max = Math.max(max, recursion(k,1));
                }
                System.out.println(String.format("%d %d %d", i, j, max));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
        
    }
    public static int recursion(int n, int v) {
        if(n==1)
            return v;
        return n%2 == 0?recursion(n/2,v+1):recursion(3*n+1,v+1);
    }
}
