import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	boolean first = true;
	while (sc.hasNext()) {
	    String yearStr = sc.next();
	    
	    if (!first) {
		System.out.println();
	    }
	    first = false;
	    
	    boolean leapYear = isLeapYear(yearStr);
	    boolean isHuluculuYear = isDivisibleBy(yearStr, 15);
	    boolean isBulukuluYear = isDivisibleBy(yearStr, 55) && isLeapYear(yearStr);
	    
	    if (leapYear) {
		System.out.println("This is leap year.");
	    }
	    if (isHuluculuYear) {
		System.out.println("This is huluculu festival year.");
	    }
	    if (isBulukuluYear) {
		System.out.println("This is bulukulu festival year.");
	    }
	    if (!isBulukuluYear && !isHuluculuYear && !leapYear) {
		System.out.println("This is an ordinary year.");
	    }
	}
	sc.close();
    }
    
    static boolean isDivisibleBy(String str, int divisor) {
	int remainder = 0;
	for (char num : str.toCharArray()) {
	    remainder = (remainder * 10 + (num - '0')) % divisor;
	}
	return remainder == 0;
    }
    
    static boolean isLeapYear(String yearStr) {
	return isDivisibleBy(yearStr, 4) && (!isDivisibleBy(yearStr, 100) || isDivisibleBy(yearStr, 400));
}
}
