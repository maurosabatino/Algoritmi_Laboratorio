package sabatino.esercizio1;

/**
 * 
 * @author Mauro
 *
 */

public class BinarySearch {
    /**
	 * cerca un elemento in un array ordinato, con restituzione della posizione 
	 * (Pre-condizione: array a deve essere ordinato)
	 * @param x intero da cercare
	 * @param a array di interi, ordinato, in cui eseguire la ricerca
	 * @param inf limite inferiore della porzione di array in cui eseguire la ricerca
	 * @param sup limite superiore della porzione di array in cui eseguire la ricerca
	 * @return posizione dell'elemento cercato, se non presente viene restituito l'inverso della posizione in cui potrebbe essere inserito
	 * */
	public static int find(int x,int[]a){
		int inf = 0;
		int sup = a.length-1;
		while(inf<=sup){
			int i = (inf+sup)/2;
			if(x<a[i])sup=i-1;
			else if(x>a[i])inf=i+1;
			else return i;
		}
		return -1;	
	}
		/**
		 * cerca un elemento in un array ordinato, con restituzione di un booleano(true: presente, false: non presente) 
	 * (Pre-condizione: array a deve essere ordinato)
	 * @param x intero da cercare
	 * @param a array di interi, ordinato, in cui eseguire la ricerca
	 * @param inf limite inferiore della porzione di array in cui eseguire la ricerca
	 * @param sup limite superiore della porzione di array in cui eseguire la ricerca
	 * @return valore booleano che indica o meno la presenza dell'elemento all'interno dell'array
		 */
	public static boolean isPresent(int x, int[]a){
		int inf = 0;
		int sup = a.length-1;
		while(inf<=sup){
			int i = (inf+sup)/2;
			if(x<a[i])sup=i-1;
			else if(x>a[i])inf=i+1;
			else return true;
		}
		return false;
	}
		/**
		 * Ricerca binaria ricorsiva: procedura top-level
		 * (Pre-condizione: array a deve essere ordinato)
		 * @param x intero da cercare
		 * @param a array di interi, su cui eseguire la ricerca
		 * @return restituisce la posizione in cui si trova l'elemento all'interno dell'array se presente, se non presente restituisce un valore negativo. 
		 */
	public static int findRecursively(int x, int[]a){
		return findRecursively(x,a,0,a.length-1);
	}
	static int findRecursively(int x, int[]a, int inf, int sup){
		if(inf<=sup){
			int i = (inf+sup)/2;
			if(x<a[i])return findRecursively(x,a,inf,i-1);
			else if(x>a[i])return  findRecursively(x,a,i+1,sup);
			else return i;
		}
		return -1;
	}
}
