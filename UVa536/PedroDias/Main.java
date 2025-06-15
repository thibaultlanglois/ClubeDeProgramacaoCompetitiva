import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String preorder = sc.next();
            String inorder = sc.next();

            TreeNode root = buildTree(preorder, inorder);
            printPostorder(root);
            System.out.println();
        }

        sc.close();
    }

    static class TreeNode {
        char val;
        TreeNode left, right;

        TreeNode(char val) {
            this.val = val;
        }
    }

    public static TreeNode buildTree(String preorder, String inorder) {
        return build(preorder, inorder);
    }

    private static TreeNode build(String preorder, String inorder) {
        if (preorder.isEmpty()) return null;

        char rootVal = preorder.charAt(0);
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inorder.indexOf(rootVal);

        String leftInorder = inorder.substring(0, rootIndex);
        String rightInorder = inorder.substring(rootIndex + 1);

        String leftPreorder = preorder.substring(1, 1 + leftInorder.length());
        String rightPreorder = preorder.substring(1 + leftInorder.length());

        root.left = build(leftPreorder, leftInorder);
        root.right = build(rightPreorder, rightInorder);

        return root;
    }

    public static void printPostorder(TreeNode node) {
        if (node == null) return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.val);
    }
}
