package sabatino.esercizio15.PriorityQueue;

import java.util.*;

/**
 * Heap implementato come coda di priorit�, con heap a partire dall'indice 0
 * indici:
 * 	-Padre: (i-1)/2
 * 	-Sinistro: (2*i)+1
 * 	-Destro: (2*1)+2
 * */

public class HeapPriorityQueue<V> implements PriorityQueue<V> {
	
	private class ElemConPrior<V>{
		V element;
		double priority;
		ElemConPrior(V el,double p){
			element=el;
			priority=p;
		}
	}
	
	ElemConPrior<V>[]heap;
	int indiceUltimo;
	HashMap<V,Integer> position;
	
	/**
	 * Costruttore della coda con priorit�
	 *@param n : grandezza della coda -1
	 *@param heap : array di elemConPrio, di lunghezza n+1;
	 *@param indiceUltimo : posizione dell'ultimo elemento, con priorit� massima
	 *@param position : tabella di hash che associa ad una stringa({@link sabatino.esercizio15.PriorityQueue.HeapPriorityQueue.ElemConPrior.element} un valore Integer, ovvero l'indice dell'elemento nell'array heap
	 * */
	public HeapPriorityQueue(int n){
		heap = new ElemConPrior[n+1];
		indiceUltimo = 0;
		position = new HashMap<V,Integer>(); 
	}
	
	/**
	 * funzione di moveUp, che in caso di elemento fuori posto lo fa salire in alto fino al posto giusto
	 * @param i : indice dell'elemento dello heap da portare verso l'alto
	 * */
	private void moveUp(int i){
		if(i >= indiceUltimo) throw new IllegalArgumentException();
		ElemConPrior ep = heap[i];
		while(i>0){
			if(ep.priority>=heap[(i-1)/2].priority)//lo fa in caso la priorit� dell'elemnto � pi� grande di quella del padre
				break;
			heap[i] = heap[(i-1)/2];
			position.put(heap[i].element, i);
			i=(i-1)/2;
		}
		heap[i]=ep;
		position.put(heap[i].element,i);
	}
	
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
	
	@Override
	public boolean isEmpty() {
		if(indiceUltimo==0)return true;
		return false;
	}

	@Override
	public void insert(V element, double priority) {
		if(indiceUltimo==heap.length) rialloca();
		
		if(!position.containsKey(element)){	
			heap[indiceUltimo] = new ElemConPrior(element,priority);
			position.put(element, indiceUltimo);
			indiceUltimo++;
			moveUp(indiceUltimo-1);
			
		}
	}

	public void rialloca(){
		ElemConPrior[] nuovo = new ElemConPrior[(heap.length*2)+1]; 
		for(int i = 0;i<heap.length;i++)
			nuovo[i]=heap[i];
		heap = nuovo;
	}
	
	@Override
	public V extractfirst() {
		if(indiceUltimo==0) throw new IllegalArgumentException();
		V first = heap[0].element;
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

	@Override
	public V getFirst() {
		return heap[0].element;
	}

	@Override
	public boolean decreasePriority(V element, double newPriority) {
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

	@Override
	public void remove(V element) {
		if(indiceUltimo<=0) throw new IllegalArgumentException();
		int i= position.get(element);
		heap[i] = heap[indiceUltimo-1];
		indiceUltimo=indiceUltimo-1;
		moveDown(i);
	}
	
	public void printHeap(){
		for(int i = 0;i<indiceUltimo;i++){
			position.toString();
			//System.out.println("Elemento: "+heap[i].element+" , Priorit�: "+heap[i].priority+" indice nell'hash: "+position.get(heap[i].element));
		}
	}
	public void printHash(){
		for(int i = 0;i<indiceUltimo;i++)
			System.out.println("hash: "+position.get(heap[i].element)+"elemnto: "+heap[position.get(heap[i].element)].element);
	}
	
	public void printInTree(){
		for(int i = 0; i<indiceUltimo;i++){
			System.out.println("padre: "+heap[(i-1)/2].element);
			System.out.println("figlio sinistro: "+heap[(2*i)+1].element+" di padre: "+heap[(i-1)/2].element);
			System.out.println("figlio destro: "+heap[(2*i)+2].element+" di padre: "+heap[(i-1)/2].element);
		}
	}
}
