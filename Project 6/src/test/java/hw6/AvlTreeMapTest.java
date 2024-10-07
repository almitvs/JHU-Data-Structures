package hw6;

import hw6.bst.AvlTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertCausesLeftRotation() {
    map.insert("1", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a left rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesRightRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesLeftRightRotation() {
    map.insert("3", "c");
    map.insert("1", "a");
    map.insert("2", "b");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesRightLeftRotation() {
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("2", "b");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesNoRotation() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void updateValue() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.put("3", "cc");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:cc"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDuplicateThrowsIllegalArgumentException() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    try {
      map.insert("3", "cc");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeCausesLeftRotation() {
    map.insert("1", "a");
    map.insert("0", " ");
    map.insert("2", "b");
    map.insert("3", "c");
    map.remove("0");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesRightRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("4", "d");
    map.insert("1", "a");
    map.remove("4");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesLeftRightRotation() {
    map.insert("3", "c");
    map.insert("1", "a");
    map.insert("4", "d");
    map.insert("2", "b");
    map.remove("4");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesRightLeftRotation() {
    map.insert("1", "a");
    map.insert("0", " ");
    map.insert("3", "c");
    map.insert("2", "b");
    map.remove("0");
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesNoRotation() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.remove("3");
    String[] expected = new String[]{
        "2:b",
        "1:a null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeNodeWithOneChild() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("4", "d");
    map.remove("3");
    String[] expected = new String[]{
        "2:b",
        "1:a 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeNodeWithTwoChildren() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.remove("2");
    String[] expected = new String[]{
            "1:a",
            "null 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesRotationAndInsertCausesRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("4", "d");
    map.insert("1", "a");
    map.remove("4");
    map.insert("4", "d");
    map.insert("5", "3");
    String[] expected = new String[]{
        "2:b",
        "1:a 4:d",
        "null null 3:c 5:3"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesRotationAndRemoveCausesRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("4", "d");
    map.remove("2");
    String[] expected = new String[]{
        "3:c",
        "1:a 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}
