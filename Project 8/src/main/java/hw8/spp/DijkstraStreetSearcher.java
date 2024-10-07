package hw8.spp;

import hw8.graph.Edge;
import hw8.graph.Graph;
import hw8.graph.Vertex;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraStreetSearcher extends StreetSearcher {

  HashMap<Vertex<String>, QueueVertex<String>> explored = new HashMap<>();
  PriorityQueue<QueueVertex<String>> queue = new PriorityQueue<>();

  /**
   * Creates a StreetSearcher object.
   *
   * @param graph an implementation of Graph ADT.
   */
  public DijkstraStreetSearcher(Graph<String, String> graph) {
    super(graph);
  }

  @Override
  public void findShortestPath(String startName, String endName) {

    if (!isValid(endName) || !isValid(startName)) {
      return;
    }

    Vertex<String> start = vertices.get(startName);
    Vertex<String> end = vertices.get(endName);

    double totalDist = -1;  // totalDist must be updated below
    totalDist = startSearch(start, end);

    // These method calls will create and print the path for you
    List<Edge<String>> path = getPath(end, start);
    if (VERBOSE) {
      printPath(path, totalDist);
    }
  }

  private Boolean isValid(String name) {
    try {
      checkValidEndpoint(name);
      return true;
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid Endpoint: " + name);
      return false;
    }
  }

  private double startSearch(Vertex<String> start, Vertex<String> end) {
    QueueVertex<String> newStart = new QueueVertex<>(start);
    newStart.distance = 0;
    explored.put(newStart.vertex, newStart);
    queue.add(newStart);
    QueueVertex<String> newEnd = new QueueVertex<>(end);
    return searchNext(newEnd);
  }

  private double searchNext(QueueVertex<String> end) {
    while (!queue.isEmpty()) {
      QueueVertex<String> ver = queue.poll();
      if (ver.vertex.equals(end.vertex)) {
        return ver.distance;
      }
      for (Edge<String> edge : graph.outgoing(ver.vertex)) {
        QueueVertex<String> next = new QueueVertex<>(graph.to(edge));
        next = checkExplored(next);
        double distance = ver.distance + (double) graph.label(edge);
        if (distance < next.distance) {
          next.distance = distance;
          graph.label(next.vertex, edge);
          queue.add(next);
        }
      }
    }
    return 0;
  }

  private QueueVertex<String> checkExplored(QueueVertex<String> vert) {
    if (explored.containsKey(vert.vertex)) {
      return explored.get(vert.vertex);
    } else {
      explored.put(vert.vertex, vert);
      return vert;
    }
  }

  private static class QueueVertex<V> implements Comparable<QueueVertex<V>> {
    Vertex<V> vertex;
    double distance;

    QueueVertex(Vertex<V> vert) {
      this.vertex = vert;
      this.distance = MAX_DISTANCE;
    }

    @Override
    public int compareTo(QueueVertex<V> vert2) {
      return Double.compare(this.distance, vert2.distance);
    }
  }
}
