package hw8;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import hw8.graph.Edge;
import hw8.graph.Graph;
import hw8.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class GraphTest {

  protected Graph<String, String> graph;
  protected Graph<String, String> graph2;

  @BeforeEach
  public void setupGraph() {
    this.graph = createGraph();
    this.graph2 = createGraph();
  }

  protected abstract Graph<String, String> createGraph();

  @Test
  @DisplayName("insert(v) returns a vertex with given data")
  public void canGetVertexAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
  }

  @Test
  @DisplayName("insert(U, V, e) returns an edge with given data")
  public void canGetEdgeAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
  }

  @Test
  @DisplayName("insert(null, V, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenFirstVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(null, v, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(null, V, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenSecondVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, null, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(null, V, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenNullVertices() {
    try {
      graph.insert(null, null, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert null vertex throws exception")
  public void insertNullVertexThrowsException() {
    try {
      Vertex<String> v = graph.insert(null);
      fail ("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert duplicate vertex throws exception")
  public void insertDuplicateVertexThrowsException() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> v1 = graph.insert("v");
      fail ("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert edge with vertex not in graph throws exception")
  public void insertEdgeWithVertexNotInGraph() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph2.insert("w");
      graph.insert(w, v, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert edge with second vertex not in graph throws exception")
  public void insertEdgeWithSecondVertexNotInGraph() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph2.insert("w");
      graph.insert(v, w, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert edge with vertices not in graph throws exception")
  public void insertEdgeWithVerticesNotInGraph() {
    try {
      Vertex<String> v = graph2.insert("v");
      Vertex<String> w = graph2.insert("w");
      graph.insert(w, v, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert edge that would make self loop throws exception")
  public void selfLoopThrowsException() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, v, "e");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("duplicate edge throws exception")
  public void duplicateEdgeThrowsException() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph.insert("w");
      graph.insert(v, w, "e");
      graph.insert(v, w, "f");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove null vertex throws exception")
  public void removeNullVertexThrowsException() {
    try {
      Vertex<String> v = null;
      graph.remove(v);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove invalid vertex throws exception")
  public void removeInvalidVertexThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      graph.remove(v);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove vertex with edges throws exception")
  public void removeVertexWithEdgesThrowsException() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph.insert("w");
      Edge<String> e = graph.insert(v, w, "e");
      graph.remove(v);
      fail("The expected exception was not thrown");
    } catch (RemovalException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove vertex")
  public void removeVertex() {
    Vertex<String> v = graph.insert("v");
    assertEquals("v", graph.remove(v));
  }

  @Test
  @DisplayName("remove vertex after removing edges")
  public void removeVertexAfterEdges() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    graph.remove(e);
    assertEquals("v", graph.remove(v));
  }

  @Test
  @DisplayName("remove invalid edge throws exception")
  public void RemoveInvalidEdgeThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      Vertex<String> w = graph2.insert("w");
      Edge<String> e = graph2.insert(w, v,"e");
      graph.remove(e);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove edge")
  public void removeEdge() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    assertEquals("e", graph.remove(e));
  }

  @Test
  @DisplayName("vertices iterator")
  public void verticesIteratorTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    int i = 0;
    for (Vertex<String> ver : graph.vertices()) {
      i++;
    }
    assertEquals(2, i);
  }

  @Test
  @DisplayName("edges iterator")
  public void edgesIteratorTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Vertex<String> x = graph.insert("x");
    Edge<String> e = graph.insert(v, w, "e");
    Edge<String> f = graph.insert(v, x, "f");
    int i = 0;
    for (Edge<String> edg : graph.edges()) {
      i++;
    }
    assertEquals(2, i);
  }

  @Test
  @DisplayName("outgoing iterator")
  public void outgoingIteratorTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Vertex<String> x = graph.insert("x");
    Edge<String> e = graph.insert(v, w, "e");
    Edge<String> f = graph.insert(v, x, "f");
    int i = 0;
    for (Edge<String> edg : graph.outgoing(v)) {
      i++;
    }
    assertEquals(2, i);
  }

  @Test
  @DisplayName("incoming iterator")
  public void incomingIteratorTest() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Vertex<String> x = graph.insert("x");
    Edge<String> e = graph.insert(v, w, "e");
    Edge<String> f = graph.insert(x, w, "f");
    int i = 0;
    for (Edge<String> edg : graph.incoming(w)) {
      i++;
    }
    assertEquals(2, i);
  }

  @Test
  @DisplayName("incoming iterator throws exception")
  public void incomingIteratorThrowsException() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph.insert("w");
      Vertex<String> x = graph.insert("x");
      Edge<String> e = graph.insert(v, w, "e");
      Edge<String> f = graph.insert(x, w, "f");
      int i = 0;
      for (Edge<String> edg : graph.incoming(w)) {
        i++;
      }
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("outgoing iterator throws exception")
  public void outgoingIteratorThrowsException() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph.insert("w");
      Vertex<String> x = graph.insert("x");
      Edge<String> e = graph.insert(v, w, "e");
      Edge<String> f = graph.insert(x, w, "f");
      int i = 0;
      for (Edge<String> edg : graph.outgoing(w)) {
        i++;
      }
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("from returns vertex")
  public void fromReturnsVertex() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    assertEquals(v, graph.from(e));
  }

  @Test
  @DisplayName("from throws exception when null")
  public void fromThrowsExceptionWhenNull() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph.insert("w");
      Edge<String> e = graph.insert(v, w, "e");
      assertEquals(v, graph.from(null));
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("from throws exception")
  public void fromThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      Vertex<String> w = graph2.insert("w");
      Edge<String> e = graph2.insert(v, w, "e");
      assertEquals(v, graph.from(e));
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("to returns vertex")
  public void toReturnsVertex() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    assertEquals(w, graph.to(e));
  }

  @Test
  @DisplayName("to throws exception when null")
  public void toThrowsExceptionWhenNull() {
    try {
      Vertex<String> v = graph.insert("v");
      Vertex<String> w = graph.insert("w");
      Edge<String> e = graph.insert(v, w, "e");
      assertEquals(w, graph.to(null));
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("to throws exception")
  public void toThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      Vertex<String> w = graph2.insert("w");
      Edge<String> e = graph2.insert(v, w, "e");
      assertEquals(w, graph.to(e));
    } catch(PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("vertex label adds label")
  public void vertexLabelReturns() {
    Vertex<String> v = graph.insert("v");
    graph.label(v, "1");
    assertEquals("1", graph.label(v));
  }

  @Test
  @DisplayName("no vertex label returns null")
  public void noVertexLabelReturnsNull() {
    Vertex<String> v = graph.insert("v");
    assertEquals(null, graph.label(v));
  }

  @Test
  @DisplayName("add vertex label throws exception")
  public void vertexLabelThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      graph.label(v, "1");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("edge label adds label")
  public void edgeLabelAddsLabel() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    graph.label(e, "1");
    assertEquals("1", graph.label(e));
  }

  @Test
  @DisplayName("no edge label returns null")
  public void noEdgeLabelReturnsNull() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    assertEquals(null, graph.label(e));
  }

  @Test
  @DisplayName("edge label throws exception")
  public void edgeLabelThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      Vertex<String> w = graph2.insert("w");
      Edge<String> e = graph2.insert(v, w, "e");
      graph.label(e, "1");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("get vertex label throws exception")
  public void getVertexLabelThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      graph.label(v);
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("get edge label throws exception")
  public void getEdgeLabelThrowsException() {
    try {
      Vertex<String> v = graph2.insert("v");
      Vertex<String> w = graph2.insert("w");
      Edge<String> e = graph2.insert(v, w, "e");
      graph.label(e);
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("clearLabel clears all labels")
  public void clearLabelClearsAllLabels() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Edge<String> e = graph.insert(v, w, "e");
    graph.label(v, "1");
    graph.label(e, "2");
    graph.clearLabels();
    assertEquals(null, graph.label(v));
    assertEquals(null, graph.label(e));
  }

  @Test
  @DisplayName("two edges with the same values but different endpoints can be inserted")
  public void twoEdgesWithSameValuesButDifferentEndpointsCanBeInserted() {
    Vertex<String> v = graph.insert("v");
    Vertex<String> w = graph.insert("w");
    Vertex<String> x = graph.insert("x");
    Edge<String> e = graph.insert(v, w, "e");
    Edge<String> e1 = graph.insert(x, w, "e");
    int i = 0;
    for (Edge<String> edge : graph.edges()) {
      i++;
    }
    assertEquals(2, i);
  }

}
