package es.uned.lsi.eped.DataStructures;


//representa un iterador de una estructura lineal: colecciones, conjuntos
//listas, pilas, colas, ...
public class IteratorLinear<T> extends Iterator<T> {


	IteratorLinear(){
		super();
	}
	IteratorLinear(Node<T> node){
		super(node);
	}

	public static void main(String [] args) throws Exception{
		List<Integer> L = new List<Integer>();
		  
		  L.insert(1, 1);
		  L.insert(2, 2);
		  L.insert(3, 3);
		  L.insert(4, 4);
		  L.insert(5, 5);
		  L.insert(6, 6);
		  
		  IteratorIF<Integer> iterator = L.iterator();
		  System.out.println("Recorrido de la lista mediante el iterador");
		  while(iterator.hasNext()){
			  System.out.print(iterator.getNext()+" ");
		  }
		  System.out.println();
		  System.out.println("Se ha consumido el iterador y al volver a recorrerlo "
		  		+ "no aparecen m�s elementos");
		  while(iterator.hasNext()){
			  System.out.print(iterator.getNext()+" ");
		  }
		  System.out.println();
		  System.out.println("Se resetea el iterador para volver a recorrer la lista");
		  iterator.reset();
		  while(iterator.hasNext()){
			  System.out.print(iterator.getNext()+" ");
		  }
	}

}
