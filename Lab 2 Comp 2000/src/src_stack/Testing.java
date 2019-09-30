package src_stack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String temp = "";
		String uno = "1";
		String doce = "2";
		System.out.printf("Input 1 to print pre-coded test messages, 2 to test your own infixes: ");
		try {
			temp = input.next();
		} catch (InputMismatchException e) {
			System.out.printf("Enter a valid input!%n");
			System.exit(0);
		}
		if (temp.equals("1")) {
			one();
		} else if (temp.equals("2")) {
			two();
		} else {
			System.out.printf("Enter a valid input!%n");
			System.exit(0);
		}

	}
	
	/**
	 * Pre-defined testing message outputs
	 */
	private static void one() {
		
	}
	
	/**
	 * Test your own input string
	 */
	private static void two() {
		Scanner input = new Scanner(System.in);
		System.out.printf("Type in a desired infix to test: ");
		
		// Converts infix to postfix and saves string to temp
		String temp = infixToPostfix.convert(input.nextLine());
		System.out.printf("Postfix String: %s%n",temp);
		
		// Converts postfix to double and saves to ans
		double ans = PostfixEvaluator.calculate(temp);
		System.out.printf("Postfix evaluation: %f%n", ans);
		
		System.out.printf("Test another infix? Enter y to go again, anything else to exit: ");
		String yn = input.next();
		if (yn.equals("y")) {
			two();
		}
		System.out.printf("Program terminating!%n");
		input.close();
	}

}
