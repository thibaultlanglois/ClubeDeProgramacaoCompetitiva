import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
// UVa 598
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int datasets = Integer.parseInt(sc.nextLine().trim());
        sc.nextLine();

        for (int dataset = 0; dataset < datasets; dataset++) {
            if (dataset > 0)
                System.out.println();

            String sizeRange = sc.nextLine().trim();
            List<String> newspapers = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty())
                    break;
                newspapers.add(line.trim());
            }

            int minSize = 1, maxSize = newspapers.size();

            if (!sizeRange.equals("*")) {
                String[] parts = sizeRange.trim().split("\\s+");
                if (parts.length == 1) {
                    minSize = maxSize = Integer.parseInt(parts[0]);
                } else {
                    minSize = Integer.parseInt(parts[0]);
                    maxSize = Integer.parseInt(parts[1]);
                }
            }

            for (int k = minSize; k <= maxSize; k++) {
                System.out.println("Size " + k);
                List<String> combinations = new ArrayList<>();
                generateCombinations(newspapers, k, 0, new ArrayList<>(), combinations);
                for (String temp : combinations) {
                    System.out.println(temp);
                }
                System.out.println();
            }
        }

        sc.close();
    }

    private static void generateCombinations(List<String> newspapers, int size, int start,
            List<String> current, List<String> combinations) {
        if (current.size() == size) {
            combinations.add(String.join(", ", current));
            return;
        }

        for (int i = start; i < newspapers.size(); i++) {
            current.add(newspapers.get(i));
            generateCombinations(newspapers, size, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }
}
