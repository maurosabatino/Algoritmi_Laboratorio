package sabatino.esercizio7;

import java.util.Random;

/*
 	ok		MergeSortWithSingleAuxiliaryArray (slide 19.51) //mergesort ecologico
	TODO	MergeSortNoMergeOnSorted (slide 19.55)
	TODO 	test	MergeSortJavaAPIVersion (slide 19.58) 	
	ok		QuickSortMidToExamine (slide 20.79) 	
	TODO	QuickSortHoare (slide 21) 
	TODO	QuickSortHoareWithInsSort (slide 20.98)
 	TODO	QuickSortHoareTailRecursionElimination (slide 20.100)	
 	ok		QuickSortCrescenzi (slide 21.43)
 */

public class FastSorting {
	
	private static Random generatore = new Random();
	
	/**merge sort ecologico*/

	public static void 	MergeSortWithSingleAuxiliaryArray(int[]a){
		int n = a.length;
		int[]aux = new int[n];
		MergeSortWithSingleAuxiliaryArray(a,0,n-1,aux);
	}
	private static void 	MergeSortWithSingleAuxiliaryArray(int[]a,int fst,int lst,int[]aux){
		if(fst<lst){
			int m=(fst+lst)/2;
			MergeSortWithSingleAuxiliaryArray(a,fst,m,aux);
			MergeSortWithSingleAuxiliaryArray(a,m+1,lst,aux);
			MergeWithSingleAuxiliaryArray(a,fst,m,lst,aux);
		}
	}
	
	/**
	 * Procedura di merge
	 * @param a porzione di array su cui andare ad eseguire la procedura di merge
	 * @param fst indice iniziale della porzione da fondere
	 * @param mid indice dell'elemento centrale 
	 * @param lst
	 * @param c array ausiliario su cui la procedura si "appoggia" per andare ad inserire gli elementi in ordine
	 */
	private static void 	MergeWithSingleAuxiliaryArray(int[]a,int fst,int mid,int lst,int[]c){
	int i=fst,j=mid+1,k=fst;
	while(i<=mid && j<=lst){
		if(a[i]<=a[j]) c[k++]=a[i++];
		else c[k++]=a[j++];
	}
	int h = mid,l = lst;
	while(h >= i) a[l--] = a[h--];
	for(int r = fst;r<k;r++) a[r]=c[r];
}
	
	
	
	/**
	 * 
	 * @param a
	 */
	public static void 	MergeSortNoMergeOnSorted(int[]a){
		int n = a.length;
		int[]aux = new int[n];
		MergeSortNoMergeOnSorted(a,0,n-1,aux);
	}
	private static void 	MergeSortNoMergeOnSorted(int[]a,int fst,int lst,int[]aux){
		if(fst<lst){
			int m=(fst+lst)/2;
			MergeSortNoMergeOnSorted(a,fst,m,aux);
			MergeSortNoMergeOnSorted(a,m+1,lst,aux);
			if(a[m]>a[m+1])MergeNoMergeOnSorted(a,fst,m,lst,aux);
		}
	}
	
	private static void 	MergeNoMergeOnSorted(int[]a,int fst,int mid,int lst,int[]c){
	int i=fst,j=mid+1,k=fst;
	while(i<=mid && j<=lst){
		if(a[i]<=a[j]) c[k++]=a[i++];
		else c[k++]=a[j++];
	}
	for(int h= mid,l=lst;h>=i;) a[l--]=a[h--];
	for(int r = fst;r<k;r++) a[r]=c[r];
}
	
/**merge sort a passo alternato*/
	public static void MergeSortJavaAPIVersion(int[]a){
		int n = a.length;
		int[]aux = a;
		MergeSortJavaAPIVersion(a,0,n-1,aux);
	}
	private static void MergeSortJavaAPIVersion(int[]a,int fst,int lst,int[]b){
		if(fst<lst){
			int m=(fst+lst)/2;
			MergeSortJavaAPIVersion(b,fst,m,a);
			MergeSortJavaAPIVersion(b,m+1,lst,a);
			MergeJavaAPIVersion(b,fst,m,lst,a);
		}
	}
	private static void MergeJavaAPIVersion(int[]a,int fst,int mid,int lst,int[]b){
		int i= fst, j=mid+1,k=fst;
		while(i<=mid && j<=lst){
			if(a[i]<=a[j]) b[k++]=a[i++];
			else b[k++]=a[j++];
		}
		while(i<=mid) b[k++]=a[i++];
		while(j<=lst) b[k++]=a[j++];
	}
	
	/**QUICKSORT*/

	
	
	/** quicksort base con bandiera*/
	public static void QuickSortMidToExamine(int[]a){
		if (a ==null || a.length==0)
      return;
		int n = a.length;
		QuickSortMidToExamine(a,0,n-1);
	}
	private static void QuickSortMidToExamine(int[]a,int inf, int sup){
		if(inf>=sup) return;
		int p = QuickSortMidToExaminePartition(a,inf,sup);
		QuickSortMidToExamine(a,inf,p-1);
		QuickSortMidToExamine(a,p+1,sup);
	}
	private static int QuickSortMidToExaminePartition(int[]a,int inf, int sup){
		int pivot = a[inf];
		int i = inf+1;
		int j = sup;
		while(i<=j){
			if(a[i]<pivot)	i++;
			else swap(a,i,j--);
		}
		swap(a,inf,j);
		return j ;	
	}

	
	/**---------QuickSortHoare-----------*/
	public static void QuickSortHoare(int[]array){
		if (array ==null || array.length==0)return;
		int n = array.length;
		QuickSortHoare(array,0,n-1);
	}
	public static void QuickSortHoare(int[]array,int fst,int lst){
		if(fst>=lst) return;
		int p = QuickSortHoarePartition(array,fst,lst);
		QuickSortHoare(array,fst,p);
		QuickSortHoare(array,p+1,lst);
	}
	
	public static int QuickSortHoarePartition(int[]array,int fst,int lst){
		int pivot = array[fst];
		int i=fst-1;
		int j=lst+1;
		while(i<j){
			i++;while(array[i]<pivot) i++;
			j--;while(array[j]>pivot) j--;
			if(i<j)swap(array,i,j);
		}
		return j;
	}
	
		
	
	/**quicksort crescenzi*/
	public static void QuickSortCrescenzi(int[]a){
		if (a ==null || a.length==0)
      return;
		int n = a.length;
		QuickSortCrescenzi(a,0,n-1);
	}
	private static  void QuickSortCrescenzi(int[]a, int inf, int sup){
		int lung = (sup-inf)+1;
		if(inf<=sup){
		int iPivot = inf+generatore.nextInt(lung);
		swap(a,sup,iPivot);
		int p = a[sup];
		int i = inf;
		int j = sup-1;
		while(i<=j){
			while(i<=j && a[i]<=p) i++;
			while(i<=j && a[j]>=p) j--;
			if(i<j)swap(a,i,j);
		}
		swap(a,i,sup);
		QuickSortCrescenzi(a,inf,i-1);
		QuickSortCrescenzi(a,i+1,sup);
		}
	}
	
	
	
	/**
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void swap(int[]a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	

}//
