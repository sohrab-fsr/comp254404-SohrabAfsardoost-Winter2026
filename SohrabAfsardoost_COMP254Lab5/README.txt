Sohrab Afsardoost - COMP254 Lab 5

This folder is rebuilt using the real lecture files.
Only small changes were made to match the lab questions.

Files changed/added:
1. TreeLabExercises.java
   - Added inorderNext(p) for Exercise 1
   - Added printAllSubtreeHeights() for Exercise 2

2. HeapPriorityQueue.java
   - Modified upheap(int j) to use recursion instead of a loop for Exercise 3

3. Exercise1Test.java
4. Exercise2Test.java
5. Exercise3Test.java
   - Simple test files to run in IntelliJ

Exercise 1:
Chosen operation: inorderNext(p)
Worst-case running time: O(h)
If the tree is skewed, h can be n, so worst-case is O(n).

Exercise 2:
Method uses postorder traversal.
Each node is visited once, so running time is O(n).

Exercise 3:
Recursive upheap still has worst-case running time O(log n),
because it can move up from a leaf to the root of the heap.

How to run:
- Open src in IntelliJ
- Run Exercise1Test
- Run Exercise2Test
- Run Exercise3Test 
