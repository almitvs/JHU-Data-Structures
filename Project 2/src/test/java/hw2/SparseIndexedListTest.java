package hw2;

import exceptions.LengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SparseIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new SparseIndexedList<>(LENGTH, INITIAL);
  }

  @Test
  @DisplayName("Constructor throws an error for invalid length")
  void testConstructorThrowsLengthException() {
    try {
      SparseIndexedList<Integer> list = new SparseIndexedList<>(-1, 1);
      fail("LengthException was not thrown for index < 0");
    } catch (LengthException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Get() returns default value when user has input null data")
  void testGetReturnsDefaultWhenNullInput() {
    SparseIndexedList<Integer> list = new SparseIndexedList<>(5, 1);
    list.put(0,null);
    assertEquals(1, list.get(0));
  }

  @Test
  @DisplayName("List can hold string types")
  void testListHoldsString() {
    SparseIndexedList<String> newList = new SparseIndexedList<>(5, "cat");
    assertEquals("cat", newList.get(0));
  }
}