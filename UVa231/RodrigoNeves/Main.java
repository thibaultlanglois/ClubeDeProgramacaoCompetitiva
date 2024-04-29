import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        List<List<Integer>> results = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {

            List<Integer> missiles = new ArrayList<Integer>();

            while (true) {

                int value = sc.nextInt();
                if (value == -1)
                    break;

                missiles.add(value);

            }
            if (missiles.isEmpty())
                break;

            results.add(missiles);

        }
        sc.close();

        List<Integer> lis = new ArrayList<Integer>();

        for (List<Integer> x : results) {
            lis.add(max(x));
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < lis.size(); i++) {
            out.append("Test #" + (i + 1) + ":");
            out.append(System.lineSeparator());
            out.append("  maximum possible interceptions: " + lis.get(i));
            if (i < lis.size() - 1) {
                out.append(System.lineSeparator());
                out.append(System.lineSeparator());

            }
        }
        System.out.println(out.toString());
        ;
        System.exit(0);
    }

    public static int max(List<Integer> missiles) {
        int size = missiles.size();
        int max = -1;
        int[] counter = new int[size];

        Arrays.fill(counter, 1);

        for (int i = size - 2; i >= 0; i--) {
            for (int j = size - 1; j > i; j--) {
                if (missiles.get(j) <= missiles.get(i) && counter[i] < counter[j] + 1) {
                    counter[i] = counter[j] + 1;
                }
            }

        }

        for (int x : counter) {
            if (max < x)
                max = x;
        }
        return max;
    }

}
