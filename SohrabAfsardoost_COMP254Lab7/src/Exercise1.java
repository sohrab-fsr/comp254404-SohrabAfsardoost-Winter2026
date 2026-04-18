public class Exercise1 {

    // Simple node class for a binary search tree
    static class Node {
        int key;
        String value;
        Node left;
        Node right;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Non-recursive treeSearch using a loop
    public static Node treeSearch(Node p, int key) {
        while (p != null) {
            if (key == p.key) {
                return p;
            } else if (key < p.key) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Build a simple BST for testing
        Node root = new Node(50, "A");
        root.left = new Node(30, "B");
        root.right = new Node(70, "C");
        root.left.left = new Node(20, "D");
        root.left.right = new Node(40, "E");
        root.right.left = new Node(60, "F");
        root.right.right = new Node(80, "G");

        Node result1 = treeSearch(root, 60);
        if (result1 != null)
            System.out.println("Key 60 found with value: " + result1.value);
        else
            System.out.println("Key 60 not found");

        Node result2 = treeSearch(root, 25);
        if (result2 != null)
            System.out.println("Key 25 found with value: " + result2.value);
        else
            System.out.println("Key 25 not found");
    }
}
