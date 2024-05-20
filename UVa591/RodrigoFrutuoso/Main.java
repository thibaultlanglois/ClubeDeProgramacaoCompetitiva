import java.util.Scanner;
import java.util.ArrayList;

class Main {

    /**
     * UVa 591 Box of Bricks
     * It reads the number of brick stacks and the input for each stack. It
     * continues to do this until the number of stacks is 0. For each set of stacks,
     * it calculates and prints the minimum number of movements needed to equalize
     * the number of bricks in each stack
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int setNumber = 0;
        while (true) {
            int numberOfStacks = sc.nextInt();
            if (numberOfStacks == 0) {
                break;
            }
            ArrayList<Integer> numberOfBricksPerStack = readInput(sc, numberOfStacks);
            setNumber++;
            printResult(setNumber, calculateMinimumMovement(numberOfBricksPerStack));
        }
        sc.close();
    }

    /**
     * Reads the user input for the number of bricks in each stack.
     * 
     * @param sc             The scanner to read the user input.
     * @param numberOfStacks The number of stacks.
     * @return An array containing the number of bricks in each stack.
     */
    private static ArrayList<Integer> readInput(Scanner sc, int numberOfStacks) {
        ArrayList<Integer> numberOfBricksPerStack = new ArrayList<>(numberOfStacks);
        for (int i = 0; i < numberOfStacks; i++) {
            numberOfBricksPerStack.add(sc.nextInt());
        }
        return numberOfBricksPerStack;
    }

    /**
     * Prints the result of the calculation.
     * 
     * @param setNumber The current set number.
     * @param movements The minimum number of movements needed to equalize the
     *                  number of bricks in each stack.
     */
    private static void printResult(int setNumber, int movements) {
        System.out.println("Set #" + setNumber);
        System.out.println("The minimum number of moves is " + movements + ".");
        System.out.println();
    }

    /**
     * Calculates the minimum number of movements needed to equalize the number of
     * bricks in each stack.
     * 
     * @param numberOfStacks                The number of stacks.
     * @param arrayOfNumberOfBricksPerStack The array containing the number of
     *                                      bricks in each stack.
     * @return The minimum number of movements.
     */
    private static int calculateMinimumMovement(ArrayList<Integer> numberOfBricksPerStack) {
        int movements = 0;
        int requiredNumberOfBricksPerStack = calculateTotalNumberOfBricks(numberOfBricksPerStack)
                / numberOfBricksPerStack.size();
        for (Integer bricks : numberOfBricksPerStack) {
            if (requiredNumberOfBricksPerStack < bricks) {
                movements += (bricks - requiredNumberOfBricksPerStack);
            }
        }
        return movements;
    }

    /**
     * Calculates the total number of bricks in all stacks.
     * 
     * @param arrayOfNumberOfBricksPerStack The array containing the number of
     *                                      bricks in each stack.
     * @return The total number of bricks.
     */
    public static int calculateTotalNumberOfBricks(ArrayList<Integer> numberOfBricksPerStack) {
        int totalNumberOfBricks = 0;
        for (Integer bricks : numberOfBricksPerStack) {
            totalNumberOfBricks += bricks;
        }
        return totalNumberOfBricks;
    }
}
