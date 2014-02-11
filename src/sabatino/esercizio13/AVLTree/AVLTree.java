package sabatino.esercizio13.AVLTree;

/**
 * classe che implementa un albero AVL
 * @author Mauro Sabatino Matricola: 736724
 *
 */

public class AVLTree implements Dictionary{
	Node root;
	int numElem;
	
	@Override
	public ObjectWhitKey get(int key) {
		return get(key,root);
	}
	public ObjectWhitKey get(int key,Node node){
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

	@Override
	public ObjectWhitKey put(ObjectWhitKey element) {
		
		root = put(element,root);
		return root.value;
	}
	private Node put(ObjectWhitKey element,Node node) {
		if(node==null){
			node = new Node(element);
			numElem++;
		}else if(element.key()<node.value.key()){
			node.left=put(element,node.left);
			if((height(node.left)-height(node.right)) == 2){
				if(element.key() > node.left.value.key())node.left=S(node.left);
					node = R(node);
			}
		}else if(element.key() > node.value.key()) {
			node.right=put(element,node.right);
			if((height(node.right)-height(node.left)) == 2){
				if(element.key() < node.right.value.key()) node.right=R(node.right);
				node = S(node);
			}
		}else if(element.key()==node.value.key()){
			node = new Node(element);
		}
		node.height = (max(height(node.left),height(node.right))+1);
		return node;
	}
	

	@Override
public ObjectWhitKey remove(int key) {
		// TODO Auto-generated method stub
		return null;
	}
/*	private Node remove(int key,Node node){
		if(node == null) return null;
		
	}*/

	@Override
	public int size() {
		return numElem;
	}

	@Override
	public ObjectWhitKey max() {
		return max(root);
	}
	public ObjectWhitKey max(Node node) {
		if(node==null) return null;
		else if(node.right!=null) return max(node.right);
		return node.value;
	}

	@Override
	public ObjectWhitKey min() {
		return min(root);
	}
	public ObjectWhitKey min(Node node) {
		if(node==null) return null;
		else if(node.left==null) return node.value;
		else return min(node.left);
	}
	
	
	public Node R(Node d){
		Node s ;
		s =d.left;
		d.left=s.right;
		s.right=d;
		d.height=max(height(d.left),height(d.right))+1;
		s.height=max(height(s.left),height(s.right))+1;
		return s;
	}
	public Node S(Node s){
		Node d;
		d=s.right;
		s.right=d.left;
		d.left=s;
		s.height=max(height(s.left),height(s.right))+1;
		d.height=max(height(d.left),height(d.right))+1;
		return d;
	}
	
	public int height(Node node){
		if(node==null) return -1;
		else return node.height;
	}

	 public static int max (int lhs, int rhs) {
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
	 

}
