package green.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleDictionary {

	private Set<String> dictionary;

	public ScrabbleDictionary() throws FileNotFoundException {

		dictionary = new HashSet<String>();
		Scanner inputFile = new Scanner(new File("./OWL.txt"));

		while (inputFile.hasNext()) {
			String data = inputFile.nextLine();
			String[] word = data.split(" ");
			dictionary.add(word[0]);

		}

	}

	public boolean contains(String userWord) {
		if (userWord == null) {
			return false;
		}
		String upperCaseWord = userWord.toUpperCase();
		return dictionary.contains(upperCaseWord);

	}

	// public static void main (String [] args) throws FileNotFoundException{
	// Scanner inputFile = new Scanner(new File("./OWL.txt"));
	// ScrabbleDictionary dictionary = new ScrabbleDictionary ();
	// // Scanner userInput = new Scanner (System.in);
	//
	// long startTime = System.currentTimeMillis();
	// for (int i=0; i<1000000;i++){
	// dictionary.contains("word");
	// }
	// long endTime = System.currentTimeMillis();
	// System.out.println (endTime - startTime);
	// System.out.println ("enter a word");
	// String userWord = userInput.next();
	// boolean found = dictionary.contains(userWord);
	// if (found == true){
	// System.out.println ("word was found");
	// }
	// else {
	// System.out.println ("word not found");
	// }
	//
	//
	//
	// }

}
