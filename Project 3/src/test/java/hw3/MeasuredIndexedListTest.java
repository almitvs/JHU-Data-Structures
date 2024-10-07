package hw3;

import hw3.list.MeasuredIndexedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import exceptions.IndexException;

public class MeasuredIndexedListTest {

  private static final int LENGTH = 15;
  private static final int DEFAULT_VALUE = 3;

  private MeasuredIndexedList<Integer> measuredIndexedList;

  @BeforeEach
  void setup() {
    measuredIndexedList = new MeasuredIndexedList<>(LENGTH, DEFAULT_VALUE);
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero reads")
  void zeroReadsStart() {
    assertEquals(0, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero writes")
  void zeroWritesStart() {
    assertEquals(0, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("MeasuredIndexedList declares an array with the set length")
  void arrayHasCorrectLength() { assertEquals(LENGTH, measuredIndexedList.length()); }

  @Test
  @DisplayName("MeasuredIndexedList instantiates the array with the default value")
  void arrayHasDefaultValue() {
    for (int element : measuredIndexedList) {
      assertEquals(DEFAULT_VALUE, element);
    }
  }

  @Test
  @DisplayName("Put adds the given value at the given index")
  void putAddsValueAtIndex() {
    measuredIndexedList.put(3, 5);
    assertEquals(5, measuredIndexedList.get(3));
  }

  @Test
  @DisplayName("Put overwrites the value at the given index if already instantiated")
  void putChangesValueAtIndex() {
    measuredIndexedList.put(3, 5);
    measuredIndexedList.put(3, 4);
    assertEquals(4, measuredIndexedList.get(3));
  }

  @Test
  @DisplayName("Put updates number of mutations")
  void putUpdatesNumMut() {
    measuredIndexedList.put(3, 5);
    assertEquals(1, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("Get updates number of accesses")
  void getUpdatesNumAcc() {
    measuredIndexedList.get(3);
    assertEquals(1, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Count causes get to update number of accesses")
  void countUpdatesNumAcc() {
    measuredIndexedList.count(1);
    assertEquals(LENGTH, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Count returns the correct value")
  void countCountsAllValues() {
    assertEquals(LENGTH, measuredIndexedList.count(DEFAULT_VALUE));
  }

  @Test
  @DisplayName("Count returns the correct number of entries")
  void countCountsAllAddedValues() {
    measuredIndexedList.put(3, 5);
    assertEquals(1, measuredIndexedList.count(5));
  }

  @Test
  @DisplayName("Reset updates number of mutations")
  void resetUpdatesNumMut() {
    measuredIndexedList.put(3, 5);
    measuredIndexedList.reset();
    assertEquals(0, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("Reset updates number of accesses")
  void resetUpdatesNumAcc() {
    measuredIndexedList.get(3);
    measuredIndexedList.reset();
    assertEquals(0, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Mutations updates number of mutations after reset")
  void updatesNumMutAfterReset() {
    measuredIndexedList.put(3, 5);
    measuredIndexedList.reset();
    measuredIndexedList.put(3, 5);
    assertEquals(1, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("Access updates number of accesses after reset")
  void updatesNumAccAfterReset() {
    measuredIndexedList.get(3);
    measuredIndexedList.reset();
    measuredIndexedList.get(3);
    assertEquals(1, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Put throws index exception")
  void putThrowsEx() {
    try {
      measuredIndexedList.put(LENGTH, 5);
      fail("Index Exception not thrown");
    } catch (IndexException ex) { return; }
  }

  @Test
  @DisplayName("Get throws index exception")
  void getThrowsEx() {
    try {
      measuredIndexedList.get(LENGTH);
      fail("Index Exception not thrown");
    } catch (IndexException ex) { return; }
  }

  @Test
  @DisplayName("Mutations only updated for valid puts")
  void numMutOnlyUpdatedWhenValid() {
    try {
      measuredIndexedList.put(LENGTH, 5);
    } catch (IndexException ex) {
      assertEquals(0, measuredIndexedList.mutations());
    }
  }

  @Test
  @DisplayName("Accesses only updated for valid gets")
  void numAccOnlyUpdatedWhenValid() {
    try {
      measuredIndexedList.get(LENGTH);
    } catch (IndexException ex) {
      assertEquals(0, measuredIndexedList.accesses());
    }
  }

}
