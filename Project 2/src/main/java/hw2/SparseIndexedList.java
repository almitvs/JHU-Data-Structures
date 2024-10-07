package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of an IndexedList designed for cases where
 * only a few positions have distinct values from the initial value.
 *
 * @param <T> Element type.
 */
public class SparseIndexedList<T> implements IndexedList<T> {
  private Node<T> head; // The head of the linked list
  private final int length; // The maximum number of entries in the linked list
  private final T defaultValue; // The default value of the list
  private Boolean headDeclared = false; // The head is not initialized until the first node is created

  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public SparseIndexedList(int size, T defaultValue) throws LengthException {
    if (size <= 0) {
      throw new LengthException(); // Throws exception for invalid lengths
    }
    this.length = size; // Sets the length
    head = new Node<>(); // Creates an empty head
    this.defaultValue = defaultValue; // Sets the default value
  }

  /**
   * A public method for users to access the length of the list.
   *
   * @return returns the length of the list
   */
  @Override
  public int length() {
    return length;
  }

  /**
   * Adds a new node and places it based on its index.
   * Indices are sorted in ascending order from the head.
   *
   * @param index the index of the new node
   * @param data the data of the new node
   */
  public void add(int index, T data) {
    Node<T> newNode = new Node<>();
    newNode.index = index;
    newNode.data = data;
    if (!headDeclared) {
      head = newNode;
      headDeclared = true; // The head has been initialized and thus this case is no longer needed
    } else if (index < head.index) {
      newNode.next = head;
      head = newNode;
    } else {
      Node<T> nodeBeforeNew = head; // The nodes are cycled through to find where a node with this index should go
      while (nodeBeforeNew.next != null && index > nodeBeforeNew.next.index) {
        nodeBeforeNew = nodeBeforeNew.next;
      }
      newNode.next = nodeBeforeNew.next;
      nodeBeforeNew.next = newNode;
    }
  }

  /**
   * A method to find if there is a node for a given index.
   *
   * @param index the index of the node
   * @return returns the node with a given index or a null node if none exists
   * @throws IndexException if the index is not within the length
   */
  private Node<T> find(int index) throws IndexException {
    if (index >= length || index < 0) {
      throw new IndexException(); // Exception is thrown for invalid index
    }
    Node<T> dummy = head; // The list is cycled through to attempt to find the node with a given index
    while (dummy != null && dummy.index != index) {
      dummy = dummy.next;
    }
    return dummy; // Either the node with the index if found or a null node
  }

  /**
   * A method to remove a node; it is only called via the put method
   * if a node exists for a given index and the user is putting the
   * default value in it.
   *
   * @param node the node to be removed
   */
  private void remove(Node<T> node) {
    if (node == head) { // A special case for the head because no node points to it
      head = head.next;
      if (head == null) {
        headDeclared = false;
      }
    } else {
      Node<T> dummy = head; // The nodes are cycled through to find the one before the one to be removed
      while (dummy.next != node) {
        dummy = dummy.next;
      }
      dummy.next = dummy.next.next; // The node before removal now points to the node after removal
    }
  }

  /**
   * A public method for users to access the data of any index within the list.
   *
   * @param index representing a position in this list.
   * @return the data for that index
   * @throws IndexException if the index is out of bounds
   */
  @Override
  public T get(int index) throws IndexException {
    if (index >= length || index < 0) {
      throw new IndexException(); // Throws exception for improper input
    }
    Node<T> nodeAtIndex = find(index);
    if (nodeAtIndex != null) {
      if (nodeAtIndex.data == null) {
        return defaultValue;
      }
      return nodeAtIndex.data;
    } else {
      return defaultValue;
    }
  }

  /**
   * A public method for the user to input new data into the list.
   * If a node exists for that index it is updated, else a new node is made.
   * If the default value is set for an existing node this node is deleted.
   *
   * @param index representing a position in this list.
   * @param value to be written at the given index.
   * @throws IndexException if the index is out of bounds
   */
  @Override
  public void put(int index, T value) throws IndexException {
    if (index >= length || index < 0) {
      throw new IndexException(); // An exception is thrown for invalid index
    }
    Node<T> nodeAtIndex = find(index);
    if (nodeAtIndex == null) {
      if (!defaultValue.equals(value) && value != null) {
        add(index, value); // If no node for this index exists or this is the first node then one is created
      }
    } else {
      if (defaultValue.equals(value) || value == null) {
        remove(nodeAtIndex);
      } else {
        nodeAtIndex.data = value;
      }
    }
  }

  /**
   * An iterator to cycle through entries in the list.
   *
   * @return the iterator for the list
   */
  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator(); // The specific implementation is private
  }

  // Node is private and nested in this class
  private static class Node<T> {
    T data; // The data stored in each node
    int index; // The position of the node
    Node<T> next; // The following node; null by default
  }

  private class SparseIndexedListIterator implements Iterator<T> {
    private int current; // The index of the entries cycled through
    private Node<T> nextNodeWithIndex; // The next index with a value stored in a node

    private SparseIndexedListIterator() {
      current = 0; // Cycles indices start with the beginning
      nextNodeWithIndex = head;
    }

    /**
     * A public method for users to see if there are remaining entries.
     *
     * @return true or false for whether there are more entries to cycle through or not
     */
    @Override
    public boolean hasNext() {
      return current < length; // False once the end of the list is hit
    }

    /**
     * A public method for users to use to iterate through the data in the list.
     *
     * @return the data in the entry
     */
    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException(); // Throws exception once the end of the list is hit
      }
      T data = defaultValue;
      if (nextNodeWithIndex != null) {
        if (current == nextNodeWithIndex.index && headDeclared) { // Checks if a node has this index
          data = nextNodeWithIndex.data;
          if (nextNodeWithIndex.next != null) {
            nextNodeWithIndex = nextNodeWithIndex.next; // Nodes are sorted, so the next index will be greater
          }
        }
      }
      current++;
      return data;
    }
  }
}
