package green.acm;

import java.util.Scanner;

public class RepeatingCharacters {

	private String pickFrom = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ$%*+-./:";
	private String dealingWith;
	private int caseNum;
	private int repeating;

	public RepeatingCharacters(String input) throws InvalidInputException {
		String[] info = input.split(" ");
		this.caseNum = Integer.parseInt(info[0]);
		repeating = Integer.parseInt(info[1]);
		if (repeating < 1 || repeating > 8) {
			throw new InvalidInputException();

		}
		dealingWith = info[2];

	}

	public String repeat(String repeat) throws InvalidInputException {
		String repeat1 = repeat.toUpperCase();
		boolean found = false;
		StringBuilder info = new StringBuilder();
		if (repeat.length() > 20) {
			throw new InvalidInputException();
		} else {
			for (int i = 0; i < repeat1.length(); i++) {
				for (int k = 0; k < pickFrom.length(); k++) {
					if (repeat1.charAt(i) == pickFrom.charAt(k)) {
						found = true;
						break;
					}

				}
				if (!found) {
					throw new InvalidInputException();

				}

				for (int j = 0; j < repeating; j++) {
					info.append(repeat1.charAt(i));
				}
			}
		}
		return info.toString();
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		// System.out.println("How many cases would you like to input?");
		try {
			int cases = keyboard.nextInt();
			if (cases < 1 || cases > 1000) {
				throw new InvalidInputException();
			}
			keyboard.nextLine();
			for (int i = 0; i < cases; i++) {
				// System.out
				// .println("please enter case number,number of times to repeat and the string you want to repeat");
				String everything = keyboard.nextLine();

				RepeatingCharacters reapChar = new RepeatingCharacters(everything);
				String output = reapChar.repeat(reapChar.dealingWith);
				System.out.println(i + 1 + " " + output);
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
