package sabatino.esercizio14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphVisitImplements<V,E> implements GraphVisit<V,E> {
	
	Queue<V> F;
	HashMap<V,Boolean>visit;
	Stack<V> stack;
	ArrayList<V>padri;
	public Graph<V, E> breadthFirst(Graph<V, E> graph, V s, VertexAnalyser<V> va) {///mancano i padri
		padri = new ArrayList<V>();
		ArrayList<V> vertex = graph.vertices();
		visit = new HashMap<V,Boolean>();	
		for(V n : vertex){
			visit.put(n, false);
		}
		F = new LinkedList<V>();
		F.clear();
		for(V v: vertex){
			if(!visit.get(v))bfs(graph,v,va);
		}
		return graph;//da controllare il valore di ritorno
	}
	private void bfs(Graph<V, E> graph, V s, VertexAnalyser<V> va) {
		visit.put(s, true);
		F.offer(s);
		while(!F.isEmpty()){
			V u = F.poll();
			va.analyse(u);
			for(V adj:graph.neighbors(u)){
				if(!visit.get(adj)){
					F.add(adj);
					visit.put(adj, true);
				}
			}
		}
	}

	@Override
	public Graph<V, E> depthFirst(Graph<V, E> graph, V s, VertexAnalyser<V> va) {
		ArrayList<V> vertex = graph.vertices();
		visit = new HashMap<V,Boolean>();	
		visit.clear();
		for(V n : vertex){
			visit.put(n, false);
		}
		stack = new Stack<V>();
		stack.clear();
		for(V v: vertex){
			if(!visit.get(v))dfs(graph,v,va);
		}
		return graph;//da controllare il valore di ritorno
	}
	public void dfs(Graph<V, E> graph, V s, VertexAnalyser<V> va) {
		visit.put(s, true);
		stack.push(s);
		while(!stack.isEmpty()){
			V u = stack.pop();
			
			for(V adj:graph.neighbors(u)){
				if(!visit.get(adj)){
					visit.put(adj, true);
					stack.push(adj);
				}else{
					stack.remove(u);
				}
			}
			va.analyse(u);
		}
	}
	
	/*	public Graph<V, E> breadthFirst(Graph<V, E> graph, V s, VertexAnalyser<V> va) {///vale solo per grafi connessi
		F = new LinkedList<V>();
		ArrayList<V> vertex = graph.vertices();
		visit = new HashMap<V,Boolean>();	
		for(V n : vertex){
			visit.put(n, false);
		}
		F.add(s);
		visit.put(s, true);
		//inizio visita s
		//va.analyse(s);
		
		while(!F.isEmpty()){
			V nodo = F.remove();
			//fine visita nodo
			va.analyse(nodo);
			for(V adj:graph.neighbors(nodo)){
				if(!visit.get(adj)){
					F.add(adj);
					visit.put(adj, true);
					//inizio visita adj
				}
			}
		}
		return graph;//da controllare il valore di ritorno
	}*/

}
