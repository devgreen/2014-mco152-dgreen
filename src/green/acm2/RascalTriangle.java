package green.acm2;

import java.util.Scanner;

public class RascalTriangle {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		int numCases = keyboard.nextInt();
		for (int i = 0; i < numCases; i++) {
			int caseNum = keyboard.nextInt();
			int line = keyboard.nextInt();
			int position = keyboard.nextInt();
			int diagnol = line - position;

			int answer = diagnol * position + 1;
			System.out.println(caseNum + " " + answer);
		}
		keyboard.close();
	}

}
