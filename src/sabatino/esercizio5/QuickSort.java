package sabatino.esercizio5;

import java.util.Random;
/**
 * 
 * @author Mauro
 * TODO testare: 
 * per il quick sort testare:
 * -array già ordinato
 * -array ordinato al contrario
 * -array di elementi tutti uguali o quasi uguali --> complessità quadratica
 *
 */

public class QuickSort implements SortingAlgorithm{
	
	Random generatore = new Random();
	/**
	 * Quicksort versione libro: funzione top-level
	 * @param a array di input su cui eseguire l'ordinamento.
	 * all'inizio la porzione da ordinare è l'intero array, perciò:
	 * inf = 0;
	 * sup = a.length-1;	
	 */
	public void sort(int[] a){
		if (a ==null || a.length==0)
      return;
		int n = a.length;
		qsort(a,0,n-1);	
	}
	/**
	 * procedura di partizione
	 * versione del libro di testo con il pivot al fondo.
	 * l'inserimento di elementi uguali al pivot in entrambe le partizioni andando così a bilanciare maggiormente i due segmenti.
	 * passo genreico
	 * a[inf...i-1] solo elementi <= pivot 
	 * a[j+1...sup] solo elementi >= pivot
	 * 
	 * @param inf indice del primo elemento della prima parte da partizionare(a[inf...i-1] )
	 * @param sup indice dell'ultimo elemento della seconda parte da partizionare(a[j+1...sup])
	 * @param porzione da array su cui eseguire la procedura di partizione 
	 * 
	 * */
	private void qsort(int[]a, int inf, int sup){
		if(sup-inf>=1){
			int iPivot = inf+generatore.nextInt(sup-inf+1);
			scambia(a,sup,iPivot);
			int p = a[sup];
			int i = inf;
			int j = sup-1;
			while(i<=j){
				while(i<=j && a[i]<=p) i++;
				while(i<=j && a[j]>=p) j--;
				if(i<j)scambia(a,i,j);
			}
			scambia(a,i,sup);
			qsort(a,inf,i-1);
			qsort(a,i+1,sup);
		}
	}
	/**
	 * procedura di scambio di elementi, in cui va a mettere un elemento al posto dell'altro
	 * @param a array su cui andare a scambiare l'elemento a[i] con a[j].
	 * @param i indice dell'array a che deve essere scambiato.
	 * @param j indice dell'array in cui bisogna andare a mettere l'altro elemento dato in input e spostato al posto dell'altro.
	 */
	private void scambia(int[]a,int i,int j){
		int x=a[i];
		a[i]=a[j];
		a[j]=x;
	}
	
	


}

 