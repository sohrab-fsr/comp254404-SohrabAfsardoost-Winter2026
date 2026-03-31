public class Exercise2Test {
  public static void main(String[] args) {
    TreeLabExercises<String> tree = new TreeLabExercises<>();

    Position<String> a = tree.addRoot("A");
    Position<String> b = tree.addLeft(a, "B");
    Position<String> c = tree.addRight(a, "C");
    tree.addLeft(b, "D");
    tree.addRight(b, "E");
    tree.addRight(c, "F");

    System.out.println("Postorder printing of each node and subtree height:");
    tree.printAllSubtreeHeights();
  }
}
