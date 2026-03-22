import queues.LinkedQueue;

public class Exercise3Test {
  public static void main(String[] args) {
    LinkedQueue<Integer> Q1 = new LinkedQueue<>();
    LinkedQueue<Integer> Q2 = new LinkedQueue<>();

    Q1.enqueue(10);
    Q1.enqueue(20);
    Q1.enqueue(30);

    Q2.enqueue(40);
    Q2.enqueue(50);
    Q2.enqueue(60);

    System.out.println("Q1 before concatenate: " + Q1);
    System.out.println("Q2 before concatenate: " + Q2);

    Q1.concatenate(Q2);

    System.out.println("Q1 after concatenate: " + Q1);
    System.out.println("Q2 after concatenate: " + Q2);
  }
}
