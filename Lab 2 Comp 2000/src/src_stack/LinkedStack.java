package src_stack;


public class LinkedStack<T> implements StackInterface<T> {
	
	private Node<T> firstNode;
	
	/**
	 * Initializes firstNode as null
	 */
	public LinkedStack() {
		firstNode = null;
	}
	
	/**
	 * Initializes Stack and creates first Node
	 * @param anEntry
	 */
	public LinkedStack(T anEntry) {
		this();
		push(anEntry);
	}
	
	/**
	 * Initializes Stack with input node as firstNode
	 * @param entry
	 */
	public LinkedStack(Node entry) {
		firstNode = entry;
	}

	/**
	 * If stack is empty, sets first node as a node with input data. Else, creates a node with entered data,
	 * makes original head as the node second to the new node, makes new node the firstNode.
	 */
	@Override
	public void push(T anEntry) {
		if (firstNode != null) {
			firstNode = new Node(anEntry, firstNode);
		} else {
			firstNode = new Node(anEntry);
		}
	}

	/**
	 * Removes first node and sets next node as firstnode, then returns removed node's data. If empty, throws EmptyStackException with desc
	 */
	@Override
	public T pop() {
		T temp;
		if (firstNode != null) {
			temp = firstNode.getData();
			firstNode = firstNode.getNext();
			return temp;
		} else {
			throw new EmptyStackException(String.format("Failed to pop; Stack Empty!%n"));
		}
	}

	/**
	 * Returns first node's data if not empty; else throws EmptyStackException with desc
	 */
	@Override
	public T peek() {
		if (firstNode != null) {
			return firstNode.getData();
		} else {
			throw new EmptyStackException(String.format("Failed to peek; Stack Empty!%n"));
		}
	}

	/**
	 * Returns true if no nodes exist (empty); false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (firstNode == null) {
			return true;
		}
		return false;
	}

	/**
	 * Pops until Stack is empty
	 */
	@Override
	public void clear() {
		while (!isEmpty()) {
			pop();
		}
		
	}

}
