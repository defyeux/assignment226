import java.util.*;

public class MyGraph<T> {
    private ArrayList<Vertex> lstVertices = new ArrayList<>();
    private final boolean undirected;

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(T data) {
        lstVertices.add(new Vertex(data));
    }

    public void addEdge(Vertex source, Vertex dest) {
        source.addEdge(dest, null);
        if (undirected) dest.addEdge(source, null);
    }

    public void removeEdge(Vertex source, Vertex dest) {
        source.removeEdge(dest);
        if (undirected) dest.removeEdge(source);
    }

    public void removeVertex(Vertex vertex) {
        if (!lstVertices.contains(vertex)) return;
        lstVertices.remove(vertex);
    }

    public ArrayList<Vertex> getLstVertices() {
        return lstVertices;
    }

    public Vertex find(T data) {
        for (Vertex v: lstVertices) {
            if (data.equals(v.getData())) {
                return v;
            }
        }

        return null;
    }


}

