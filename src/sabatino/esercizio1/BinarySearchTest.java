package sabatino.esercizio1;



public class BinarySearchTest {

	public static void main(String[] args){
		int[] array={1,3,5,7,13,18,23,35};
		
		System.out.println(BinarySearch.find(5, array));
		System.out.println(BinarySearch.isPresent(55, array));
		System.out.println(BinarySearch.findRecursively(55, array));
	}
}
