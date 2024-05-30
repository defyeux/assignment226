import java.util.*;

public class DijkstraSearch<T> {
    private Set<Vertex<T>> settledNodes;
    private Set<Vertex<T>> unsettledNodes;
    private Map<Vertex<T>, Vertex<T>> edgeTo;
    private Map<Vertex<T>, Double> distances;
    private WeightedGraph<T> graph;
    private Vertex<T> source;

    public DijkstraSearch(WeightedGraph<T> graph, Vertex<T> source) {
        this.graph = graph;
        this.source = source;
        this.settledNodes = new HashSet<>();
        this.unsettledNodes = new HashSet<>();
        this.edgeTo = new HashMap<>();
        this.distances = new HashMap<>();
        dijkstra();
    }

    private void dijkstra() {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex<T> currentNode = getVertexWithMinimumWeight(unsettledNodes);
            unsettledNodes.remove(currentNode);
            settledNodes.add(currentNode);

            for (Edge e : currentNode.getListEdges()) {
                Vertex<T> neighbor = e.getDest();
                if (!settledNodes.contains(neighbor)) {
                    double newDist = distances.get(currentNode) + e.getWeight();
                    if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                        distances.put(neighbor, newDist);
                        edgeTo.put(neighbor, currentNode);
                        unsettledNodes.add(neighbor);
                    }
                }
            }
        }
    }

    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertices) {
        Vertex<T> minimum = null;
        for (Vertex<T> vertex : vertices) {
            if (minimum == null || distances.getOrDefault(vertex, Double.MAX_VALUE) < distances.get(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    public boolean hasPathTo(Vertex<T> v) {
        return distances.containsKey(v);
    }

    public Iterable<Vertex<T>> pathTo(Vertex<T> v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = v; x != source; x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public double getDistanceTo(Vertex<T> v) {
        return distances.getOrDefault(v, Double.MAX_VALUE);
    }
}
