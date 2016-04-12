package es.uned.lsi.eped.DataStructures;

/* Representa un arbol binario de elementos, en el que un nodo	*
 * puede tener, a lo sumo, dos hijos (fan-out <= 2 para todos   *
 * los nodos).                                                  */
public interface BTreeIF<E> extends CollectionIF<E>{
  public int PREORDER  = 0;
  public int POSTORDER = 1;
  public int BREADTH = 2;
  public int INORDER   = 3;
  public int RLBREADTH = 4;
    
  /* Obtiene la raíz del árbol (único elemento sin antecesor).	*
   * @Pre: !isEmpty ();											*
   * @return el elemento que ocupa la raíz del árbol. 			*/
   public E getRoot ();
    
 /* Obtiene el hijo izquierdo del árbol llamante o un árbol 	*
  * vacío en caso de no existir.								*
  * @return un árbol, bien el hijo izquierdo bien uno vacío 	*
  * de no existir tal hijo										*/
  public BTreeIF<E> getLeftChild ();
    
  /* Obtiene el hijo derecho del árbol llamante o un árbol  	*
   * vacío en caso de no existir.								*
   * @return un árbol, bien el hijo derecho bien uno vacío  	*
   * de no existir tal hijo										*/
   public BTreeIF<E> getRightChild ();

  /* Modifica la raíz del árbol.								*
   * @param el elemento que se quiere poner como raíz del 		*
   * árbol.														*/ 	
   public void setRoot (E e);
    
 /* Pone el árbol parámetro como hijo izquierdo del árbol		*
  * llamante. Si ya había hijo izquierdo, el antiguo dejará de  *
  * ser accesible (se pierde).                                  *
  * @param child el árbol que se debe poner como hijo izquierdo.*/
  public void setLeftChild (BTreeIF <E> child);
    
  /* Pone el árbol parámetro como hijo derecho del árbol		*
   * llamante. Si ya había hijo izquierdo, el antiguo dejará de *
   * ser accesible (se pierde).                                 *													*
   * @param child el árbol que se debe poner como hijo derecho.	*/
   public void setRightChild (BTreeIF <E> child);

   /* Elimina el hijo izquierdo del árbol.						*/
   public void removeLeftChild ();
    
   /* Elimina el hijo derecho del árbol.						*/
   public void removeRightChild ();
    
   /* Determina si el árbol llamante es una hoja.				*
    * @return true sii el árbol es una hoja (no tiene hijos).	*/
    public boolean isLeaf (); 
    
    /* Obtiene un iterador para el árbol.						*
     * @param traversal el tipo de recorrido indicado por las   * 
     * constantes PREORDER (preorden o profundidad), POSTORDER  *
     * (postorden), BREADTH (anchura), INORDER (inorden) o 		*
     * RLBREADTH (anchura de derecha a izquierda).				*
     * @return un iterador segn el recorrido indicado.			*/
     public IteratorIF<E> iterator (int traversal);    
}