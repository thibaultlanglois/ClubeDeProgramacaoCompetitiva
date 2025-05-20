import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {

	private static int[][] spreadsheet;
	private static String[][] formulas;
	private static final String EOL = System.lineSeparator();
	private static int numberOfRows;
	private static int numberOfColumns;
	private static boolean[][] evaluated;
	private static boolean hasCycle;

	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		while (sc.hasNext()) {
			numberOfRows = sc.nextInt();
			numberOfColumns = sc.nextInt();

			if (numberOfRows == 0 || numberOfColumns == 0) {
				break;
			}

			spreadsheet = new int[numberOfRows][numberOfColumns];
			formulas = new String[numberOfRows][numberOfColumns];

			for (int i = 0; i < numberOfRows * numberOfColumns; i++) {
				String input = sc.next();
				int row = i / numberOfColumns;
				int col = i % numberOfColumns;

				if (onlyNumbers(input)) {
					spreadsheet[row][col] = Integer.parseInt(input);
				} else {
					formulas[row][col] = input;
				}
			}

			processCells();

			if (hasCycle) {
				printRemainingFormulas();
			} else {
				System.out.println(spreadToString());
			}
		}

	}

	private static void processCells() {
		evaluated = new boolean[numberOfRows][numberOfColumns];
		hasCycle = false;

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				if (!evaluated[i][j]) {
					boolean[][] visited = new boolean[numberOfRows][numberOfColumns];
					if (!wasEvaluated(i, j, visited)) {
						hasCycle = true;
					}
				}
			}
		}
	}

	private static boolean wasEvaluated(int row, int col, boolean[][] visited) {
		if (evaluated[row][col])
			return true;
		if (visited[row][col])
			return false;

		if (formulas[row][col] == null) {
			evaluated[row][col] = true;
			return true;
		}

		visited[row][col] = true;

		Result out = new Result();
		if (doMath(formulas[row][col], visited, out)) {
			spreadsheet[row][col] = out.value;
			formulas[row][col] = null;
			evaluated[row][col] = true;
			visited[row][col] = false;
			return true;
		} else {
			visited[row][col] = false;
			return false;
		}
	}

	private static boolean doMath(String input, boolean[][] visited, Result out) {
		String[] parts = input.split("(?=[+-])|(?<=[+-])");
		int result = 0;
		String operator = "+";

		for (String part : parts) {
			if (part.equals("+") || part.equals("-")) {
				operator = part;
			} else {
				int value;
				if (part.matches("[A-Z].*")) {
					Result temp = new Result();
					if (!getCellValue(part, visited, temp)) {
						return false;
					}
					value = temp.value;
				} else {
					value = Integer.parseInt(part);
				}
				result = operator.equals("+") ? result + value : result - value;
			}
		}
		out.value = result;
		return true;
	}

	private static boolean getCellValue(String cellRef, boolean[][] visited, Result out) {
		if (cellRef.length() < 2)
			return false;

		char rowChar = cellRef.charAt(0);
		String colPart = cellRef.substring(1);

		if (!colPart.matches("\\d+"))
			return false;

		int row = rowChar - 'A';
		int col = Integer.parseInt(colPart);

		if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfColumns) {
			return false;
		}
		if (!wasEvaluated(row, col, visited)) {
			return false;
		}
		out.value = spreadsheet[row][col];
		return true;
	}

	public static boolean onlyNumbers(String s) {
		return s.matches("\\d+");
	}

	private static void printRemainingFormulas() {
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfColumns; col++) {
				if (formulas[row][col] != null) {
					char rowChar = (char) ('A' + row);
					System.out.println(rowChar + "" + col + ": " + formulas[row][col]);
				}
			}
		}
		System.out.println();
	}

	private static String spreadToString() {
		StringBuilder sb = new StringBuilder();
		int rows = spreadsheet.length;
		int cols = spreadsheet[0].length;

		for (int i = -1; i < rows; i++) {
			for (int j = -1; j < cols; j++) {
				if (i == -1 && j == -1) {
					sb.append(" ");
				} else if (i == -1) {
					sb.append(String.format("%6d", j));
				} else if (j == -1) {
					sb.append(String.format("%s", (char) ('A' + i)));
				} else {
					sb.append(String.format("%6d", spreadsheet[i][j]));
				}
			}
			sb.append(EOL);
		}
		return sb.toString();
	}

	private static class Result {
		int value;
	}
}
