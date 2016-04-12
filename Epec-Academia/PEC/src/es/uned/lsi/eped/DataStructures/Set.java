package es.uned.lsi.eped.DataStructures;

public class Set<T> extends Collection<T> implements SetIF<T> {
	//constructor por defecto: crea conjunto vacío
	public Set(){
		super();
	}
	//constructor por parámetro: crea conjunto unitario con elemento e
	public Set(T e){
		super(e);
	}
	//devuelve el número de elementos del conjunto, i.e., el cardinal del conjunto
	public int size() {
		return super.size();
	}
    //decide si el conjunto llamante es el conjunto vacío
	public boolean isEmpty() {
		return super.isEmpty();
	}
    //decide si el elemento dado por parámetro pertenece al conjunto llamante
	public boolean contains(T e) {
		return super.contains(e);
	}
   //borra todos los elementos del conjunto
	public void clear() {
		super.clear();
	}
   //devuelve un iterador para el conjunto
	public IteratorIF<T> iterator() {
		return super.iterator();
	}
	
	/* Devuelve la unión del conjunto llamante con el parámetro */
	public SetIF<T> union(SetIF<T> s) {
		Set<T> union = new Set<T>();
		//añadir todos los elementos del conjunto llamante
		IteratorIF<T> iterator = iterator();
		while(iterator.hasNext()){
			T elem = iterator.getNext();
			if(!union.contains(elem)){
				if(union.isEmpty()){
					union = new Set<T>(elem);
				}
				else{
					int sUnion = union.size();
					Node<T> lastNode = union.firstNode.getNode(sUnion);
					Node<T> newNode = new Node<T>(elem);
					lastNode.setNext(newNode);
					union.size++;
				}
			}
		}
		/*añadir todos los elementos del conjunto parámetro s
		  evitando repeticiones*/
		IteratorIF<T> iteratorS = s.iterator();
		while(iteratorS.hasNext()){
			T elem = iteratorS.getNext();
			if(!union.contains(elem)){
				if(union.isEmpty()){
					union = new Set<T>(elem);
				}
				else{
					int sUnion = union.size();
					Node<T> lastNode = union.firstNode.getNode(sUnion);
					Node<T> newNode = new Node<T>(elem);
					lastNode.setNext(newNode);
					union.size++;
				}
			}
		}
		return union;
	}
	/* Devuelve la intersección con el parámetro				*/
	public SetIF<T> intersection(SetIF<T> s) {
		Set<T> intersection = new Set<T>();
		IteratorIF<T> iterator = iterator();
		while(iterator.hasNext()){
			T elem = iterator.getNext();
			//añadir elementos que pertenecen a los conjuntos llamante y parámetro
			if(s.contains(elem)){
				if(intersection.isEmpty()){
					intersection = new Set<T>(elem);
				}
				else{
					int sIntersection = intersection.size();
					Node<T> lastNode = intersection.firstNode.getNode(sIntersection);
					Node<T> newNode = new Node<T>(elem);
					lastNode.setNext(newNode);
					intersection.size++;
				}
			}
		}
		return intersection;
	}
	/* Devuelve la diferencia con el parámetro (los elementos	* 
	 * que 	están en el llamante pero no en el parámetro		*/
	public SetIF<T> difference(SetIF<T> s) {
		Set<T> difference = new Set<T>();
		IteratorIF<T> iterator = iterator();
		while(iterator.hasNext()){
			T elem = iterator.getNext();
			/*añadir elementos que pertenecen al conjunto llamante 
			  pero no pertenecen al conjunto parámetro*/
			if(!s.contains(elem)){
				if(difference.isEmpty()){
					difference = new Set<T>(elem);
				}
				else{
					int sDifference = difference.size();
					Node<T> lastNode = difference.firstNode.getNode(sDifference);
					Node<T> newNode = new Node<T>(elem);
					lastNode.setNext(newNode);
					difference.size++;
				}
			}
		}
		return difference;
	}
	/* Devuelve cierto sii el parámetro es un subconjunto del 	*
	 * llamante 												*/
	public boolean isSubset(SetIF<T> s) {
		/*s no es subconjunto del conjunto llamante si 
		  contiene algún elemento que no pertenece a este último*/
		IteratorIF<T> iteratorS = s.iterator();
		while(iteratorS.hasNext()){
			T elem = iteratorS.getNext();
			if(!contains(elem)){
				return false;
			}
		}
		return true;
	}
	

	
	public static void main(String [] args) throws Exception{
		SetIF<String> emptySet = new Set<String>();
		System.out.println("Cjto. Vacío");
		System.out.println("isEmpty?: "+emptySet.isEmpty());
		IteratorIF<String> iEmpty = emptySet.iterator();
		System.out.println("Recorrido Cjto. Vacío: ");
		while(iEmpty.hasNext()){
			String e = iEmpty.getNext();
			System.out.print(e+" ");
		}
		System.out.println();
		
		/*los constructores de los conjuntos son: 
		 * (i) Conjunto vacío
		 * (ii) Conjunto unitario
		Se crean conjuntos no-unitarios mediante la operación de unión de conjuntos
		*/
		SetIF<String> SA = new Set<String>("A");
		SetIF<String> SB = new Set<String>("B");
		SetIF<String> SC = new Set<String>("C");
		SetIF<String> SD = new Set<String>("D");
		SetIF<String> SE = new Set<String>("E");
		SetIF<String> SF = new Set<String>("F");
		SetIF<String> SG = new Set<String>("G");

		SetIF<String> SAB = SA.union(SB);
		SetIF<String> SABC = SAB.union(SC);
		SetIF<String> SABCD = SABC.union(SD);
		System.out.println("Cjto. 1: C1 = {A,B,C,D} ");
		IteratorIF<String> i1 = SABCD.iterator();
		while(i1.hasNext()){
			String e = i1.getNext();
			System.out.print(e+" ");
		}
		System.out.println();
		System.out.println("isEmpty Cjto. 1?: "+SABCD.isEmpty());
		SetIF<String> SAD = SA.union(SD);
		SetIF<String> SADE = SAD.union(SE);
		SetIF<String> SADEF = SADE.union(SF);
		SetIF<String> SADEFG = SADEF.union(SG);
		System.out.println("Cjto. 2: C2 = {A,D,E,F,G} ");
		IteratorIF<String> i2 = SADEFG.iterator();
		while(i2.hasNext()){
			String e = i2.getNext();
			System.out.print(e+" ");
		}
		System.out.println();
		System.out.println("isEmpty Cjto. 2?: "+SADEFG.isEmpty());
		System.out.println("Union: U = C1 U C2 = {A,B,C,D,E,F,G} ");		
		SetIF<String> union = SABCD.union(SADEFG);
		IteratorIF<String> iUnion = union.iterator();
		while(iUnion.hasNext()){
			String e = iUnion.getNext();
			System.out.print(e+" ");
		}
		System.out.println();
		System.out.println("Interseccion: I = C1 ∩ C2 = {A,D}");		
		SetIF<String> intersection = SABCD.intersection(SADEFG);
		IteratorIF<String> iIntersection = intersection.iterator();
		while(iIntersection.hasNext()){
			String e = iIntersection.getNext();
			System.out.print(e+" ");
		}
		System.out.println();
		System.out.println("Diferencia: D = C1 \\ C2 = {B,C} ");		
		SetIF<String> difference = SABCD.difference(SADEFG);
		IteratorIF<String> iDifference = difference.iterator();
		while(iDifference.hasNext()){
			String e = iDifference.getNext();
			System.out.print(e+" ");
		}
		System.out.println();
		System.out.println("Cjto1.isSubset(Cjto2)?: "+SABCD.isSubset(SADEFG));
		System.out.println("ABCD.isSubset(C)?: "+SABCD.isSubset(SC));

	}

}
