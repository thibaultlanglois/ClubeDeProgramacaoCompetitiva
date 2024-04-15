import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int howManyRoutes = sc.nextInt();

        for (int currentRoute = 1; currentRoute <= howManyRoutes; currentRoute++) {

            int howManyStops = sc.nextInt();
            int maxNicenessSum = 0;
            int currentNicenessSum = 0;
            int begin = 1;
            int end = 1;
            int currentBegin = 1;

            for (int currentStop = 1; currentStop < howManyStops; currentStop++) {
                currentNicenessSum += sc.nextInt();
                
                if (currentNicenessSum < 0) {
                    currentNicenessSum = 0;
                    currentBegin = currentStop + 1;
                }
                
                if (currentNicenessSum >= maxNicenessSum) {
                    if (currentNicenessSum > maxNicenessSum || (currentNicenessSum == maxNicenessSum && currentStop + 1 - currentBegin > end - begin)) {
                        maxNicenessSum = currentNicenessSum;
                        begin = currentBegin;
                        end = currentStop + 1;
                    }
                }
            }
            if (maxNicenessSum > 0)
                System.out.println("The nicest part of route " + currentRoute + " is between stops " + begin + " and " + end);
            else
                System.out.println("Route " + currentRoute + " has no nice parts");
        }  
        sc.close();
    }
}

// b = howManyRoutes
// r = currentRoute
// s = howManyStops
// i = currentStop
