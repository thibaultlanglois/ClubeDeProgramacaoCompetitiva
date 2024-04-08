import java.util.Scanner;

class Main {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt(), m = sc.nextInt(), i = 1;
            char[][] field = new char[n][m];
            while (n != 0 && m != 0) {
                if (i != 1) {
                    System.out.println("");
                }

                for (int j = 0; j < n; j++) {
                    field[j] = sc.next().toCharArray();
                }

                System.out.println("Field #"+i+":");
                getAns(field, n, m);

                n = sc.nextInt();
                m = sc.nextInt();
                i++;
                field = new char[n][m];
            }
            sc.close();
    }

    static void getAns(char[][] field, int n, int m) {
        int num;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == '*') {
                    System.out.print("*");
                } else {
                    num = getNum(field, n, m, i, j);
                    System.out.print(num);
                }
            }
            System.out.println("");
        }
    }

    static int getNum(char[][] field, int n, int m, int i, int j) {
        int ans = 0;
        for (int a = i-1; a <= i+1; a++) {
            for (int b = j-1; b <= j+1; b++) {
                if (a >= 0 && a < n && b >= 0 && b < m) {
                    ans = (field[a][b] == '*') ? ans+1 : ans;
                }
            }
        }
        return ans;
    }
}
