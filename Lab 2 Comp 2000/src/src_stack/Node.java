package src_stack;


public class Node<T> {
	private T data;
	@SuppressWarnings("rawtypes")
	private Node next;
	
	@SuppressWarnings("rawtypes")
	public Node (T data, Node nextNode) {
		this.data = data;
		next = nextNode;
	}
	
	public Node (T data) {
		this(data, null);
	}
	
	public T getData() {
		return data;
	}
	
	public void setData (T newData) {
		data = newData;
	}
	
	@SuppressWarnings("rawtypes")
	public Node getNext () {
		return next;
	}
	
	@SuppressWarnings("rawtypes")
	public void setNext (Node nextNode) {
		next = nextNode;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Node copyData() {
		return new Node (data);
	}
}
