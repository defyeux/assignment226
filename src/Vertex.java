import java.util.ArrayList;
import java.util.Iterator;

public class Vertex<T> {
    private T data;
    private ArrayList<Edge> listEdges;

    public Vertex(T data) {
        this.data = data;
        listEdges = new ArrayList<>();
    }

    public void addEdge(Vertex dest, Double weight) {
        listEdges.add(new Edge(this, dest, weight));
    }

    public void removeEdge(Vertex dest) {
        Iterator<Edge> iterator = listEdges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getDest().equals(dest)) {
                iterator.remove();
            }
        }
    }

    public T getData() { return (T) data; }

    public ArrayList<Edge> getListEdges() {
        return listEdges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data);
    }
}
