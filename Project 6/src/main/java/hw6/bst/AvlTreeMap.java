package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;

  private Node<K, V> rightRotation(Node<K, V> node) {
    Node<K, V> child = node.left;
    node.left = child.right;
    child.right = node;
    node.height = calcNewHeight(node);
    child.height = calcNewHeight(child);
    return child;
  }

  private Node<K, V> leftRotation(Node<K, V> node) {
    Node<K, V> child = node.right;
    node.right = child.left;
    child.left = node;
    node.height = calcNewHeight(node);
    child.height = calcNewHeight(child);
    return child;
  }

  private int calcNewHeight(Node<K, V> node) {
    if (node.left == null && node.right == null) {
      return 0;
    } else if (node.left == null) {
      return node.right.height + 1;
    } else if (node.right == null) {
      return node.left.height + 1;
    } else if (node.left.height > node.right.height) {
      return node.left.height + 1;
    } else {
      return node.right.height + 1;
    }
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    root = insert(root, k, v);
    size++;
  }

  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new Node<>(k, v);
    }
    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
      n.height = calcNewHeight(n);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
      n.height = calcNewHeight(n);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }
    return checkBalance(n, calcBalFac(n));
  }

  private int calcBalFac(Node<K, V> node) {
    if (node.left == null && node.right == null) {
      return 0;
    } else if (node.left == null) {
      return -node.right.height - 1;
    } else if (node.right == null) {
      return node.left.height + 1;
    } else {
      return node.left.height - node.right.height;
    }
  }

  private Node<K, V> checkBalance(Node<K, V> node, int balFac) {
    if (balFac >= 2) {
      if (calcBalFac(node.left) < 0) {
        node.left = leftRotation(node.left);
      }
      return rightRotation(node);
    } else if (balFac <= -2) {
      if (calcBalFac(node.right) > 0) {
        node.right = rightRotation(node.right);
      }
      return leftRotation(node);
    } else {
      return node;
    }
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    Node<K, V> node = findForSure(k);
    V value = node.value;
    root = remove(root, node);
    size--;
    return value;
  }

  private Node<K, V> remove(Node<K, V> subtreeRoot, Node<K, V> toRemove) {
    int cmp = subtreeRoot.key.compareTo(toRemove.key);
    if (cmp == 0) {
      return remove(subtreeRoot);
    } else if (cmp > 0) {
      subtreeRoot.left = remove(subtreeRoot.left, toRemove);
    } else {
      subtreeRoot.right = remove(subtreeRoot.right, toRemove);
    }
    return checkBalance(subtreeRoot, calcBalFac(subtreeRoot));
  }

  private Node<K, V> remove(Node<K, V> node) {
    if (node.right == null) {
      return node.left;
    } else if (node.left == null) {
      return node.right;
    }
    Node<K, V> toReplaceWith = max(node);
    node.key = toReplaceWith.key;
    node.value = toReplaceWith.value;
    node.left = remove(node.left, toReplaceWith);
    return checkBalance(node, calcBalFac(node));
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    return n.value;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  @Override
  public int size() {
    return size;
  }

  private Node<K, V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    Node<K, V> n = root;
    while (n != null) {
      int cmp = k.compareTo(n.key);
      if (cmp < 0) {
        n = n.left;
      } else if (cmp > 0) {
        n = n.right;
      } else {
        return n;
      }
    }
    return null;
  }

  private Node<K, V> findForSure(K k) {
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  private Node<K, V> max(Node<K, V> node) {
    Node<K, V> curr = node.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<K, V> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public K next() {
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }

  /**
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing any existing names, deleting any existing variables,
   * or modifying the overriding methods.
   *
   * <p>Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers.</p>
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int height;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      height = 0;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }
  }

}
