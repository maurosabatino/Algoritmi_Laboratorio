package sabatino.esercizio1bis;

import java.util.ArrayList;

public class InSortedArray<T extends Comparable<T>> {
	
	private ArrayList<T> elements;
	
	private int binarySearch(T elem){
		int inf = 0;
		int sup = elements.size()-1;
		if(sup==-1 || elem.compareTo(elements.get(0))<0) return -1;
		if(elem.compareTo(elements.get(sup))>0) return -(elements.size()+1);
		while(inf<=sup){
			int i = (inf+sup)>>>1;
			if(elem.compareTo(elements.get(i))<0)sup=i-1;
			else if(elem.compareTo(elements.get(i))>0)inf=i+1;
			else return i;
		}
		return -(inf+1);
	}
	
	
}
