package green.acm;

import java.util.Scanner;

public class NthLargestValue {

	private int[] numbers = new int[10];
	private int caseNum;

	public NthLargestValue(String input) throws InvalidInputException {
		String[] info = input.split(" ");
		caseNum = Integer.parseInt(info[0]);
		for (int i = 1; i < info.length; i++) {
			if (Integer.parseInt(info[i]) < 1 || Integer.parseInt(info[i]) > 1000) {
				throw new InvalidInputException();
			}
			numbers[i - 1] = Integer.parseInt(info[i]);
		}

	}

	public int getThirdHighest() {
		int highest = numbers[0];
		int place = 0;
		int count = 0;
		while (count < 3) {
			for (int i = 0; i < numbers.length; i++) {
				if (numbers[i] > highest) {
					highest = numbers[i];
					place = i;
				}
			}
			if (count < 2) {
				numbers[place] = 0;
				highest = numbers[place];
			}
			count++;

		}
		return highest;
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		// System.out.println("How many cases?");
		try {
			int cases = keyboard.nextInt();
			if (cases < 1 || cases > 1000) {
				throw new InvalidInputException();
			}
			keyboard.nextLine();
			for (int i = 0; i < cases; i++) {
				// System.out.println("enter case number followed by 10 numbers between 1 and 1000");
				String info = keyboard.nextLine();

				NthLargestValue value = new NthLargestValue(info);
				int answer = value.getThirdHighest();
				System.out.println(i + 1 + " " + answer);
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
