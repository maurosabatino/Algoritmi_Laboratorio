package sabatino.esercizio8;

public interface PriorityQueueString {
	boolean isEmpty();
	void insert(String element, double priority);
	String extractfirst();
	String getFirst();
	boolean decreasePriority(String element, double newPriority);
	void remove(String element);
}
