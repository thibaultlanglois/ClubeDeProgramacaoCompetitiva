import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = null;
        if (args.length == 1) {
            // System.out.println("input file: " + args[0]);
            in = new Scanner(new File(args[0]));
        } else {
            in = new Scanner(System.in);
            // System.out.println("input file: input5.txt");
            // in = new Scanner(new File("input1.txt"));
        }
        int nCases = in.nextInt();
        for (int k = 0; k < nCases; k++) {
            System.out.println("Case " + (k + 1) + ":");
            int nNodes = in.nextInt();
            // System.out.println("nNodes: " + nNodes);
            Graph g = new Graph(nNodes, Graph.DIRECT);
            for (int i = 0; i < nNodes; i++) {
                //onStack[i] = false;
                for (int j = 0; j < nNodes; j++) {
                    int connect = in.nextInt();
                    if (connect == 1) {
                        g.add(i, j);
                    }
                }
            }
            // System.out.println(g);
            // System.exit(0);
            StringBuilder sb = new StringBuilder();
            sb.append("+");
            for (int i = 0; i < g.size() - 1; i++) {
                sb.append("--");
            }
            sb.append("-+\n");
            int[][] table = new int[g.size()][];
            int [] dfs = g.dfs(0);
            for (int i = 0; i < g.size(); i++) {
                sb.append("|");
                // table[i] = dominators(g, i);
                table[i] = dominators2(g, i, dfs);
                printRow(sb, table[i]);
                sb.append("+");
                for (int a = 0; a < g.size() - 1; a++) {
                    sb.append("--");
                }
                sb.append("-+");
                if (i < g.size() -1) {
                    sb.append("\n");
                }
            }
            System.out.println(sb);
        }
        System.out.print("\n");
        System.exit(0);
    }

    private static void printRow(StringBuilder sb, int[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == -1) {
                sb.append("N|");
            } else {
                sb.append("Y|");
            }
        }
        sb.append("\n");
    }

    private static int[] dominators(Graph g, int n) {
        int[] result = new int[g.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        // This version computes the same value n times.
        int[] dfs = g.dfs(0);
        int[] dfsn = g.dfs(0, n);
        for (int k = 0; k < g.size(); k++) {
            if (isIn(dfs, k) && !isIn(dfsn, k)) {
                // n dominates k
                result[k] = k;
            }
        }
        return result;
    }

    private static int[] dominators2(Graph g, int n, int[] dfs) {
        int[] result = new int[g.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        int[] dfsn = g.dfs(0, n);
        for (int k = 0; k < g.size(); k++) {
            if (isIn(dfs, k) && !isIn(dfsn, k)) {
                // n dominates k
                result[k] = k;
            }
        }
        return result;
    }

    private static boolean isIn(int[] a, int val) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == val) {
                return true;
            }
        }
        return false;
    }

    private static void remove(int[] a, int old) {
        int i = 0;
        while (i < a.length - 1) {
            if (a[i] == old) {
                a[i] = a[i + 1];
            } else {
                i = i + 1;
            }
        }
    }

    private static void add(int[] a, int val) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == -1) {
                a[i] = val;
                return;
            }
        }
    }

    private static int size(int[] a) {
        int i = 0;
        while (a[i] != -1) {
            i++;
        }
        return i;
    }

}

class Graph {
    //--------------------------------

    public static final boolean DIRECT = true;
    public static final boolean UNDIRECT = !DIRECT;

    public static final boolean SPARSE = true,
            NOTSPARSE = !SPARSE;

    private final boolean isSparse;    // is the graph using a sparse representation?
    private final boolean isDirected;  // is the graph directed?
    private final int size;        // number of nodes

    // Non-sparse representation:
    //  A 2D array where rows and columns represent the nodes and
    //    each position represents the weight between nodes (zero means no connection)
    //  The graph will consist of V nodes and E edges (E <= V^2)
    private int[][] graphMatrix;

    // Sparse representation
    //  An array of hashmaps to represent sparse graphs
    //  The edge (i,j,w) will be added as graphList[i].put(j,w)
    private ArrayList<HashMap<Integer, Integer>> graphList;

    /////////////////////////// BASIC METHODS ////////////////////////////////////
    // by default we use a matrix to represent a graph, ie, a non-sparse representation
    public Graph(int nodes, boolean graphType) {
        size = nodes;
        isDirected = graphType;
        isSparse = false;
        graphMatrix = new int[size][size];
    }

    public Graph(int nodes, boolean graphType, boolean sparse) {
        size = nodes;
        isDirected = graphType;
        isSparse = sparse;

        if (isSparse) {
            graphList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                graphList.add(i, new HashMap<>());
            }
        } else {
            graphMatrix = new int[size][size];
        }

    }

    /**
     * Add edge to graph
     */
    public void add(int from, int to, int weight) {
        if (isSparse) {
            graphList.get(from).put(to, weight);
            if (isDirected == UNDIRECT) {
                graphList.get(to).put(from, weight);
            }
        } else {
            graphMatrix[from][to] = weight;
            if (isDirected == UNDIRECT) {
                graphMatrix[to][from] = weight;
            }
        }
    }

    public void add(int from, int to) {
        add(from, to, 1);
    }

    /**
     * Remove edge to graph
     */
    public void remove(int from, int to) {
        if (isSparse) {
            graphList.get(from).remove(to);
            if (isDirected == UNDIRECT) {
                graphList.get(to).remove(from);
            }
        } else {
            add(from, to, 0);  // remove edge
        }
    }

    public int weight(int from, int to) {
        if (isSparse) {
            Integer w = graphList.get(from).get(to);
            return w == null ? 0 : w;
        } else {
            return graphMatrix[from][to];
        }
    }

    public int size() {
        return size;
    }

    /**
     * Remove all in-edges and out-edges from/into node
     */
    public void isolate(int node) {
        if (isSparse) {
            graphList.set(node, new HashMap<>()); // remove out-edges
            for (int i = 0; i < size; i++) {
                // remove in-edges (slow)
                graphList.get(i).remove(node);             
            }
        } else {
            if (isDirected == DIRECT) {
                graphMatrix[node] = new int[size];
            }
            for (int i = 0; i < size; i++) {
                remove(i, node);
            }
        }
    }

    /**
     * @param node The node which successors we need
     * @requires a directed graph
     * @return an array with the indexes of the node's successors
     */
    public int[] sucessors(int node) {
        ArrayList<Integer> l = new ArrayList<>();

        if (isSparse) {
            for (Integer successor : graphList.get(node).keySet()) {
                l.add(successor);
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (weight(node, i) != 0) {
                    l.add(i);
                }
            }
        }

        return list2array(l);
    }

    /**
     * @param node The node which predecessors we need
     * @requires a directed graph
     * @return an array with the indexes of the node's predecessors
     */
    public int[] predecessors(int node) {
        ArrayList<Integer> l = new ArrayList<>();

        if (isSparse) {
            for (int i = 0; i < size; i++) { // slow
                Integer weight = graphList.get(i).get(node);
                if (weight != null) {
                    l.add(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (weight(i, node) != 0) {
                    l.add(i);
                }
            }
        }
        return list2array(l);
    }

    /**
     * Make a copy of this
     *
     * @return the reference to the copy
     */
    // @SuppressWarnings("unchecked")
    @SuppressWarnings("unchecked")
    public Graph copy() {

        Graph cp = new Graph(this.size, this.isDirected, this.isSparse);

        if (isSparse) {
            for (int i = 0; i < cp.size; i++) {
                cp.graphList.set(i,
                        (HashMap<Integer, Integer>) this.graphList.get(i).clone());
            }
        } else {
            for (int i = 0; i < cp.size; i++) {
                cp.graphMatrix[i] = this.graphMatrix[i].clone();
            }
        }

        return cp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (isSparse) {
            for (int i = 0; i < size; i++) {
                for (Integer j : graphList.get(i).keySet()) {
                    int weight = graphList.get(i).get(j);
                    if (weight != 0) {
                        if (weight == 1) {
                            sb.append(i + "->" + j + " "); // don't show weights 1
                        } else {
                            sb.append(i + "-{" + weight + "}->" + j + " ");
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (weight(i, j) != 0) {
                        if (weight(i, j) == 1) {
                            sb.append(i + "->" + j + " "); // don't show weights 1
                        } else {
                            sb.append(i + "-{" + weight(i, j) + "}->" + j + " ");
                        }
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * Depth-first search from a given node
     *
     * @param node The node from which to start
     * @complexity O(V + E)
     * @requires a directed graph
     * @return an array with the indexes of the dfs
     */
    public int[] dfs(int node) {
        Stack<Integer> stack = new Stack<>();      // for backtracking
        ArrayList<Integer> l = new ArrayList<>();  // contains the visiting order
        boolean[] visited = new boolean[size];         // false means not visited
        stack.push(node);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited[current]) {
                continue;
            }
            visited[current] = true;
            l.add(current);
            for (int next : sucessors(current)) {
                if (!visited[next]) {
                    stack.push(next);
                }
            }
        }
        return list2array(l);
    }

    public int[] dfs(int node, int ignore) {
        Stack<Integer> stack = new Stack<>();      // for backtracking
        ArrayList<Integer> l = new ArrayList<>();  // contains the visiting order
        boolean[] visited = new boolean[size];         // false means not visited
        visited[ignore] = true;
        stack.push(node);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited[current]) {
                continue;
            }
            visited[current] = true;
            l.add(current);
            for (int next : sucessors(current)) {
                if (!visited[next]) {
                    stack.push(next);
                }
            }
        }
        return list2array(l);
    }

    private boolean[] deactivated;  // vector useful for topSort

    // get the index of a node with no in-edges (for topSort)
    // returns -1 if no such node found (ie, a cycle exists)
    private int getOutNode() {
        int i;
        boolean foundIn = true;

        for (i = 0; i < size && foundIn; i++) {
            if (deactivated[i]) {
                continue;
            }
            foundIn = false;
            for (int j = 0; j < size && !foundIn; j++) {
                foundIn = weight(j, i) != 0;
            }
        }
        if (!foundIn) {
            isolate(i - 1);
            deactivated[i - 1] = true;
        }
        return !foundIn ? i - 1 : -1; // if all have in-edges, there's a cycle
    }
    /**
     * Performs Topological Sort
     *
     * @requires a directed graph
     * @complexity O(V + E)
     * @return an array of indexes with one topological sort of the graph or
     * null if the graph has cycles (ie, it is not a DAG)
     */
    public int[] topSort() {
        ArrayList<Integer> l = new ArrayList<>();
        int node, nodesLeft = size;
        Graph cp = this.copy();
        cp.deactivated = new boolean[size];
        while (nodesLeft-- > 0) {
            node = cp.getOutNode();  // find & deactivate node with no in-edges
            if (node == -1) {
                return null;
            }
            l.add(node);
        }
        return list2array(l);
    }



    public static int[] list2array(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        int index = 0;

        for (int elem : list) {
            array[index++] = elem;
        }
        return array;
    }
}
