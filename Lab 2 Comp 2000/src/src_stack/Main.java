package src_stack;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// STACK USAGE EXAMPLE 1
		ArrayStack<String> stringStack = new ArrayStack<>();
		
		stringStack.push("Jim");
		stringStack.push("Jess");
		stringStack.push("Jill");
		stringStack.push("Joe");
		stringStack.push("Jane");
		
		String top;
		for (int i = 0; i < 3; i++) {
			top = stringStack.peek();
			System.out.println(top + " is at the top of the stack");
			top = stringStack.pop();
			System.out.println(top + " removed from the stack");
		}
		top = stringStack.peek();
		System.out.println(top + " is at the top of the stack");
		
		
		
		
		
		
		// STACK USAGE: REVERSING STRING
		ArrayStack<Character> cStack = new ArrayStack<>();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a phrase, and I will reverse it");
		String inputString = input.nextLine();
		String outputString = "";
		for (int i = 0; i < inputString.length(); i++) {
			cStack.push(inputString.charAt(i));
		}
		while (!cStack.isEmpty()) {
			outputString += cStack.pop();
		}
		System.out.println(outputString);
		/* For reversing a sentence without reversing the characters (Hi i am --> am i Hi),
		 * Enter strings into stacks from the start and pop out the strings instead of chars
		 */
		
		
		
		
		
		
	}

}
