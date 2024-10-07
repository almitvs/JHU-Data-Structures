package hw6;

import hw6.bst.TreapMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>(1);
  }

  @Test
  public void replaceValue() {
    map.insert("2", "b");
    map.put("2", "bb");
    String[] expected = new String[]{
            "2:bb:-1155869325"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDuplicateKeyThrowsIllegalArgumentException() {
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
  public void insertCausesLeftRotation() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("4", "d");
    String[] expected = new String[]{
        "2:b:-1155869325",
        "1:a:431529176 4:d:1749940626",
        "null null 3:c:1761283695 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesRightRotation() {
    map.insert("3", "c");
    map.insert("4", "d");
    map.insert("2", "b");
    map.insert("1", "a");
    String[] expected = new String[]{
        "3:c:-1155869325",
        "1:a:1749940626 4:d:431529176",
        "null 2:b:1761283695 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesNoRotation() {
    map.insert("1", "a");
    map.insert("2", "b");
    String[] expected = new String[]{
        "1:a:-1155869325",
        "null 2:b:431529176"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesLeftRotation() {
    map.insert("2", "b");
    map.insert("3", "c");
    map.insert("1", "a");
    map.remove("2");
    String[] expected = new String[]{
        "3:c:431529176",
        "1:a:1761283695 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesRightRotation() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.remove("2");
    String[] expected = new String[]{
        "1:a:431529176",
        "null 3:c:1761283695"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeNodeWithOneLeftChildCausesNoRotation() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.remove("2");
    String[] expected = new String[]{
        "1:a:431529176"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeNodeWithOneRightChildCausesNoRotation() {
    map.insert("2", "b");
    map.insert("3", "c");
    map.remove("2");
    String[] expected = new String[]{
        "3:c:431529176"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeLeafCausesNoRotation() {
    map.insert("2", "b");
    map.insert("3", "c");
    map.remove("3");
    String[] expected = new String[]{
        "2:b:-1155869325"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesMultipleRotations() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("4", "d");
    map.insert("5", "e");
    map.insert("6", "f");
    map.remove("3");
    map.remove("4");
    map.insert("7", "g");
    // every operation up to this point set up the tree so removing 2 will cause multiple rotations
    map.remove("2");
    String[] expected = new String[]{
        "6:f:155629808",
        "1:a:431529176 7:g:1429008869",
        "null 5:e:892128508 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertCausesRotationAndRemoveCausesRotation() {
    map.insert("3", "c");
    map.insert("4", "d");
    map.insert("2", "b");
    map.insert("1", "a");
    map.remove("3");
    String[] expected = new String[]{
        "4:d:431529176",
        "1:a:1749940626 null",
        "null 2:b:1761283695 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeCausesRotationAndInsertCausesRotation() {
    map.insert("3", "c");
    map.insert("4", "d");
    map.insert("2", "b");
    map.remove("3");
    map.insert("1", "a");
    String[] expected = new String[]{
        "4:d:431529176",
        "1:a:1749940626 null",
        "null 2:b:1761283695 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

}