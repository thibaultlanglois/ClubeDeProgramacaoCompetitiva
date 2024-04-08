// package Problems.Minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Minesweeper
 *
 * Given a field with mines, we need to find the number of mines around each cell.
 *
 * Input:
 * The input consists of several fields. The first line of each field contains two integers n and m (0 < n, m <= 100)
 * representing the number of rows and columns of the field. Each of the next n lines contains a string with m characters,
 * representing the field. Each character is either a '.' (dot) or a '*' (asterisk). The first field is numbered 1.
 *
 * Output:
 * For each field, print the field number followed by a colon. Then print the field with the number of mines around each cell.
 *
 * Sample Input:
 * 4 4
 * *...
 * ....
 * .*..
 * ....
 * 3 5
 * **...
 * .....
 * .*...
 * 0 0
 *
 * Sample Output:
 * Field #1:
 * *100
 * 2210
 * 1*10
 * 1110
 *
 * Field #2:
 * **100
 * 33200
 * 1*100
 *
 * @author fc59885 - Miguel Mendes
 */
class Main {

    /**
     * Read the input and call the solver
     */
    public static void Runner() {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
        String s;
        int numFields = 1;
        StringBuilder strB = new StringBuilder();
        try {
            while ((s = in.readLine()) != null) {

                int rows, cols;
                String[] split = s.split(" ");
                rows = Integer.parseInt(split[0]);
                cols = Integer.parseInt(split[1]);
                if(rows == 0 || cols == 0)
                    break;
                if (numFields != 1) {
                    strB.append(System.lineSeparator());
                    strB.append(System.lineSeparator());
                }
                strB.append("Field #" + numFields + ":");
                strB.append(System.lineSeparator());
                numFields++;
                // Read camp
                String[] camp = new String[rows];
                for(int i = 0; i < rows; i++) {
                    camp[i] = in.readLine();
                }

                Solver(camp, strB, rows, cols);
            }
            System.out.println(strB.toString());
            in.close();
        } catch (IOException e) {
            System.err.println("Error reading content. Wrong row or col number");
        }
    }

    /*
     * Solve the problem
     *
     * @param camp: the camp
     * @param strB: the string builder to append the result
     * @param rows: the number of rows
     * @param cols: the number of cols
     * @return the result of the problem
     */
    private static void Solver(String[] camp, StringBuilder strB, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(camp[i].charAt(j) == '*') {
                    strB.append("*");
                } else {
                    strB.append(searchMines(camp, i, j));
                }
            }
            if (i != rows - 1)
                strB.append(System.lineSeparator());
        }
    }

    /*
     * Search for mines around the cell
     *
     * @param camp: the camp
     * @param row: the row of the selected cell
     * @param col: the col of the selected cell
     * @return the number of mines around the cell
     */
    private static int searchMines(String[] camp, int row, int col) {
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = col - 1; j <= col + 1; j++) {
                if(validIndex(camp, i, j) && camp[i].charAt(j) == '*')
                    count++;
            }
        }
        return count;
    }

    /*
     * Check if the index is valid
     *
     * @param camp: the camp
     * @param row: the row of the selected cell
     * @param col: the col of the selected cell
     * @return true if the index is valid, false otherwise
     */
    private static boolean validIndex(String[] camp, int row, int col) {
        return 0 <= row && row < camp.length &&
            0 <= col && col < camp[0].length();
    }

    public static void main(String[] args) {
        Runner();
    }
}
