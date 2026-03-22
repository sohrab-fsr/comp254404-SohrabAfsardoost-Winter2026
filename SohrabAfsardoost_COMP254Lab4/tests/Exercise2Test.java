import stacks.LinkedStack;
import stacks.Stack;

public class Exercise2Test {
  public static <E> void transfer(Stack<E> S, Stack<E> T) {
    while (!S.isEmpty())
      T.push(S.pop());
  }

  public static void main(String[] args) {
    LinkedStack<Integer> S = new LinkedStack<>();
    LinkedStack<Integer> T = new LinkedStack<>();

    S.push(1);
    S.push(2);
    S.push(3);
    S.push(4);

    System.out.println("S before transfer: " + S);
    System.out.println("T before transfer: " + T);

    transfer(S, T);

    System.out.println("S after transfer: " + S);
    System.out.println("T after transfer: " + T);
  }
}
