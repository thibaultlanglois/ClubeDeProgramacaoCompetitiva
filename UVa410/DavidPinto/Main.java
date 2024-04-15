import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int k = 1;
        while (input.hasNext()) {
            System.out.printf("Set #%d\n", k++);
            int chamber = input.nextInt();
            int species = input.nextInt();
            input.nextLine();
//             Cria um array de int com "2 espaços reservados" para cada maquina
            int[] masses = new int[chamber << 1];
            fillArray(masses, input.nextLine());
            double media = (double) sum(masses) / chamber;

            int[] massesInOrder = masses.clone();
            Arrays.sort(massesInOrder);
            boolean[] notAlreadyUsed = new boolean[massesInOrder.length];
            int currentChamber = 0;
            double imbalance = 0;
            // repetir para todas as especies
            for (int i = 0; i < species; i++) {
                int indexOfNumber = Arrays.binarySearch(massesInOrder, masses[i]);
                indexOfNumber = hasThisNumber(massesInOrder, massesInOrder[indexOfNumber], indexOfNumber,
                        notAlreadyUsed);
                if (indexOfNumber >= 0) {
                    int indexMatchingNumber = massesInOrder.length - indexOfNumber - 1;
                    System.out.printf(" %d: ", currentChamber++);

                    if (massesInOrder[indexOfNumber] != 0) {
                        System.out.print(massesInOrder[indexOfNumber]);
                        if (massesInOrder[indexMatchingNumber] != 0) {
                            System.out.print(" ");
                        }
                    }
                    if (massesInOrder[indexMatchingNumber] != 0) {
                        System.out.print(massesInOrder[indexMatchingNumber]);

                    }
                    System.out.println();
                    // calculo do imabalnce
                    imbalance += Math.abs((massesInOrder[indexOfNumber] + massesInOrder[indexMatchingNumber]) - media);

                    notAlreadyUsed[indexOfNumber] = true;
                    notAlreadyUsed[indexMatchingNumber] = true;
                }
            }
            for (int i = currentChamber; i < chamber; i++) {
                System.out.printf(" %d:\n", i);
                imbalance += media;
            }
            System.out.printf("IMBALANCE = %.5f\n\n", imbalance);
        }
        input.close();

    }

    private static int hasThisNumber(int[] masses, int number, int index, boolean[] notAlreadyUsed) {
	if(index >0) {
            int indexBase = index-1;
            while (masses[indexBase] == number) {
                if (!notAlreadyUsed[indexBase]) {
                    return indexBase;
                }
                if (--indexBase <= 0)
                    break;
            }
        }
        while (masses[index] == number) {
            if (!notAlreadyUsed[index]) {
                return index;
            }
            if (++index >= masses.length)
                break;
        }
        return -1;
    }

    private static void fillArray(int[] specimes, String massString) {
        String[] massArray = massString.split(" ");
        for (int i = 0; i < specimes.length; i++) {
            specimes[i] = Integer.parseInt(massArray[i]);
            if (!(massArray.length > i + 1))
                break;
        }
    }

    private static int sum(int[] a) {
        int sum = 0;
        for (int x : a) {
            sum += x;
        }
        return sum;
    }
}
