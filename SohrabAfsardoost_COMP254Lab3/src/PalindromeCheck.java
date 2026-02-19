import java.util.Scanner;

/**
 * COMP254 - Lab Assignment #3 (Recursion)
 * Exercise 2: Check if a string is a palindrome using recursion.
 * Student: Sohrab Afsardoost
 */
public class PalindromeCheck {

  /** Returns true if s is a palindrome (same forwards and backwards). */
  public static boolean isPalindrome(String s) {
    if (s.length() <= 1)
      return true;
    if (s.charAt(0) != s.charAt(s.length() - 1))
      return false;
    return isPalindrome(s.substring(1, s.length() - 1));
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Enter a string to check: ");
    String s = in.nextLine();

    if (isPalindrome(s))
      System.out.println("Palindrome: YES");
    else
      System.out.println("Palindrome: NO");
  }
}
