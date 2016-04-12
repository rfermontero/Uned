package es.uned.lsi.eped.DataStructures;

public class BTreeIterator<T> extends Iterator<T> {

	private Node<T> pathNode;	//nodo auxiliar para construir el recorrido

	//constructor por defecto
	BTreeIterator(){
		super();
	}
	//constructor por par�metro: �rbol binario y tipo de recorrido
	BTreeIterator(BTreeIF<T> bT, int traversal){
		super();
		switch (traversal) {
		case BTreeIF.PREORDER:
			preorder(bT);
			break;
		case BTreeIF.INORDER:
			inorder(bT);
			break;
		case BTreeIF.POSTORDER:
			postorder(bT);
			break;
		case BTreeIF.BREADTH:
			breadthLR(bT);
			break;
		case BTreeIF.RLBREADTH:
			breadthRL(bT);
			break;
		}
	}

	public void preorder(BTreeIF<T> bT){
		//recorrido: ra�z - hijo izq. - hijo dcho.
		if(!bT.isEmpty()){
			//visitar raiz
			if(initialNode==null){
				initialNode = new Node<T>(bT.getRoot());
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(bT.getRoot()));
				pathNode=pathNode.getNext();
			}
			//recorrer hijo izquierdo
			BTreeIF<T> left = bT.getLeftChild();
			preorder(left);
			//recorrer hijo derecho
			BTreeIF<T> right = bT.getRightChild();
			preorder(right);
		}
		currentNode = initialNode;
	}

	
	public void postorder(BTreeIF<T> bT){
		//recorrido: hijo izq. - hijo dcho. - raíz
		if(!bT.isEmpty()){
			//recorrer hijo izq.
			BTreeIF<T> left = bT.getLeftChild();
			postorder(left);	
			//recorrer hijo dcho.
			BTreeIF<T> right = bT.getRightChild();
			postorder(right);
			//visitar raíz
			if(initialNode==null){
				initialNode = new Node<T>(bT.getRoot());
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(bT.getRoot()));
				pathNode=pathNode.getNext();
			}
		}
		currentNode = initialNode;
	}
	

	public void breadthLR(BTreeIF<T> bT){
		//recorrido en profundida de izquierda a derecha
		QueueIF<BTreeIF<T>> queue = new Queue<BTreeIF<T>>();
		queue.enqueue(bT);
		while(!queue.isEmpty()){
			BTreeIF<T> currentBTree = queue.getFirst();
			//visitar raiz
			T root = currentBTree.getRoot();
			if(initialNode==null){
				initialNode = new Node<T>(root);
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(root));
				pathNode=pathNode.getNext();
			}
			//visitar hijo izquierdo en primer lugar (LR)
			BTreeIF<T> left = currentBTree.getLeftChild();
			if(!left.isEmpty()){
				queue.enqueue(left);
			}
			//visitar hijo derecho en segundo lugar (LR)
			BTreeIF<T> right = currentBTree.getRightChild();
			if(!right.isEmpty()){
				queue.enqueue(right);
			}
			queue.dequeue();
		}
		currentNode = initialNode;
	}
	
	public void inorder(BTreeIF<T> bT){
		//recorrido: hijo izq. - ra�z - hijo dcho.
		if(!bT.isEmpty()){
			//visitar hijo izquierdo
			BTreeIF<T> left = bT.getLeftChild();
			inorder(left);			
			//visitar raiz
			if(initialNode==null){
				initialNode = new Node<T>(bT.getRoot());
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(bT.getRoot()));
				pathNode=pathNode.getNext();
			}
			//visitar hijo derecho
			BTreeIF<T> right = bT.getRightChild();
			inorder(right);
		}
		currentNode = initialNode;
	}
	
	public void breadthRL(BTreeIF<T> bT){
		//recorrido en profundidad de derecha a izquierda
		QueueIF<BTreeIF<T>> queue = new Queue<BTreeIF<T>>();
		queue.enqueue(bT);
		while(!queue.isEmpty()){
			BTreeIF<T> currentBTree = queue.getFirst();
			//visitar raiz
			T root = currentBTree.getRoot();
			if(initialNode==null){
				initialNode = new Node<T>(root);
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(root));
				pathNode=pathNode.getNext();
			}
			//visitar hijo derecho en primer lugar (RL)
			BTreeIF<T> right = currentBTree.getRightChild();
			if(!right.isEmpty()){
				queue.enqueue(right);
			}
			//visitar hijo izquierdo en segundo lugar (RL)
			BTreeIF<T> left = currentBTree.getLeftChild();
			if(!left.isEmpty()){
				queue.enqueue(left);
			}
			queue.dequeue();
		}
		currentNode = initialNode;
	}
	

	
	public static void main(String [] args) throws Exception{
		/*
		Ejemplo extraido de Wikipedia:
		Link: https://es.wikipedia.org/wiki/Recorrido_de_%C3%A1rboles
		En este enlace aparecen algunos recorridos de este �rbol binario
		*/
		BTree<String> btree = new BTree<String>("F");
		//construcci�n hijo izquierdo
		BTree<String> izq = new BTree<String>("B");
		BTree<String> izqizq = new BTree<String>("A");
		izq.setLeftChild(izqizq);
		BTree<String> izqder = new BTree<String>("D");
		BTree<String> izqderizq = new BTree<String>("C");
		izqder.setLeftChild(izqderizq);
		BTree<String> izqderder = new BTree<String>("E");
		izqder.setRightChild(izqderder);
		izq.setRightChild(izqder);
		btree.setLeftChild(izq);
		//construcci�n hijo derecho
		BTree<String> der = new BTree<String>("G");
		BTree<String> derder = new BTree<String>("I");
		BTree<String> derderizq = new BTree<String>("H");
		derder.setLeftChild(derderizq);
		der.setRightChild(derder);
		btree.setRightChild(der);
		
		/*RECORRIDOS*/
		
		//recorrido en preorden
		System.out.print("Preorden: ");
		IteratorIF<String> iterPreorder = new BTreeIterator<String>(btree,0);
		while(iterPreorder.hasNext()){
			System.out.print(iterPreorder.getNext()+" ");
		}
		System.out.println();
		//recorrido en postorden
		System.out.print("Postorden: ");
		IteratorIF<String> iterPostorder = new BTreeIterator<String>(btree,1);
		while(iterPostorder.hasNext()){
			System.out.print(iterPostorder.getNext()+" ");
		}
		System.out.println();
		//recorrido en profundidad LR
		System.out.print("Breadth LR: ");
		IteratorIF<String> iterBreadthLR = new BTreeIterator<String>(btree,2);
		while(iterBreadthLR.hasNext()){
			System.out.print(iterBreadthLR.getNext()+" ");
		}
		System.out.println();		
		//recorrido en inorden
		System.out.print("Inorden: ");
		IteratorIF<String> iterInorden = new BTreeIterator<String>(btree,3);
		while(iterInorden.hasNext()){
			System.out.print(iterInorden.getNext()+" ");
		}
		System.out.println();
		//recorrido en profundidad RL
		System.out.print("Breadth RL: ");
		IteratorIF<String> iterBreadthRL = new BTreeIterator<String>(btree,4);
		while(iterBreadthRL.hasNext()){
			System.out.print(iterBreadthRL.getNext()+" ");
		}
		System.out.println();		
	}
}
