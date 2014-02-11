package sabatino.esercizio13.AVLTree;

public class Elem implements ObjectWhitKey{
	String elem;
	int key;
	
	public Elem( String elem, int key){
		this.elem=elem;
		this.key=key;
	}
	@Override
	public int key() {
		return key;
	}
	public String toString() {
    return key + " " + elem;
  }
}
