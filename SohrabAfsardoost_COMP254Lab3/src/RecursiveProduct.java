import java.util.Scanner;

/**
 * COMP254 - Lab Assignment #3 (Recursion)
 * Exercise 1: Recursive product using only addition and subtraction.
 * Student: Sohrab Afsardoost
 */
public class RecursiveProduct {

  /** Returns m * n for positive integers using recursion and only + and -. */
  public static int product(int m, int n) {
    if (n == 0)
      return 0;
    else
      return m + product(m, n - 1);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Enter m (positive integer): ");
    int m = in.nextInt();

    System.out.print("Enter n (positive integer): ");
    int n = in.nextInt();

    int answer = product(m, n);
    System.out.println(m + " * " + n + " = " + answer);
  }
}
