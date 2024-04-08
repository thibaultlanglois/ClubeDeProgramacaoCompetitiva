import java.util.*;
/**
 * UVa 146 ID codes
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String code = sc.nextLine().trim();
            if (code.equals("#")) break;

            String successor = findSuccessor(code);
            System.out.println(successor);
        }
    }

    private static String findSuccessor(String code) {
        char[] chars = code.toCharArray();

        int i = chars.length - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }

        if (i == -1) {
            return "No Successor";
        }

        int j = chars.length - 1;
        while (chars[j] <= chars[i]) {
            j--;
        }

        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

        reverse(chars, i + 1, chars.length - 1);

        return new String(chars);
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
