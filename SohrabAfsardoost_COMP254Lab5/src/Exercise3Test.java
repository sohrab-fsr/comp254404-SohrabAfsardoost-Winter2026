public class Exercise3Test {
  public static void main(String[] args) {
    HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

    pq.insert(47, "A");
    pq.insert(75, "C");
    pq.insert(28, "B");
    pq.insert(51, "D");
    pq.insert(31, "F");
    pq.insert(22, "G");
    pq.insert(15, "H");

    System.out.println("Heap entries after recursive upheap:");
    for (int i = 0; i < pq.size(); i++)
      System.out.println("(" + pq.heap.get(i).getKey() + ", " + pq.heap.get(i).getValue() + ")");

    System.out.println("Minimum key: " + pq.min().getKey());
  }
}
