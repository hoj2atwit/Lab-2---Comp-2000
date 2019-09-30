package src_stack;

public class infixToPostfix {
	
	public static String convert(String entry) {
		LinkedStack<Character> operands = new LinkedStack<Character>();		// Used to store operands
		ArrayStack<Character> infix = getInfix(entry);				// infix in form of ArrayStack
		String postfix = "";										// Postfix String
		int noNums = 0;												// Used to keep track of # of numbers
		int noOperands = 0;											// Used to keep track of # of operands
		int stringLength = entry.length();							// infix length
		char curChar;												// Current char being used in for loop
		for (int i = 0; i < stringLength; i++) {
			curChar = infix.pop();
			
			
			// Case for when current char is an integer
			if (isInt(curChar)) {
				noNums++;
				postfix += curChar;
				// Checking if integer is more than one digit; if so, add next int to postfix
				while(!infix.isEmpty() && (isInt(infix.peek()))){
					postfix+= infix.pop();
					i++;
				}
				postfix += ' ';					
				
			// Case for when current character is (, [ or {
			} else if (isFirstParenthesis(curChar)) {
				operands.push(curChar);
								
			// Case for when current char is ), ] or }
			} else if (isClosingParenthesis(curChar)) {
				try {
					// Case for when current char is )
					if (curChar == 41) {
						// Popping operands and adding to postfix while ( is not detected
						while (operands.peek() != 40) {
							postfix += operands.pop();
							postfix += ' ';
						}
						if (operands.peek() == 40) {
							operands.pop();
						}
					// Case for when current char is ]
					} else if (curChar == 93) {
						// Popping operands and adding to postfix while [ is not detected
						while (operands.peek() != 91) {
							postfix += operands.pop();
							postfix += ' ';
						}
						if (operands.peek() == 91) {
							operands.pop();
						}
					// Case for when current char is }
					} else if (curChar == 125) {
						// Popping operands and adding to postfix while { is not detected
						while (operands.peek() != 123) {
							postfix += operands.pop();
							postfix += ' ';
						}
						if (operands.peek() == 123) {
							operands.pop();
						}
					}					
				} catch (EmptyStackException e) {
					System.out.printf("infix is unbalanced! (Parenthesis error)%n");
					return null;
				}
				
				
			// Case for operands
			} else if (isOperand(curChar)) {
				noOperands++;				
				if (operands.isEmpty() || isFirstParenthesis(operands.peek())){
					operands.push(curChar);
				} else if (getPref(curChar) == getPref(operands.peek())) {
					if (getPref(curChar) == 1) {
						operands.push(curChar);
					} else {
						postfix += operands.pop();
						postfix += ' ';
						operands.push(curChar);
					}
				} else if (getPref(curChar) < getPref(operands.peek())) {
					operands.push(curChar);
				} else if (getPref(curChar) > getPref(operands.peek())) {
					int temp = getPref(operands.peek());
					while ((!operands.isEmpty() && !isFirstParenthesis(operands.peek())) && (getPref(operands.peek()) == temp)) {
						postfix += operands.pop();
						postfix += ' ';
					}
					if (!operands.isEmpty() && getPref(operands.peek()) == getPref(curChar)) {
						postfix += operands.pop();
						postfix += ' ';
					}
					operands.push(curChar);
				}
			} else {
				System.out.printf("infix is unbalanced! (Invalid character)%n");
				return null;
			}
			// Case for when there are no more chars in infix
			if (infix.isEmpty()) {
				if (noNums - 1 != noOperands) {
					System.out.printf("infix is unbalanced! (invalid ratio of numbers and operands)%n");
					return null;
				}
				while (!operands.isEmpty()) {
					if (isFirstParenthesis((operands.peek()))){
						System.out.printf("infix is unbalanced! (Parenthesis error; closing parenthesis not detected)%n");
						return null;
					}
					postfix += (char)operands.pop();
					postfix += ' ';
				}
				break;
			}			
		}
		return postfix;
	}
	
	/**
	 * Creates an ArrayStack that pops in order of input string. Used for convenience
	 * @param infix
	 * @return
	 */
	private static ArrayStack<Character> getInfix(String infix) {
		ArrayStack<Character> temp = new ArrayStack<Character>(infix.length());
		for (int i = infix.length() - 1; i >= 0; i--) {
			temp.push(infix.charAt(i));
		}
		return temp;
	}
	
	/**
	 * Assigns preference for specific operands for use in stacking and postfix assignmetns
	 * @param entry
	 * @return
	 */
	@SuppressWarnings("null")
	private static int getPref(char entry) {
		if (entry == 40 || entry == 91 || entry == 123) {		//(, [ {
			return 0;
		} else if (entry == 94) {								//^
			return 1;
		} else if (entry == 42 || entry == 47) {				//*, /
			return 2;
		} else if (entry == 43 || entry == 45) {				//+, -
			return 3;
		} else {
			System.out.printf("Incorrect usage of getInfix!%n");
			return (Integer) null;
		}
	}
	
	/**
	 * Checks if char is (, [ or {
	 * @param entry
	 * @return
	 */
	private static boolean isFirstParenthesis(char entry) {
		return (entry == 40 || entry == 91 || entry == 123);
	}
	
	/**
	 * Checks if char is ), ] or }
	 * @param entry
	 * @return
	 */
	private static boolean isClosingParenthesis(char entry) {
		return (entry == 41 || entry == 93 || entry == 125);
	}
	
	/**
	 * Checks if char is an integer
	 * @param entry
	 * @return
	 */
	private static boolean isInt(char entry) {
		return (entry >= 48 && entry <= 57);
	}
	
	/**
	 * Checks if char is +, -, *, / or ^
	 * @param entry
	 * @return
	 */
	private static boolean isOperand(char entry) {
		return (entry == 42 || entry == 43 || entry == 45 || entry == 47 || entry == 94);
	}

}
