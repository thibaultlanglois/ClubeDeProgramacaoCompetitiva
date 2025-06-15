import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Queue<Integer> testCases = readTestCases(scanner);
        StringBuilder output = new StringBuilder();

        while (!testCases.isEmpty()) {
            int k = testCases.poll();
            int result = calculateMinimumN(k);
            output.append(result).append("\n\n");
        }

        scanner.close();

        if (output.length() >= 2) {
            output.setLength(output.length() - 1); 
        }

        System.out.print(output);
    }

    private static Queue<Integer> readTestCases(Scanner scanner) {
        Queue<Integer> testCases = new LinkedList<>();
        int t = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                t = Integer.parseInt(line);
                break;
            }
        }

        while (testCases.size() < t && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                testCases.add(Integer.parseInt(line));
            }
        }

        return testCases;
    }

    public static int calculateMinimumN(int k) {
        final int absK = Math.abs(k);
        int n = 1;
        long sum = 1;

        while (sum < absK || (sum - absK) % 2 != 0) {
            n++;
            sum += n;
        }

        return n;
    }
}
