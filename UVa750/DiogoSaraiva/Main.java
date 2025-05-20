import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {

	private static int[] row;
	private static int testCases;
	private static int a; // linha onde uma das rainhas tem de ser colocada
	private static int b; // coluna onde uma das rainhas tem de ser colocada
	private static int lineCounter;

	public static void main(String[] args) throws FileNotFoundException {
		row = new int[8];
		boolean fromFile = false;

		Scanner sc = fromFile ? new Scanner(new File("in.txt"))
				: new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		testCases = sc.nextInt();
		while (testCases > 0) {
			testCases--;
			a = sc.nextInt();
			b = sc.nextInt();
			a--;
			b--; 
			// Subtrai 1 para usar indices que começam em 0, como habitual em java

			
			//preenche o array com 0's
			for (int i = 0; i < 8; i++) {
				row[i] = 0;
			}
			
			lineCounter = 0;

			System.out.println("SOLN       COLUMN");
			System.out.println(" #      1 2 3 4 5 6 7 8\n");


			backtrack(0);

			if (testCases > 0) {
				System.out.println();
			}
		}

		sc.close();
	}

	public static boolean place(int r, int col) {
		// Verifica se é possível colocar uma rainha na posição (r, col):
		for (int i = 0; i < col; i++) {
			if (row[i] == r || (Math.abs(row[i] - r) == Math.abs(i - col))) {
				return false;
			}
		}
		return true;
	}

	// Tenta colocar uma rainha na coluna e chama-se recursivamente para c+1.
	public static void backtrack(int col) {

		// Se colocou 8 rainhas e a posição obrigatória foi satisfeita...
	
		if (col == 8 && row[b] == a) {
			//...imprime a solução
			System.out.printf("%2d      %d", ++lineCounter, row[0] + 1);
			for (int i = 1; i < 8; i++) {
				System.out.printf(" %d", row[i] + 1);
			}
			System.out.println();
		}
		//Para cada linha i na coluna, tenta colocar uma rainha.
		for (int i = 0; i < 8; i++) {
			// Se for válido, guarda a posição e continua para a próxima coluna.
			if (place(i, col)) {
				row[col] = i;
				backtrack(col + 1);
			}
		}
	}
}
