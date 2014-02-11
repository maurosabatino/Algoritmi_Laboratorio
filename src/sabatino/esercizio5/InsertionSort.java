package sabatino.esercizio5;

public class InsertionSort implements SortingAlgorithm{	
	/**
	 * devo modificare questa javadoc per la versione 2 dell'insertion sort
	 * 
	 * @param a array di interi dato in input
	 * @param i indice del primo elemento della parte non ordinata, inizialmente viene inizializzato ad 1 perchè l'array formato da un solo elemnto è banalmente ordinato, quindi la parte da ordinare parte da 1
	 * @param x è l'elemento da inserire, minore di tutti gli altri elemnti già spostati
	 * @param j indice del posto in cui l'elemento posto in a[i] va inserito
	 * 
	 *  questa è la versione 2 inserisci x in a[0..i-1], dove x è l'elemnto da inserire minore di tutti gli altri
	 */
	public void sort(int[] a) {
		int n=a.length;
		for(int i=1;i<n;i++){
			int x = a[i]; 
			int j=i;
			while(j>0 && x<a[j-1]){
				a[j] = a[j-1];
				j--;
			}
			a[j] = x;
		}
		
	}
	
	/**
	 * selection sort versione 1 
	 * @param a
	 */
	public void SSort(int[]a){
		int n = a.length;
		for(int i = 1;i<n; i++){
			int x = a[i];
			for(int j = i;j>0;j--){
				if(x>=a[j-1]) break;//x va inserito in a[i]
				a[j] = a[j-1];
			}
			a[i] = x;
		}
	}
}
