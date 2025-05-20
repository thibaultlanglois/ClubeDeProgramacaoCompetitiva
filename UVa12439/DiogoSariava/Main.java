import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
// UVa 12439
class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int cases = sc.nextInt();
		sc.nextLine();

		for (int caseN = 1; caseN <= cases; caseN++) {

			int[][] dates = new int[2][3];
			for (int i = 0; i < 2; i++) {
				String[] input = sc.nextLine().split(" ");
				dates[i][0] = Integer.parseInt(input[2]);
				dates[i][1] = parseMonth(input[0]);
				dates[i][2] = Integer.parseInt(input[1].replace(",", ""));
			}
			int count = howManyLeapYearsBetween(dates[0], dates[1]);
			System.out.println("Case " + caseN + ": " + count);
		}
		sc.close();
	}

	private static int howManyLeapYearsBetween(int[] fromDate, int[] toDate) {
		int count = 0;
		int fromYear = fromDate[0];
		int toYear = toDate[0];

		count = leapsUntil(toYear - 1) - leapsUntil(fromYear);

		if (isLeap(fromYear) && (fromDate[1] < 2 || (fromDate[1] == 2 && fromDate[2] <= 29))) {
			count++;
		}

		if (isLeap(toYear) && (toDate[1] > 2 || (toDate[1] == 2 && toDate[2] == 29))) {
			count++;
		}

		return count;

	}
	
	
	private static int leapsUntil(int year) {
		return year / 4 - year / 100 + year / 400;
	}

	private static boolean isLeap(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	private static int parseMonth(String month) {
		if (month.equals("January"))
			return 1;
		if (month.equals("February"))
			return 2;
		if (month.equals("March"))
			return 3;
		if (month.equals("April"))
			return 4;
		if (month.equals("May"))
			return 5;
		if (month.equals("June"))
			return 6;
		if (month.equals("July"))
			return 7;
		if (month.equals("August"))
			return 8;
		if (month.equals("September"))
			return 9;
		if (month.equals("October"))
			return 10;
		if (month.equals("November"))
			return 11;
		if (month.equals("December"))
			return 12;
		return -1;
	}
}
