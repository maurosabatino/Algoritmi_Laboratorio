package sabatino.esercizio15;

import java.util.*;

import sabatino.esercizio14.*;


public class TestDijkstra {
	public static void main(String[]args){
		Graph<Integer,String> grafo = new SparseGraph<Integer,String>();
		MinPathDijkstra dijkstra = new MinPathDijkstra();
		//System.out.println("vertici del grafo");
		grafo.addVertex(4);
		grafo.addVertex(8);
		grafo.addVertex(11);
		grafo.addVertex(3);
		//grafo.addVertex(5);
		grafo.addVertex(9);
		
		//grafo.printVertex();
		//System.out.println("archi del grafo");
		grafo.addEdge(4, 8,"4->8");
		grafo.addEdge(4, 11,"4->11");
		grafo.addEdge(8, 3,11,"8->3");
		grafo.addEdge(3, 9,21,"3->9");
		grafo.addEdge(11, 9,"11->9");
		ArrayList<Integer> result = dijkstra.minPath(grafo, 4, 9);
		for(Integer i: result) System.out.println(i);
	}
	
	
}
