package sabatino.esercizio4;

import static java.lang.Math.abs;
import static java.lang.Math.max;


public class BinaryTree {

  protected class Node {
    protected Integer element;
    protected Node left, right;

    Node(int element) {
      this.element = element;
      left = right = null;
    }

    Node(int element, Node left, Node right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    boolean isLeaf() {
      return left == null && right == null;
    }
  }
  
  /** abbozzo di classe, messa solo
   *  per poter realizzare il metodo find,
   *  non usata -- come si potrebbe --
   *  negli altri esercizi
   *  
   * @author elio
   *
   */
  public class NodeReference {
    private Node node;
    
    private NodeReference(Node node) {
      this.node = node;
    }
    
    public int getElement() {
      return node.element;
    }
    
    public void setElement(int e) {
      node.element = e;
    }
  }

  protected Node root;

  public BinaryTree() {
    root = null;
  }

  public void display() {
    display(root, 0);
  }

  protected void display(Node node, int k) {
    if(node != null) {
      display(node.right, k+1);
      for(int i = 0; i < k; i++) System.out.print("   ");
      System.out.println(node.element);
      display(node.left, k+1);
    }
  }

  public boolean isEmpty() {
    return root == null;
  }

  /**
   * inserisce un elemento nell'albero,
   * nella posizione indicata da path;
   * se il path termina su un nodo gia' esistente,
   * sovrascrive il valore dell'elemento;
   * se il path termina su un nodo nullo,
   * oppure se si raggiunge un nodo nullo
   * prima che il path sia finito,
   * l'elemento viene inserito creando un nuovo nodo
   * in quella posizione (vedi slides)
   * @param element elemento da aggiungere all'albero
   * @param path posizione in cui va inserito l'elemento all'interno dell'cammino gerarchico dell'albero
   */
  public void add(int element, String path) {
    root = add(element, path, root);
  }

  protected Node add(int elem, String path, Node nd) {
  	 if(nd==null) return new Node(elem);
     else {
     	if(path.length()==0) nd.element=elem;
     	else{
     		char go = path.charAt(0);
     		String nextPath = path.substring(1);
     		if(go=='L') nd.left= add(elem,nextPath,nd.left);
     		else if(go=='R') nd.right=add(elem,nextPath,nd.right);
     		else throw new IllegalArgumentException("stringa non corretta");
     	}
       return nd;
     }
  }

  /** 
   * Scrive gli elementi in preordine sulla consolle
   */
  public void printPreOrder() {
    if(root == null) System.out.println("albero vuoto");
    printPreOrder(root);
    System.out.println();
  }
/**
 * Metodo ausiliario della stampa in preordine
 * se il nodo passato come argomento è nullo, la procedura termina attraverso una return
 * @param node nodo da stampare
 * */
  protected void printPreOrder(Node node) {
    if(node==null) return;
    System.out.println(node.element);
    printPreOrder(node.left);
  	printPreOrder(node.right);
  	
    		
  }
/**
 * scrive gli elementi in ordine sulla consolle
 * */
  public void printInOrder() {
  	 if(root == null) System.out.println("albero vuoto");
     printInOrder(root);
     System.out.println();
  }
  /**
   * Metodo ausiliario della stampa in ordine
   * se il nodo passato come argomento è nullo, la procedura termina attraverso una return
   * @param node nodo da stampare
   * */
  protected void printInOrder(Node node) {
  	if(node==null) return;
    printInOrder(node.left);
    System.out.println(node.element);
    printInOrder(node.right);
  }
  /**
   * scrive gli elementi in postordine sulla consolle
   * */
  public void printPostOrder() {
  	if(root == null) System.out.println("albero vuoto");
    printPostOrder(root);
    System.out.println();
  }

  /**
   * Metodo ausiliario della stampa in postordine
   * se il nodo passato come argomento è nullo, la procedura termina attraverso una return
   * @param node nodo da stampare
   * */
  protected void printPostOrder(Node node) {
  	if(node==null) return;
    printPostOrder(node.left);
    printPostOrder(node.right);
    System.out.println(node.element);
  }

  /** altezza dell'albero
   *  (altezza dell'albero vuoto = -1) 
   * @return intero rappresentante il valore dell'altezza dell'albero
   */
  public int height() {
    return height(root);
  }
/**
 * altezza dell'albero(metodo ausiliario)
 * se il nodo è vuoto, ritorna -1,
 * altrimenti calcola il massimo tra l'altezza del figlio sinistro e destro
 * @param node nodo su cui effettuare il calcolo dell'altezza
 * @return intero, rappresentante l'altezza fino al nodo passato come paramento.
 * */
  protected int height(Node node) {
  	return node==null?-1:1+max(height(node.left),height(node.right));
  }
  
  /** somma dei valori degli elementi
   * (somma di un albero vuoto = 0,
   *  oppure IllegalStateException)
   * @return intero rappresentante la somma dei valori dei nodi dell'albero
   */
  public int sum() {
    return sum(root);
  }

  private int sum(Node nd) {
  	return nd==null ? 0: nd.element+sum(nd.left)+sum(nd.right);
  }

  /** numero dei nodi
   * (numero dei nodi di un albero vuoto = 0)
   * @return
   */
  public int size() {
    return size(root);
  }

  private int size(Node nd) {
  	return nd==null ? 0 :size(nd.left)+size(nd.right)+1;
  }

  /** numero delle foglie */
  public int numberOfLeaves() {
    return numberOfLeaves(root);
  }

  private int numberOfLeaves(Node nd) {
  	if(nd==null) return 0;
    else if (nd.isLeaf()) return 1;
    else return numberOfLeaves(nd.left)+numberOfLeaves(nd.right);
  }

/** da' come risultato true se l'elemento x si trova in this,
    false altrimenti  */
  public boolean search(int x) {
    return search(x,root);
  }

  /** realizzare con una sola istruzione */
  protected boolean search(int x, Node nd) {
  	return (nd==null) ? false : (nd.element==x) ? true :  (search(x,nd.left)|| search(x,nd.right));
  }

  /**
   * 
   * @param node
   */
 
  public void mirrorInPlace() {
    mirrorInPlace(root);
  }
  
  
  protected void mirrorInPlace(Node node) {
  	if(node!=null){
  	Node temp=node.left;
		node.left=node.right;
		node.right=temp;
		mirrorInPlace(node.left);
		mirrorInPlace(node.right);
  	}
  }


  public void increment() {
    increment(root);
  }

  private void increment(Node node) {
  	if(node!=null){
    	node.element++;
    	increment(node.left);
    	increment(node.right);
    }
  }

/* costruisce un nuovo albero privo dei nodi
   di livello >= h
 */
  public BinaryTree trimmed(int h) {
    BinaryTree trimmedTree = new BinaryTree();
    trimmedTree.root=trimmed(h,root);
    return trimmedTree;
  }

  /*  realizzare con una sola istruzione */
  protected Node trimmed(int h, Node nd){
  	return (nd==null || h==0) ? null: new Node(nd.element,trimmed(h-1,nd.left),trimmed(h-1,nd.right));
  }


/**
  elimina dall'albero i nodi di livello >= h:
  realizzazione con la tecnica "naturale":
  il caso di h<=0 sulla radice viene
  trattato come caso particolare nella
  procedura "wrapper"
*/
  public void trim(int h) {
    trim(h, root);
  }
  
 // PRECOND: h >= 1
  protected void trim1(int h, Node nd) {
  	if(nd!=null){
  		if(h==1){
  			nd.left=nd.right=null;
  		}else{
  			trim(h-1,nd.left);
  			trim(h-1,nd.right);
  		}
  	}
  }


/** in alternativa:
  realizzazione con la tecnica usata nel libro di testo
*/

  private Node trim(int h, Node nd) {
  	 if(nd==null || h==0)return null;
     else{
     	nd.left=trim(h-1,nd.left);
     	nd.right=trim(h-1,nd.right);
     	return nd;
     }
  }

 /** 
  * costruisce e restituisce un nuovo albero speculare di t
  * 
  * @return speculare di t
  */
  public BinaryTree mirror() {
  	BinaryTree mirror = new BinaryTree();
    mirror.root=mirror(root);
    return mirror;
  }
  protected Node mirror(Node nd){
  	return (nd == null) ? null : new Node(nd.element, (nd.right != null ? mirror(nd.right) : null), (nd.left != null ? mirror(nd.left) : null));
  }

  // definire il metodo protected corrispondente,
  // analogamente ai metodi precedenti

  public boolean equalTo(BinaryTree t) {
    return areEqual(root,t.root); // si richiami areEqual
  } 

  // si completi il metodo, richiamando areEqual
	public boolean equals(Object ob) {
    ;
  	if(ob == null) return false;
    if(getClass() != ob.getClass())
    	return areEqual(root,(Node)ob);
    return false;
  }

  protected boolean areEqual(Node node1, Node node2) {
  	return node1 == node2 || (node1!=null && node2!=null && node1.element==node2.element && areEqual(node1.left,node2.left) && areEqual(node1.right,node2.right));
  }

  // crea un nuovo albero binario copia di this
  public BinaryTree copy() {
  	BinaryTree copy = new BinaryTree();
    copy.root=mirror(root);
    return copy;
  }
  
  protected Node copy(Node nd) {
  	return (nd==null)? null : new Node(nd.element,copy(nd.left),copy(nd.right));
  }  

  private Node find(int x, Node nd) {
    if(nd == null || nd.element == x) return nd;
    Node ris = find(x, nd.left);
    if(ris == null) ris = find(x, nd.right);
    return ris;
  }
  
  public NodeReference find(int x) {
    return new NodeReference(find(x, root));
  }

  /** restituisce true se l'albero è completamente bilanciato
   *  false altrimenti (vedi definizione su libro di testo)
   * @return
   */
  public boolean isCompletelyBalanced() {
  	int x = isCB(root);
  	if(x>=0) return true;
  	else return false;
  }

  private int isCB(Node nd) {
  	if(nd==null)return -1;
  	else{
  	int l = isCB(nd.left);
  	int r = isCB(nd.right);
  	if(l==r) return l+1;
  	else return -(max(l,r)+1);
  	}
  }

  /**  restituisce true se l'albero è 1-bilanciato
   *  false altrimenti (vedi definizione su libro di testo);
   *  metodo da realizzare a novembre/dicembre
   * @return
   */  
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
  

  /**  numero dei nodi a livello liv */
  public int numNodesAtLevel(int liv) {
    return numNodesAtLevel(root, liv);
  }

  protected int numNodesAtLevel(Node nd, int lev) {
  	if(nd!=null){
  		if(lev!=0){
  		int sumL = numNodesAtLevel(nd.left,lev-1);
  		int sumR = numNodesAtLevel(nd.right,lev-1);
  		return sumL+sumR;
  		}
  		else return 1;
  	}
  	else return 0;
  }


  private class BoolNode {
    boolean found; // fatto
    Node node;

    BoolNode(boolean found, Node node) {
      this.found = found;
      this.node = node;
    }
  }

/**Esercizio opzionale:
   elimina il sottoalbero di radice x;
   se l'elemento x e' presente piu' volte,
   elimina uno solo dei sottoalberi di radice x
   (il primo che trova);
   se l'eliminazione e' stata effettuata,
   restituisce true;
   se invece l'elemento x non e' presente,
   restituisce false
 */
  public boolean removeSubtree(int x) {
    BoolNode ris = removeSubtree(x, root);
    root = ris.node;
    return ris.found;
  }

  protected BoolNode removeSubtree(int x, Node nd) {
  	if(nd!=null){
  	if(nd.element == x) {
  		nd.left=nd.right=null;
  		return new BoolNode(true, root);
  	}
  	BoolNode ris = removeSubtree(x, nd.left);
  	if(ris.found==false) ris = removeSubtree(x, nd.right);
  	return ris;
  	}
  	return new BoolNode(false, root);
  }

 //cardine
  public void printCentralNodes() {
    System.out.print("nodi centrali o nodi-cardine: ");
    central(root,0);
    System.out.println();
  }

  private int central(Node nd, int p) {
  	if(nd==null) return -1;
  	else{
  		int hl = central(nd.left,p+1);
  		int hr = central(nd.right,p+1);
  		int h = max(hl,hr)+1;
  		if(p==h) {
  			System.out.println("cardine : "+nd.element);
  		}
  		return h;
  	}
  }
  
//maxElem
  public int maxElem() {
    if(root == null) throw new IllegalStateException("albero vuoto");
    return maxElem(root);
  }
  
  private static int max3(int x, int y, int z) {
    return max(x, max(y, z));
  }
  
  private int maxElem(Node nd) {
    return nd==null? Integer.MIN_VALUE: max3(nd.element,maxElem(nd.left),maxElem(nd.right));
  }
  
  /* TODO Esercizio 3.13 pag. 99 libro di testo
   * Restituisce il riferimento al nodo (o a uno dei nodi,
   * se ne esistono più d'uno) v tale che:
   * il rapporto fra il numero dei nodi del sottoalbero
   * di radice v (quindi incluso il nodo stesso) e l'altezza + 1
   * dello stesso sia massimo;
   * in questo esercizio si considera l'altezza incrementata di 1,
   * altrimenti per le foglie il rapporto sarebbe 1/0 = infinito,
   * e l'esercizio sarebbe banale: il risultato sarebbe
   * una qualunque foglia.
   * E' come se l'altezza fosse definita 0 per l'albero vuoto,
   * 1 per le foglie, ecc.
   *
   * Per ragioni di debugging,
   * il metodo può (e anzi, per ora deve), prima di restituire il riferimento al nodo,
   * scrivere sulla console il valore del rapporto per quel nodo.
   *
   * NOTA BENE: Si richiede che l'algoritmo sia lineare nel numero
   * dei nodi, visitando quindi l'albero una volta sola.
   *
   * Si puo' definire una classe privata ausiliaria,
   * analogamente a quanto proposto per qualche esercizio precedente
   *
   *
   * Naturalmente per fare "il vero lavoro"
   * occorre definire un metodo ricorsivo protected o private,
   * richiamato dal metodo pubblico
   */
  public NodeReference maxDescendantsHeightRatio() {
    return null;
  } 
  
  /**
   * TODO Esercizio 3.14 libro di testo
   * scrive sulla consolle i valori dei nodi v che soddisfano
   * alla seguente condizione:
   * la somma dei valori degli antenati di v (v incluso)
   * è uguale alla somma dei valori dei discendenti di v (v incluso).
   * Nota: si definisca un opportuno metodo protetto o privato ...
   */
  
  public void printEquiNodes() {
    System.out.print("nodi equi: ");
    // ...
    System.out.println();    
  }  
}

