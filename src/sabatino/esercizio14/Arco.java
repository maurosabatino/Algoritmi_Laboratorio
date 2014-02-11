package sabatino.esercizio14;

public class Arco<V,E>{
	E info;
	V in;
	V fin;
	double weight;
	
	public Arco(V in, V fin){
		this.info=null;
		this.in = in;
		this.fin = fin;
		this.weight=1;
	}
	public Arco(E info,V in, V fin){
		this.info = info;
		this.in = in;
		this.fin = fin;
		this.weight=1;
	}
	public Arco(E info,V in, V fin,double weight){
		this.info = info;
		this.in = in;
		this.fin = fin;
		this.weight = weight;
	}
	double getWeight(){
		return weight;
	}
}