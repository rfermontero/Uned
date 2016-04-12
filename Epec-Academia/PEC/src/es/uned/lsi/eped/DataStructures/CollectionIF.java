package es.uned.lsi.eped.DataStructures;

/* Representa una colección de elementos. Una colección no 		*
 * tiene orden.													*/
public interface CollectionIF<T> {

/* Los siguientes métodos se eliminan de la interfaz para poder *
 * nombrarlos en cada subinterfaz como corresponde al TAD: add, *
 * remove, get, [set].                              		    */ 

	/* Devuelve el número de elementos de la colección.			*/
	public int size ();
	
	/* Devuelve true sii la colección no contiene elementos.	*/	
	public boolean isEmpty ();
	
	/* Devuelve true sii e está en la colección.				*/
	public boolean contains (T e);
	
	/* Elimina todos los elementos de la colección.				*/
	public void clear ();
	
	/* Devuelve un iterador sobre la colección.					*/
	public IteratorIF<T> iterator ();
}
