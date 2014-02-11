package sabatino.esercizio8;

import java.util.*;

/**
 * Heap implementato come coda di priorit�, con heap a partire dall'indice 0
 * indici:
 * 	-Padre: (i-1)/2
 * 	-Sinistro: (2*i)+1
 * 	-Destro: (2*1)+2
 * */

public class HeapPriorityQueue implements PriorityQueueString {
	
	private class ElemConPrior{
		String element;
		double priority;
		ElemConPrior(String el,double p){
			element=el;
			priority=p;
		}
	}
	
	ElemConPrior[]heap;
	int indiceUltimo;
	HashMap<String,Integer> position;
	
	/**
	 * Costruttore della coda con priorit�
	 *@param n : grandezza della coda -1
	 *@param heap : array di elemConPrio, di lunghezza n+1;
	 *@param indiceUltimo : posizione dell'ultimo elemento, con priorit� massima
	 *@param position : tabella di hash che associa ad una stringa({@link sabatino.esercizio8.HeapPriorityQueue.ElemConPrior.element} un valore Integer, ovvero l'indice dell'elemento nell'array heap
	 * */
	public HeapPriorityQueue(int n){
		heap = new ElemConPrior[n+1];
		indiceUltimo = 0;
		position = new HashMap<String,Integer>(); 
	}
	
	/**
	 * funzione di moveUp, che in caso di elemento fuori posto rispetto al genitore(priorit� minore del genitore),
	 * lo fa risalire fino al posto giusto.
	 * 
	 * @param i : indice dell'elemento dello heap da portare verso l'alto
	 * @exception se l'indice fornito in input � maggiore dell'indice dell'ultimo elemento, solleva un eccezione IllegalArgumentException. 
	 * */
	private void moveUp(int i){
		if(i >= indiceUltimo) throw new IllegalArgumentException();
		ElemConPrior ep = heap[i];
		while(i>0){
			if(ep.priority>=heap[(i-1)/2].priority)
				break;
			heap[i] = heap[(i-1)/2];
			position.put(heap[i].element, i);
			i=(i-1)/2;
		}
		heap[i]=ep;
		position.put(heap[i].element,i);
	}
	/**
	 * procedura di moveDown o fixHeap
	 * se i � l'indice di un elemento fuori posto rispetto ai figli(priorit� maggiore di almeno uno dei figli),
	 * lo fa scendere al posto giusto, scambiandolo ogni volta con il figlio di priorit� minore.
	 * @param i int � l'indice del posto vuoto
	 * @exception se l'indice fornito in input � maggiore dell'indice dell'ultimo elemento, solleva un eccezione IllegalArgumentException.
	 */
	private void moveDown(int i){
		if(i > indiceUltimo) throw new IllegalArgumentException();
			ElemConPrior el = heap[i];
			int j;
			while((j=(2*i)+1)<=indiceUltimo){
				if(j+1<=indiceUltimo && heap[j+1].priority<heap[j].priority) 
					j++;
				if(el.priority<=heap[j].priority) break;
				heap[i]=heap[j];
				i=j;
			}
			heap[i]=el;
			position.put(heap[i].element, i);
	}
	
	/**
	 * metodo che testa se lo heap ha elementi oppure no,
	 * va a controllare se la variabile indiceUltimo � uguale a 0, 
	 * in caso contrario vuol dire che ci sono elementi e lo heap non � vuoto  
	 * @return boolean true: se non ci sono elementi e quindi la coda � vuota, false: se ci sono elementi quindi la coda non � vuota. 
	 */
	public boolean isEmpty() {
		if(indiceUltimo==0)return true;
		return false;
	}

	/** viene aggiunto un nodo alla fine dello heap(viene creata una nuova foglia) in un posto vuoto,
	 * se l'elemento da inserire ha priorit� minore del genitore del posto vuoto lo si fa risalire
	 * attraverso la moveUp fino al posto giusto.
	 * nel caso in cui la coda fosse piena, ne rialloca una nuova di dimensione doppia, ricopiando il contenuto di quella vecchia.
	 * @param element String elemento da inserire all'interno dello heap.
	 * @param priority double priorit� dell'elemento da inserire.
	 */
	public void insert(String element, double priority) {
		if(indiceUltimo==heap.length) rialloca();
		
		if(!position.containsKey(element)){	
			heap[indiceUltimo] = new ElemConPrior(element,priority);
			position.put(element, indiceUltimo);
			indiceUltimo++;
			moveUp(indiceUltimo-1);
			
		}
	}
/**
 * metodo ausiliario che rialloca la coda di dimensione doppia in caso di coda del tutto piena(indiceUltimo == heap.length).
 */
	private void rialloca(){
		ElemConPrior[] nuovo = new ElemConPrior[(heap.length*2)+1]; 
		for(int i = 0;i<heap.length;i++)
			nuovo[i]=heap[i];
		heap = nuovo;
	}
	
	/**estrazione dell'elemento con priorit� pi� alta(ovvero l'elemento con priorit� minima),
	 * si estrae la radice, si toglie l'ultimo nodo e lo si mette al posto della radice, 
	 * quasi sicuramente sar� di priorit� maggiore di uno dei suoi figli(fuori posto)
	 * e allora si far� scendere al posto giusto per mezzo della moveDown.
	 * @return String elemento che ha priorit� pi� alta
	 * @exception in caso di coda vuota, la procedura solleva un eccezione.
	 */
	public String extractfirst() {
		if(indiceUltimo==0) throw new IllegalArgumentException();
		String first = heap[0].element;
		position.put(null, indiceUltimo);
		ElemConPrior last = heap[indiceUltimo-1];
		heap[indiceUltimo--] = null;
		if(indiceUltimo>=0){
			heap[0]=last;
			moveDown(0);
		}
		position.remove(first);
		return first;
	}

	/**
	 * ritorna l'elemento con priorit� con priorit� pi� alta senza per� estrarlo
	 * @exception in caso di coda vuota, la procedura solleva un eccezione.
	 * @return String elemento che ha la priorit� pi� alta (ovvero la radice dello heap).
	 */
	public String getFirst() {
		if(indiceUltimo==0) throw new IllegalArgumentException();
		return heap[0].element;
	}

	/**
	 * metodo che imposta a newPriority la priorit� dell'elemento element
	 * occorre trovare in tempo costante l'elemento element, e per questo motivo abbiamo usato una tabella di hash. 
	 * una volta ottenuta la posizione, andiamo a controllare se la nuova priorit� � maggiore della priorit� precedente,
	 * in tal caso � un operazione illecita (dobbiamo rispettare l'invariante di implementazione),
	 * altrimenti si modifica la priorit� e lo si riposiziona nel posto giusto prima provano a farlo salire con una moveUp
	 * e poi facendolo scendere con una moveDown.
	 * @param element String elemento che si vuole andare a modificare la priorit�.
	 * @param newPriority double valore della nuova priorit� che si vuole impostare all'elemento (deve essere inferiore alla vecchia priorit�)
	 * @return boolean restituisce un valore booleano indicante la buona riuscita o meno dell'operazione di variazione di priorit�.
	 * @exception in caso di un elemento non appartenente alla coda, la procedura solleva un eccezione.
	 */
	public boolean decreasePriority(String element, double newPriority) {
		if(position.get(element)!=null){
			int i = position.get(element);
			if(i>indiceUltimo || i<0) throw new IllegalArgumentException();
			if(heap[i].priority<newPriority) return false;
			heap[i].priority = newPriority;
			moveDown(i);
			moveUp(i);
			return true;
		}
		return false;
	}

	/**
	 * procedura che rimuove un elemento dalla coda
	 * la procedura ricerca nella coda l'elemento attraverso una tabella di hash,
	 * se lo trova lo sovrascrive con l'ultima foglia e poi la fa scendere al posto giusto attraverso la moveDown
	 * @param element String elemnto che si vuole rimuovere dalla coda
	 * @exception se l'elemento in input non appartiene alla coda, oppure la coda � vuota, la procedura solleva un eccezione.
	 */
	public void remove(String element) {
		if(indiceUltimo<=0) throw new IllegalArgumentException();
		int i= position.get(element);
		if(i>indiceUltimo || i<0) throw new IllegalArgumentException();// da controllare
		heap[i] = heap[indiceUltimo-1];
		indiceUltimo=indiceUltimo-1;
		moveDown(i);
	}
	
	
}
