import java.util.Scanner;
import java.io.File;

/**
 * COMP254 - Lab Assignment #3 (Recursion)
 * Exercise 3: Recursively find all entries under a path with a given file name.
 * Student: Sohrab Afsardoost
 */
public class FindFile {

  /** Reports all entries of the file system rooted at the given path having the given file name. */
  public static void find(String path, String filename) {
    find(new File(path), filename);
  }

  /** Recursive helper that works with a File object. */
  public static void find(File root, String filename) {
    if (root.getName().equals(filename))
      System.out.println(root.getAbsolutePath());

    if (root.isDirectory()) {
      for (String childname : root.list()) {
        File child = new File(root, childname);
        find(child, filename);
      }
    }
  }

  public static void main(String[] args) {
    String start;
    String filename;

    if (args.length >= 2) {
      start = args[0];
      filename = args[1];
    } else {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter the starting location (path): ");
      start = in.nextLine();
      System.out.print("Enter the file name to find: ");
      filename = in.nextLine();
    }

    find(start, filename);
  }
}
