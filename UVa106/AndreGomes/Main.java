import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Main {
    private static ArrayList<Stack<Integer>> blocks;
    private static int[] positions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        blocks = new ArrayList<>(n);
        positions = new int[n];

        // Inicia os blocos
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            blocks.add(stack);
            positions[i] = i;
        }

        // Processa comandos
        while (true) {
            String command = scanner.next();
            if (command.equals("quit")) {
                break;
            }
            int a = scanner.nextInt();
            String preposition = scanner.next();
            int b = scanner.nextInt();

            if (a == b || positions[a] == positions[b]) {
                continue;
            }

            if (command.equals("move")) {
                if (preposition.equals("onto")) {
                    moveOnto(a, b);
                } else {
                    moveOver(a, b);
                }
            } else if (command.equals("pile")) {
                if (preposition.equals("onto")) {
                    pileOnto(a, b);
                } else {
                    pileOver(a, b);
                }
            }
        }

        // Imprime o resultado
        for (int i = 0; i < n; i++) {
            System.out.print(i + ":");
            for (int block : blocks.get(i)) {
                System.out.print(" " + block);
            }
            System.out.println();
        }
    }

    private static void moveOnto(int a, int b) {
        resetAbove(a);
        resetAbove(b);
        moveBlock(a, b);
    }

    private static void moveOver(int a, int b) {
        resetAbove(a);
        moveBlock(a, b);
    }

    private static void pileOnto(int a, int b) {
        resetAbove(b);
        movePile(a, b);
    }

    private static void pileOver(int a, int b) {
        movePile(a, b);
    }

    private static void resetAbove(int a) {
        Stack<Integer> stack = blocks.get(positions[a]);
        while (stack.peek() != a) {
            int top = stack.pop();
            blocks.get(top).push(top);
            positions[top] = top;
        }
    }

    private static void moveBlock(int a, int b) {
        Stack<Integer> fromStack = blocks.get(positions[a]);
        fromStack.pop();
        blocks.get(positions[b]).push(a);
        positions[a] = positions[b];
    }

    private static void movePile(int a, int b) {
        Stack<Integer> fromStack = blocks.get(positions[a]);
        Stack<Integer> toStack = blocks.get(positions[b]);
        Stack<Integer> tempStack = new Stack<>();
        
        while (fromStack.peek() != a) {
            tempStack.push(fromStack.pop());
        }
        tempStack.push(fromStack.pop());
        
        while (!tempStack.isEmpty()) {
            int block = tempStack.pop();
            toStack.push(block);
            positions[block] = positions[b];
        }
    }
}
