package es.uned.lsi.eped.DataStructures;

public class Tree<T> extends Collection<T> implements TreeIF<T> {
	
	private T root;
	private ListIF<TreeIF<T>> children; 
	
	//constructor por defecto: construye un árbol n-ário vacío
	Tree(){
		root = null;
		children = new List<TreeIF<T>>();
	}
	//constructor por parámetro: construye un árbol n-ário sin hijos
	//cuya raíz es el elemento dado por parámetro
	Tree(T e){
		setRoot(e);
		children = new List<TreeIF<T>>();
	}
	//constructor por copia
	Tree(Tree<T> T){
		this();
		setRoot(T.getRoot());
		IteratorIF<TreeIF<T>> iterator = T.getChildren().iterator();
		while(iterator.hasNext()){
			TreeIF<T> child = iterator.getNext();
			int numChildren = children.size();
			addChild(numChildren+1, child);
		}
	}
	
	public int size() {
		if(isEmpty()){
			return 0;
		}
		int numChildren = 1; //nodo ra�z
		IteratorIF<TreeIF<T>> iterator = children.iterator();
		//sumar los nodos de los hijos
		while (iterator.hasNext()){
			TreeIF<T> child = iterator.getNext();
			numChildren = numChildren+child.size();
		}
		return numChildren;
	}

	public boolean isEmpty() {
		return root==null;
	}
	
	public boolean contains(T e) {
		if(isEmpty()){
			return false;
		}
		//comprobar si el elemento se corresponde con la raíz del árbol.
		T root = getRoot();
		if(root.equals(e)){
			return true;
		}
		//comprobar si el elemento aparece en alguno de los hijos
		IteratorIF<TreeIF<T>> iterator = children.iterator();
		while (iterator.hasNext()){
			TreeIF<T> child = iterator.getNext();
			T rootChild = child.getRoot();
			if(rootChild.equals(e) || child.contains(e)){
				return true;
			}
		}
		return false;
	}

	public void clear() {
		root = null;
		children = null;
	}

	public IteratorIF<T> iterator() {
		return new TreeIterator<T>();
	}
	
	public T getRoot() {
		return root;
	}

	public void setRoot(T e) {
		root = e;
	}

	public ListIF<TreeIF<T>> getChildren() {
		return children;
	}

	public TreeIF<T> getChild(int pos) {
		return children.get(pos);
	}

	public void addChild(int pos, TreeIF<T> e) {
		children.insert(e, pos);
	}

	public void removeChild(int pos) {
		children.remove(pos);
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public IteratorIF<T> iterator(int traversal) {
		return new TreeIterator<T>(this,traversal);
	}
	public static void main(String [] args) throws Exception{
		TreeIF<Integer> tree = new Tree<Integer>(1);
		//hijo 1
		TreeIF<Integer> tree1 = new Tree<Integer>(2);
		TreeIF<Integer> tree1tree1 = new Tree<Integer>(5);
		//obtener posici�n donde ubicar un nuevo hijo: n�mero de hijos + 1
		int sizeTree1 = tree1.getChildren().size()+1;
		tree1.addChild(sizeTree1, tree1tree1);
		TreeIF<Integer> tree1tree2 = new Tree<Integer>(6);
		sizeTree1 = tree1.getChildren().size()+1;
		tree1.addChild(sizeTree1, tree1tree2);
		int sizeTree = tree.getChildren().size()+1;
		tree.addChild(sizeTree,tree1);
		//hijo 2
		TreeIF<Integer> tree2 = new Tree<Integer>(3);
		sizeTree = tree.getChildren().size()+1;
		tree.addChild(sizeTree,tree2);
		//hijo 3
		TreeIF<Integer> tree3 = new Tree<Integer>(4);
		TreeIF<Integer> tree3tree1 = new Tree<Integer>(7);
		int sizeTree3 = tree3.getChildren().size()+1;
		tree3.addChild(sizeTree3,tree3tree1);
		sizeTree = tree.getChildren().size()+1;
		tree.addChild(sizeTree,tree3);
		
		//operaciones sobre árboles n-ários
		System.out.println("Numero de nodos: "+tree.size());
		System.out.println("contains(3): "+tree.contains(3));
		System.out.println("contains(9): "+tree.contains(9));
		System.out.println("isEmpty?: "+tree.isEmpty());
		System.out.println("isLeaf(1)?: "+tree.isLeaf());
		System.out.println("isLeaf(3)?: "+tree2.isLeaf());
		
	}
}
