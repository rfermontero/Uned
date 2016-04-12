package es.uned.lsi.eped.DataStructures;

public class Node<T> {

	private T value;
	private Node<T> next;
	
	Node(){
		value = null;
		next = null;
	}
	
	Node(T e){
		value = e;
		next = null;
	}
	
	public T getValue(){
		return value;
	}
	public void setValue(T e){
		value = e;
	}
	public Node<T> getNext(){
		return next;
	}
	public void setNext(Node<T> n){
		next = n;
	}
	
	
	public Node<T> getNode(int pos){
		if(pos<1){
			return null;
		}
		if(pos==1){
			return this;
		}
		Node<T> node = getNext();
		if(node == null){
			return node;
		}
		return node.getNode(pos-1);
	}
	
	
}
