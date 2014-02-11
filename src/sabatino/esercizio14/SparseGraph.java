package sabatino.esercizio14;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class SparseGraph<V,E> implements Graph<V,E>{
	
	ArrayList<V> nodi;	
	HashMap<V,ArrayList<E>> archi;
	int n;
	int m;
	
	public SparseGraph(){
		nodi = new ArrayList<V>();
		archi = new HashMap<V,ArrayList<E>>();
		n = 0;
		m = 0;
	}
	
	@Override
	public boolean addVertex(V vertex) {
		if(!nodi.contains(vertex)){
			nodi.add(vertex);
			n++;
			ArrayList<E> neighbors = new ArrayList<E>();
			archi.put(vertex,(ArrayList<E>) neighbors);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean addEdge(V v1, V v2, E info) {
		if(nodi.contains(v1)){
			if(!nodi.contains(v1)) addVertex(v1);
			if(!nodi.contains(v2)) addVertex(v2);
			Arco<V,E> a = new Arco<V, E>(info,v1,v2);
			ArrayList<Arco> neighbors = (ArrayList<Arco>)archi.get(v1);
			neighbors.add(a);
			m++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addEdge(V v1, V v2, double weight, E info) {
		if(nodi.contains(v1)){
			if(!nodi.contains(v1)) addVertex(v1);
			if(!nodi.contains(v2)) addVertex(v2);
			Arco<V,E> a = new Arco<V, E>(info,v1,v2,weight);
		
			ArrayList<Arco<V,E>> neighbors = (ArrayList<Arco<V,E>>)archi.get(v1);
			neighbors.add(a);
			m++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addUndirectedEdge(V v1, V v2, E info) {
		addEdge(v1,v2,info);
		addEdge(v2,v1,info);
		return true;
	}

	@Override
	public boolean addUndirectedEdge(V v1, V v2, double weight, E info) {
		addEdge(v1,v2,weight,info);
		addEdge(v2,v1,weight,info);
		return true;
	}

	@Override
	public boolean hasVertex(V vertex) {
		if(nodi.contains(vertex))
			return true;
		return false;
	}

	@Override
	public boolean hasEdge(V v1, V v2) {
		if(nodi.contains(v1)){
			if(nodi.contains(v2)){
				ArrayList<Arco<V,E>> neighbors = (ArrayList<Arco<V,E>>)archi.get(v1);
				if(neighbors.contains(new Arco(v1,v2)));
				return true;
			}
		}
		return false;
	}

	@Override
	public double getWeight(V source, V dest) {
		return getEdge(source,dest).getWeight();
	}

	@Override
	public ArrayList<V> vertices() {
		return nodi;
	}

	@Override
	public ArrayList<V> neighbors(V vertex) {
	if(nodi.contains(vertex)){
		ArrayList<V> neighbors = new ArrayList<V>();
		ArrayList<Arco> arch = (ArrayList<Arco>)archi.get(vertex);
	
		for(Arco a: arch)
		neighbors.add((V) a.fin);
		return neighbors;
	}
	return null;
	}
	
	public Arco getEdge(V source, V dest){
		ArrayList<Arco> neighbors = (ArrayList<Arco>)archi.get(source);
		Arco find = new Arco(source,dest);
		for(Arco<V,E> a : neighbors){
			if((a.fin.equals(find.fin))&&(a.in.equals(find.in)))
				return a;
		}
		return null;
	}

	public void printVertex(){
		for(V i : nodi)
			System.out.println(i);
	}
	public void printNeighbors(int vertex){
		ArrayList<Arco> neighbors = (ArrayList<Arco>)archi.get(vertex);
		for(Arco<V,E> a : neighbors)
			System.out.println("iniziale: "+a.in+" finale: "+a.fin+" info: "+a.info);
	}
	public void ToDot(String GraphName) throws IOException{
		String Graph = GraphName+".dot";
	  FileWriter outFile = new FileWriter(Graph, false);
	  PrintWriter out = new PrintWriter(outFile);
	  out.println("digraph "+GraphName+"{");
	  for(V i : nodi){
	  	ArrayList<Arco> neighbors = (ArrayList<Arco>)archi.get(i);
			for(Arco<V,E> a : neighbors){
				out.println(a.in+" -> "+a.fin+";");
			}
	  }
	  out.println("}");
	  out.close();
	}
}
