package sabatino.esercizio14;

import java.util.ArrayList;

public interface Graph<V, E> {
	boolean addVertex(V vertex);
	boolean addEdge(V v1, V v2, E info);
	boolean addEdge(V v1, V v2, double weight, E info);
	boolean addUndirectedEdge(V v1, V v2, E info);
	boolean addUndirectedEdge(V v1, V v2, double weight, E info);
	boolean hasVertex(V vertex);
	boolean hasEdge(V v1, V v2);
	double getWeight(V source, V dest);
	ArrayList<V> vertices();
	ArrayList<V> neighbors(V vertex);
}
