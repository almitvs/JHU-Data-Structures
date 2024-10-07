package hw8.graph;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import java.util.Hashtable;
import java.util.Collections;

/**
 * An implementation of Graph ADT using incidence lists
 * for sparse graphs where most nodes aren't connected.
 *
 * @param <V> Vertex element type.
 * @param <E> Edge element type.
 */
public class SparseGraph<V, E> implements Graph<V, E> {

  private Hashtable<String, VertexNode<V>> vertexNodes = new Hashtable<>();

  private Hashtable<String, EdgeNode<E>> edgeNodes = new Hashtable<>();

  // Converts the vertex back to a VertexNode to use internally
  private VertexNode<V> convert(Vertex<V> v) throws PositionException {
    try {
      VertexNode<V> gv = (VertexNode<V>) v;
      if (gv.owner != this) {
        throw new PositionException();
      }
      return gv;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  // Converts an edge back to a EdgeNode to use internally
  private EdgeNode<E> convert(Edge<E> e) throws PositionException {
    try {
      EdgeNode<E> ge = (EdgeNode<E>) e;
      if (ge.owner != this) {
        throw new PositionException();
      }
      return ge;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  private Boolean hasVertex(VertexNode<V> ver) {
    return vertexNodes.containsKey(ver.data.toString());
  }

  private Boolean hasEdge(EdgeNode<E> edg) {
    return edgeNodes.containsKey(edg.toString());
  }

  private Boolean hasEdge(VertexNode<V> from, VertexNode<V> to) {
    for (Edge<E> edge : outgoing(from)) {
      if ((((EdgeNode<E>) edge).to).equals(to)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Vertex<V> insert(V v) throws InsertionException {
    VertexNode<V> ver = new VertexNode<>(v);
    if (v == null || hasVertex(ver)) {
      throw new InsertionException();
    }
    ver.owner = this;
    vertexNodes.put(v.toString(), ver);
    return ver;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
      throws PositionException, InsertionException {
    VertexNode<V> fro = vertexNodes.get(convert(from).data.toString());
    VertexNode<V> too = vertexNodes.get(convert(to).data.toString());
    if (fro == null || too == null) {
      throw new PositionException();
    }
    EdgeNode<E> edg = new EdgeNode<>(fro, too, e);
    if (fro.data.equals(too.data) || hasEdge(fro, too)) {
      throw new InsertionException();
    }
    edg.owner = this;
    edgeNodes.put(edg.toString(), edg);
    fro.outgoing.put(edg.toString(), edg);
    too.incoming.put(edg.toString(), edg);
    return edg;
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException, RemovalException {
    VertexNode<V> vertex = convert(v);
    if (v == null || !hasVertex(vertex)) {
      throw new PositionException();
    }
    if (!vertex.outgoing.isEmpty() || !vertex.incoming.isEmpty()) {
      throw new RemovalException();
    }
    vertexNodes.remove(vertex.data.toString());
    return vertex.data;
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    if (!hasEdge(edge)) {
      throw new PositionException();
    }
    edgeNodes.remove(edge.toString());
    edge.from.outgoing.remove(edge.toString());
    edge.to.incoming.remove(edge.toString());
    return edge.data;
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    return Collections.unmodifiableCollection(vertexNodes.values());
  }

  @Override
  public Iterable<Edge<E>> edges() {
    return Collections.unmodifiableCollection(edgeNodes.values());
  }

  @Override
  public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    if (v == null || !hasVertex(vertex)) {
      throw new PositionException();
    }
    return Collections.unmodifiableCollection(vertex.outgoing.values());
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    if (v == null || !hasVertex(vertex)) {
      throw new PositionException();
    }
    return Collections.unmodifiableCollection(vertex.incoming.values());
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    if (!hasEdge(edge)) {
      throw new PositionException();
    }
    return edge.from;
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    if (!hasEdge(edge)) {
      throw new PositionException();
    }
    return edge.to;
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {
    VertexNode<V> vertex = convert(v);
    if (!hasVertex(vertex)) {
      throw new PositionException();
    }
    vertex.label = l;
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {
    EdgeNode<E> edge = convert(e);
    if (!hasEdge(edge)) {
      throw new PositionException();
    }
    edge.label = l;
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    if (!hasVertex(vertex)) {
      throw new PositionException();
    }
    return vertex.label;
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    if (!hasEdge(edge)) {
      throw new PositionException();
    }
    return edge.label;
  }

  @Override
  public void clearLabels() {
    for (VertexNode<V> vertex : vertexNodes.values()) {
      vertex.label = null;
      for (EdgeNode<E> edge : vertex.incoming.values()) {
        edge.label = null;
      }
    }
  }

  @Override
  public String toString() {
    GraphPrinter<V, E> gp = new GraphPrinter<>(this);
    return gp.toString();
  }

  // Class for a vertex of type V
  private final class VertexNode<V> implements Vertex<V> {
    V data;
    Graph<V, E> owner;
    Object label;
    Hashtable<String, EdgeNode<E>> incoming;
    Hashtable<String, EdgeNode<E>> outgoing;

    VertexNode(V v) {
      this.data = v;
      this.label = null;
      incoming = new Hashtable<>();
      outgoing = new Hashtable<>();
    }

    @Override
    public V get() {
      return this.data;
    }
  }

  //Class for an edge of type E
  private final class EdgeNode<E> implements Edge<E> {
    E data;
    Graph<V, E> owner;
    VertexNode<V> from;
    VertexNode<V> to;
    Object label;

    // Constructor for a new edge
    EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
      this.from = f;
      this.to = t;
      this.data = e;
      this.label = null;
    }

    @Override
    public E get() {
      return this.data;
    }
  }
}
