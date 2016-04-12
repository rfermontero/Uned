package es.uned.lsi.eped.DataStructures;

/* Representa una cola de elementos. Una cola es una			* 
 * especialización de una lista, que mantiene el orden de       * 
 * almacenamiento de sus elementos y una política de acceso 	*
 * First In First Out (FIFO)                                    */
public interface QueueIF <E> extends CollectionIF<E>{

	/* @Pre !isEmpty()											*
	 * @return la cabeza de la cola (su primer elemento).		*/
     public E getFirst ();

    /* Incluye un elemento al final de la cola. Modifica el 	*
     * tamaño de la misma.										*
     * @param elem el elemento que debe encolar (añadir).		*/
     public void enqueue (E elem);
    
    /* Elimina el primer elemento de la cola. Modifica la 		*
     * tamaño de la misma.										*
     * @Pre !isEmpty();											*/
     public void dequeue ();        
}
