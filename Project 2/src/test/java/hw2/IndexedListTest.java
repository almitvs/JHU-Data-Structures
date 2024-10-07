package hw2;

import exceptions.IndexException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Tests for any class implementing the IndexedList interface.
 */
public abstract class IndexedListTest {
  protected static final int LENGTH = 10;
  protected static final int INITIAL = 7;
  private IndexedList<Integer> indexedList;

  public abstract IndexedList<Integer> createArray();

  @BeforeEach
  public void setup() {
    indexedList = createArray();
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < indexedList.length(); index++) {
      assertEquals(INITIAL, indexedList.get(index));
    }
  }

  @Test
  @DisplayName("get() throws exception if index is below the valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      indexedList.get(-1);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("get() throws exception if index is above the valid range.")
  void testGetWithIndexAboveRangeThrowsException() {
    try {
      indexedList.get(LENGTH);
      fail("IndexException was not thrown for index >= LENGTH");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("length() returns the length of the list after the list has been instantiated")
  void testLengthAfterConstruction() {
    assertEquals(LENGTH, indexedList.length());
  }

  @Test
  @DisplayName("get() returns the correct value for a given index after data has been input")
  void testGetAfterSingleDataInstantiated() {
    indexedList.put(0,1);
    assertEquals(1, indexedList.get(0));
  }

  @Test
  @DisplayName("get() returns the correct value for a given index after data has been re-input")
  void testGetAfterSingleDataInstantiatedTwice() {
    indexedList.put(0,1);
    indexedList.put(0,2);
    assertEquals(2, indexedList.get(0));
  }

  @Test
  @DisplayName("get() returns the correct value for a given index after data has been set to default")
  void testGetAfterDataSetToDefault() {
    indexedList.put(0,1);
    indexedList.put(0, INITIAL);
    assertEquals(INITIAL, indexedList.get(0));
  }

  @Test
  @DisplayName("get() returns the correct value after the first data point has been set to default")
  void testGetAfterHeadSetToDefault() {
    indexedList.put(0,1);
    indexedList.put(1,1);
    indexedList.put(0, INITIAL);
    assertEquals(1, indexedList.get(1));
  }

  @Test
  @DisplayName("get() returns the correct value after a middle data point has been set to default")
  void testGetAfterMiddleSetToDefault() {
    indexedList.put(0,1);
    indexedList.put(1,1);
    indexedList.put(2,1);
    indexedList.put(1, INITIAL);
    assertEquals(1, indexedList.get(2));
  }

  @Test
  @DisplayName("get() returns the correct value after the end point has been set to default")
  void testGetAfterEndSetToDefault() {
    indexedList.put(0,1);
    indexedList.put(1,1);
    indexedList.put(2,1);
    indexedList.put(2, INITIAL);
    assertEquals(INITIAL, indexedList.get(2));
  }

  @Test
  @DisplayName("put() throws exception if index is above the valid range.")
  void testPutWithIndexAboveRangeThrowsException() {
    try {
      indexedList.put(LENGTH, 1);
      fail("IndexException was not thrown for index >= LENGTH");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("put() throws exception if index is below the valid range.")
  void testPutWithIndexBelowRangeThrowsException() {
    try {
      indexedList.put(-1, 1);
      fail("IndexException was not thrown for index >= LENGTH");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Enhanced For Loop Iterates Through List")
  void testEnhancedForLoop() {
    Iterator<Integer> iterator = indexedList.iterator();
    int counter = 0;
    for(int element: indexedList) {
      assertEquals(INITIAL, element);
      counter++;
    }
    assertEquals(LENGTH, counter);
  }

  @Test
  @DisplayName("Iterator iterates through values with while loop")
  void testIteratorWhileLoop() {
    Iterator<Integer> iterator = indexedList.iterator();
    int counter = 0;
    int element;
    while(iterator.hasNext()) {
      element = iterator.next();
      counter++;
    }
    assertEquals(LENGTH, counter);
  }

  @Test
  @DisplayName("Iterator throws NoSuchElementException")
  void testNoSuchElementException() {
    Iterator<Integer> iterator = indexedList.iterator();
    int counter = 0;
    int element;
    while(iterator.hasNext()) {
      element = iterator.next();
      counter++;
    }
    try {
      element = iterator.next();
      fail("NoSuchElementException was not thrown");
    } catch (NoSuchElementException ex) {
      return;
    }
  }

}
