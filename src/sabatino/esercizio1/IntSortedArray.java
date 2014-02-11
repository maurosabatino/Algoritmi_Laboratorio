package sabatino.esercizio1;

/**
 * La classe è costituita da un array di interi e un contatore di quanti elementi sono presenti al suo interno.
 * In questa classe vengono forniti i metodi per inserire e cercare un elemento per mezzo dell'algoritmo di ricerca binaria.
 * La ricerca binaria 
 * @author Mauro
 */
public class IntSortedArray {
	private int[]elements ;
	private int numElements;
	public IntSortedArray(int initialCapacity){
		elements = new int[initialCapacity];
		numElements=0;
	}
	/**
	 * procedura per conoscere il numero di elementi inseriti nell'array di elementi interi
	 * @return intero rappresentante il numero di elementi all'interno dell'array.
	 */
	public int size(){
		return numElements;
	}
	
	/**
	 * procedura che dato un intero x, lo ricerca all'interno di un array, restituendone la posizione di inserimento,
	 * in caso di elemento non presente fornisce la posizione di inerimento corretta.
	 * (Pre-condizione: l'array su cui viene eseguita la ricerca binaria deve essere ordinato).
	 * 
	 * @param x elemento intero da ricercare all'interno dell'array.
	 * @return indice dell'elemento cercato se presente, altimenti restituisce la posizione in cui andrebbe inserito.
	 */
	public int binarySearch(int x){
		int inf = 0;
		int sup = numElements-1;
		if(sup==-1 || x<elements[0]) return -1;
		if(x>elements[sup]) return -(numElements+1);
		while(inf<=sup){
			int i = (inf+sup)>>>1;
			if(x<elements[i]) sup = i-1;
			else if(x>elements[i]) inf=i+1;
			else return i;
		}
		return -(inf+1);
	}
	
	/**
	 * procedura per inserire in ordine un elemento all'interno dell'array, 
	 * se l'elemento non è presente sposta di uno in avanti tutti gli elementi dalla posizione in cui andrebbe inserito e lo inserisce restituendo l'indice di inserimento.
	 * se l'array è saturo, lo rialloca e ne crea uno di dimensione doppia
	 * in caso di elemento già presente, non fa nulla e restituisce -1.
	 * @param x elemento intero da inserire all'interno dell'array
	 * @return in caso di inserimento corretto(l'elemento non è presente nell'array) restituisce l'indice di inserimento, in caso contrario restituisce -1.
	 */
	public int insert(int x){
		int result=binarySearch(x);
		int inf =-(result+1);
		if(inf>=0){
			if(numElements==elements.length){
				reAlloc();
			}
		shiftRight(inf);
		elements[inf]=x;
		numElements=numElements+1;
		return inf;
		}else return -1;
	}
	
	/**
	 * procedura per spostare in anvanti di una posizione tutti gli elementi successivi all'indice inf
	 * @param inf indice da cui far partire lo spostamento in avanti
	 */
	public void shiftRight(int inf){
		for(int j=numElements-1;j>inf-1;j--){
			elements[j+1]=elements[j];
		}
	}
	/**
	 * procedura che crea un array di dimensione doppia, perchè quello originale è saturo, e ne copia tutto il contenuto del vecchio array.
	 */
	public void reAlloc(){
	int[] copia = new int[numElements*2];
	for(int i=0;i<elements.length;i++){
		copia[i] = elements[i];
	}	
	elements=copia;
	}
	

	/**
	 * procedura che permette di ottenere l'elemento dell'array di posizione i
	 * @param i indice dell'elemnto dell'array che si vuole ottenere
	 * @return elemento intero dell'array in posizione i.
	 */
	public int get(int i){
		return elements[i];
	}
	/**
	 * procedura che stampa il contenuto dell'array fino a dove è stato riempito(numElements).
	 */
	public void print(){
		for(int i=0;i<numElements;i++){
			System.out.print(elements[i]+",");
		}
	}
}
