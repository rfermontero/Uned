package es.uned.lsi.eped.DataStructures;

public class Collection<T> implements CollectionIF<T> {

	protected Node<T> firstNode;
	protected int size;
	//Constructor por defecto
	Collection(){
		firstNode = null;
		size = 0;
	}
	//constructor por parámetro: colección unitaria
	Collection(T e){
		firstNode = new Node<T>(e);
		size = 1;
	}
	
	/* Devuelve el número de elementos de la colección.			*/
	public int size() {
		return size;
	}

	/* Devuelve true sii la colección no contiene elementos.	*/	
	public boolean isEmpty() {
		return size==0;
	}

	/* Devuelve true sii e está en la colección.				*/
	public boolean contains(T e) {
		Node<T> node = firstNode;
		while(node!=null){
			T next = node.getValue();
			if(next.equals(e)){
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	/* Elimina todos los elementos de la colección.				*/
	public void clear() {
		firstNode = null;
		size = 0;
	}

	/* Devuelve un iterador sobre la colección.					*/
	public IteratorIF<T> iterator() {
		return new IteratorLinear<T>(firstNode);
	}

	
	
}
