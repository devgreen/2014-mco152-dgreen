package green.acm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PenneyGame {

	Map<String, Integer> map;
	private String[] options = { "TTT", "TTH", "THT", "THH", "HTT", "HTH", "HHT", "HHH" };
	private String coinTosses;
	private Integer[] counter = new Integer[8];
	private ArrayList<Integer> counter2;
	private String key;
	private Integer value;

	public PenneyGame(String input) {
		map = new HashMap<String, Integer>();
		counter2 = new ArrayList<Integer>();
		for (int i = 0; i < options.length; i++) {
			key = options[i];

			Integer setValue = map.get(key);
			if (setValue == null) {
				map.put(key, 0);
				value = map.get(key);
				counter[i] = value;
				// counter2.add(value);
			}

		}
		coinTosses = input;
	}

	public Integer[] numTimesAppeared() {
		StringBuilder info = new StringBuilder();
		String checking;

		for (int i = 0; i < 38; i++) {
			info.append(coinTosses.charAt(i));
			info.append(coinTosses.charAt(i + 1));
			info.append(coinTosses.charAt(i + 2));
			checking = info.toString();

			if (map.containsKey(checking)) {
				value = map.get(checking);
				map.put(checking, ++value);

			}
			info.setLength(0);
			// for (int j = 0; j < options.length; j++) {
			// if (checking.equals(options[j])) {
			// counter[j]++;
			// info.setLength(0);
			//
			// }
			//
			// }

		}

		return counter;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		for (int i = 0; i < options.length; i++) {
			Integer value = map.get(options[i]);
			info.append(value);
			if (i < map.size() - 1) {
				info.append(" ");

			}

		}
		return info.toString();
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		// System.out.println("How many cases would you like to input?");
		int cases = keyboard.nextInt();

		for (int i = 0; i < cases; i++) {
			// System.out.println("What case number is this");
			int caseNumber = keyboard.nextInt();
			// System.out.println("please enter the coin tosses");
			String input = keyboard.next();
			PenneyGame game = new PenneyGame(input);
			Integer[] finalOutput = game.numTimesAppeared();
			System.out.print(caseNumber + " ");
			String info = game.toString();
			System.out.println(info);
			/*
			 * for (int j = 0; j < finalOutput.length; j++) {
			 * System.out.print(finalOutput[j]); if (j < finalOutput.length - 1)
			 * { System.out.print(" ");
			 * 
			 * }
			 * 
			 * }
			 * 
			 * System.out.println();
			 */

		}
		keyboard.close();

	}

}
