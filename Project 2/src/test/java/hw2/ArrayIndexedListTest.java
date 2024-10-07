package hw2;

import exceptions.LengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ArrayIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new ArrayIndexedList<>(LENGTH, INITIAL);
  }

  @Test
  @DisplayName("Constructor throws an error for invalid length")
  void testConstructorThrowsLengthException() {
    try {
      ArrayIndexedList<Integer> list = new ArrayIndexedList<>(-1, 1);
      fail("LengthException was not thrown for index < 0");
    } catch (LengthException ex) {
      return;
    }
  }
}
