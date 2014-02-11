package sabatino.esercizio2;

import java.util.Random;

public class TestThreeColoredFlag {
	private static Random generatore = new Random();
	static final int n = 1000;
	static int[] array = new int[n];
	
	public static void main(String[] args){
		fillRandomNatArray(array, n, 9*n/10);
		ThreeColoredFlag.separateColors(array);
		System.out.println(ThreeColoredFlag.checkColors(array));
	}
	
	public static void fillRandomNatArray(int[] a, int n, int max) {
    if(n > a.length) throw new IllegalArgumentException();
    int sup = max+1;
    for(int i = 0; i < n; i++) {
      a[i] = generatore.nextInt(sup);
    }
  }
}
