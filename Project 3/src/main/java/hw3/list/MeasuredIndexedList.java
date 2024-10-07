package hw3.list;

import exceptions.IndexException;

/**
 * An ArrayIndexedList that is able to report the number of
 * accesses and mutations, as well as reset those statistics.
 *
 * @param <T> The type of the array.
 */
public class MeasuredIndexedList<T> extends ArrayIndexedList<T>
    implements Measured<T> {

  // The number of accesses and mutations performed
  private int numAcc;
  private int numMut;

  /**
   * Constructor for a MeasuredIndexedList.
   *
   * @param size         The size of the array.
   * @param defaultValue The initial value to set every object to in the array..
   */
  public MeasuredIndexedList(int size, T defaultValue) {
    super(size, defaultValue);

    // Initializes the number of accesses and mutations to 0
    numAcc = 0;
    numMut = 0;
  }

  @Override
  public int length() {
    return super.length();
  }

  @Override
  public T get(int index) throws IndexException {
    T data;
    try {
      data = super.get(index);
      numAcc++;
    } catch (IndexException ex) {
      throw new IndexException();
    }
    return data;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    try {
      super.put(index, value);
      numMut++;
    } catch (IndexException ex) {
      throw new IndexException();
    }
  }

  @Override
  public void reset() {
    numAcc = 0;
    numMut = 0;
  }

  // Returns the number of accesses
  @Override
  public int accesses() {
    return numAcc;
  }

  // Returns the number of mutations
  @Override
  public int mutations() {
    return numMut;
  }

  @Override
  public int count(T value) {
    int count = 0;
    for (int i = 0; i < super.length(); i++) {
      // The get method will update the number of accesses
      if (get(i).equals(value)) {
        // The count of entries with the given value is updated
        count++;
      }
    }
    return count;
  }

}
