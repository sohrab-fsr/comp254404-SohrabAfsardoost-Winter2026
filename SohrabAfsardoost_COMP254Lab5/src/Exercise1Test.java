public class Exercise1Test {
  public static void main(String[] args) {
    TreeLabExercises<String> tree = new TreeLabExercises<>();

    Position<String> a = tree.addRoot("A");
    Position<String> b = tree.addLeft(a, "B");
    Position<String> c = tree.addRight(a, "C");
    Position<String> d = tree.addLeft(b, "D");
    Position<String> e = tree.addRight(b, "E");
    Position<String> f = tree.addLeft(c, "F");
    Position<String> g = tree.addRight(c, "G");

    System.out.println("Inorder traversal:");
    for (Position<String> p : tree.inorder())
      System.out.print(p.getElement() + " ");
    System.out.println();

    System.out.println("\nTesting inorderNext(p):");
    printNext(tree, d);
    printNext(tree, b);
    printNext(tree, e);
    printNext(tree, a);
    printNext(tree, f);
    printNext(tree, c);
    printNext(tree, g);

    System.out.println("\nWorst-case running time: O(h), where h is the height of the tree.");
    System.out.println("In the worst case, h can be n, so worst case is O(n).");
  }

  private static void printNext(TreeLabExercises<String> tree, Position<String> p) {
    Position<String> next = tree.inorderNext(p);
    if (next == null)
      System.out.println("inorderNext(" + p.getElement() + ") = null");
    else
      System.out.println("inorderNext(" + p.getElement() + ") = " + next.getElement());
  }
}
