package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ChainingHashMap<K, V> implements Map<K, V> {

  private int numItems;
  private int size;
  private LinkedList<Node<K, V>>[] table;
  private final int[] primes = {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,102877, 205759,
                                411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeCount;
  private final double threshold;

  private static class Node<K, V> {
    private final K key;
    private V value;

    private Node(K k, V v) {
      this.key = k;
      this.value = v;
    }
  }

  /**
   * Creates a new ChainingHashMap.
   */
  public ChainingHashMap() {
    numItems = 0;
    primeCount = 0;
    threshold = 0.7;
    size = primes[primeCount];
    table = new LinkedList[size];
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null || has(k)) {
      throw new IllegalArgumentException();
    }
    insert(table, new Node<>(k, v));
    numItems++;
    if ((double) numItems / (double) size >= threshold) {
      grow();
    }
  }

  private void insert(LinkedList<Node<K, V>>[] newTable, Node<K, V> node) {
    int index = Math.abs(node.key.hashCode()) % size;
    if (newTable[index] == null) {
      newTable[index] = new LinkedList<>();
    }
    newTable[index].addLast(node);
  }

  private void grow() {
    primeCount++;
    if (primeCount >= primes.length) {
      size *= 2;
    } else {
      size = primes[primeCount];
    }
    LinkedList<Node<K, V>>[] newTable = new LinkedList[size];
    for (LinkedList<Node<K, V>> list : table) {
      if (list != null) {
        for (Node<K, V> node : list) {
          insert(newTable, node);
        }
      }
    }
    table = newTable;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    int index = Math.abs(k.hashCode()) % size;
    Node<K, V> node = find(k);
    if (node == null) {
      throw new IllegalArgumentException();
    }
    table[index].remove(node);
    if (table[index].isEmpty()) {
      table[index] = null;
    }
    numItems--;
    return node.value;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    Node<K, V> node = find(k);
    if (node == null) {
      throw new IllegalArgumentException();
    }
    node.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    Node<K, V> node = find(k);
    if (node == null) {
      throw new IllegalArgumentException();
    }
    return node.value;
  }

  @Override
  public boolean has(K k) {
    if (k != null) {
      return find(k) != null;
    }
    return false;
  }

  private Node<K, V> find(K k) {
    int index = Math.abs(k.hashCode()) % size;
    if (table[index] != null) {
      for (Node<K, V> node : table[index]) {
        if (k.equals(node.key)) {
          return node;
        }
      }
    }
    return null;
  }

  @Override
  public int size() {
    return numItems;
  }

  @Override
  public Iterator<K> iterator() {
    return new ChainingHashMapIterator();
  }

  private class ChainingHashMapIterator implements Iterator<K> {

    private int listIndex;
    private int tableIndex;
    private int numIterated;

    private ChainingHashMapIterator() {
      listIndex = 0;
      tableIndex = 0;
      numIterated = 0;
    }

    @Override
    public boolean hasNext() {
      return numItems != 0 && numIterated != numItems;
    }

    @Override
    public K next() throws NoSuchElementException {
      Node<K, V> node;
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      while (table[tableIndex] == null) {
        tableIndex++;
      }
      node = table[tableIndex].get(listIndex);
      if (node == table[tableIndex].getLast()) {
        listIndex = 0;
        tableIndex++;
      } else {
        listIndex++;
      }
      numIterated++;
      return node.key;
    }
  }
}
