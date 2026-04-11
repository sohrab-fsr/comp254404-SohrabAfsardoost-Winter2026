package SohrabAfsardoost_COMP254Lab6;

import java.util.Random;

public class Exercise1Test {
  public static void main(String[] args) {
    int n = 20000;
    double[] loads = {0.5, 0.7, 0.9};
    Random rand = new Random();

    for (double load : loads) {
      ChainHashMap<Integer, Integer> map = new ChainHashMap<>(17, load);

      long startPut = System.nanoTime();
      for (int i = 0; i < n; i++) {
        int key = rand.nextInt(1000000);
        map.put(key, i);
      }
      long endPut = System.nanoTime();

      long startGet = System.nanoTime();
      for (int i = 0; i < n; i++) {
        int key = rand.nextInt(1000000);
        map.get(key);
      }
      long endGet = System.nanoTime();

      System.out.println("ChainHashMap with load factor " + load);
      System.out.println("Put time: " + (endPut - startPut) + " ns");
      System.out.println("Get time: " + (endGet - startGet) + " ns");
      System.out.println("Final size: " + map.size());
      System.out.println();
    }
  }
}
