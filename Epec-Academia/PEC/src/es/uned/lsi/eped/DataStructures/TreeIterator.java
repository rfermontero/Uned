package es.uned.lsi.eped.DataStructures;

public class TreeIterator<T> extends Iterator<T>{
	private Node<T> pathNode;
	TreeIterator(){
		super();
	}
	TreeIterator(TreeIF<T> tree, int traversal){
		super();
		pathNode = null;
		switch (traversal) {
		case TreeIF.PREORDER:
			preorder(tree);
			break;
		case TreeIF.POSTORDER:
			postorder(tree);
			break;
		case TreeIF.BREADTH:
			breadthLR(tree);
			break;
			
		}
		
	}
	
	public void preorder(TreeIF<T> tree){
		if(!tree.isEmpty()){
			if(initialNode==null){
				initialNode = new Node<T>(tree.getRoot());
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(tree.getRoot()));
				pathNode=pathNode.getNext();
			}
			ListIF<TreeIF<T>> children = tree.getChildren();
			IteratorIF<TreeIF<T>> iteratorChildren =  children.iterator();
			while(iteratorChildren.hasNext()){
				TreeIF<T> child = iteratorChildren.getNext();
				if(child!=null){
					preorder(child);
				}
			}
		}
		currentNode = initialNode;
	}
	
	public void postorder(TreeIF<T> tree){
		if(!tree.isEmpty()){
			ListIF<TreeIF<T>> children = tree.getChildren();
			IteratorIF<TreeIF<T>> iteratorChildren =  children.iterator();
			while(iteratorChildren.hasNext()){
				TreeIF<T> child = iteratorChildren.getNext();
				if(child!=null){
					postorder(child);
				}
			}
			if(initialNode==null){
				initialNode = new Node<T>(tree.getRoot());
				pathNode = initialNode;
			}
			else{
				pathNode.setNext(new Node<T>(tree.getRoot()));
				pathNode=pathNode.getNext();
			}
		}
		currentNode = initialNode;
	}
	public void breadthLR(TreeIF<T> tree){
		if(!tree.isEmpty()){
			QueueIF<TreeIF<T>> queue = new Queue<TreeIF<T>>();
			queue.enqueue(tree);
			while(!queue.isEmpty()){
				TreeIF<T> currentTree = queue.getFirst();
				//visitar raiz
				T root = currentTree.getRoot();
				if(initialNode==null){
					initialNode = new Node<T>(root);
					pathNode = initialNode;
				}
				else{
					pathNode.setNext(new Node<T>(root));
					pathNode=pathNode.getNext();
				}
				IteratorIF<TreeIF<T>> iteratorChildren =  currentTree.getChildren().iterator();
				while(iteratorChildren.hasNext()){
					TreeIF<T> child = iteratorChildren.getNext();
					if(child!=null){
						queue.enqueue(child);
					}
				}
				queue.dequeue();
			}
		}
		currentNode = initialNode;
	}
	public static void main(String [] args) throws Exception{
		TreeIF<Integer> tree = new Tree<Integer>(1);
		//hijo 1
		TreeIF<Integer> tree1 = new Tree<Integer>(2);
		TreeIF<Integer> tree1tree1 = new Tree<Integer>(5);
		tree1.addChild(1, tree1tree1);
		TreeIF<Integer> tree1tree2 = new Tree<Integer>(6);
		tree1.addChild(2, tree1tree2);
		tree.addChild(1,tree1);
		//hijo 2
		TreeIF<Integer> tree2 = new Tree<Integer>(3);
		tree.addChild(2,tree2);
		//hijo 3
		TreeIF<Integer> tree3 = new Tree<Integer>(4);
		TreeIF<Integer> tree3tree1 = new Tree<Integer>(7);
		tree3.addChild(1,tree3tree1);
		tree.addChild(3,tree3);

		
		//recorrido en preorden
		System.out.print("Preorden: ");
		IteratorIF<Integer> iterPreorder = new TreeIterator<Integer>(tree,0);
		while(iterPreorder.hasNext()){
			System.out.print(iterPreorder.getNext()+" ");
		}
		System.out.println();
		//recorrido en postorden		
		System.out.print("Postorden: ");
		IteratorIF<Integer> iterPostorder = new TreeIterator<Integer>(tree,1);
		while(iterPostorder.hasNext()){
			System.out.print(iterPostorder.getNext()+" ");
		}
		System.out.println();
		//recorrido en profundidad		
		System.out.print("Breadth: ");
		IteratorIF<Integer> iterBreadth = new TreeIterator<Integer>(tree,2);
		while(iterBreadth.hasNext()){
			System.out.print(iterBreadth.getNext()+" ");
		}
		System.out.println();
	}

}
