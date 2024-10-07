package hw2;

import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    SparseIndexedList<String> list = new SparseIndexedList<>(10, "cat");
    list.put(3, "333");
    list.put(4, "444");
    list.put(8, "888");
    list.put(3, "...333");
    list.put(2, "222");
    list.put(1, "111");
    list.put(5, "555");
    list.put(9, "999");
    list.put(9, null);
    list.put(1, null);
    list.put(0, null);
    list.put(4, ".444");
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
