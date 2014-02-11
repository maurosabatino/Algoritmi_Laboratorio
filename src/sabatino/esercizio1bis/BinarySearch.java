package sabatino.esercizio1bis;

import java.util.ArrayList;

public class BinarySearch {
	public static <T extends Comparable<T>> int find(T elem, ArrayList<T> array){
	int inf = 0;
	int sup = array.size();
	while(inf<=sup){
		int i = (inf+sup)/2;
		if(elem.compareTo(array.get(i))<0)sup=i-1;
		else if(elem.compareTo(array.get(i))>0)inf=i+1;
		else return i;	
	}
	return -1;			
	}
	
	public static <T extends Comparable<T>> boolean isPresent(T elem, ArrayList<T> array){
		int inf = 0;
		int sup = array.size()-1;
		while(inf<=sup){
			int i = (inf+sup)/2;
			if(elem.compareTo(array.get(i))==-1)sup=i-1;
			else if(elem.compareTo(array.get(i))==1)inf=i+1;
			else return true;
		}
		return false;
	}
	
	public static <T extends Comparable<T>> int findRecursively(T elem, ArrayList<T>array){
		return findRecursively(elem,array,0,array.size()-1);
	}
	static  <T extends Comparable<T>> int findRecursively(T elem, ArrayList<T>array, int inf, int sup){
		if(inf<=sup){
			int i = (inf+sup)/2;
			if(elem.compareTo(array.get(i))==-1)return findRecursively(elem,array,inf,i-1);
			else if(elem.compareTo(array.get(i))==1)return  findRecursively(elem,array,i+1,sup);
			else return i;
		}
		return -1;
	}
}

