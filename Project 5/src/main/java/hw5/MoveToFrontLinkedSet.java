package hw5;

/**
 * Set implemented using a doubly linked list and move-to-front heuristic.
 *
 * @param <T> Element type.
 */
public class MoveToFrontLinkedSet<T> extends LinkedSet<T> {
  @Override
  protected Node<T> find(T t) {
    for (Node<T> n = head; n != null; n = n.next) {
      if (n.data.equals(t)) {
        remove(n);
        if (head == null) {
          head = n;
        } else {
          head.prev = n;
          n.next = head;
          head = n;
          head.prev = null;
        }
        return head;
      }
    }
    return null;
  }

  /**
   * The main method.
   *
   * @param args string args
   */
  public static void main(String[] args) {
    MoveToFrontLinkedSet<Integer> set = new MoveToFrontLinkedSet<Integer>();
    for (int i = 1; i <= 20; i++) {
      set.insert(i);
    }
    System.out.println(set.has(4));
    set.insert(20);
    set.insert(21);
    set.remove(4);
    System.out.println(set.has(4));
    System.out.println(set.has(9));
    set.remove(15);
    set.remove(15);
  }
}
