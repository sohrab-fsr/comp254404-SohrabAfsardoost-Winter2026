public class PrefixAverageExperiment {

  private static double[] buildArray(int n) {
    double[] x = new double[n];
    for (int i = 0; i < n; i++)
      x[i] = i;
    return x;
  }

  public static void main(String[] args) {
    int n = 1000;       // starting value
    int trials = 8;     // number of different input sizes

    System.out.println("Testing prefixAverage2 (expected faster)...");
    for (int t = 0; t < trials; t++) {
      double[] x = buildArray(n);
      long startTime = System.currentTimeMillis();
      double[] a = PrefixAverage.prefixAverage2(x);
      long endTime = System.currentTimeMillis();
      long elapsed = endTime - startTime;
      System.out.println(String.format("n: %9d took %12d ms", n, elapsed));
      n *= 2;
    }

    System.out.println("\nTesting prefixAverage1 (expected slower)...");
    n = 1000;  // reset n
    for (int t = 0; t < trials; t++) {
      double[] x = buildArray(n);
      long startTime = System.currentTimeMillis();
      double[] a = PrefixAverage.prefixAverage1(x);
      long endTime = System.currentTimeMillis();
      long elapsed = endTime - startTime;
      System.out.println(String.format("n: %9d took %12d ms", n, elapsed));
      n *= 2;
    }
  }
}
