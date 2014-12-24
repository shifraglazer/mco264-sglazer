package linkedList;

import java.util.*;
import java.util.Stack;

public class EvalExpression {
	private Stack<String> opStack;

	public EvalExpression() {
		opStack = new Stack<String>();

	}

	public double evalPostFix(StringTokenizer expression) {
		String token;
		char operator;
		Double number = 0.0;
		Double number1;
		Double number2;
		while (expression.hasMoreElements()) {
			token = expression.nextToken();
			if (token.matches("[\\*\\+-/]")) {
				operator = token.charAt(0);
				// need to use double because when divide could be left with
				// decimals/fractions
				number1 = Double.parseDouble(opStack.pop());

				number2 = Double.parseDouble(opStack.pop());
				switch (operator) {
				case '*': {
					number = number2 * number1;
					break;
				}
				case '/': {
					number = number2 / number1;
					break;
				}
				case '+': {
					number = number2 + number1;
					break;
				}
				case '-': {
					number = number2 - number1;
					break;
				}
				}

				opStack.push(Double.toString(number));
			} else {
				opStack.push(token);
			}
		}
		return number;
	}

	public String PostFix(StringTokenizer infix) throws RuntimeException {
		String token;
		String topToken;
		char operator;
		StringBuffer postFixExp = new StringBuffer(); // contains PostFix
														// expression
		final String BLANK = " ";
		while (infix.hasMoreElements()) {
			token = infix.nextToken();
			// since + in an expression means one or more occurrences when we
			// want to compiler to treat it as the character +
			// we must preface it with a \ but since the backslash also has
			// special meaning we preface the \ with another \
			// so now it becomes \\+ to compare to a + sign
			// we have to do the same thing for a * since the * means zero or
			// more occurrences in a string!

			if (token.matches("[\\+-/\\*()]")) {
				operator = token.charAt(0);
				// this is a mathematical operator or a parenthesis
				switch (operator) {
				case '(':
					opStack.push(token);
					break;
				case ')':
					for (;;) {
						try {
							topToken = opStack.pop();
							if (topToken.charAt(0) == '(')
								break;
							else {
								postFixExp.append(BLANK);
								postFixExp.append(topToken);
							}
						} catch (Exception e) {
							// doesn't have a matching open parenthesis
							throw new RuntimeException("invalid expression");

						}
					}
					break;

				case '+':
				case '-':
				case '*':
				case '/':
					for (;;) {
						char topTok;
						if (!opStack.empty())
							topTok = opStack.peek().charAt(0);
						else
							topTok = ' '; // set it to blank
						if (opStack.empty() || topTok == '('
								|| (operator == '*' || operator == '/')
								&& (topTok == '+' || topTok == '-')) {
							opStack.push(token);
							break;

						} else {
							topToken = opStack.pop();
							postFixExp.append(BLANK + topToken);

						}
					}
					break;
				}

			} else {
				// it is a number , just place it into the expression
				postFixExp.append(BLANK + token);

			}

		}
		while (!opStack.empty()) { // pop what is left in the operator stack
			topToken = opStack.pop();
			if (topToken.charAt(0) != '(') {
				postFixExp.append(BLANK + topToken);
			} else {
				// found an unmatched ( parenthesis
				throw new RuntimeException("*** Error in infix expression");

			}
		}
		System.out.println(postFixExp);
		return new String(postFixExp);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		EvalExpression anExpression = new EvalExpression();
		Scanner console = new Scanner(System.in);
		String completeExpression;
		StringTokenizer expressionParts;
		Double value;
		System.out.println("Enter a complete algebraic expression");
		completeExpression = console.nextLine();
		expressionParts = new StringTokenizer(completeExpression);
		String postFixExp;
		postFixExp = anExpression.PostFix(expressionParts);

		// now evaluate the expression
		expressionParts = new StringTokenizer(postFixExp);

		value = anExpression.evalPostFix(expressionParts);
		System.out.println("expression = " + value);
		console.close();

	}
}
