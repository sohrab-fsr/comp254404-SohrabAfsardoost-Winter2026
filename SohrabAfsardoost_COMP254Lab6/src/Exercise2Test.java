package SohrabAfsardoost_COMP254Lab6;

public class Exercise2Test {
  public static void main(String[] args) {
    SortedTableMap<Integer, String> map = new SortedTableMap<>();

    map.put(10, "A");
    map.put(20, null);
    map.put(30, "C");

    System.out.println("containKey(10): " + map.containKey(10));
    System.out.println("containKey(20): " + map.containKey(20));
    System.out.println("containKey(25): " + map.containKey(25));
    System.out.println("get(20): " + map.get(20));
    System.out.println("get(25): " + map.get(25));
  }
}
