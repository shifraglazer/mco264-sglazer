package binary;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
	private Stack<Integer> stack;

	public DecimalToBinary() {
		stack = new Stack<Integer>();
	}

	public String toBinary(int decimal) {
		int remainder;
		while (decimal != 0) {
			remainder = decimal % 2;
			decimal -= Math.ceil(decimal / 2.0);
			stack.push(remainder);
		}

		StringBuilder builder = new StringBuilder();
		int length = stack.size();
		for (int i = 0; i < length; i++) {
			builder.append(stack.pop());
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int binary = 0;
		boolean found = false;
		do {
			try {
				System.out.println("Enter decimal number to convert to binary");
				binary = keyboard.nextInt();
				found = true;
			} catch (InputMismatchException e) {
				System.out.println("cannot convert to binary");
				keyboard.nextLine();
			}
		} while (!found);
		keyboard.close();
		DecimalToBinary decimal = new DecimalToBinary();
		System.out.println(decimal.toBinary(binary));

	}
}
