package es.uned.lsi.eped.DataStructures;

/* Representa un conjunto de elementos. Se trata del concepto	*
 * matemático de conjunto finito (no tiene orden). 				*/
public interface SetIF<T> extends CollectionIF<T> {
	
	/* Devuelve la unión del conjunto llamante con el parámetro */
	public SetIF<T> union (SetIF<T> s);
	
	/* Devuelve la intersección con el parámetro				*/
	public SetIF<T> intersection (SetIF<T> s);
	
	/* Devuelve la diferencia con el parámetro (los elementos	* 
	 * que 	están en el llamante pero no en el parámetro		*/
	public SetIF<T> difference (SetIF<T> s);
	
	/* Devuelve cierto sii el parámetro es un subconjunto del 	*
	 * llamante 												*/
	public boolean isSubset (SetIF<T> s);
}
