import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem #11414
 * 
 * RESULT:
 * >> Accepted -> Run Time: 0.300 [Submission 30437115]
 * 
 * @author Pedro Reinaldo Mendes (Dot) -> Online Judge ID: 1695802
 * @version 1.0
 */
class Main {

    private static String solveIndividualTestCase(BufferedReader inputReader) throws IOException {
        String currentLine = inputReader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(currentLine);
        int numberOfPoints = Integer.parseInt(tokenizer.nextToken());

        if (numberOfPoints == 0) {
            return "Yes";
        }

        List<Integer> degrees = new ArrayList<>();
        long sumOfDegrees = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            int pointDegree = Integer.parseInt(tokenizer.nextToken());
            degrees.add(pointDegree);
            sumOfDegrees += pointDegree;
        }

        if (sumOfDegrees % 2 != 0) {
            return "No";
        }

        boolean result = isGraphicalSequence(degrees);
        return result ? "Yes" : "No";
    }

    private static boolean isGraphicalSequence(List<Integer> initialDegrees) {
        List<Integer> currentDegrees = new ArrayList<>(initialDegrees);

        while (true) {
            List<Integer> nonZeroDegrees = new ArrayList<>();
            for (Integer degreeValue : currentDegrees) {
                if (degreeValue != 0) {
                    nonZeroDegrees.add(degreeValue);
                }
            }
            currentDegrees = nonZeroDegrees;

            if (currentDegrees.isEmpty()) {
                return true;
            }

            Collections.sort(currentDegrees, Collections.reverseOrder());

            int largestDegree = currentDegrees.get(0);
            int numberOfRemainingPoints = currentDegrees.size();

            if (largestDegree < 0) {
                return false;
            }

            if (largestDegree >= numberOfRemainingPoints) {
                return false;
            }

            currentDegrees.remove(0);

            for (int i = 0; i < largestDegree; i++) {
                int newDegreeValue = currentDegrees.get(i) - 1;
                if (newDegreeValue < 0) {
                    return false;
                }
                currentDegrees.set(i, newDegreeValue);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(bufferedReader.readLine());
        StringBuilder outputBuilder = new StringBuilder();

        for (int i = 0; i < numberOfTestCases; i++) {
            outputBuilder.append(solveIndividualTestCase(bufferedReader)).append("\n");
        }

        System.out.print(outputBuilder.toString());
    }
}
