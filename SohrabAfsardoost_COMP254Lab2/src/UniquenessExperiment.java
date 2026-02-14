public class UniquenessExperiment {

    private static final long LIMIT_MS = 60000; // 1 minute

    private static int[] buildUniqueArray(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++)
            data[i] = i;  // all unique (worst-case for unique1)
        return data;
    }

    private static long timeUnique1(int n) {
        int[] data = buildUniqueArray(n);
        long start = System.currentTimeMillis();
        boolean ans = Uniqueness.unique1(data);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long timeUnique2(int n) {
        int[] data = buildUniqueArray(n);
        long start = System.currentTimeMillis();
        boolean ans = Uniqueness.unique2(data);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static int maxNForUnique1() {
        int low = 1;
        int high = 4000;

        // Doubling to find upper bound
        while (true) {
            long t = timeUnique1(high);
            System.out.println("unique1 doubling: n=" + high + " time=" + t + " ms");
            if (t > LIMIT_MS) break;
            low = high;
            high *= 2;
        }

        // Binary search between low (good) and high (too slow)
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            long t = timeUnique1(mid);
            System.out.println("unique1 binary:   n=" + mid + " time=" + t + " ms");
            if (t <= LIMIT_MS) low = mid;
            else high = mid;
        }

        return low;
    }

    private static int maxNForUnique2() {
        int low = 1;
        int high = 100000; // start larger so it doesn't waste time doubling from 1

        // Doubling to find upper bound
        while (true) {
            long t = timeUnique2(high);
            System.out.println("unique2 doubling: n=" + high + " time=" + t + " ms");
            if (t > LIMIT_MS) break;
            low = high;
            high *= 2;
        }

        // Binary search between low (good) and high (too slow)
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            long t = timeUnique2(mid);
            System.out.println("unique2 binary:   n=" + mid + " time=" + t + " ms");
            if (t <= LIMIT_MS) low = mid;
            else high = mid;
        }

        return low;
    }

    public static void main(String[] args) {
        System.out.println("Finding max n for unique1 (<= 1 minute)...");
        int n1 = maxNForUnique1();
        System.out.println("Max n for unique1: " + n1 + " (time: " + timeUnique1(n1) + " ms)");

        System.out.println("\nFinding max n for unique2 (<= 1 minute)...");
        int n2 = maxNForUnique2();
        System.out.println("Max n for unique2: " + n2 + " (time: " + timeUnique2(n2) + " ms)");
    }
}
