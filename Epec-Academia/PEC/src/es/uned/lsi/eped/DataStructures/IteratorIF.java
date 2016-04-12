package es.uned.lsi.eped.DataStructures;

 /* Representa un iterador sobre un Tipo Abstracto de Datos. */
 public interface IteratorIF<T> {

 /* Obtiene el siguiente elemento de la iteración. 	     	 *
  * @Pre: hasNext ();                                        *
  * @return el siguiente elemento de la iteración,			 */
  public T getNext ();
    
 /* Comprueba si aún quedan elementos por iterar.	    	 *
  * @return true sii el iterador dispone de más elementos.   */
  public boolean hasNext ();
    
 /* Vuelve la posición del iterador al principio. Esto       *
  * permite reutilizar un iterador evitando crear otro nuevo.*/
  public void reset ();
}
