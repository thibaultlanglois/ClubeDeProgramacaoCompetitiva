import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// UVa 1544
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile("(\\d+)([+\\-*])(\\d+)");
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            Matcher m = pattern.matcher(input);
            if (m.matches()) {
                formatExpr(m.group(1), m.group(2).charAt(0), m.group(3), sb);
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void formatExpr(String s1, char op, String s2, StringBuilder sb) {
        BigInteger a = new BigInteger(s1);
        BigInteger b = new BigInteger(s2);
        BigInteger result;

        int len1 = s1.length();
        int len2 = s2.length() + 1;
        int lenr;

        if (op == '+') {
            result = a.add(b);
        } else if (op == '-') {
            result = a.subtract(b);
        } else {
            result = a.multiply(b);
        }

        String sr = result.toString();
        lenr = sr.length();

        int width = Math.max(Math.max(len1, len2), lenr);

        sb.append(pad(s1, width)).append("\n");
        sb.append(pad(op + s2, width)).append("\n");

        if (op == '+' || op == '-') {
            sb.append(pad(repeat('-', Math.max(len2, sr.length())), width)).append("\n");
            sb.append(pad(sr, width)).append("\n");
        } else {
            if (s2.length() == 1) {
                sb.append(pad(repeat('-', Math.max(len2, sr.length())), width)).append("\n");
                sb.append(pad(sr, width)).append("\n");
            } else {
                List<String> partials = new ArrayList<>();
                int shift = 0;

                for (int i = s2.length() - 1; i >= 0; i--, shift++) {
                    int digit = s2.charAt(i) - '0';
                    BigInteger part = a.multiply(BigInteger.valueOf(digit));
                    String partialStr = part.toString();
                    partials.add(String.format("%" + (width - shift) + "s", partialStr));
                }

                int dashLen = Math.max(partials.get(0).trim().length(), len2);
                sb.append(pad(repeat('-', dashLen), width)).append("\n");
                for (String p : partials) {
                    sb.append(p).append("\n");
                }

                int maxPartialLen = partials.get(partials.size() - 1).trim().length() + partials.size() - 1;
                int dashLength = Math.max(sr.length(), maxPartialLen);
                sb.append(pad(repeat('-', dashLength), width)).append("\n");
                sb.append(pad(sr, width)).append("\n");
            }
        }
    }

    public static String pad(String s, int width) {
        return String.format("%" + width + "s", s);
    }

    private static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) sb.append(c);
        return sb.toString();
    }
}
