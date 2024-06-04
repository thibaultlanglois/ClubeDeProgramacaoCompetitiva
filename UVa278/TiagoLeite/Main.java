import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        while (testCases-- > 0) {
            char piece = scanner.next().charAt(0);
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            System.out.println(maxPieces(piece, m, n));
        }
        scanner.close();
    }

    private static int maxPieces(char piece, int m, int n) {
        switch (piece) {
            case 'r':
            case 'Q':
                // For rooks and queens, the maximum number is the minimum of m and n
                return Math.min(m, n);
            case 'k':
                // For knights, the maximum number is half the number of squares on the board
                return (m * n + 1) / 2;
            case 'K':
                // For kings, the maximum number is every other square on the board
                return ((m + 1) / 2) * ((n + 1) / 2);
            default:
                return 0;
        }
    }
}
