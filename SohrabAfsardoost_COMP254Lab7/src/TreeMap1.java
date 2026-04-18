import java.util.ArrayList;
import java.util.Comparator;

/** An implementation of a sorted map using a binary search tree. */
public class TreeMap1<K,V> extends AbstractSortedMap<K,V> {

  protected LinkedBinaryTree<Entry<K,V>> tree = new LinkedBinaryTree<Entry<K,V>>();

  public TreeMap1() {
    super();
    tree.addRoot(null);
  }

  public TreeMap1(Comparator<K> comp) {
    super(comp);
    tree.addRoot(null);
  }

  @Override
  public int size() {
    return (tree.size() - 1) / 2;
  }

  private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
    tree.set(p, entry);
    tree.addLeft(p, null);
    tree.addRight(p, null);
  }

  protected Position<Entry<K,V>> root() { return tree.root(); }
  protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p) { return tree.parent(p); }
  protected Position<Entry<K,V>> left(Position<Entry<K,V>> p) { return tree.left(p); }
  protected Position<Entry<K,V>> right(Position<Entry<K,V>> p) { return tree.right(p); }
  protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p) { return tree.sibling(p); }
  protected boolean isRoot(Position<Entry<K,V>> p) { return tree.isRoot(p); }
  protected boolean isExternal(Position<Entry<K,V>> p) { return tree.isExternal(p); }
  protected boolean isInternal(Position<Entry<K,V>> p) { return tree.isInternal(p); }
  protected void set(Position<Entry<K,V>> p, Entry<K,V> e) { tree.set(p, e); }
  protected Entry<K,V> remove(Position<Entry<K,V>> p) { return tree.remove(p); }

  /**
   * Iterative version of treeSearch.
   * This does not use recursion.
   */
  private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key) {
    Position<Entry<K,V>> walk = p;

    while (isInternal(walk)) {
      int comp = compare(key, walk.getElement());
      if (comp == 0)
        return walk;
      else if (comp < 0)
        walk = left(walk);
      else
        walk = right(walk);
    }

    return walk;
  }

  public V get(K key) throws IllegalArgumentException {
    checkKey(key);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isExternal(p)) return null;
    return p.getElement().getValue();
  }

  public V put(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    Entry<K,V> newEntry = new MapEntry<>(key, value);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isExternal(p)) {
      expandExternal(p, newEntry);
      return null;
    } else {
      V old = p.getElement().getValue();
      set(p, newEntry);
      return old;
    }
  }

  public V remove(K key) throws IllegalArgumentException {
    checkKey(key);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isExternal(p)) {
      return null;
    } else {
      V old = p.getElement().getValue();
      if (isInternal(left(p)) && isInternal(right(p))) {
        Position<Entry<K,V>> replacement = treeMax(left(p));
        set(p, replacement.getElement());
        p = replacement;
      }
      Position<Entry<K,V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
      remove(leaf);
      remove(p);
      return old;
    }
  }

  protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) {
    Position<Entry<K,V>> walk = p;
    while (isInternal(walk))
      walk = left(walk);
    return parent(walk);
  }

  protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
    Position<Entry<K,V>> walk = p;
    while (isInternal(walk))
      walk = right(walk);
    return parent(walk);
  }

  @Override
  public Entry<K,V> firstEntry() {
    if (isEmpty()) return null;
    return treeMin(root()).getElement();
  }

  public Entry<K,V> lastEntry() {
    if (isEmpty()) return null;
    return treeMax(root()).getElement();
  }

  @Override
  public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException {
    checkKey(key);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isInternal(p)) return p.getElement();
    while (!isRoot(p)) {
      if (p == left(parent(p)))
        return parent(p).getElement();
      else
        p = parent(p);
    }
    return null;
  }

  public Entry<K,V> floorEntry(K key) throws IllegalArgumentException {
    checkKey(key);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isInternal(p)) return p.getElement();
    while (!isRoot(p)) {
      if (p == right(parent(p)))
        return parent(p).getElement();
      else
        p = parent(p);
    }
    return null;
  }

  public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException {
    checkKey(key);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isInternal(p) && isInternal(left(p)))
      return treeMax(left(p)).getElement();
    while (!isRoot(p)) {
      if (p == right(parent(p)))
        return parent(p).getElement();
      else
        p = parent(p);
    }
    return null;
  }

  @Override
  public Entry<K,V> higherEntry(K key) throws IllegalArgumentException {
    checkKey(key);
    Position<Entry<K,V>> p = treeSearch(root(), key);
    if (isInternal(p) && isInternal(right(p)))
      return treeMin(right(p)).getElement();
    while (!isRoot(p)) {
      if (p == left(parent(p)))
        return parent(p).getElement();
      else
        p = parent(p);
    }
    return null;
  }

  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
    for (Position<Entry<K,V>> p : tree.inorder())
      if (isInternal(p)) buffer.add(p.getElement());
    return buffer;
  }

  public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
    if (compare(fromKey, toKey) < 0)
      subMapRecurse(fromKey, toKey, root(), buffer);
    return buffer;
  }

  private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p,
                             ArrayList<Entry<K,V>> buffer) {
    if (isInternal(p)) {
      if (compare(p.getElement(), fromKey) < 0)
        subMapRecurse(fromKey, toKey, right(p), buffer);
      else {
        subMapRecurse(fromKey, toKey, left(p), buffer);
        if (compare(p.getElement(), toKey) < 0) {
          buffer.add(p.getElement());
          subMapRecurse(fromKey, toKey, right(p), buffer);
        }
      }
    }
  }

  public static void main(String[] args) {
    TreeMap1<Integer, String> map = new TreeMap1<Integer, String>();

    map.put(6, "A");
    map.put(2, "B");
    map.put(4, "C");
    map.put(1, "D");
    map.put(9, "E");
    map.put(8, "F");

    System.out.println("Search key 4: " + map.get(4));
    System.out.println("Search key 8: " + map.get(8));
    System.out.println("Search key 3: " + map.get(3));

    System.out.println("All entries:");
    for (Entry<Integer, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
  }
}
