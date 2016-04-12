package es.uned.lsi.eped.DataStructures;

public class Queue<T> extends Collection<T> implements QueueIF<T>  {

	//constructor por defecto: crea una cola vacía
	Queue(){
		super();
	}
	//constructor por copia
	Queue(Queue<T> Q){
		super();
		IteratorIF<T> iterator = Q.iterator();
		while (iterator.hasNext()){
			T e = iterator.getNext();
			enqueue(e);
		}
	}
	//devuelve el número de elementos de la cola
	public int size() {
		return super.size();
	}
    //decide si la cola llamante está vacía
	public boolean isEmpty() {
		return super.isEmpty();
	}
    //decide si la cola llamante contiene el elemento dado por parámetro
	public boolean contains(T e) {
		return super.contains(e);
	}
  //borra todos los elementos de la cola
	public void clear() {
		super.clear();
	}
   //devuelve un iterador para la cola llamante
	public IteratorIF<T> iterator() {
		return super.iterator();
	}
	/* @Pre !isEmpty()											*
	 * @return la cabeza de la cola (su primer elemento).		*/
	public T getFirst() {
		if(!isEmpty()){
			return firstNode.getValue();
		}
		return null;
	}

	/* Incluye un elemento al final de la cola. Modifica el 	*
     * tamaño de la misma.										*
     * @param elem el elemento que debe encolar (añadir).		*/
	public void enqueue(T elem) {
		if(isEmpty()){
			Node<T> newNode = new Node<T>(elem);
			firstNode = newNode;
		}
		else{
			Node<T> lastNode = firstNode.getNode(size());
			Node<T> newNode = new Node<T>(elem);
			lastNode.setNext(newNode);
		}
		size++;
	}
	 /* Elimina el primer elemento de la cola. Modifica la 		*
     * tamaño de la misma.										*
     * @Pre !isEmpty();											*/
	public void dequeue() {
		if(!isEmpty()){
			firstNode = firstNode.getNext();
			size--;
		}
	}
	
	public static void main(String [] args) throws Exception{
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		System.out.println("Recorrido mediante iterador:");
		IteratorIF<Integer> iterator = queue.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.getNext()+" ");
		}
		System.out.println();
		System.out.println("Tamaño de la cola: "+queue.size);
		System.out.println("contains(3)?: "+queue.contains(3));
		System.out.println("contains(9)?: "+queue.contains(9));
		System.out.println("Recorrido copia de la cola mediante iterador:");
		QueueIF<Integer> copyQueue = new Queue<Integer>(queue);
		IteratorIF<Integer> iteratorCopy = copyQueue.iterator();
		while(iteratorCopy.hasNext()){
			System.out.print(iteratorCopy.getNext()+" ");
		}
		System.out.println();
		System.out.println("Aplicar getFirst y dequeue:");
		System.out.println(queue.getFirst());
		queue.dequeue();
		System.out.println(queue.getFirst());
		queue.dequeue();
		System.out.println(queue.getFirst());
		queue.dequeue();
		System.out.println(queue.getFirst());
		queue.dequeue();
		System.out.println(queue.getFirst());
		queue.dequeue();
		System.out.println("isEmpty?: "+queue.isEmpty());

	}

}
