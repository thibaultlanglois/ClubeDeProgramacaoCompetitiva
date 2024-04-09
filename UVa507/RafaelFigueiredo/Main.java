import java.util.Scanner;
/**
 * UVa 507 Jill Rides Again
 */
class Main {
    
    public static void main(String[] args) {
	
        Scanner s = new Scanner((System.in));
        int numberOfRoutes = s.nextInt();
        int numberOfStops;
        int routeCounter = 1;
	
        while (s.hasNextLine() && routeCounter <= numberOfRoutes) {
	    
            numberOfStops = s.nextInt();
            int[] values = new int[numberOfStops - 1];
            for (int i = 0; i < values.length; i++) {
                values[i] = s.nextInt();
            }
            int sum = 0;
            int maxVal = 0;
            int size = values.length;
            int start = 0;
            int end = 0;
            int tempStart = 0;
            for (int i = 1; i <= size; i++) {
                sum += values[i - 1];
                if (sum < 0) {
                    sum = 0;
                    tempStart = i;
                }
                if (maxVal < sum || sum == maxVal && i - tempStart > end - start) {
                    maxVal = sum;
                    start = tempStart;
                    end = i;
                }
            }
            if (maxVal > 0) {
                System.out.println(String.format("The nicest part of route %d is between stops %d and %d", routeCounter, start + 1, end + 1));
            } else {
                System.out.println(String.format("Route %d has no nice parts", routeCounter));
            }
            routeCounter++;
        }
    }
}
