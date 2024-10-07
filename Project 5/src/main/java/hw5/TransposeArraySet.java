package hw5;

/**
 * Set implemented using plain Java arrays and transpose-sequential-search heuristic.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

  @Override
  protected int find(T t) {
    for (int i = 0; i < numElements; i++) {
      if (t.equals(data[i])) {
        if (i != 0) {
          // If t is at index 0 it does not need to be moved
          // Swap
          T dummy = data[i];
          data[i] = data[i - 1];
          data[i - 1] = dummy;
          // t is now at index i - 1
          return i - 1;
        }
        return i;
      }
    }
    return -1;
  }

  /**
   * The main method.
   *
   * @param args string args
   */
  public static void main(String[] args) {
    TransposeArraySet<String> set = new TransposeArraySet<String>();
    for (int i = 1; i <= 20; i++) {
      set.insert(Integer.toString(i));
    }
    System.out.println(set.has("4"));
    set.insert("20");
    set.insert("20");
    set.remove("4");
    System.out.println(set.has("4"));
    System.out.println(set.has("9"));
    set.remove("15");
    set.remove("15");
  }

}
