import java.io.*;
import java.util.*;
// UVa 402
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int selectionNumber = 1;

        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int lp = Integer.parseInt(st.nextToken());

            boolean[] alive = new boolean[n + 1];
            Arrays.fill(alive, true);
            int rem = n;

            int[] cards = new int[20];
            int cardCount = 0;

            while (cardCount < 20) {
                if (!st.hasMoreTokens()) {
                    line = br.readLine();
                    if (line == null || line.trim().isEmpty()) break;
                    st = new StringTokenizer(line);
                }
                cards[cardCount++] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < cardCount && rem > lp; i++) {
                int card = cards[i];
                int count = 0;

                for (int j = 1; j <= n; j++) {
                    if (alive[j]) {
                        count++;
                        if (count == card) {
                            alive[j] = false;
                            rem--;
                            count = 0;
                        }
                        if (rem <= lp) break;
                    }
                }
            }

            System.out.println("Selection #" + selectionNumber++);
            StringBuilder sb = new StringBuilder();
            int printed = 0;
            for (int i = 1; i <= n; i++) {
                if (alive[i]) {
                    sb.append(i);
                    printed++;
                    if (printed != rem) sb.append(" ");
                }
            }
            System.out.println(sb.toString());
            System.out.println();
        }
    }
}
