package src_stack;

import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
	private static final int DEFAULT_CAPACITY = 25;
	private T[] stackArray;
	private int numberOfEntries;
	private int capacity;
	
	/**
	 * Creates stack with given capacity
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		T[] tempArray = (T[]) new Object[capacity];
		stackArray = tempArray;
		numberOfEntries = 0;
		this.capacity = capacity;
	}
	
	/**
	 * Creates stack with default capacity 25
	 */
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Pushes entry onto the top of the stack, sends msg if full
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void push(T anEntry) {
		if (numberOfEntries < capacity) {
			try {
				stackArray[numberOfEntries] = anEntry;
			} catch (Exception e) {
				System.out.println("Wrong data type entered");
			}
			numberOfEntries++;
		} else {
			System.out.println("Stack is full ");
		}

	}

	/**
	 * Removes last entered entry in stack, returns removed item
	 */
	@Override
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		T item = stackArray[numberOfEntries - 1];
		stackArray[numberOfEntries - 1] = null;
		numberOfEntries--;
		return item;
	}

	/**
	 * Returns last entered item in stack
	 */
	@Override
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stackArray[numberOfEntries - 1];
	}

	/**
	 * Returns true if # of entries is 0
	 */
	@Override
	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}

	/**
	 * Removes entries until empty
	 */
	@Override
	public void clear() {
		while (!isEmpty()) {
			pop();
		}
		
	}

	

}
