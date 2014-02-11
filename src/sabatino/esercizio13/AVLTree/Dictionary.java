package sabatino.esercizio13.AVLTree;

public interface Dictionary{

	ObjectWhitKey get(int key);
	
	boolean isEmpty();
	
	/**
	 * inserisce nel dizionario l'elemnto el.
	 * se � gia presente un elemento con la stessa chiave, lo sostituisce con il nuovo elemento;
	 * se invece non � presente un elemento con la stessa chiave, inserisce il nuovo elemento
	 * @param element elemento che si vuole inserire nel dizionario
	 * @return ObjectWhitKey se � gia presente un elemento con la stessa chiave, restituisce il vecchio elemento; se invece non � presente restituisce un elemento nullo.
	 */
	ObjectWhitKey put(ObjectWhitKey element);
	
	/**
	 * cancella dal dizionario l'elemento di chiave key.
	 * @param key chiave dell'elemento che si vuole cancellare dal dizionario.
	 * @return se l'elemento � presente e viene cancellato, resituisce l'elemento cancellato, se non � presente restituisce null.
	 */
	ObjectWhitKey remove(int key);
	
	/*
	 * restituisce il numero di elementi presenti nel dizionario
	 */
	int size();
	
	/*
	 * restituisce l'elemento di chiave massima
	 */
	ObjectWhitKey max();
	
	ObjectWhitKey min();
}
