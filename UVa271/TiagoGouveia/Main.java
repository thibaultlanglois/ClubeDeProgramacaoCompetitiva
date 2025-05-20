import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;
// UVa 271
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String frase = sc.nextLine().trim();

            if (!frase.isEmpty()) {
                if (valida(frase)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static boolean valida(String frase) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = frase.length() - 1; i >= 0; i--) {
            char c = frase.charAt(i);

            if (c >= 'p' && c <= 'z') {
                deque.addFirst(c);
            } else if (c == 'N') {
                if (deque.isEmpty()) {
                    return false;
                }
            } else if (c == 'C' || c == 'D' || c == 'E' || c == 'I') {
                if (deque.size() < 2) {
                    return false;
                }
                deque.removeFirst();
            } else {
                return false;
            }
        }
        return deque.size() == 1;
    }
}
