/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package linkedlists;

/**
 * A basic singly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SinglyLinkedList<E> implements Cloneable {
  //---------------- nested Node class ----------------
  /**
   * Node of a singly linked list, which stores a reference to its
   * element and to the subsequent node in the list (or null if this
   * is the last node).
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;            // reference to the element stored at this node

    /** A reference to the subsequent node in the list */
    private Node<E> next;         // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
    
  } //----------- end of nested Node class -----------

  // instance variables of the SinglyLinkedList
  /** The head node of the list */
  private Node<E> head = null;               // head node of the list (or null if empty)

  /** The last node of the list */
  private Node<E> tail = null;               // last node of the list (or null if empty)

  /** Number of nodes in the list */
  private int size = 0;                      // number of nodes in the list

  /** Constructs an initially empty list. */
  public SinglyLinkedList() { }              // constructs an initially empty list

  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return head.getElement();
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) {                // adds element e to the front of the list
    head = new Node<>(e, head);              // create and link a new node
    if (size == 0)
      tail = head;                           // special case: new node becomes tail also
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) {                 // adds element e to the end of the list
    Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
    if (isEmpty())
      head = newest;                         // special case: previously empty list
    else
      tail.setNext(newest);                  // new node after existing tail
    tail = newest;                           // new node becomes the tail
    size++;
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {                   // removes and returns the first element
    if (isEmpty()) return null;              // nothing to remove
    E answer = head.getElement();
    head = head.getNext();                   // will become null if list had only one node
    size--;
    if (size == 0)
      tail = null;                           // special case as list is now empty
    return answer;
  }

  @SuppressWarnings({"unchecked"})
  public boolean equals(Object o) {
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
    if (size != other.size) return false;
    Node walkA = head;                               // traverse the primary list
    Node walkB = other.head;                         // traverse the secondary list
    while (walkA != null) {
      if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;   // if we reach this, everything matched successfully
  }

  @SuppressWarnings({"unchecked"})
  public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
    // always use inherited Object.clone() to create the initial copy
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
    if (size > 0) {                    // we need independent chain of nodes
      other.head = new Node<>(head.getElement(), null);
      Node<E> walk = head.getNext();      // walk through remainder of original list
      Node<E> otherTail = other.head;     // remember most recently created node
      while (walk != null) {              // make a new node storing same element
        Node<E> newest = new Node<>(walk.getElement(), null);
        otherTail.setNext(newest);     // link previous node to this one
        otherTail = newest;
        walk = walk.getNext();
      }
    }
    return other;
  }

  public int hashCode() {
    int h = 0;
    for (Node walk=head; walk != null; walk = walk.getNext()) {
      h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
      h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
    }
    return h;
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  
  /**
   * Exercise 2 (Lab 2):
   * Swap two nodes (node1 and node2) by changing links (NOT swapping element contents).
   * You are given references only to node1 and node2, so we must traverse to find their previous nodes.
   *
   * @param node1 first node reference
   * @param node2 second node reference
   */
  public void swapNodes(Node<E> node1, Node<E> node2) {
    if (node1 == null || node2 == null) return;
    if (node1 == node2) return; // same node, nothing to swap
    if (head == null) return;

    // Find previous nodes for node1 and node2 (prev can be null if node is head)
    Node<E> prev1 = null;
    Node<E> prev2 = null;
    Node<E> walk = head;

    while (walk != null && (prev1 == null || prev2 == null)) {
      Node<E> next = walk.getNext();

      if (next == node1) prev1 = walk;
      if (next == node2) prev2 = walk;

      walk = next;
    }

    // If node1 is head, prev1 should be null. If node2 is head, prev2 should be null.
    if (head == node1) prev1 = null;
    if (head == node2) prev2 = null;

    // Confirm both nodes are actually in the list
    boolean found1 = (head == node1) || (prev1 != null);
    boolean found2 = (head == node2) || (prev2 != null);
    if (!found1 || !found2) return;

    // Handle adjacency cases separately to avoid breaking links
    if (node1.getNext() == node2) {
      // node1 immediately before node2
      if (prev1 == null) head = node2;
      else prev1.setNext(node2);

      node1.setNext(node2.getNext());
      node2.setNext(node1);

    } else if (node2.getNext() == node1) {
      // node2 immediately before node1
      if (prev2 == null) head = node1;
      else prev2.setNext(node1);

      node2.setNext(node1.getNext());
      node1.setNext(node2);

    } else {
      // Non-adjacent swap
      if (prev1 == null) head = node2;
      else prev1.setNext(node2);

      if (prev2 == null) head = node1;
      else prev2.setNext(node1);

      Node<E> temp = node1.getNext();
      node1.setNext(node2.getNext());
      node2.setNext(temp);
    }

    // Fix tail if needed
    if (tail == node1) tail = node2;
    else if (tail == node2) tail = node1;
  }

public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }
  //main method
  public static void main(String[] args) {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.addLast(10);
    list.addLast(20);
    list.addLast(30);
    list.addLast(40);
    list.addLast(50);

    System.out.println("Original list: " + list);

    // Get references to two nodes by traversing (for testing)
    Node<Integer> node1 = list.head.getNext();              // node with 20
    Node<Integer> node2 = list.head.getNext().getNext().getNext(); // node with 50? let's be careful

    // head -> 10 -> 20 -> 30 -> 40 -> 50
    // head is 10, so:
    // head.getNext() is 20
    // head.getNext().getNext().getNext() is 40
    node2 = list.head.getNext().getNext().getNext(); // node with 40

    System.out.println("Swapping nodes (20) and (40) ...");
    list.swapNodes(node1, node2);
    System.out.println("After swap: " + list);

    // Test swapping head with tail
    Node<Integer> headNode = list.head; // current head node
    Node<Integer> tailNode = list.tail; // current tail node
    System.out.println("\nSwapping head and tail (" + headNode.getElement() + ") and (" + tailNode.getElement() + ") ...");
    list.swapNodes(headNode, tailNode);
    System.out.println("After swap: " + list);

    // Test swapping same node
    System.out.println("\nSwapping a node with itself (no change) ...");
    list.swapNodes(list.head, list.head);
    System.out.println("After swap: " + list);
  }
  
}
