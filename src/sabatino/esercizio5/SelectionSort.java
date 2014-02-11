package sabatino.esercizio5;

public class SelectionSort implements SortingAlgorithm{

	/**
	 * 
	 */
	public void sort(int[] a) {
		int n = a.length;
		for(int i = 0;i<n-1;i++){
			int iMin = i;
			for(int j=i+1;j<n;j++){
				if(a[j]<a[iMin])iMin=j;
				scambia(a,i,iMin);
			}
		}
	}
	/**
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	private void scambia(int[]a,int i,int j){
		int x=a[i];
		a[i]=a[j];
		a[j]=x;
	}

}
