import java.util.*;

public class DepthFirstSearch<T> {
    private Set<Vertex<T>> marked;
    private Map<Vertex<T>, Vertex<T>> edgeTo;
    private Vertex<T> source;

    public DepthFirstSearch(MyGraph<T> graph, Vertex<T> source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();

        dfs(graph, source);
    }

    private void dfs(MyGraph<T> graph, Vertex<T> current) {
        marked.add(current);
        for (Edge e : current.getListEdges()) {
            Vertex<T> v = e.getDest();
            if (!marked.contains(v)) {
                edgeTo.put(v, current);
                dfs(graph, v);
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
