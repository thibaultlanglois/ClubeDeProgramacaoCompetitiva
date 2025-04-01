import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.math.*;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (new BufferedReader(new InputStreamReader(System.in)));
		
		while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) 
            	break;
            char[] pepas = array(Fatorial(n));
            int[] eng = Eng(pepas);
            System.out.println(toString(eng, n));
		}
		sc.close();
	}
	
	public static BigInteger Fatorial(int n) {
		BigInteger result = BigInteger.ONE;
		
		for(int i = 1; i <= n; i ++) {
			result = result.multiply(BigInteger.valueOf(i));;
		}
		
		return result;
	}
	
	public static char[] array(BigInteger n) {
		char[] eng = n.toString().toCharArray();
		
		return eng;
	}
	
	public static int[] Eng(char[] yt) {
		int[] result = new int[10];
		
		for(int i = 0; i < yt.length; i++) {
			switch(yt[i]) {
			case '0':
				result[0] += 1;
				break;
			case '1':
				result[1] += 1;
				break;
			case '2':
				result[2] += 1;
				break;
			case '3':
				result[3] += 1;
				break;
			case '4':
				result[4] += 1;
				break;
			case '5':
				result[5] += 1;
				break;
			case '6':
				result[6] += 1;
				break;
			case '7':
				result[7] += 1;
				break;
			case '8':
				result[8] += 1;
				break;
			case '9':
				result[9] += 1;
				break;
			}
		}
		return result;
	}
	
	public static String toString(int[] eng, int n) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(n + "! --\n");
		sb.append("(0)   " + eng[0] + "   (1)   " + eng[1] + "   (2)   " + eng[2] + "   (3)   " + eng[3] + "   (4)   " + eng[4] + "\n");
		sb.append("(5)   " + eng[5] + "   (6)   " + eng[6] + "   (7)   " + eng[7] + "   (8)   " + eng[8] + "   (9)   " + eng[9]);
		
		return sb.toString();
	}
}
