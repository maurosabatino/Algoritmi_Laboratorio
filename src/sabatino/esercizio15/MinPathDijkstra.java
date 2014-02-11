package sabatino.esercizio15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import sabatino.esercizio14.Graph;
import sabatino.esercizio15.PriorityQueue.HeapPriorityQueue;

public class MinPathDijkstra<V,E> {
	HeapPriorityQueue<V> q;
	HashMap<V,Double> dist;
	Map<V,V> padri;
	public ArrayList<Object> minPath(Graph<V,E> g,V s,V d){
		dist = new HashMap<V,Double>();
		padri = new HashMap<V,V>();
		q = new HeapPriorityQueue<V>(g.vertices().size());
		ArrayList<Object> path = new ArrayList<Object>();
		ArrayList<V> vertex = g.vertices();
		for(V x:vertex){
			dist.put(x,Double.POSITIVE_INFINITY);
			padri.put(x, null);
		}
		
		dist.put(s, 0.0);
		padri.put(s, s);
		
		for(V x:vertex){
			q.insert(x, dist.get(x));

		}
		while(!q.isEmpty()){
			V u = q.extractfirst();
			path.add(u);
			for(V v:g.neighbors(u)){
				if(!v.equals(d)){
					double weight = g.getWeight(u, v);
					double distanceThrought = dist.get(u)+weight;//distanza del padre + la sua distanza
					if(distanceThrought<dist.get(v)){//se il costo è inferiore al nodo attuale
						padri.put(v, u);
						dist.put(v, distanceThrought);
						q.decreasePriority(v, dist.get(v));
					}
				}else break;
			}
	}
		
		
		
		
		
		
		return path;
	}
	
	
	
	
	
	
	public Graph<V,E> minPathW(Graph<V,E> g,V s, V d){
	
		
		
		return null;
	}
}
