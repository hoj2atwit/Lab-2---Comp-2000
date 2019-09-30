package src_stack;

import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String temp = "";
		System.out.printf("Input 1 to print pre-coded test messages, 2 to test your own infixes: ");
		while(!(temp.equals("1")) && !(temp.equals("2"))) {
			temp = input.next();
			if (temp.equals("1")) {
				one();
			} else if (temp.equals("2")) {
				two();
			} else {
				System.out.printf("Enter a valid input!(1 for premade code, 2 for custom equation)%n");
			}	
		}
		input.close();

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
		
		// Converts postfix to double and saves to ans
		try {
			// Converts infix to postfix and saves string to temp
			String temp = infixToPostfix.convert(input.nextLine());
			if(temp != null) {
				System.out.printf("Postfix String: %s%n",temp);
			}
			
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
		}catch(Exception e) {
		}
		
		
		System.out.printf("Test another infix? Enter y to go again, anything else to exit: ");
		String yn = input.next();
		if (yn.equals("y")) {
			two();
		}else {
			System.out.printf("Program terminating!%n");
			input.close();
		}
	}

}
