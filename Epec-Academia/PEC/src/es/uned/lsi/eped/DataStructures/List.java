package es.uned.lsi.eped.DataStructures;

public class List<T> extends Collection<T> implements ListIF<T> {

	/*constructor por defecto: crea una lista vacía*/
	List(){
		super();
	}
	//constructor por copia
	List(ListIF<T> L){
		super();
		IteratorIF<T> iterator = L.iterator();
		while (iterator.hasNext()){
			T e = iterator.getNext();
			this.insert(e, size+1);
		}
	}
	//número de elementos de la lista
	public int size() {
		return super.size();
	}
    //decide si la lista es vacía
	public boolean isEmpty() {
		return super.isEmpty();
	}
    //decide si la lista contiene el elemento dado por parámetro
	public boolean contains(T e) {
		return super.contains(e);
	}
   //borra todos los elementos de la lista
	public void clear() {
		super.clear();
	}
  //devuelve un iterador para la lista llamante
	public IteratorIF<T> iterator() {
		return super.iterator();
	}
	/* Devuelve el elemento de la lista que ocupa la posición   *
	 * indicada por el parámetro.								*	
	 * @param pos la posición comenzando en 1.					*	
	 * @Pre: 1 <= pos <= size().								*
	 * @return el elemento en la posición pos.					*/
	public T get(int pos) {
		if(1<=pos && pos<=size()){
			Node<T> node = firstNode.getNode(pos);
			return node.getValue();
		}
		return null;
	}
	/* Modifica la posición dada por el parámetro pos para que  *
	 * contenga el valor dado por el parámetro e.				*
	 * @param pos la posición cuyo valor se debe modificar,		*
	 *  comenzando en 1.                                        *
	 * @param e el valor que debe adoptar la posición pos.		*
	 * @Pre: 1 <= pos <= size().								*/
	public void set(int pos, T e) {
		if(1<=pos && pos<=size()){
			Node<T> node = firstNode.getNode(pos);
			node.setValue(e);
		}
	}
	/* Inserta un elemento en la Lista.  			            *
   	 * @param elem El elemento que hay que añadir.				*				
   	 * @param pos  La posición en la que se debe añadir elem,   *
   	 *  comenzando en 1.      	                                *
 	 * @Pre: 1 <= pos <= size()+1									*/
	public void insert(T elem, int pos) {
		if(1<=pos && pos<=this.size()+1){
				if(pos==1){
					Node<T> newNode = new Node<T>(elem);
					newNode.setNext(firstNode);
					firstNode = newNode;
				}
				else{
					Node<T> newNode = new Node<T>(elem);
					Node<T> previousNode = firstNode.getNode(pos-1);
					Node<T> nextNode = firstNode.getNode(pos);
					previousNode.setNext(newNode);
					if(pos<size()+1){
						newNode.setNext(nextNode);
					}
				}
				size++;
			}
	}

	 /* Elimina el elemento que ocupa la posición del parámetro 	*	 
     * @param pos la posición que ocupa el elemento a eliminar,	*
     *  comenzando en 1                                         *
	 * @Pre: 1 <= pos <= size()									*/
	public void remove(int pos) {
		if(1<=pos && pos<=size){
			if(pos==1){
				firstNode = firstNode.getNext();
			}
			else{
				Node<T> previousNode = firstNode.getNode(pos-1);
				Node<T> nextNode = firstNode.getNode(pos+1);
				previousNode.setNext(nextNode);
			}
			size--;
		}

	}
	
	  public static void main(String [] args) throws Exception{
		  List<Integer> L = new List<Integer>();
		  L.insert(1, 1);
		  L.insert(2, 2);
		  L.insert(3, 3);
		  L.insert(4, 4);
		  L.insert(5, 5);
		  L.insert(6, 6);
		  L.set(5, 10);
		  L.insert(10,1);
		  L.insert(11,2);
		  L.insert(19,9);
		  System.out.println("Recorrido de la lista sin iterador:");
		  Node<Integer> n = L.firstNode;
		  while(n!=null){
			  System.out.print(n.getValue()+" ");
			  n = n.getNext();
		  }
		  System.out.println();
		  System.out.println("Recorrido copia de la lista mediante iterador:");
		  ListIF<Integer> copyList = new List<Integer>(L);
		  IteratorIF<Integer> iteratorCopy = copyList.iterator();
		  while(iteratorCopy.hasNext()){
			  System.out.print(iteratorCopy.getNext()+" ");
		  }
		  System.out.println();		  
		  System.out.println("Elemento en la posición 2: "+L.get(2));		  
		  System.out.println("Tamaño de la lista: "+L.size());	
		  System.out.println("Eliminar elemento en la posición 9: ");			  
		  L.remove(9);
		  n = L.firstNode;
		  while(n!=null){
			  System.out.print(n.getValue()+" ");
			  n = n.getNext();
		  }
		  System.out.println();		  
		  System.out.println("Eliminar elemento en la posición 1: ");			  
		  L.remove(1);
		  n = L.firstNode;
		  while(n!=null){
			  System.out.print(n.getValue()+" ");
			  n = n.getNext();
		  }
		  System.out.println();		  
		  System.out.println("Eliminar elemento en la posición 4: ");			  
		  L.remove(4);
		  n = L.firstNode;
		  while(n!=null){
			  System.out.print(n.getValue()+" ");
			  n = n.getNext();
		  }
		  System.out.println();		  
		  System.out.println("La lista contiene el elemento 2? "+L.contains(2));
		  System.out.println("La lista contiene el elemento 0? "+L.contains(0));		  
		  System.out.println("Recorrer la lista mediante iterador: ");		  		  
		  IteratorIF<Integer> iterator2 = L.iterator();
		  while(iterator2.hasNext()){
			  System.out.print(iterator2.getNext()+" ");
		  }
		System.out.println();		  
		System.out.println("Aplicar clear");
		L.clear();
		System.out.println("isEmpty? "+L.isEmpty());
		iterator2 = L.iterator();
		while(iterator2.hasNext()){
			System.out.print(iterator2.getNext()+" ");
		}
	 }
}
