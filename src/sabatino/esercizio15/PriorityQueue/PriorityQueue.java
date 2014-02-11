package sabatino.esercizio15.PriorityQueue;

public interface PriorityQueue<V> {
	boolean isEmpty();
	void insert(V element, double priority);
	V extractfirst();
	V getFirst();
	boolean decreasePriority(V element, double newPriority);
	void remove(V element);
}
