import java.util.*;

class Main {
    
    static class Box implements Comparable<Box> {
        int id;                 // original box ID (order from input)
        int[] dims;             // sorted dimensions

        public Box(int id, int[] dims) {
            this.id = id;
            this.dims = dims;
            Arrays.sort(this.dims); // sort dimensions initially for easy comparisons
        }

        // Check whether this box can nest inside another box 
        public boolean nestsInto(Box other) {
            for (int i = 0; i < dims.length; i++) {
                if (this.dims[i] >= other.dims[i])
                    return false;
            }
            return true;
        }

        // Boxes are compared based on their sorted dims (lexicographically compare dimensions)
        public int compareTo(Box other) {
            for (int i = 0; i < dims.length; i++) {
                if (dims[i] != other.dims[i])
                    return dims[i] - other.dims[i];
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int k = scanner.nextInt();  // number of boxes
            int n = scanner.nextInt();  // dimensions of each box
            
            Box[] boxes = new Box[k];
            for (int i = 0; i < k; i++) {
                int[] dims = new int[n];
                for (int j = 0; j < n; j++) {
                    dims[j] = scanner.nextInt();
                }
                boxes[i] = new Box(i + 1, dims);
            }
            
            Arrays.sort(boxes); // Sorting helps simplify nesting logic.
            
            // longest nesting ending at each box
            int[] dp = new int[k];
            int[] parent = new int[k];
            Arrays.fill(dp, 1);
            Arrays.fill(parent, -1);
            
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < i; j++) {
                    if (boxes[j].nestsInto(boxes[i]) && dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        parent[i] = j;
                    }
                }
            }
            
            // Find also the box that provides the longest overall solution
            int maxLen = 0, idx = -1;
            for (int i = 0; i < k; i++) {
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                    idx = i;
                }
            }
            
            // Trace back the path
            List<Integer> seq = new ArrayList<>();
            while (idx != -1) {
                seq.add(boxes[idx].id); // box numbering starts at 1, matches problem requirement 
                idx = parent[idx];
            }
            Collections.reverse(seq); // reverse order since we traced from largest to smallest box
            
            // Output the results
            System.out.println(maxLen);
            for (int i = 0; i < seq.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(seq.get(i));
            }
            System.out.println();
        }
        scanner.close();
    }
}
