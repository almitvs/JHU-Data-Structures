package hw3.sort;


import hw3.list.IndexedList;

/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  @Override
  public void sort(IndexedList<T> indexedList) {
    T data;
    int j;
    // The sorting algorithm starts at index 1 because index 0 is already trivially sorted
    for (int i = 1; i < indexedList.length(); i++) {
      // The data at the index to be sorted is stored for later
      data = indexedList.get(i);
      // All data right of the new index for the entry to be sorted is shifted right
      for (j = i - 1; j >= 0 && (indexedList.get(j)).compareTo(data) > 0; j--) {
        indexedList.put(j + 1, indexedList.get(j));
      }
      // The entry is put in its appropriate slot
      indexedList.put(j + 1, data);
    }
  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
