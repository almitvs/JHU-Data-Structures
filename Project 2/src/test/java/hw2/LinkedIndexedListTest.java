package hw2;

import exceptions.LengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class LinkedIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new LinkedIndexedList<>(LENGTH, INITIAL);
  }

  @Test
  @DisplayName("Constructor throws an error for invalid length")
  void testConstructorThrowsLengthException() {
    try {
      LinkedIndexedList<Integer> list = new LinkedIndexedList<>(-1, 1);
      fail("LengthException was not thrown for index < 0");
    } catch (LengthException ex) {
      return;
    }
  }
}
