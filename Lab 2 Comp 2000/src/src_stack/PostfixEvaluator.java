package src_stack;


public class PostfixEvaluator {
	
	/**
	 * Method that calculates the value of a given postfix string
	 * @param postfixString: outcome of infixConversion.java
	 * @return double: outcome of postfix string
	 */
	public static double calculate(String postfixString) {
		LinkedStack<String> postfix = toStringArray(postfixString);
		LinkedStack<Double> numbers = new LinkedStack<Double>();
		double answer = 0;
		double temp = 0;
		// Loop runs until postfix array is empty
		while(!postfix.isEmpty()) {
			// Current element
			String current = postfix.pop();
			// Check for if element is an integer; stores in numbers stack if is an int
			if (isInt(current.charAt(0))){
				numbers.push(toNum(current));
				// Check for if element is an operand; proceeds to operation if true
			} else if (isOperand(current.charAt(0))) {
				if (current.charAt(0) == '-') {			// Case for negative; Turns number into negative and adds instead
					// Pops first number, as it is used "second" for division/power purposes
					temp = numbers.pop();
					// Takes in number a, number b, operand and does math
					temp = doMath(-numbers.pop(), temp, "+");
				} else {
					temp = numbers.pop();
					temp = doMath(numbers.pop(), temp, current);
				}
				// If operation was the last operation in postfix, sets answer to be returned
				if (postfix.isEmpty()) {
					answer = temp;
				} else {
					// If operation was not the last operation in postfix, pushes result to numbers Stack to be used in order again
					numbers.push(temp);
				}
			}
		}
		return answer;
	}
	
	/**
	 * Changes postfix string into a stack array of strings containing elements (variables, operands) separately
	 * @param entry: String, should be the postfix string obtained from infixConversion.java
	 * @return LinkedStack<String>: postfix string changed into linkedstack with each element being a unique number/operand, all spaces removed
	 */
	private static LinkedStack<String> toStringArray(String entry){
		LinkedStack<String> temp = new LinkedStack<String>();				// Entered backwards; will fix with second Stack
		LinkedStack<String> temp2 = new LinkedStack<String>();				// Exists cause i forgot to put in elements backwards in temp
		int stringLength = entry.length();
		char curChar;
		String tempStr = "";
		// Entering each element of postfix into an array separately
		for (int i = 0; i < stringLength; i++) {
			curChar = entry.charAt(i);
				// Case if element is a space; identification between unique elements
			if (curChar == ' ') {
				continue;
				// Case if element is a number
			} else if (isInt(curChar)) {
				tempStr += curChar;
				while (i < stringLength - 1) {
					i++;
					curChar = entry.charAt(i);
					// If next char is space
					if (curChar == ' ') {
						i--;
						break;
					// If next char is a number; for numbers over one digit
					} else if (isInt(curChar)) {
						tempStr += curChar;
					}
				}
				temp.push(tempStr);
			} else if (isOperand(curChar)) {
				tempStr += curChar;
				if (entry.charAt(i+1) != ' ') {
					System.out.printf("Incorrect postfix! (Check operands)");
					System.exit(0);
				}
				temp.push(tempStr);
			}
			tempStr = "";
		}
		
		while (!temp.isEmpty()) {	// Puts in elements of temp into temp2 reversing order so that they pop from the "front" of postfix
			temp2.push(temp.pop());	// Read above, oops			
		}
		return temp2;
	}
	
	/**
	 * Takes in a string (In this case, 1 length operand) and gives back an integer according to the operand type
	 * 0 = ^
	 * 1 = *
	 * 2 = /
	 * 3 = +
	 * 4 = -
	 * @param entry: String should be one length string, wither ^, *, /, + or -
	 * @return int: integer according to operation
	 */
	private static int getOpType(String entry) {
		int stringLength = entry.length();
		if (stringLength != 1) {
			System.out.printf("Incorrect operation syntax! (getOpType, postfixCalculation.java)");
			System.exit(0);
		}
		char operand = entry.charAt(0);
		if (operand == '^') {
			return 0;
		} else if (operand == '*') {
			return 1;
		} else if (operand == '/') {
			return 2;
		} else if (operand == '+') {
			return 3;
		} else if (operand == '-') {
			return 4;
		} else {
			System.out.printf("Incorrect operation syntax! (getOptype, error message 2, postfixCalculation.java");
			System.exit(0);
			return -1;
		}
	}
	
	/**
	 * Changes string integer to integer
	 * @param entry: Integer equal to or greater than 0
	 * @return int: Integer conversion of input String
	 */
	private static double toNum(String entry) {
		int stringLength = entry.length();
		double sum = 0;
		char current;
		if (isInt(entry.charAt(0))) {
			for (int i = 0; i < stringLength; i++) {
				current = entry.charAt(stringLength - i - 1);
				sum += (current - 48)*Math.pow(10, i);
			}
		} else {
			System.out.printf("Invalid string input to toNum! (toNum, postfixCalculation)");
		}
		
		return sum;
	}
	
	/**
	 * Takes in numbers a and b to figure out with operation operand
	 * @param a: Number to be operated upon
	 * @param b: Number to be operated upon
	 * @param operand: Operation to be done with a and b
	 * @return double: a and b operated with operand
	 */
	private static double doMath(double a, double b, String operand) {
		double answer = 0;
		int opType = getOpType(operand);
		if (opType == 0) {					// ^
			answer = Math.pow(a, b);
		} else if (opType == 1) {			// *
			answer = a*b;
		} else if (opType == 2) {			// /
			answer = a/b;
		} else if (opType == 3) {			// +
			answer = a+b;
		} else if (opType == 4) {			// -
			answer = a-b;
		} else {
			System.out.printf("Invalid operand! (doMath, postfixCalculation)");
			System.exit(0);
		}
		return answer;
	}
	
	/**
	 * Checks if char is +, -, *, / or ^
	 * @param entry
	 * @return
	 */
	private static boolean isOperand(char entry) {
		return (entry == 42 || entry == 43 || entry == 45 || entry == 47 || entry == 94);
	}
	
	/**
	 * Checks if char is an integer
	 * @param entry
	 * @return
	 */
	private static boolean isInt(char entry) {
		return (entry >= 48 && entry <= 57);
	}
}
