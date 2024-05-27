import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int numParticipants = sc.nextInt();
            int budget = sc.nextInt();
            int numHotels = sc.nextInt();
            int numWeeks = sc.nextInt();
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < numHotels; i++) {
                int pricePerPerson = sc.nextInt();
                for (int j = 0; j < numWeeks; j++) {
                    int numBedsAvailable = sc.nextInt();
                    if (numBedsAvailable >= numParticipants) {
                        minCost = Math.min(minCost, pricePerPerson * numParticipants);
                    }
                }
            }
            if (minCost <= budget) {
                System.out.println(minCost);
            } 
            else {
                System.out.println("stay home");
            }
        }
        sc.close();
    }
}
