package sabatino.esercizio12;

/**
 * Classe implementazione dell'union-find di elementi interi in un range[0...n-1]
 * la struttura dati viene implementata attraverso un array di interi, il quale viene inizializzato con il valore della posizione,
 * ovvero ogni nodo, ha come padre se stesso.
 * Nel momento in cui viene creato un insieme, viene inserito il riferimento al padre, scrivendone al suo interno l'indice del padre.
 * 
 * @author Mauro Sabatino Matricola : 736724
 * 
 *
 */
public class UnionFindInt implements UnionFind {
	
	private int[] parentOrNSize;
	private int count;
	/**
	 * costruttore il quale inizializza a lunghezza n-1 l'array parentOrNSize
	 * e il contatore count al numero di inisemi presenti all'interno della struttura
	 * @param n int dimensione della struttura dati, implementato come array
	 */
	UnionFindInt(int n){
		parentOrNSize = new int[n];
		count = n;
		for(int i = 0;i<n;i++)
			parentOrNSize[i]=i;
	}
	/**
	 * ritorna il valore della variabile count, il quale tiene il conto degli insiemi contenuti nella union-find
	 * @return int numero di insiemi contenuti all'interno della struttura
	 */
	public int getCapacity() {
		return count;
	}

	
	/**
	 * Amplia la struttura ad una nuova dimensione n
	 * viene creato un nuovo array di dimesione n, che deve essere obbligatoriamente maggiore della dimensione precedente,
	 * nel quale viene copiato il contenuto dell'array originale.
	 * @param n int nuovo valore della dimensione della struttura
	 */
	public void setCapacity(int n) throws IllegalArgumentException {
		if(n<parentOrNSize.length)throw new IllegalArgumentException("hai inserito una dimensione inferiore a quella precedente");
		int[] nuovo = new int[n];
		for(int i = 0;i<count;i++)
			nuovo[i]=parentOrNSize[i];
		count = n;
		parentOrNSize = nuovo;
	}

	/**
	 * cerca l'indice del padre dell'elemento e all'interno della union-find
	 * @param e int elemnto di cui bisogna trovare il padre all'interno della union-find
	 * @return int indice del genitore dell'elemento dato in input
	 */
	public int find(int e) {
		if(e<0 || e>=parentOrNSize.length)throw new IllegalArgumentException();
		while(e!= parentOrNSize[e])
			e = parentOrNSize[e];
		return e;
	}

	/**
	 * @param a
	 * @param b
	 */
	public boolean union(int a, int b) {
		int i = find(a);
		int j = find(b);
		if(i==j)return false;
			parentOrNSize[i]=j;
			count--;
			return true;
	}
	public static void main(String[]agrs){
		UnionFindInt uf = new UnionFindInt(10);
		uf.union(1, 2);
		uf.union(3, 4);
		System.out.println(uf.find(1));
		uf.union(2, 4);
		System.out.println(uf.find(1));
		uf.setCapacity(15);
		System.out.println(uf.find(1));
	}
	
}
