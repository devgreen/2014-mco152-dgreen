package green.acm2;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyPrimes {

	private int[] numbers;
	private ArrayList<Double> values = new ArrayList<Double>();

	public HappyPrimes(String number) {

		numbers = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			numbers[i] = Integer.parseInt(Character.toString(number.charAt(i)));

		}

	}

	public HappyPrimes(String number, ArrayList<Double> values) {
		this.values = values;
		numbers = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			numbers[i] = Integer.parseInt(Character.toString(number.charAt(i)));

		}

	}

	public boolean checkifPrime(String number) {
		int num = Integer.parseInt(number);
		if (num == 1) {
			return false;
		}
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;

			}
		}
		return true;

	}

	public boolean check() {
		double check = 0.0;
		for (int i = 0; i < numbers.length; i++) {
			check += Math.pow(numbers[i], 2);
		}
		if (check == 1) {
			return true;
		} else if (check != 1) {
			for (int i = 0; i < values.size(); i++) {
				// Double checkAgainst = check;
				if (values.get(i).equals(check)) {
					return false;
				}
			}
		}

		values.add(check);
		int check2 = (int) check;
		HappyPrimes next = new HappyPrimes(Integer.toString(check2), values);
		return next.check();

	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int amount = keyboard.nextInt();
		for (int i = 0; i < amount; i++) {
			int caseNum = keyboard.nextInt();
			String input = keyboard.next();
			HappyPrimes next = new HappyPrimes(input);
			boolean prime = next.checkifPrime(input);
			if (prime) {
				boolean happy = next.check();
				// values.clear();
				if (happy) {
					System.out.println(caseNum + " " + input + " YES");
				} else {
					System.out.println(caseNum + " " + input + " NO");
				}
			} else {
				System.out.println(caseNum + " " + input + " NO");
			}
		}
		keyboard.close();
	}
}
