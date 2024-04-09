// package Problems.StationBalance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Station Balance
 *
 * Given a number of stations and a number of chambers, we need to find the best way to distribute the stations in the chambers.
 *
 * Input:
 * The input consists of several test cases. The first line of each test case contains two integers c and s (1 <= c <= 10 and 2*c <= s <= 20).
 * The next line contains s integers representing the stations. All stations are positive integers less than 100.
 *
 * Output:
 * For each test case, print the test case number followed by the chambers with the stations. The chambers must be ordered by the sum of the stations.
 *
 * Sample Input
 * 2 3
 * 6 3 8
 * 3 5
 * 51 19 27 14 33
 * 5 9
 * 1 2 3 5 7 11 13 17 19
 *
 * Sample Output
 * Set #1
 * 0: 6 3
 * 1: 8
 * IMBALANCE = 1.00000
 * Set #2
 * 0: 51
 * 1: 19 27
 * 2: 14 33
 * IMBALANCE = 6.00000
 * Set #3
 * 0: 1 17
 * 1: 2 13
 * 2: 3 11
 * 3: 5 7
 * 4: 19
 * IMBALANCE = 11.60000
 *
 * @author fc59885 - Miguel Mendes
 */
class Main {

    public static void Runner() {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
        String s;
        int numSets = 1;
        try {
            while ((s = in.readLine()) != null && s != "") {
                StringBuilder strB = new StringBuilder();

                int numChambers, numStations, sum = 0;
                String[] split = s.split(" ");
                numChambers = Integer.parseInt(split[0]);
                numStations = Integer.parseInt(split[1]);
                int[] stations = new int[numChambers * 2];

                String[] line = in.readLine().split(" ");
                for(int i = 0; i < numStations; i++) {
                    int result = Integer.parseInt(line[i]);
                    stations[i] = result;
                    sum += result;
                }


                // Print set number
                if (numSets != 1) {
                    strB.append(System.lineSeparator());
                    strB.append(System.lineSeparator());
                }
                strB.append("Set #" + numSets);
                strB.append(System.lineSeparator());
                numSets++;

                // Read camp
                Solver(stations, numChambers, sum, strB);
                System.out.println(strB.toString());
            }
            in.close();
        } catch (IOException e) {
            System.err.println("Error reading content. Wrong row or col number");
        }
    }

    /*
     * Balance the stations and return the imbalance
     *
     * @param stations the stations
     * @param numChambers the number of chambers
     * @param sum the sum of the stations value
     * @param strB the string builder
     */
    public static void Solver(int[] stations, int numChambers, int sum, StringBuilder strB) {
        double avg =  ((double) sum) / numChambers, imbalance = 0;
        int setNum = 0;
        Arrays.sort(stations);
        for (int i = 0; i < numChambers; i++) {
            strB.append(setNum +": ");
            int first = stations[i], second = stations[2 * numChambers - i - 1];
            if(first != 0 && second != 0)
                strB.append(first + " " + second);
            else if (first != 0)
                strB.append(first);
            else
                strB.append(second);
            strB.append(System.lineSeparator());
            imbalance += Math.abs((first + second) - avg);
            setNum++;
        }
        strB.append("IMBALANCE = ");
        strB.append(String.format("%.5f", imbalance));
    }

    public static void main(String[] args) {
        Runner();
    }
}
