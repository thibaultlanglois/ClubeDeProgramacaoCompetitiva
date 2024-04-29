import java.util.Scanner;

public class Main {
    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            if (m == 0 && n == 0)
                continuar = false;
            else {
                char[][] grid = new char[m][n];
                for (int i = 0; i < m; i++) {
                    grid[i] = scanner.next().toCharArray();
                }
                int quantidade = counter(grid);
                System.out.println(quantidade);
            }
        }
        scanner.close();
    }

    static int counter(char[][] grid) {
        int quantidade = 0;
        int linhas = grid.length;
        int colunas = grid[0].length;
        boolean[][] visitado = new boolean[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (grid[i][j] == '@' && !visitado[i][j]) {
                    direcoes(grid, visitado, i, j);
                    quantidade++;
                }
            }
        }
        return quantidade;
    }

    static void direcoes(char[][] grid, boolean[][] visitado, int x, int y) {
        int linhas = grid.length;
        int colunas = grid[0].length;
        visitado[x][y] = true;
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && nx < linhas && ny >= 0 && ny < colunas && grid[nx][ny] == '@' && !visitado[nx][ny]) {
                direcoes(grid, visitado, nx, ny);
            }
        }
    }
}
