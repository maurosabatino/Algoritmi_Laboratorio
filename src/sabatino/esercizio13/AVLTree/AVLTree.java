package sabatino.esercizio13.AVLTree;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * classe che implementa un albero AVL
 * @author Mauro Sabatino Matricola: 736724
 *
 */

public class AVLTree implements Dictionary{
	Node root;
	int numElem;

    /**
     *
     * @param key
     * @return
     */
	@Override
	public ObjectWhitKey get(int key) {
		return get(key,root);
	}

    private ObjectWhitKey get(int key, Node node){
		if(node ==null) return null;
		if(key<node.value.key()) return get(key,node.left);
		else if(key>node.value.key()) return get(key,node.right);
		else return node.value;
	}

	@Override
	public boolean isEmpty() {
		if(root==null) return true;
		return false;
	}

    /**
     * 
     * @param element elemento che si vuole inserire nel dizionario
     * @return
     *
     */
    @Override
    public ObjectWhitKey put(ObjectWhitKey element) {
		root = put(element,root);
		return root.value;
	}
	private Node put(ObjectWhitKey element,Node node) {
		if(node==null){
			node = new Node(element);
			numElem++;
		}else if(element.key()<node.value.key()){//se la chiave è minore di quella del nodo attuale, bisogna posizionarsi sulla sinistra
			node.left=put(element,node.left);//richiama la procedura sul sisnistro, quando avrà terminato il controllo torna al padre
			if(sbil(node) == 2){//caso di sbilanciamento sinistro(SS), il controllo viene fatto dal padre sui figli
				if(element.key() > node.left.value.key())node.left= DD(node.left);//(caso SD1)se è maggiore la chiave da inserire rispetto al nodo,bisogna fare una rotazione destra
					node = SS(node);//(Caso SS1)altrimenti effettua una rotazione sinistra
			}
		}else if(element.key() > node.value.key()) {
			node.right=put(element,node.right);
			if(sbil(node) == - 2){
				if(element.key() < node.right.value.key()) node.right= SS(node.right);//in questo caso sarebbe DD
				 node = DD(node);//in questo caso sarebbe DS1
			}
		}else if(element.key()==node.value.key()){
			node = new Node(element);
		}
		upgradeHeight(node);
		return node;
	}

    /**
     *TODO remove
     * @param key chiave dell'elemento che si vuole cancellare dal dizionario.
     * @return valore dell'elemento che era presente nell'AVL, se non presente ritorna null.
     */
	@Override
public ObjectWhitKey remove(int key) {
		return remove(key,root).value;
	}
	private Node remove(int key,Node node){
    if(node == null) return null;//non trovato e ritorno null
    if(key<node.value.key()){//la chiave sti può trovare nel sottoalbero sisintro,ribilancio da sopra
			node.left = remove(key,node.left);
	    if(sbil(node) == 2){//caso di sbilanciamento sinistro(SS), il controllo viene fatto dal padre sui figli
		    if(key > node.left.value.key())node.left= DD(node.left);//(caso SD1)se è maggiore la chiave da inserire rispetto al nodo,bisogna fare una rotazione destra
		    node = SS(node);
	    //ora bisogna ribilanciare
	   /* int l = node.left != null ? node.left.height : 0;
	    if((node.right != null) && (node.right.height - l >= 2)){
				int rightHeight = node.right.right != null ? node.right.right.height : 0;
        int leftHeight = node.right.left != null ? node.right.left.height : 0;
        if(rightHeight <= leftHeight) DD(node);
		    SS(node);
	    }*/
    }
    }
    else if(key>node.value.key()){//la chiave si può trovare nel sottoalbero destro
      node.right = remove(key,node.right);
	    if(sbil(node) == - 2){
		    if(key < node.right.value.key()) node.right= SS(node.right);//in questo caso sarebbe DD
		    node = DD(node);
	   /* int r = node.right != null ? node.right.height : 0;
	    if((node.left != null) && (node.left.height - r >= 2)){
		    int leftHeight = node.left.left != null ? node.left.left.height : 0;
		    int rightHeight = node.left.right != null ? node.left.right.height : 0;
		    if(leftHeight <= rightHeight) SS(node);
		    DD(node);
	    }*/
    }
    }else if(key==node.value.key()){
			if(node.left==null) node = node.right;
	    else if(node.right==null) node= node.left;
	    else{
				node.value = min(node.right);
				node.right = remove(node.value.key(),node.right);
				numElem--;
			}
		}
	upgradeHeight(node);
  return node;
  }

	@Override
	public int size() {
		return numElem;
	}

	@Override
	public ObjectWhitKey max() {
		return max(root);
	}
	private ObjectWhitKey max(Node node) {
		if(node==null) return null;
		else if(node.right!=null) return max(node.right);
		return node.value;
	}

	@Override
	public ObjectWhitKey min() {
		return min(root);
	}
	private ObjectWhitKey min(Node node) {
		while(node.left!=null) node = node.left;
		return node.value;
	}
	private Node deleteMin(Node node){
		if(node.left==null) return node.right;
		node.left = deleteMin(node.left);
		return node;
	}


    /**
     * Rotazione ss
     * @param d
     * @return
     */
	private Node SS(Node d){
		Node s ;
		s =d.left;
		d.left=s.right;
		s.right=d;
		upgradeHeight(d);
		upgradeHeight(s);
		return s;
	}

    /**
     * rotazione dd
     * @param s
     * @return
     */
	private Node DD(Node s){
		Node d;
		d=s.right;
		s.right=d.left;
		d.left=s;
		upgradeHeight(s);
		upgradeHeight(d);
		return d;
	}
	//------------------funzioni ausuliare----------------\\
	private int height(Node node){
		if(node==null) return -1;
		else return node.height;
	}
	private void upgradeHeight(Node node){
		if(node!=null)
		node.height = max(height(node.left),height(node.right))+1;//ricalcola l'altezza del nodo radice(node)
	}
   private int sbil(Node node){
       return height(node.left)-height(node.right);
   }
	 private static int max(int lhs, int rhs) {
		 return lhs > rhs ? lhs : rhs;
	 }
	 
	 public void printInOrder() {
	 	 if(root == null) System.out.println("albero vuoto");
	    printInOrder(root);
	    System.out.println("size: "+size()+" max: "+max()+" min: "+min());
	 }

	 protected void printInOrder(Node node) {
	 	if(node==null) return;
	   printInOrder(node.left);
	   System.out.println(node.value.toString());
	   printInOrder(node.right);
	 }

    public boolean is1Balanced() {
        int x = is1Balanced(root);
        if(x>=0)return true;
        else return false;
    }

    private int is1Balanced(Node node) {
        if(node==null) return -1;
        else{
            int l= is1Balanced(node.left);
            int r= is1Balanced(node.right);
            if(l==r || abs(l-r)<=1) return max(l,r)+1;
            else return -max(l,r)+1;
        }
    }
}
