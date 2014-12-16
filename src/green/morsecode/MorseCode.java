package green.morsecode;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {

	private Character[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
			".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
			"/", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----." };

	public String toMorseCode(String b) {
		String word = b.toUpperCase();
		StringBuilder code = new StringBuilder();

		Map<Character, String> map = new HashMap<Character, String>();
		for (int i = 0; i < alphabet.length; i++) {
			String value = map.get(alphabet[i]);
			if (value == null) {
				map.put(alphabet[i], morseCode[i]);
			}
		}
		char[] text = new char[word.length()];
		for (int h = 0; h < word.length(); h++) {
			text[h] = word.charAt(h);
		}

		for (int i = 0; i < text.length; i++) {
			String morse = map.get(text[i]);
			code.append(morse);
			if (i < text.length - 1) {
				code.append(" ");
			}

		}
		return code.toString();
	}

	public String toPlainText(String data) {
		StringBuilder code = new StringBuilder();
		String[] a = data.split(" ");
		Map<String, Character> map = new HashMap<String, Character>();
		for (int i = 0; i < morseCode.length; i++) {
			Character value = map.get(morseCode[i]);
			if (value == null) {
				map.put(morseCode[i], alphabet[i]);
			}
		}

		for (int i = 0; i < a.length; i++) {
			Character word = map.get(a[i]);
			code.append(word);
		}

		return code.toString();

	}

}
