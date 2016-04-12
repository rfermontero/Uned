package es.uned.lsi.eped.DataStructures;

public class Iterator<T> implements IteratorIF<T> {
	protected Node<T> initialNode;
	protected Node<T> currentNode;
	
	Iterator(){
		initialNode = null;
		currentNode = null;
	}

	Iterator(Node<T> node){
		this();
		if(node!=null){
			initialNode = node;
			currentNode = initialNode;
		}
	}
	
	 /* Obtiene el siguiente elemento de la iteración. 	     	 *
	  * @Pre: hasNext ();                                        *
	  * @return el siguiente elemento de la iteración,			 */
	public T getNext() {
		T elem = currentNode.getValue(); 
		currentNode = currentNode.getNext();
		return elem;
	}
	/* Comprueba si aún quedan elementos por iterar.	    	 *
	  * @return true sii el iterador dispone de más elementos.   */
	public boolean hasNext() {
		return currentNode!=null;
	}
	 /* Vuelve la posición del iterador al principio. Esto       *
	  * permite reutilizar un iterador evitando crear otro nuevo.*/
	public void reset() {
		currentNode = initialNode;
	}
}
