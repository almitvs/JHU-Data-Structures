package hw5;

import exceptions.EmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class DequeTest {

  private Deque<String> deque;

  @BeforeEach
  public void setUp() {
    this.deque = createDeque();
  }

  protected abstract Deque<String> createDeque();

  @Test
  @DisplayName("Deque is empty after construction.")
  public void testConstructor() {
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Deque is empty after insertFront and removeFront.")
  public void testInsertFrontThenRemoveFront() {
    deque.insertFront("x");
    deque.removeFront();
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Deque is empty after insertFront and removeBack.")
  public void testInsertFrontThenRemoveBack() {
    deque.insertFront("x");
    deque.removeBack();
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Deque is empty after insertBack and removeFront.")
  public void testInsertBackThenRemoveFront() {
    deque.insertBack("x");
    deque.removeFront();
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Deque is empty after insertBack and removeBack.")
  public void testInsertBackThenRemoveBack() {
    deque.insertBack("x");
    deque.removeBack();
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertFront.")
  public void testLengthAfterInsertFront() {
    deque.insertFront("x");
    assertEquals(1, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack.")
  public void testLengthAfterInsertBack() {
    deque.insertBack("x");
    assertEquals(1, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertFront twice.")
  public void testLengthAfterInsertFrontTwice() {
    deque.insertFront("x");
    deque.insertFront("x");
    assertEquals(2, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack twice.")
  public void testLengthAfterInsertBackTwice() {
    deque.insertBack("x");
    deque.insertBack("x");
    assertEquals(2, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack thrice.")
  public void testLengthAfterInsertBackThrice() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    assertEquals(3, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack four times.")
  public void testLengthAfterInsertBackFourTimes() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.insertBack("a");
    assertEquals(4, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack five times.")
  public void testLengthAfterInsertBackFiveTimes() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.insertBack("a");
    deque.insertBack("b");
    assertEquals(5, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack six times.")
  public void testLengthAfterInsertBackSixTimes() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.insertBack("a");
    deque.insertBack("b");
    deque.insertBack("c");
    assertEquals(6, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack seven times.")
  public void testLengthAfterInsertBackSevenTimes() {
    int n = 7;
    for (int i = 1; i <= n; i++) {
      deque.insertBack(Integer.toString(i));
    }
    assertEquals(n, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBack 100 times.")
  public void testLengthAfterInsertBackOneHundredTimes() {
    for (int i = 1; i <= 100; i++) {
      deque.insertBack(Integer.toString(i));
    }
    assertEquals(100, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertFront then insertBack.")
  public void testLengthAfterInsertFrontInsertBack() {
    deque.insertFront("x");
    deque.insertBack("y");
    assertEquals(2, deque.length());
  }

  @Test
  @DisplayName("Length is updated after insertBach then insertFront.")
  public void testLengthAfterInsertBackInsertFront() {
    deque.insertBack("x");
    deque.insertFront("x");
    assertEquals(2, deque.length());
  }

  @Test
  @DisplayName("Front throws EmptyException after construction.")
  public void frontThrowsEmptyExceptionAfterConstruction() {
    try {
      deque.front();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Back throws EmptyException after construction.")
  public void backThrowsEmptyExceptionAfterConstruction() {
    try {
      deque.back();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Front throws EmptyException after insertFront then removeFront.")
  public void frontThrowsEmptyExceptionAfterInsertFrontRemoveFront() {
    deque.insertFront("x");
    deque.removeFront();
    try {
      deque.front();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Front throws EmptyException after insertFront then removeBack.")
  public void frontThrowsEmptyExceptionAfterInsertFrontRemoveBack() {
    deque.insertFront("x");
    deque.removeBack();
    try {
      deque.front();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Front throws EmptyException after insertBack then removeFront.")
  public void frontThrowsEmptyExceptionAfterInsertBackRemoveFront() {
    deque.insertBack("x");
    deque.removeFront();
    try {
      deque.front();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Front throws EmptyException after insertBack then removeBack.")
  public void frontThrowsEmptyExceptionAfterBackFrontRemoveBack() {
    deque.insertBack("x");
    deque.removeBack();
    try {
      deque.front();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }


  @Test
  @DisplayName("Back throws EmptyException after insertFront then removeFront.")
  public void backThrowsEmptyExceptionAfterInsertFrontRemoveFront() {
    deque.insertFront("x");
    deque.removeFront();
    try {
      deque.back();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Back throws EmptyException after insertFront then removeBack.")
  public void backThrowsEmptyExceptionAfterInsertFrontRemoveBack() {
    deque.insertFront("x");
    deque.removeBack();
    try {
      deque.back();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Back throws EmptyException after insertBack then removeFront.")
  public void backThrowsEmptyExceptionAfterInsertBackRemoveFront() {
    deque.insertBack("x");
    deque.removeFront();
    try {
      deque.back();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Back throws EmptyException after insertBack then removeBack.")
  public void backThrowsEmptyExceptionAfterBackFrontRemoveBack() {
    deque.insertBack("x");
    deque.removeBack();
    try {
      deque.back();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("Front returns correct value after insertFront.")
  public void frontReturnsCorrectlyAfterInsertFront() {
    deque.insertFront("x");
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after insertBack.")
  public void frontReturnsCorrectlyAfterInsertBack() {
    deque.insertBack("x");
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after insertFront twice.")
  public void frontReturnsCorrectlyAfterInsertFrontTwice() {
    deque.insertFront("x");
    deque.insertFront("y");
    assertEquals("y", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after insertFront then insertBack.")
  public void frontReturnsCorrectlyAfterInsertFrontThenInsertBack() {
    deque.insertFront("x");
    deque.insertBack("y");
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after insertBack then insertFront.")
  public void frontReturnsCorrectlyAfterInsertBackThenInsertFront() {
    deque.insertBack("x");
    deque.insertFront("y");
    assertEquals("y", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after insertBack twice.")
  public void frontReturnsCorrectlyAfterInsertBackTwice() {
    deque.insertBack("x");
    deque.insertBack("y");
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Back returns correct value after insertFront.")
  public void backReturnsCorrectlyAfterInsertFront() {
    deque.insertFront("x");
    assertEquals("x", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after insertBack.")
  public void backReturnsCorrectlyAfterInsertBack() {
    deque.insertBack("x");
    assertEquals("x", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after insertFront twice.")
  public void backReturnsCorrectlyAfterInsertFrontTwice() {
    deque.insertFront("x");
    deque.insertFront("y");
    assertEquals("x", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after insertFront then insertBack.")
  public void backReturnsCorrectlyAfterInsertFrontThenInsertBack() {
    deque.insertFront("x");
    deque.insertBack("y");
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("back returns correct value after insertBack then insertFront.")
  public void backReturnsCorrectlyAfterInsertBackThenInsertFront() {
    deque.insertBack("x");
    deque.insertFront("y");
    assertEquals("x", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after insertBack twice.")
  public void backReturnsCorrectlyAfterInsertBackTwice() {
    deque.insertBack("x");
    deque.insertBack("y");
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("Front returns correct value after removeFront.")
  public void frontReturnsCorrectlyAfterRemoveFront() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeFront();
    assertEquals("y", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after removeBack.")
  public void frontReturnsCorrectlyAfterRemoveBack() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeBack();
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Back returns correct value after removeFront.")
  public void backReturnsCorrectlyAfterRemoveFront() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeFront();
    assertEquals("z", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after removeBack.")
  public void backReturnsCorrectlyAfterRemoveBack() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeBack();
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("Front returns correct value after removeFront Twice.")
  public void frontReturnsCorrectlyAfterRemoveFrontTwice() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeFront();
    deque.removeFront();
    assertEquals("z", deque.front());
  }

  @Test
  @DisplayName("Front returns correct value after removeBack Twice.")
  public void frontReturnsCorrectlyAfterRemoveBackTwice() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeBack();
    deque.removeBack();
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Back returns correct value after removeFront Twice.")
  public void backReturnsCorrectlyAfterRemoveFrontTwice() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeFront();
    deque.removeFront();
    assertEquals("z", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after removeBack Twice.")
  public void backReturnsCorrectlyAfterRemoveBackTwice() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeBack();
    deque.removeBack();
    assertEquals("x", deque.back());
  }

  @Test
  @DisplayName("Front returns correct value after removeFront then removeBack.")
  public void frontReturnsCorrectlyAfterRemoveFrontThenRemoveBack() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeFront();
    deque.removeBack();
    assertEquals("y", deque.front());
  }

  @Test
  @DisplayName("Back returns correct value after removeBack Twice.")
  public void frontReturnsCorrectlyAfterRemoveBackThenRemoveFront() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeBack();
    deque.removeBack();
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Back returns correct value after removeFront then removeBack.")
  public void backReturnsCorrectlyAfterRemoveFrontThenRemoveBack() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeFront();
    deque.removeBack();
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("Back returns correct value after removeBack Twice.")
  public void backReturnsCorrectlyAfterRemoveBackThenRemoveFront() {
    deque.insertBack("x");
    deque.insertBack("y");
    deque.insertBack("z");
    deque.removeBack();
    deque.removeFront();
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("Front insert then Front returns correct value after removeFront.")
  public void frontInsertThenFrontReturnsCorrectlyAfterRemoveFront() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeFront();
    assertEquals("y", deque.front());
  }

  @Test
  @DisplayName("Front insert then Front returns correct value after removeBack.")
  public void frontInsertThenFrontReturnsCorrectlyAfterRemoveBack() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeBack();
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeFront.")
  public void frontInsertThenBackReturnsCorrectlyAfterRemoveFront() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeFront();
    assertEquals("z", deque.back());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeBack.")
  public void frontInsertThenBackReturnsCorrectlyAfterRemoveBack() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeBack();
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("Front insert then Front returns correct value after removeFront Twice.")
  public void frontInsertThenFrontReturnsCorrectlyAfterRemoveFrontTwice() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeFront();
    deque.removeFront();
    assertEquals("z", deque.front());
  }

  @Test
  @DisplayName("Front insert then Front returns correct value after removeBack Twice.")
  public void frontInsertThenFrontReturnsCorrectlyAfterRemoveBackTwice() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeBack();
    deque.removeBack();
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeFront Twice.")
  public void frontInsertThenBackReturnsCorrectlyAfterRemoveFrontTwice() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeFront();
    deque.removeFront();
    assertEquals("z", deque.back());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeBack Twice.")
  public void frontInsertThenBackReturnsCorrectlyAfterRemoveBackTwice() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeBack();
    deque.removeBack();
    assertEquals("x", deque.back());
  }

  @Test
  @DisplayName("Front insert then Front returns correct value after removeFront then removeBack.")
  public void frontInsertThenFrontReturnsCorrectlyAfterRemoveFrontThenRemoveBack() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeFront();
    deque.removeBack();
    assertEquals("y", deque.front());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeBack Twice.")
  public void frontInsertThenFrontReturnsCorrectlyAfterRemoveBackThenRemoveFront() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeBack();
    deque.removeBack();
    assertEquals("x", deque.front());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeFront then removeBack.")
  public void frontInsertThenBackReturnsCorrectlyAfterRemoveFrontThenRemoveBack() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeFront();
    deque.removeBack();
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("Front insert then Back returns correct value after removeBack Twice.")
  public void frontInsertThenBackReturnsCorrectlyAfterRemoveBackThenRemoveFront() {
    deque.insertFront("z");
    deque.insertFront("y");
    deque.insertFront("x");
    deque.removeBack();
    deque.removeFront();
    assertEquals("y", deque.back());
  }

  @Test
  @DisplayName("RemoveFront throws EmptyException.")
  public void removeFrontThrowsEmptyException() {
    try {
      deque.removeFront();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

  @Test
  @DisplayName("RemoveBack throws EmptyException.")
  public void removeBackThrowsEmptyException() {
    try {
      deque.removeBack();
      fail("EmptyException not thrown");
    } catch (EmptyException e) {
      return;
    }
  }

}
