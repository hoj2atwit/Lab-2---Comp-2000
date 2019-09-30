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
		
		// Holds answer as string for formatting decimal places
		String ansString;
		if(ans%1==0) {
			ansString = String.format("%.0f", ans);
		}else {
			ansString = String.format("%f", ans);
			int latestZero = 0;
			boolean foundZero = false;
			
			//Removes any extra zero's that are remaining in decimal
			for(int i = 0; i < ansString.length(); i++) {
				//Starts checking after decimal
				if(ansString.charAt(i) == '.') {
					for(int j = i+1; j < ansString.length(); j++) {
						if(ansString.charAt(j) == '0' && !foundZero) {
							latestZero = j;
							foundZero = true;
						}else if(!(ansString.charAt(j) == '0')){
							latestZero = 0;
							foundZero = false;
						}
					}
					break;
				}
			}
			
			//Removes extra 0's
			if(latestZero != 0 && foundZero) {
				ansString = ansString.substring(0, latestZero);
			}
		}
		
		System.out.printf("Postfix evaluation: %s%n", ansString);
		
		System.out.printf("Test another infix? Enter y to go again, anything else to exit: ");
		String yn = input.next();
		if (yn.equals("y")) {
			two();
		}
		System.out.printf("Program terminating!%n");
		input.close();
	}

}
