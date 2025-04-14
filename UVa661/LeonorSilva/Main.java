import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	int count = 1;
	while (sc.hasNext()) {
	    
	    int n = sc.nextInt();
	    int m = sc.nextInt();
	    int c = sc.nextInt();
	    int[] ni = new int[n];
	    int[] mi = new int[m];
	    for (int i = 0; i < n; i++) {
		ni[i] = sc.nextInt();
	    }
	    for (int i = 0; i < m; i++) {
		mi[i] = sc.nextInt();
	    }
	    if (n == 0 && m == 0 && c == 0) {
		return;
	    }
	    System.out.println("Sequence " + count);
	    if (isFuseBlown(n, m, c, ni, mi)) {
		System.out.println("Fuse was blown.");
	    } else {
		System.out.println("Fuse was not blown.");
		System.out.println("Maximal power consumption was " + howMuchConsumed(n, m, ni, mi) + " amperes.");
	    }
	    if (count > 0) {
		System.out.println();
	    }
	    count++;
	}
	sc.close();
    }
    
    static boolean isFuseBlown(int n, int m, int c, int[] ni, int[] mi) {
	return howMuchConsumed(n, m, ni, mi) > c;
    }
    
    static int howMuchConsumed(int n, int m, int[] ci, int[] mi) {
	boolean[] turnedOn = new boolean[n];
	Arrays.fill(turnedOn, false);
	int sum = 0;
	int max = 0;
	for (int i = 0; i < m; i++) { // percorre operações on/off
	    for (int j = 0; j < n; j++) { // percorre o nº de dispositivos
		if (mi[i] == j + 1) {
		    if (!turnedOn[j]) {
			turnedOn[j] = true;
			sum += ci[j];
		    } else {
			turnedOn[j] = false;
			sum -= ci[j];
		    }
		    if (sum > max) {
			max = sum;
		    }
		}
	    }
	}
	return max;
    }
    
}
