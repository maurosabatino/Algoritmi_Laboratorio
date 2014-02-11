package sabatino.esercizio5.test;

import sabatino.esercizio5.InsertionSort;
import sabatino.esercizio5.MergeSort;
import sabatino.esercizio5.QuickSort;
import sabatino.esercizio5.SelectionSort;
import sabatino.esercizio5.SortingAlgorithm;
import sabatino.esercizio5.utility.RandomArrays;

public class Test {
	static RandomArrays arrays = new RandomArrays();
  static SortingAlgorithm isort = new InsertionSort(); 
  static SortingAlgorithm ssort = new SelectionSort(); 
  static SortingAlgorithm msort = new MergeSort(); 
  static SortingAlgorithm qsort = new QuickSort(); 
  static int N = 500000;
  static int [] array = new int[N];
	public static void main(String[] args){
		array = new int[N];
		RandomArrays.fillRandomNatArray(array, N, 100);
		System.out.println("originale");
		//print(array);
		System.out.println("sto per fare il quicksort");
		qsort.sort(array);
		System.out.println("ho fatto il qsort");
		System.out.println("ordinato");
		//print(array);
		System.out.println("l'array è ordinato?: "+ordinato(array,N));
	}
	public static void print(int[] a){
		for(int i=0; i<a.length;i++)
			System.out.print(a[i]+",");
		System.out.println();
	}
	static boolean ordinato(int a[],int n) {
  	for(int i = 1; i < n-1; i++) if(a[i-1] > a[i]) return false;
	return true;
  }
	
}
