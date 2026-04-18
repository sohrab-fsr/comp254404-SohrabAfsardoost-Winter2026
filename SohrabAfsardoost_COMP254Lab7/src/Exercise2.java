import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Exercise2 {

    // Merge two sorted queues into one sorted queue
    public static <K> Queue<K> merge(Queue<K> q1, Queue<K> q2, Comparator<K> comp) {
        Queue<K> result = new LinkedList<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (comp.compare(q1.peek(), q2.peek()) <= 0) {
                result.offer(q1.poll());
            } else {
                result.offer(q2.poll());
            }
        }

        while (!q1.isEmpty()) {
            result.offer(q1.poll());
        }

        while (!q2.isEmpty()) {
            result.offer(q2.poll());
        }

        return result;
    }

    // Bottom-up merge sort using a queue of queues
    public static <K> Queue<K> bottomUpMergeSort(Queue<K> items, Comparator<K> comp) {
        Queue<Queue<K>> queueOfQueues = new LinkedList<>();

        while (!items.isEmpty()) {
            Queue<K> single = new LinkedList<>();
            single.offer(items.poll());
            queueOfQueues.offer(single);
        }

        while (queueOfQueues.size() > 1) {
            Queue<K> q1 = queueOfQueues.poll();
            Queue<K> q2 = queueOfQueues.poll();
            queueOfQueues.offer(merge(q1, q2, comp));
        }

        return queueOfQueues.poll();
    }

    public static void main(String[] args) {
        Queue<String> items = new LinkedList<>();
        items.offer("Tom");
        items.offer("Anna");
        items.offer("Mike");
        items.offer("Bob");
        items.offer("Zara");

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };

        Queue<String> sorted = bottomUpMergeSort(items, comp);

        System.out.println("Sorted queue:");
        while (!sorted.isEmpty()) {
            System.out.println(sorted.poll());
        }
    }
}
