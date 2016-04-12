package es.uned.lsi.eped.DataStructures;

public class BTree<T> extends Collection<T> implements BTreeIF<T> {

	private T root;
	private BTreeIF<T> leftChild;
	private BTreeIF<T> rightChild;
	
	//constructor por defecto: crea un �rbol binario vac�o
	BTree(){
		root = null;
		leftChild = null;
		rightChild = null;
	}
	//contructor por par�metro: crea un �rbol con ra�z elem e hijos vac�os
	BTree(T elem){
		root = elem;
		leftChild = new BTree<T>();
		rightChild = new BTree<T>();
	}
	//contructor por copia
	BTree(BTreeIF<T> bT){
		T btRoot = bT.getRoot();
		setRoot(btRoot);
		BTreeIF<T> btLeft = bT.getLeftChild();
		setLeftChild(btLeft);
		BTreeIF<T> btRight = bT.getRightChild();
		setRightChild(btRight);
	}
	
	//número de nodos del árbol binario
	public int size() {
		if(isEmpty()){
			return 0;
		}
		return 1+leftChild.size()+rightChild.size();
	}
   //decide si el árbol binario es vacío
	public boolean isEmpty() {
		return root==null;
	}
	//decide si el árbol binario contiene el valor dado por parámetro
	public boolean contains(T e) {
		if(isEmpty()){
			return false;
		}
		return root.equals(e) || leftChild.contains(e) || rightChild.contains(e);
	}
    //borra el árbol binario
	public void clear() {
		root = null;
		leftChild = null;
		rightChild = null;
	}
    //devuelve iterador vacío. Hay que implementar esste método porque BTree
	//extiende a la clase Collection
	public IteratorIF<T> iterator() {
		return new BTreeIterator<T>();
	}
	  /* Obtiene la raíz del árbol (único elemento sin antecesor).	*
	   * @Pre: !isEmpty ();											*
	   * @return el elemento que ocupa la raíz del árbol. 			*/
	public T getRoot() {
		return root;
	}
	 /* Obtiene el hijo izquierdo del árbol llamante o un árbol 	*
	  * vacío en caso de no existir.								*
	  * @return un árbol, bien el hijo izquierdo bien uno vacío 	*
	  * de no existir tal hijo										*/
	public BTreeIF<T> getLeftChild() {
		if(leftChild==null){
			return new BTree<T>();
		}
		return leftChild;
	}
	 /* Obtiene el hijo derecho del árbol llamante o un árbol  	*
	   * vacío en caso de no existir.								*
	   * @return un árbol, bien el hijo derecho bien uno vacío  	*
	   * de no existir tal hijo										*/
	public BTreeIF<T> getRightChild() {
		if(rightChild==null){
			return new BTree<T>();
		}
		return rightChild;
	}
	/* Modifica la raíz del árbol.								*
	   * @param el elemento que se quiere poner como raíz del 		*
	   * árbol.														*/ 	
	public void setRoot(T e) {
		root = e;
	}
	 /* Pone el árbol parámetro como hijo izquierdo del árbol		*
	  * llamante. Si ya había hijo izquierdo, el antiguo dejará de  *
	  * ser accesible (se pierde).                                  *
	  * @param child el árbol que se debe poner como hijo izquierdo.*/
	public void setLeftChild(BTreeIF<T> child) {
		leftChild = child;
	}
	  /* Pone el árbol parámetro como hijo derecho del árbol		*
	   * llamante. Si ya había hijo izquierdo, el antiguo dejará de *
	   * ser accesible (se pierde).                                 *													*
	   * @param child el árbol que se debe poner como hijo derecho.	*/
	public void setRightChild(BTreeIF<T> child) {
		rightChild = child;		
	}
	   /* Elimina el hijo izquierdo del árbol.						*/
	public void removeLeftChild() {
		leftChild = new BTree<T>();
	}
	   /* Elimina el hijo derecho del árbol.						*/
	public void removeRightChild() {
		rightChild = new BTree<T>();		
	}
	   /* Determina si el árbol llamante es una hoja.				*
	    * @return true sii el árbol es una hoja (no tiene hijos).	*/
	public boolean isLeaf() {
		return root!=null && leftChild.isEmpty() && rightChild.isEmpty();
	}
	 /* Obtiene un iterador para el árbol.						*
     * @param traversal el tipo de recorrido indicado por las   * 
     * constantes PREORDER (preorden o profundidad), POSTORDER  *
     * (postorden), BREADTH (anchura), INORDER (inorden) o 		*
     * RLBREADTH (anchura de derecha a izquierda).				*
     * @return un iterador segn el recorrido indicado.			*/
	public IteratorIF<T> iterator(int traversal) {
		BTreeIF<T> bT = new BTree<T>(this);
		return new BTreeIterator<T>(bT,traversal);
	}
	
	public static void main(String [] args) throws Exception{
		/*
		ejemplo extraido de Wikipedia:
		link: https://es.wikipedia.org/wiki/Recorrido_de_%C3%A1rboles
		En la página web aparecen los recorridos del árbol
		*/
		BTree<String> btree = new BTree<String>("F");
		//hijo izquierdo
		BTree<String> izq = new BTree<String>("B");
		BTree<String> izqizq = new BTree<String>("A");
		izq.setLeftChild(izqizq);
		BTree<String> izqder = new BTree<String>("D");
		BTree<String> izqderizq = new BTree<String>("C");
		izqder.setLeftChild(izqderizq);
		BTree<String> izqderder = new BTree<String>("E");
		izqder.setRightChild(izqderder);
		izq.setRightChild(izqder);
		btree.setLeftChild(izq);
		//hijo derecho
		BTree<String> der = new BTree<String>("G");
		BTree<String> derder = new BTree<String>("I");
		BTree<String> derderizq = new BTree<String>("H");
		derder.setLeftChild(derderizq);
		der.setRightChild(derder);
		btree.setRightChild(der);
		System.out.println("Número de nodos: "+btree.size());
		//recorrido en preorden
		System.out.print("Preorden: ");
		BTreeIterator<String> iterPreorder = (BTreeIterator<String>) btree.iterator(0);
		while(iterPreorder.hasNext()){
			System.out.print(iterPreorder.getNext()+" ");
		}
		System.out.println();
		//recorrido en postorden
		System.out.print("Postorden: ");
		BTreeIterator<String> iterPostorder = (BTreeIterator<String>) btree.iterator(1);
		while(iterPostorder.hasNext()){
			System.out.print(iterPostorder.getNext()+" ");
		}
		System.out.println();
		//recorrido en profundidad LR
		System.out.print("Breadth LR: ");
		BTreeIterator<String> iterBreadthLR = (BTreeIterator<String>) btree.iterator(2);
		while(iterBreadthLR.hasNext()){
			System.out.print(iterBreadthLR.getNext()+" ");
		}
		System.out.println();		
		//recorrido en inorden
		System.out.print("Inorden: ");
		BTreeIterator<String> iterInorden = (BTreeIterator<String>) btree.iterator(3);
		while(iterInorden.hasNext()){
			System.out.print(iterInorden.getNext()+" ");
		}
		System.out.println();
		//recorrido en profundidad RL
		System.out.print("Breadth RL: ");
		BTreeIterator<String> iterBreadthRL = (BTreeIterator<String>) btree.iterator(4);
		while(iterBreadthRL.hasNext()){
			System.out.print(iterBreadthRL.getNext()+" ");
		}
		System.out.println();		
	}

	
}
