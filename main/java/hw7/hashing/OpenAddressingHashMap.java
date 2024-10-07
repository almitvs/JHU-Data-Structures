package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class OpenAddressingHashMap<K, V> implements Map<K, V> {

  private int numEntries;
  private int numItems;
  private int size;
  private Item[] table;
  private final int[] primes = {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,102877, 205759,
                                411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeCount;
  private final double threshold;

  private abstract static class Item {
  }

  private static class Entry<K, V> extends Item {
    private final K key;
    private V value;

    private Entry(K k, V v) {
      this.key = k;
      this.value = v;
    }
  }

  private static class Tombstone extends Item {
    private Tombstone() {
    }
  }

  /**
   * Creates a new OpenAddressingHashMap.
   */
  public OpenAddressingHashMap() {
    numItems = 0;
    numEntries = 0;
    primeCount = 0;
    threshold = 0.7;
    size = primes[primeCount];
    table = new Item[size];
  }


  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null || find(k) >= 0) {
      throw new IllegalArgumentException();
    }
    Entry<K, V> entry = new Entry<>(k, v);
    insert(table, entry);
    numEntries++;
    numItems++;
    if (((double) numItems / (double) size) >= threshold) {
      grow();
    }
  }

  private void insert(Item[] newTable, Entry<K, V> entry) {
    int index = Math.abs(entry.key.hashCode()) % size;
    if (newTable[index] != null) {
      index = probe(newTable, index);
    }
    newTable[index] = entry;
  }

  private int probe(Item[] theTable, int index) {
    while (theTable[index] != null  && !(theTable[index] instanceof Tombstone)) {
      index = (index + 1) % size;
    }
    return index;
  }

  private void grow() {
    Item[] newTable;
    primeCount++;
    if (primeCount >= primes.length) {
      size *= 2;
    } else {
      size = primes[primeCount];
    }
    newTable = new Item[size];
    for (Item item : table) {
      if (item instanceof Entry) {
        insert(newTable, (Entry<K, V>) item);
      }
    }
    numItems = numEntries;
    table = newTable;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k != null) {
      int index = find(k);
      if (index >= 0) {
        V v = ((Entry<K, V>) table[index]).value;
        table[index] = new Tombstone();
        numEntries--;
        return v;
      }
    }
    throw new IllegalArgumentException();
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k != null) {
      int index = find(k);
      if (index >= 0) {
        ((Entry<K, V>) table[index]).value = v;
        return;
      }
    }
    throw new IllegalArgumentException();
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k != null) {
      int index = find(k);
      if (index >= 0) {
        return ((Entry<K, V>) table[index]).value;
      }
    }
    throw new IllegalArgumentException();
  }

  @Override
  public boolean has(K k) {
    if (k != null) {
      return find(k) >= 0;
    }
    return false;
  }

  private int find(K k) {
    int index = Math.abs(k.hashCode()) % size;
    while (table[index] != null) {
      if (table[index] instanceof Entry && k.equals(((Entry<K, V>) table[index]).key)) {
        return index;
      }
      index = (index + 1) % size;
    }
    return -1;
  }

  @Override
  public int size() {
    return numEntries;
  }

  @Override
  public Iterator<K> iterator() {
    return new OpenAddressingHashMapIterator();
  }

  private class OpenAddressingHashMapIterator implements Iterator<K> {
    int counter;
    int numIterated;

    private OpenAddressingHashMapIterator() {
      counter = 0;
      numIterated = 0;
    }

    @Override
    public boolean hasNext() {
      return numIterated < numEntries;
    }

    @Override
    public K next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      while (table[counter] == null || table[counter] instanceof Tombstone) {
        counter++;
      }
      K k = ((Entry<K, V>)table[counter]).key;
      counter++;
      numIterated++;
      return k;
    }
  }

}
