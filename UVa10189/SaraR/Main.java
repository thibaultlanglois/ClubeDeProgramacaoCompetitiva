import java.util.Scanner;

class MineSweeper {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int n = reader.nextInt();
		int m = reader.nextInt();
		boolean[][] mines = new boolean [n][m]; 
		int counterGrids = 1;
		
		while(!(n == 0 && m == 0)) {
			for(int i = 0; i < n; i++) {
				String isBomb = reader.next();
				char[] line = isBomb.toCharArray();
				for(int j = 0; j < line.length; j++) {
					if(line[j] == '*') {
						mines[i][j] = true;
					}
					else {
						mines[i][j] = false;
					}
				}
			}
			
			createAndPrintGrid(mines, counterGrids);
			
			n = reader.nextInt();
			m = reader.nextInt();
			mines = new boolean [n][m];
			counterGrids++;
		}
		
		reader.close();
	}
	
	static void createAndPrintGrid(boolean[][] mines, int counter) {
		System.out.println("Field #" + counter + ":");
		int[][] grid = new int[mines.length + 2][mines[0].length + 2];
		for(int i = 0; i < mines.length; i++) {
			for(int j = 0; j < mines[i].length; j++) {
				if(mines[i][j]) {
					for(int k = 0; k < 3; k++) {
						for(int l = 0; l < 3; l++) {
							if(!(k == 1 && l == 1)) {
								grid[i+k][j+l] += 1;
							}
						}
					}
				}
			}
		}
		
		for(int i = 0; i < mines.length; i++) {
			for(int j = 0; j < mines[i].length; j++) {
				if(mines[i][j]) {
					System.out.print("*");
				}
				else {
					System.out.print(grid[i + 1][j + 1]);
				}
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
