package es.uned.lsi.eped.DataStructures;

/* Representa una lista de elementos. Una lista es una			*
 * colección que mantiene un orden lineal, es decir, una        * 
 * secuencia.                               					*/
public interface ListIF<T> extends CollectionIF<T>{
	
	/* Devuelve el elemento de la lista que ocupa la posición   *
	 * indicada por el parámetro.								*	
	 * @param pos la posición comenzando en 1.					*	
	 * @Pre: 1 <= pos <= size().								*
	 * @return el elemento en la posición pos.					*/
	public T get (int pos);
    
	/* Modifica la posición dada por el parámetro pos para que  *
	 * contenga el valor dado por el parámetro e.				*
	 * @param pos la posición cuyo valor se debe modificar,		*
	 *  comenzando en 1.                                        *
	 * @param e el valor que debe adoptar la posición pos.		*
	 * @Pre: 1 <= pos <= size().								*/
	public void set (int pos, T e);
	
   	/* Inserta un elemento en la Lista.  			            *
   	 * @param elem El elemento que hay que añadir.				*				
   	 * @param pos  La posición en la que se debe añadir elem,   *
   	 *  comenzando en 1.      	                                *
 	 * @Pre: 1 <= pos <= size()+1									*/
    public void insert (T elem, int pos);
    
    /* Elimina el elemento que ocupa la posición del parámetro 	*	 
     * @param pos la posición que ocupa el elemento a eliminar,	*
     *  comenzando en 1                                         *
	 * @Pre: 1 <= pos <= size()									*/
    public void remove (int pos);
}