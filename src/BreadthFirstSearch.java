import java.util.*;

public class BreadthFirstSearch<T> {
    private Set<Vertex<T>> marked;
    private Map<Vertex<T>, Vertex<T>> edgeTo;
    private Vertex<T> source;

    public BreadthFirstSearch(MyGraph<T> graph, Vertex<T> source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();

        bfs(graph, source);
    }

    private void bfs(MyGraph<T> graph, Vertex<T> current) {
        marked.add(current);
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            for (Edge e : v.getListEdges()) {
                Vertex<T> vertex = e.getDest();
                if (!marked.contains(vertex)) {
                    marked.add(vertex);
                    edgeTo.put(vertex, v);
                    queue.add(vertex);
                }
            }
        }
    }

    public boolean hasPathTo(Vertex<T> v) {
        return marked.contains(v);
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
}
