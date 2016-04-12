package es.uned.lsi.eped.DataStructures;

/* Representa un comparador entre elementos respecto a una   *
 * relaci�n de (al menos) preorden.                          */
public interface ComparatorIF<T>{
  /* Sean a, b elementos de un conjunto dado y < la relaci�n *
   * que establece un preorden entre ellos:                  */ 
  public static int LT = -1; /* Less than: a < b             */ 
  public static int EQ = 0;  /* Equals: !(a < b) && !(a > b) */
  public static int GT = 1;  /* Greater than: a > b          */
 
 /* Compara dos elementos respecto a un preorden e indica su *
  * relaci�n respecto al mismo, es decir, cu�l precede al    *
  * otro mediante esa relaci�n.                              *
  * @param a el primer elemento.                             *
  * @param b el segundo elemento.                            *
  * @return a LT b sii a < b;                                *
  *         a EQ b sii !(a < b) && !(a > b)                  *
  *         a GT b sii a > b                                 */
  public int compare (T a, T b);
    
 /* Determina si el primer par�metro precede en el preorden  *
  * al segundo (a < b).                                      * 
  * @param a el primer elemento.                             *
  * @param b el segundo elemento.                            *
  * @return a < b;                                           */
  public boolean lt (T a, T b);
    
  /* Determina si el primer par�metro es igual al segundo en *
   * el preorden.                                            *
   * @param a el primer elemento.                            *
   * @param b el segundo elemento.                           *
   * @return a EQ b sii !(a < b) && !(a > b)                 */
  public boolean eq (T a, T b);
    
  /* Determina si el primer par�metro sucede en el preorden  *
   * al segundo (b > a).                                     * 
   * @param a el primer elemento.                            *
   * @param b el segundo elemento.                           *
   * @return a GT b sii b > a                                */
  public boolean gt (T a, T b);
}