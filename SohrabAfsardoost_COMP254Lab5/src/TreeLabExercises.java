public class TreeLabExercises<E> extends LinkedBinaryTree<E> {

  // Exercise 1: return the node visited after p in an inorder traversal
  public Position<E> inorderNext(Position<E> p) {
    if (right(p) != null) {
      Position<E> walk = right(p);
      while (left(walk) != null)
        walk = left(walk);
      return walk;
    }

    Position<E> walk = p;
    Position<E> parent = parent(walk);
    while (parent != null && walk == right(parent)) {
      walk = parent;
      parent = parent(walk);
    }
    return parent;
  }

  // Exercise 2: print each node followed by the height of its subtree using postorder
  public int printSubtreeHeights(Position<E> p) {
    int maxChildHeight = -1;
    for (Position<E> c : children(p)) {
      int childHeight = printSubtreeHeights(c);
      if (childHeight > maxChildHeight)
        maxChildHeight = childHeight;
    }

    int height = maxChildHeight + 1;
    System.out.println(p.getElement() + " -> " + height);
    return height;
  }

  public void printAllSubtreeHeights() {
    if (!isEmpty())
      printSubtreeHeights(root());
  }
}
