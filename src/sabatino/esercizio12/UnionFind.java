package sabatino.esercizio12;

public interface UnionFind {
	int getCapacity();
	void setCapacity(int n) throws IllegalArgumentException;
	int find(int e);
	boolean union(int a, int b);
}
