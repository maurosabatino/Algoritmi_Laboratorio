package sabatino.esercizio5;

public class MergeSort implements SortingAlgorithm{

	/**
	 * implementazione del mergesort ecologico
	 * [(devo descrivere le ottimizzazioni)]
	 * @param a array di interi in input da ordinare per mezzo dell'algoritmo di mergesort.
	 * @param aux array di interi ausiliario in cui vengono fusi i segmenti ordinati, ha dimensione uguale all'array a.
	 */
	public void sort(int[] a) {
		int n = a.length;
		int [] aux = new int[n];
		msort(a,0,n-1,aux);
	}
	
	/**
	 * [descrizione]
	 * @param a porzione di array da esaminare e da ordinare a[first...last]. Inizialmente la parte da ordinare è l'intero array a[0...a.length-1]
	 * @param first indice de primo elemento della parte da ordinare.
	 * @param last indice dell'ultimo elemento della parte da ordinare.
	 * @param b array ausiliario di interi di lunghezza a.length.
	 */
	private static void msort(int[]a,int first,int last,int[]b){
		if(first<last){
			int m =(first+last)/2;
			msort(a,first,m,b);
			msort(a,m+1,last,b);
			merge(a,first,m,last,b);
		}
	}
	
	/**
	 * algoritmo di fusione, per fondere le le due sequenze a[first...mid],[mid+1...last] ordinate in una sequenza ordinata, 
	 * si eseguono i confronti per vedre quale elemento delle due sequenze è precedente nell'ordine
	 * e con l'ultimo ciclo for vado a ricopiare l'array ausiliario ordinato nell'array di input a.
	 * TODO (descrivere le ottimizzazioni)
	 * @param a array di interi che va fuso
	 * @param fst indice del primo elemento della prima sequenza
	 * @param mid indice dell'ultimo elemento della prima sequenza, mid+1 primo elemento della seconda sequenza
	 * @param lst indice dell'ultimo elemento della seconda sequenza
	 * @param c array ausiliario che viene usato come array in cui andare a mettere gli elementi delle due sequenze in ordine
	 */
	private static void merge(int[]a,int fst,int mid,int lst,int[]c){
		int i=fst,j=mid+1,k=fst;
		while(i<=mid && j<=lst){
			if(a[i]<=a[j]) c[k++]=a[i++];
			else c[k++]=a[j++];
		}
		int h = mid, l = lst;
		while(h>=i) a[l--]=a[h--];
		for(int r = fst;r<k;r++) a[r]=c[r];
	}
	
}
