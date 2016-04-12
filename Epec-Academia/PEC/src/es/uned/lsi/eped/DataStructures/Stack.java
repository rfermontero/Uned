package es.uned.lsi.eped.DataStructures;

public class Stack<T> extends Collection<T> implements StackIF<T> {

	//constructor por defecto: crea una pila vacía
	Stack(){
		super();
	}
	//constructor por copia
	Stack(Stack<T> S){
		super();
		//se requiere una pila auxiliar que contendrá los elementos
		//de la pila dada por parámetro en el orden inverso
		StackIF<T> stack = new Stack<T>();
		IteratorIF<T> iterator = S.iterator();
		while (iterator.hasNext()){
			T e = iterator.getNext();
			stack.push(e);
		}
		//la copia de la pila dada por parámetro se obtiene invertiendo nuevamente
		//el orden de inserción de los elementos de la pila original
		IteratorIF<T> iteratorStack = stack.iterator();
		while (iteratorStack.hasNext()){
			T e = iteratorStack.getNext();
			push(e);
		}
	}
	//devuelve el número de elementos de la pila llamante
	public int size() {
		return super.size();
	}
   //decide si la pila llamante es vacía
	public boolean isEmpty() {
		return super.isEmpty();
	}

	public boolean contains(T e) {
		return super.contains(e);
	}

	public void clear() {
		super.clear();
	}

	public IteratorIF<T> iterator() {
		return super.iterator();
	}
	public T getTop() {
		return firstNode.getValue();
	}

	public void push(T elem) {
		if(isEmpty()){
			Node<T> newNode = new Node<T>(elem);
			firstNode = newNode;
		}
		else{
			Node<T> newNode = new Node<T>(elem);
			newNode.setNext(firstNode);
			firstNode = newNode;
		}
		size++;
	}

	public void pop() {
		if(!isEmpty()){
			firstNode = firstNode.getNext();
			size--;
		}
	}
	
	public static void main(String [] args) throws Exception{
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println("Recorrido mediante iterador:");		
		IteratorIF<Integer> iterator = stack.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.getNext()+" ");
		}
		System.out.println();
		System.out.println("Tamaño pila:"+stack.size());		
		System.out.println("contains(3):"+stack.contains(3));
		System.out.println("contains(9):"+stack.contains(9));		
		System.out.println("Recorrido copia de la pila mediante iterador:");		
		StackIF<Integer> copyStack = new Stack<Integer>(stack);
		IteratorIF<Integer> iteratorCopy = copyStack.iterator();
		while(iteratorCopy.hasNext()){
			System.out.print(iteratorCopy.getNext()+" ");
		}
		System.out.println();
		System.out.println("contains(3)? "+stack.contains(3));
		System.out.println("contains(7)? "+stack.contains(7));
		System.out.println("Aplicar getTop y pop:");		
		System.out.println("Cima: "+stack.getTop());
		stack.pop();
		System.out.println("Cima: "+stack.getTop());
		stack.pop();
		System.out.println("Cima: "+stack.getTop());
		stack.pop();
		System.out.println("Cima: "+stack.getTop());
		stack.pop();
		System.out.println("Cima: "+stack.getTop());
		stack.pop();
		System.out.println("isEmpty? "+stack.isEmpty());
		
		
	}

}
