import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

// UVa 11048

class Main {
	private static Set<String> dictSet;
	private static List<String> dictList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int numberOfWordsInDict = sc.nextInt();
		dictSet = new HashSet<>();
		dictList = new ArrayList<>();
		for (int i = 0; i < numberOfWordsInDict; i++) {
			String word = sc.next();
			dictList.add(word);
			dictSet.add(word);
		}

		int numberOfQueries = sc.nextInt();
		for (int i = 0; i < numberOfQueries; i++) {
			String query = sc.next();
			checkSpelling(query);
		}
		sc.close();
	}

	private static void checkSpelling(String query) {
		if (dictSet.contains(query)) {
			System.out.println(query + " is correct");
		} else {
			String correction = getCorrection(query);
			if (correction == null) {
				System.out.println(query + " is unknown");
			} else {
				System.out.println(query + " is a misspelling of " + correction);
			}
		}
	}

	private static String getCorrection(String query) {
		for (String dictWord : dictList) {
			if (isSimilar(dictWord, query)) {
				return dictWord;
			}
		}
		return null;
	}

	private static boolean isSimilar(String expected, String actual) {
		int expectedLenght = expected.length();
		int actualLenght = actual.length();

		if (expectedLenght - actualLenght == 1 || actualLenght - expectedLenght == 1) {
			String longer = expectedLenght > actualLenght ? expected : actual;
			String shorter = expectedLenght > actualLenght ? actual : expected;
			int longerLength = longer.length();
			for (int i = 0; i < longerLength; i++) {
				String outWordPart = longer.substring(0, i) + longer.substring(i + 1);
				if (outWordPart.equals(shorter)) {
					return true;
				}
			}
		}

		else if (expectedLenght == actualLenght) {

			int differences = 0;
			for (int i = 0; i < expectedLenght; i++) {
				if (expected.charAt(i) != actual.charAt(i)) {
					differences++;
				}
			}
			if (differences == 1) {
				return true;
			}

			if (differences == 2) {
				for (int i = 0; i < expectedLenght - 1; i++) {
					if (expected.charAt(i) != actual.charAt(i)) {
						if (expected.charAt(i) == actual.charAt(i + 1) &&
								expected.charAt(i + 1) == actual.charAt(i)) {
							return true;
						} else {
							return false;
						}

					}
				}
			}
		}

		return false;
	}

}
